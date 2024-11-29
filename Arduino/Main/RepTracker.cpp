#include "RepTracker.h"

void RepTracker::init() 
  {
  Serial.begin(115200);

  pinMode(LED_BUILTIN, OUTPUT);
  BLEDevice::init("");

  // Print a message to indicate that setup is complete
  Serial.println("Setup complete, starting BLE scan...");
  repCount = 0;
  }

  void RepTracker::scan(){
    
    BLEScan *scan = BLEDevice::getScan();
    scan->setActiveScan(true);

    // Start scan and get results
    BLEScanResults results = *scan->start(1);  // Dereferencing pointer
    int best = CUTOFF;
    bool deviceFound = false;
    

    for (int i = 0; i < results.getCount(); i++) {
      BLEAdvertisedDevice device = results.getDevice(i);
      //String macAddress = device.getAddress().toString(); // Get MAC address
      int rssi = device.getRSSI();

      char macAddress[18];
      device.getAddress().toString().toCharArray(macAddress, sizeof(macAddress));

      // Check if the detected device matches the target MAC address
      //if (macAddress.equals(targetMacAddress) && rssi > CUTOFF) {
      if (strcmp(macAddress, targetMacAddress.c_str()) == 0 && rssi > CUTOFF) {
        repCount++;

        // Print the MAC address and RSSI of the matched device
        Serial.print("Target Device Found, Rep:");
        //Serial.print(macAddress);
        //Serial.print(" RSSI: ");
        //Serial.println(rssi);
        Serial.println(repCount);

        
        // Update best RSSI and indicate device is found
        best = rssi;
        deviceFound = true;
      }
    }

    // Print the result if no matching device is found
    if (!deviceFound) {
      Serial.println("Target device not found in this scan.");
      
    }

    // Set LED based on RSSI of the target device
    digitalWrite(LED_BUILTIN, best > CUTOFF ? HIGH : LOW);
  }

  int RepTracker::getRepCount(){
    return repCount;
  }

  void RepTracker::resetRepCount(){
    repCount=0;
  }
