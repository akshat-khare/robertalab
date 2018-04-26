#include <math.h> 
#include <BnrOneA.h>   // Bot'n Roll ONE A library 
#include <BnrRescue.h>   // Bot'n Roll CoSpace Rescue Module library 
#include <RobertaFunctions.h>   // Open Roberta library 
#include <BnrRoberta.h>    // Open Roberta library 
#include <SPI.h>   // SPI communication library required by BnrOne.cpp 
#include <Wire.h>   //a library required by BnrRescue.cpp for the additional sonar  
BnrOneA one; 
BnrRescue brm; 
RobertaFunctions rob;  
BnrRoberta bnr(one, brm);  
#define SSPIN  2 
#define MODULE_ADDRESS 0x2C 
byte colorsLeft[3]={0,0,0}; 
byte colorsRight[3]={0,0,0};
    
 
 void setup() 
{
    Wire.begin();
    Serial.begin(9600);   // sets baud rate to 9600bps for printing values at serial monitor.
    one.spiConnect(SSPIN);   // starts the SPI communication module
    brm.i2cConnect(MODULE_ADDRESS);   // starts I2C communication
    brm.setModuleAddress(0x2C);
    one.stop();
    bnr.setOne(one);
    bnr.setBrm(brm);
    
}

void loop() 
{
    
    bnr.moveTimePID(30, 30, 500);
}
