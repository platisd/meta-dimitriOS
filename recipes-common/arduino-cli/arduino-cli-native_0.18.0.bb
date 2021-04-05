LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=341f857b71e57f69fdce3c06b8b71fd5"

SRC_URI = "\
    https://github.com/arduino/arduino-cli/releases/download/${PV}/arduino-cli_${PV}_Linux_64bit.tar.gz \
    file://arduino-cli.yaml \
"
SRC_URI[md5sum] = "65d5f3f90a74ce92363ae51901cc66e6"
SRC_URI[sha256sum] = "a49542fd8dab76bce956333b1af189c3f5dc5ba846b6b7708f6c563230e957ba"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    # TODO: Find a better place instead of the native libdir?
    install -d ${D}${libdir}

    install -m 755 ${S}/arduino-cli ${D}${bindir}
    install -m 755 ${S}/arduino-cli.yaml ${D}${libdir}
}


inherit native
