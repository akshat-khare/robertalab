#define _GNU_SOURCE

#include "MicroBit.h" 
#include <array>
#include <stdlib.h>
MicroBit uBit;


int initTime = uBit.systemTime(); 

int main() 
{
    uBit.init();
    
    uBit.rgb.setColour(MicroBitColor(255, 0, 0, 255));
    uBit.display.clear();
    release_fiber();
}
