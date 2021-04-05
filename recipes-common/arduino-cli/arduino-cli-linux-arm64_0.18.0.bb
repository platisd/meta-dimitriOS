LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=341f857b71e57f69fdce3c06b8b71fd5"

SRC_URI = "https://github.com/arduino/arduino-cli/releases/download/${PV}/arduino-cli_${PV}_Linux_ARM64.tar.gz"
SRC_URI[md5sum] = "8baec5b09035cf028ff94e6f46e15de3"
SRC_URI[sha256sum] = "3197478fa19ed5e1bfb54cc1a1fd19acc385be9ce4cd3646fc53434af03a6f56"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}

    install -m 644 ${S}/arduino-cli ${D}${bindir}
}

FILES_${PN} = "\
    ${bindir} \
"

INSANE_SKIP_${PN} += "ldflags"
