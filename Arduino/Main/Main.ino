#include <WiFi.h>
#include <WebServer.h>
#include "RepTracker.h"

const char* ssid = "KdG-iDev";
const char* password = "Gdpc9Swt3phH9ujG";

WebServer server(80);  // Set up web server on port 80

RepTracker rt;
bool readyToRun = false;

void setup() {
  Serial.begin(115200); 
  rt.init();

  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  Serial.println("Connecting to WiFi");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi. IP Address: ");
  Serial.println(WiFi.localIP());  // Show the IP address

  // Define route to handle POST requests
  server.on("/trigger", HTTP_POST, handleTrigger);

  // Start the web server
  server.begin();
  Serial.println("Server started, waiting for POST requests...");
}

void loop() {
  // Handle incoming client requests
  server.handleClient();

  // If the flag is true, run the program logic
  if (readyToRun) {
    runRestOfProgram();
  }
}

// Function to handle the POST request
void handleTrigger() {
  // Print when a POST request is received
  Serial.println("POST request received at /trigger");

  // Read the body of the POST request (if any)
  String postBody = server.arg("plain");
  Serial.println("POST request body: " + postBody);

  // Send a response back to the client
  server.send(200, "text/plain", "Trigger received, starting the program...");

  // Set the flag to run the program
  readyToRun = true;
}

// Your main program logic goes here
void runRestOfProgram() {
  Serial.println("Running the rest of the program...");
  
  // Example: scanning and getting rep counts
  rt.scan();
  Serial.print("Rep Count: ");
  Serial.println(rt.getRepCount());

  // Reset flag to prevent immediate rerun
  readyToRun = false;
}
