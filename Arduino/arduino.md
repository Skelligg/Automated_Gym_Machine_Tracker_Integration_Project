
# Arduino Read Me

## Team
**EasyRep**
- Michael Lukyanov
- Anna Mestres Casadesus
- Philipe Souza
- Ben Lancry
- Martin Ivanov

## Responsibilites
Michael worked on 
- Bluetooth rep tracking
- Configuration of sending the data to the Java Spring Boot application.
Anna worked on 
- Weight tracking sensor 
- Wiring for the ESP32 device with the sensor.

## Reviewed
Michael reviewed Anna's part and Anna reviewed Michael's part. Philipe also went through the whole code in order to have
another outside perspective.

## Configuration
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

