LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=341f857b71e57f69fdce3c06b8b71fd5"

SRC_URI = "https://github.com/arduino/arduino-cli/releases/download/${PV}/arduino-cli_${PV}_Linux_ARMv6.tar.gz"
SRC_URI[md5sum] = "793ffb38fa5129ce3f77bd7453566403"
SRC_URI[sha256sum] = "fe4e4fcf5b399fe236ab56095ff6e58dbb8a380a2f469b31124a2c64e9e572d3"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}

    install -m 644 ${S}/arduino-cli ${D}${bindir}
}

FILES_${PN} = "\
    ${bindir} \
"

INSANE_SKIP_${PN} += "ldflags"
