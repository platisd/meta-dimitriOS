LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=341f857b71e57f69fdce3c06b8b71fd5"

SRC_URI = "https://github.com/arduino/arduino-cli/releases/download/${PV}/arduino-cli_${PV}_Linux_ARMv7.tar.gz"
SRC_URI[md5sum] = "58ef61061e8324d03f9468d2462549c4"
SRC_URI[sha256sum] = "465b90c2652879ecf0502ceff0b05673ee3b982ef1022fa46684cf0d7b92dc80"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}

    install -m 644 ${S}/arduino-cli ${D}${bindir}
}

FILES_${PN} = "\
    ${bindir} \
"

INSANE_SKIP_${PN} += "ldflags"
