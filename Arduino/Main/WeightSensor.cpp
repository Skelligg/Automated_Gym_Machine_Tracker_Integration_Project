#include "WeightSensor.h"

          //pins:
    const int HX711_dout = 16; //mcu > HX711 dout pin A5
    const int HX711_sck = 4; //mcu > HX711 sck pin A16
  
    //HX711 constructor:
    HX711_ADC LoadCell(HX711_dout, HX711_sck);
    
void WeightSensor::setup() {
  

  Serial.begin(115200); delay(10);
  Serial.println();
  Serial.println("Starting...");
 
  LoadCell.begin();
  //LoadCell.setReverseOutput(); //uncomment to turn a negative output value to positive
  //float calibrationValue = 31.84; // calibration value (see example file "Calibration.ino")
  //31.84
#if defined(ESP32)
  EEPROM.begin(512); //to fetch the calibration value from eeprom
#endif
  EEPROM.get(calVal_eepromAdress, calibrationValue); // uncomment this if you want to fetch the calibration value from eeprom
 
  unsigned long stabilizingtime = 2000; // preciscion right after power-up can be improved by adding a few seconds of stabilizing time

  //tareSet(false); //set this to false if you don't want tare to be performed in the next step
  max_weight = 0;
  weight = 0;
}
 
void WeightSensor::scan() {
  static boolean newDataReady = 0;
  const int serialPrintInterval = 100; // Adjust this interval as needed

  // Get current time
  unsigned long currentMillis = millis();

  // Check if it's time to perform a new weight reading
  if (currentMillis - t >= serialPrintInterval) {
    if (LoadCell.update()) newDataReady = true;

    if (newDataReady) {
      weight = LoadCell.getData();  // Get the weight data

      // Print to the serial monitor for debugging purposes
      Serial.print("Load cell output val: ");
      Serial.println(weight);

      newDataReady = 0;
      t = currentMillis;  // Update last check time
    }
  }
}

void WeightSensor::setTare(){
    unsigned long stabilizingtime = 1000; // preciscion right after power-up can be improved by adding a few seconds of stabilizing time
    boolean _tare = true; //set this to false if you don't want tare to be performed in the next step
  LoadCell.start(stabilizingtime, _tare);
  if (LoadCell.getTareTimeoutFlag()) {
    Serial.println("Timeout, check MCU>HX711 wiring and pin designations");
    while (1);
  }
  else {
    LoadCell.setCalFactor(calibrationValue); // set calibration value (float)
    //Serial.println("Startup is complete");
  }
  }

void WeightSensor::setWeight(){
      if (weight > 10){
        // keep max weight
      if (weight>max_weight){
            max_weight = weight;
        }
      }
      if (weight<10){
         max_weight = 0;
      }
      scan();
  }


float WeightSensor::getWeight(){
  return max_weight;
}

void WeightSensor::resetWeight(){
  max_weight=0;
}
