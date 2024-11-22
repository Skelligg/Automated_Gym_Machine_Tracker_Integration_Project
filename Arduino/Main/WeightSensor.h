#ifndef WEIGHTSENSOR_H
#define WEIGHTSENSOR_H

#include <HX711_ADC.h>
#include <EEPROM.h>

class WeightSensor {
  private:

    const int calVal_eepromAdress = 0;
    unsigned long t = 0;
    float max_weight;
    float weight;

  public:

  
    WeightSensor(){}
    void setup();
    void scan();
    float getWeight();
    void setWeight();
    void resetWeight();
  };

#endif
