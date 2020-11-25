SUMMARY = "Automount USB drives"
DESCRIPTION = "Automount USB drives with systemd"
HOMEPAGE = "https://github.com/raamsri/automount-usb"

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://UNLICENSE;md5=911690f51af322440237a253d695d19f"

UDEV_RULES_DIR = "${sysconfdir}/udev/rules.d"

SRC_URI = "\
    git://git@github.com/raamsri/automount-usb;protocol=ssh;branch=master \
"
SRCREV = "4a543445147bc23b9bef4005c9427a3e99d64c36"

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${S}/usb-mount@.service ${D}/${systemd_system_unitdir}

    install -d ${D}/${UDEV_RULES_DIR}
    install -m 0644 ${S}/99-local.rules.usb-mount ${D}/${UDEV_RULES_DIR}/99-local.rules

    install -d ${D}/${bindir}
    install -m 0644 ${S}/usb-mount.sh ${D}/${bindir}
}

FILES_${PN} += "\
    ${systemd_system_unitdir} \
    ${UDEV_RULES_DIR} \
    ${bindir} \
"

REQUIRED_DISTRO_FEATURES= "systemd"
SYSTEMD_SERVICE_${PN} = "usb-mount@.service"

inherit features_check systemd
