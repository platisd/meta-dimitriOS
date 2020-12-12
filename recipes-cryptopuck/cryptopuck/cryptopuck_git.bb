SUMMARY = "Pocket sized encryption for your removable media"
DESCRIPTION = "Cryptopuck allows handheld devices to encrypt whatever drive gets attached to them."
HOMEPAGE = "https://github.com/platisd/cryptopuck"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

RDEPENDS_${PN} = " \
    automount-usb \
    python3 \
    python3-pycrypto \
    python3-pyinotify \
    rpi-gpio \
    rng-tools \
"

SRC_URI = "\
    git://git@github.com/platisd/cryptopuck;protocol=https;branch=master \
    file://cryptopuck.service \
    file://key.public \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
CRYPTOPUCK_CONFIG = "${sysconfdir}/cryptopuck"

do_install_append() {
    # Add systemd service
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/cryptopuck.service ${D}/${systemd_system_unitdir}

    install -d ${D}/${bindir}
    install -m 0644 ${S}/cryptopuck.py ${D}/${bindir}
    install -m 0644 ${S}/encrypt.py ${D}/${bindir}

    install -d ${D}/${ROOT_HOME}
    install -d ${D}/${CRYPTOPUCK_CONFIG}
    install -m 0644 ${WORKDIR}/key.public ${D}/${CRYPTOPUCK_CONFIG}
}

FILES_${PN} += "\
    ${systemd_system_unitdir} \
    ${bindir} \
    ${ROOT_HOME} \
    ${CRYPTOPUCK_CONFIG} \
"

REQUIRED_DISTRO_FEATURES= "systemd"
SYSTEMD_SERVICE_${PN} = "cryptopuck.service"

inherit features_check systemd
