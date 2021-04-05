LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0cd19979747a2e101adb8956e9a8cee0"

# Needed for compilation of ESP32 sketches
DEPENDS += "\
    python3-pyserial-native \
"

SRC_URI = "\
    git://git@github.com/DIT112-V21/smartcar-mqtt-controller.git;protocol=https;branch=main \
"
SRCREV = "d0a595acefe7b639d6b01cdbb09c12c31dc08df8"
S = "${WORKDIR}/git"

ARDUINO_SKETCH_BOARD_URL = "https://dl.espressif.com/dl/package_esp32_index.json"
ARDUINO_SKETCH_BOARD_CORE = "esp32:esp32"
ARDUINO_SKETCH_TARGET_BOARD = "esp32doit-devkit-v1"

ARDUINO_SKETCH_LIBRARIES = "'Smartcar shield@7.0.1' 'MQTT'"
ARDUINO_SKETCH_PATH = "${S}/arduino/smartcar_mqtt/smartcar_mqtt.ino"

do_compile_prepend() {
    # TODO: Find a better way to do this!
    # platform.txt of ESP32 wants to use python
    # but python does not exist in recent meta-openembedded, only python3!
    ln -nsf "${STAGING_BINDIR_NATIVE}/python3-native/python3" "${STAGING_BINDIR_NATIVE}/python3-native/python"
}

do_install() {
    install -d ${D}/${datadir}/smartcar
    install -m 0644 ${ARDUINO_SKETCH_BUILD_DIR}/smartcar_mqtt.ino.bin ${D}/${datadir}/smartcar/
}

FILES_${PN} += "\
    ${datadir}/smartcar \
"

inherit arduino_sketch python3native
