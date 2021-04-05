LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=341f857b71e57f69fdce3c06b8b71fd5"

SRC_URI = "https://github.com/arduino/arduino-cli/releases/download/${PV}/arduino-cli_${PV}_Linux_32bit.tar.gz"
SRC_URI[md5sum] = "9ce8bd28e7d3ecc96d5768c8dc100820"
SRC_URI[sha256sum] = "eeb461a1ccdfc5739aa1bf884d6ad89a71ad007d3a1f234e4414a7332cfb0b7a"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}

    install -m 644 ${S}/arduino-cli ${D}${bindir}
}

FILES_${PN} = "\
    ${bindir} \
"
