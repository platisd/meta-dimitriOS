# smartcar-mqtt

The `smartcar-mqtt` BitBake recipe demonstrates the compilation of an Arduino sketch
for an ESP32 microcontroller and places the generated binary on the target filesystem.<br>
This can be valuable when we have a system where an Embedded Linux computer is connected
to an Arduino-compatible microcontroller: We can compile our microcontroller firmware during
the Embedded Linux build, deploy the binary on the computer's filesystem and finally,
during runtime, flash/update the microcontroller.

## Resources

[arduino-cli](https://github.com/arduino/arduino-cli) is utilized for compiling the firmware
and can be also placed on the the target filesystem to be used for flashing the microcontroller
during runtime.<br>
To achieve that, the following resources have been created:

* [arduino-cli/](../../recipes-common/arduino-cli): Recipes to use `arduino-cli` natively
  or to deploy on target
* [arduino_sketch.bbclass](../../classes/arduino_sketch.bbclass): Parent class for recipes
  that compile Arduino sketches
* [smartcar-mqtt_git.bb](smartcar-mqtt_git.bb): BitBake recipe to compile an Arduino sketch
  for ESP32

### arduino_sketch.bbclass

To compile an Arduino sketch, one would first need to `inherit arduino_sketch` and potentially
other classes that would be required by the toolchain of the target microcontroller
(e.g. `python3native` for ESP32). The same goes for other dependencies, for example
`python3-pyserial-native` also for the ESP32.

Next, the following variables may be set:

* `ARDUINO_SKETCH_PATH`: The path to the main `.ino` that should be compiled
* `ARDUINO_SKETCH_BOARD_CORE`: The board "core" you are compiling against, e.g. `arduino:avr`
  for AVR microcontrollers supported by Arduino or `esp32:esp32` for ESP32 by Espressif
* `ARDUINO_SKETCH_TARGET_BOARD`: The name of the board you are compiling for, e.g. `uno` for
  Arduino Uno
* `ARDUINO_SKETCH_BOARD_URL`: **(Optional)** URL to a `.json` that contains custom hardware
  definitions
* `ARDUINO_SKETCH_LIBRARIES`: **(Optional)** Names for one or more libraries, as found in
  Library Manager, required for the compilation of your sketch. Each library name should
  be surrounded by `'` and different libraries separated by spaces
* `ARDUINO_SKETCH_LIBRARIES_GIT`: **(Optional)** `git` URLs for one or more libraries,
  separated by spaces, required for the compilation of your sketch
* `ARDUINO_SKETCH_LIBRARIES_ZIP`: **(Optional)** One or more paths to `.zip` files,
  separated by spaces, that contain libraries required for the compilation of your sketch
