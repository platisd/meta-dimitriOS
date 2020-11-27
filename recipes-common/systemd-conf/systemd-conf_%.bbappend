FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://wifi.network \
"

do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wifi.network ${D}${sysconfdir}/systemd/network
}

FILES_${PN} += " \
    ${sysconfdir}/systemd/network/wifi.network \
"
