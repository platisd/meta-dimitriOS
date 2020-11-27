FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://wpa_supplicant.conf \
"
PERSISTENT_WPA_SUPPLICANT_DIR = "/data/wpa_supplicant"

do_install_append() {
    install -d ${D}/${sysconfdir}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}/${sysconfdir}/wpa_supplicant
}

FILES_${PN} += "\
    ${sysconfdir} \
"
