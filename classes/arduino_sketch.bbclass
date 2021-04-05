ARDUINO_SKETCH_BOARD_URL ??= ""
ARDUINO_SKETCH_BOARD_CORE ??= ""
ARDUINO_SKETCH_TARGET_BOARD ??= ""
ARDUINO_SKETCH_LIBRARIES ??= ""
ARDUINO_SKETCH_LIBRARIES_GIT ??= ""
ARDUINO_SKETCH_LIBRARIES_ZIP ??= ""
ARDUINO_SKETCH_PATH ??= ""
ARDUINO_SKETCH_BUILD_DIR = "${S}/arduino-build"

CONFIG_DIR = "${S}/arduino-config"
CONFIG_FILE_YAML = "${CONFIG_DIR}/arduino-cli.yaml"

DEPENDS += "\
    arduino-cli-native \
"

arduino_sketch_do_configure() {
    mkdir -p ${CONFIG_DIR}
    mkdir -p ${CONFIG_DIR}/data
    mkdir -p ${CONFIG_DIR}/data/staging
    mkdir -p ${CONFIG_DIR}/Arduino

    cp ${STAGING_LIBDIR_NATIVE}/arduino-cli.yaml ${CONFIG_FILE_YAML}

    if [ -n "${ARDUINO_SKETCH_BOARD_URL}" ];
    then arduino-cli config set \
        board_manager.additional_urls ${ARDUINO_SKETCH_BOARD_URL} \
        --config-file ${CONFIG_FILE_YAML};
    fi

    arduino-cli config set \
        directories.data ${CONFIG_DIR}/data \
        --config-file ${CONFIG_FILE_YAML}
    arduino-cli config set \
        directories.downloads ${CONFIG_DIR}/data/staging \
        --config-file ${CONFIG_FILE_YAML}
    arduino-cli config set \
        directories.user ${CONFIG_DIR}/Arduino \
        --config-file ${CONFIG_FILE_YAML}

    arduino-cli core update-index --config-file ${CONFIG_FILE_YAML}

    if [ -n "${ARDUINO_SKETCH_BOARD_CORE}" ];
    then arduino-cli core install ${ARDUINO_SKETCH_BOARD_CORE} \
        --config-file ${CONFIG_FILE_YAML};
    fi

    if [ -n "${ARDUINO_SKETCH_LIBRARIES}" ];
    then arduino-cli lib install ${ARDUINO_SKETCH_LIBRARIES} \
        --config-file ${CONFIG_FILE_YAML};
    fi

    if [ -n "${ARDUINO_SKETCH_LIBRARIES_GIT}" ];
    then arduino-cli lib install \
        --git-url ${ARDUINO_SKETCH_LIBRARIES_GIT} \
        --config-file ${CONFIG_FILE_YAML};
    fi

    if [ -n "${ARDUINO_SKETCH_LIBRARIES_ZIP}" ];
    then arduino-cli lib install \
        --zip-path ${ARDUINO_SKETCH_LIBRARIES_ZIP} \
        --config-file ${CONFIG_FILE_YAML};
    fi
}

arduino_sketch_do_compile()  {
    mkdir -p ${ARDUINO_SKETCH_BUILD_DIR}

    arduino-cli compile \
        -b ${ARDUINO_SKETCH_BOARD_CORE}:${ARDUINO_SKETCH_TARGET_BOARD} \
        ${ARDUINO_SKETCH_PATH} \
        --build-path ${ARDUINO_SKETCH_BUILD_DIR} \
        --config-file ${CONFIG_FILE_YAML}
}

EXPORT_FUNCTIONS do_configure do_compile
