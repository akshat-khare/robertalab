//NEPO definitions for calliope
// Display brightness is in interval of 0..255
// To scale that to 0..9 we use the following multipliers:
#define _SET_BRIGHTNESS_MULTIPLIER 28.34
#define _GET_BRIGHTNESS_MULTIPLIER 0.0353
// Light sensor returns values in interval 0..255
// To scale them to 0..100 we use this multiplier:
#define _GET_LIGHTLEVEL_MULTIPLIER 0.3922
#define _ITERATION_SLEEP_TIMEOUT 1
