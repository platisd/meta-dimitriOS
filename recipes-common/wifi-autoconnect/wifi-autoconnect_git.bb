LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=c4026df227ddec6816bfa724910052ed"

RDEPENDS_${PN} = "\
    wpa-supplicant \
"

SRC_URI = "\
    file://98-wireless-interface-naming.link \
    file://dimitrios-wifi-wlan0.service \
    file://LICENSE \
"

do_install() {
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/dimitrios-wifi-wlan0.service ${D}/${systemd_system_unitdir}

    install -d ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/98-wireless-interface-naming.link ${D}${sysconfdir}/systemd/network/
}

FILES_${PN} += " \
    ${systemd_system_unitdir} \
    ${sysconfdir} \
"

REQUIRED_DISTRO_FEATURES= "systemd"
SYSTEMD_SERVICE_${PN} = "\
    dimitrios-wifi-wlan0.service \
"

inherit systemd features_check
