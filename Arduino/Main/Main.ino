#include <WiFi.h>
#include <WebServer.h>
#include "RepTracker.h"
#include "WeightSensor.h"
#include <HTTPClient.h>

const char* ssid = "KdG-iDev";
const char* password = "Gdpc9Swt3phH9ujG";

WebServer server(80);  // Set up web server on port 80

String serverName = "http://10.134.178.163:80/setInput";

// Timer variables
unsigned long lastTime = 0;
unsigned long timerDelay = 10000;  // 10 seconds delay

// Rep tracking
int id = 9000;

WeightSensor ws;
RepTracker rt;
bool readyToRun = false;
int lastRepCount = 0;  // Last rep count
int setNumber = 1;
int setStarted = 1;
unsigned long startSetTime;
unsigned long elapsedSetTime;
unsigned long currentSetTime;
unsigned long lastRepChangeTime = 0;  // Last time rep count was updated
unsigned long repUnchangedDelay = 5000;  // Time for checking unchanged rep (5 seconds)
unsigned long gettingWeightDelay = 3000;  // Time for checking unchanged rep (3 seconds)
int currentRepCount = 0;

void sendRegistrationPostRequest() {
  HTTPClient http;

  // Construct the URL with query parameters
  String registrationServerPath = "http://10.134.178.163:80/registerIP";
  registrationServerPath += "?id=" + String(id);
  registrationServerPath += "&ip=" + WiFi.localIP().toString();

  // Begin the HTTP request
  http.begin(registrationServerPath);

  // Send the GET request (using GET since the data is in the URL)
  int httpResponseCode = http.GET();

  // Handle the response
  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    String response = http.getString();
    Serial.println("Response: " + response);
  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }

  // Free resources
  http.end();
}


// Function to handle the POST request
void handleTrigger() {
  Serial.println("POST request received at /trigger");

  // Read the body of the POST request (if any)
  String postBody = server.arg("plain");
  Serial.println("POST request body: " + postBody);

  // Send a response back to the client
  server.send(200, "text/plain", "Trigger received, Tracking Reps...");

  // Toggle the flag to run the program
  readyToRun = !readyToRun;
}

void setup() {
  Serial.begin(115200); 
  rt.init();
  ws.setup();
  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  Serial.println("Connecting to WiFi");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi. IP Address: ");
  Serial.println(WiFi.localIP());

  // Keep sending the registration request until success
  while (true) {
    sendRegistrationPostRequest();

    // Break the loop if the response was successful
    if (WiFi.status() == WL_CONNECTED) {
      Serial.println("Registration successful.");
      break; // Exit the loop after a successful registration
    }

    // Wait a bit before retrying
    delay(3000);
    Serial.println("Retrying registration...");
  }

  // Define route to handle POST requests
  server.on("/trigger", HTTP_POST, handleTrigger);

  // Start the web server
  server.begin();
  Serial.println("Server started, waiting for POST requests...");
}

// Function to send HTTP POST request
void sendPostRequest(int setNumber, int setTime, int repCount) {
  HTTPClient http;

  String serverPath = serverName + "?setNumber=" + String(setNumber) + "&setTime=" + String(setTime) + "&repCount=" + String(repCount);
  
  // Your Domain name with URL path or IP address with path
  http.begin(serverPath.c_str());
        
  // Send HTTP GET request
  int httpResponseCode = http.GET();
  
  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    String payload = http.getString();
    Serial.println(payload);
  } else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }

  // Free resources
  http.end();
}

// Your main program logic goes here
void runRestOfProgram() {
   
  while(setStarted){

    if(currentRepCount == 1){
        startSetTime = millis();
    }

    if(currentRepCount != 0){
    currentSetTime = millis();
    elapsedSetTime = (currentSetTime - startSetTime);
    }
  
  // Example: scanning and getting rep counts
  rt.scan();
  currentRepCount = rt.getRepCount();
  ws.scan();
  
  //Serial.print("Rep Count: ");
  //Serial.println(currentRepCount);
  Serial.println(elapsedSetTime / 1000);

  // Check if the rep count has changed
  if (currentRepCount != lastRepCount) {
    lastRepCount = currentRepCount;  // Update the last rep count
    lastRepChangeTime = millis();    // Record the time of the change
  }

  // Check if the rep count has been unchanged for the specified delay (5 seconds)
  if ((millis() - lastRepChangeTime) > repUnchangedDelay && currentRepCount != 0) {
    // Send POST request if the rep count is unchanged for 5 seconds
    sendPostRequest(setNumber,(elapsedSetTime / 1000) - 3,lastRepCount);
    lastRepChangeTime = millis();  // Reset the last change time after sending the POST
    currentSetTime = millis();
    while(millis()<(currentSetTime+gettingWeightDelay)){
      //get the max value of weight after the set
        ws.setWeight();
    }
    Serial.print("Weight: ");
    Serial.println(ws.getWeight());
    Serial.printf("Set %d sent",setNumber);
    setNumber++;
    rt.resetRepCount();
    ws.resetWeight();
    currentRepCount = 0;
  }
}
}

void loop() {
  // Handle incoming client requests
  server.handleClient();

  // If the flag is true, run the program logic
  if (readyToRun) {
    runRestOfProgram();
  }
}
