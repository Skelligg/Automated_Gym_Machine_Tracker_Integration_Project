# Arduino Based Rep Tracker
Created a web application that connects to an ESP32 running on an arduino device that
automatically counts the reps done on a gym machine by a user through bluetooth technology.

## Configuration for arduino
A Calibration program must be run when the weight sensors are first installed in order to set their weight to 0 grams. The calibration must only be run once and never again.

Otherwise, the arduino is idle waiting for a bluetooth connection in order to start counting the weight for a given set.

## Hardware
We used 
- Adafruit HUZZAH32 – ESP32 Feather Board rather than the Arduino Uno as it already featured bluetooth and WiFi capabilities.
- 4 x 50 kg Load Cell HX711 AD Modules (Weight Sensors)
- Generic Bluetooth Tag

## External Libraries
- <WiFi.h>
- <HTTPClient.h>
- <BLEAdvertisedDevice.h>
- <BLEDevice.h>
- <BLEScan.h>
- <HX711_ADC.h>
- <EEPROM.h>

![](images/arduino.jpeg)
![](images/sensors.jpeg)
