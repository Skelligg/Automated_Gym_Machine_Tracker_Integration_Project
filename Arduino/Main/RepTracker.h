#ifndef REPTRACKER_H
#define REPTRACKER_H

#include <Arduino.h>
#include <BLEAdvertisedDevice.h>
#include <BLEDevice.h>
#include <BLEScan.h>

class RepTracker {
  private:
    String targetMacAddress = "5b:f5:b7:26:ca:2d";
    int repCount;
    const int CUTOFF = -60;
  public:
  RepTracker(){ }

  void init();

  void scan();

  int getRepCount();

   void resetRepCount();
};



#endif
