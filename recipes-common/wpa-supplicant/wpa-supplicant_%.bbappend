FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://wpa_supplicant.conf \
"

WIFI_SSID ??= "ssid"
WIFI_PASSWORD ??= "password"

do_install_append() {
    install -d ${D}/${sysconfdir}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}/${sysconfdir}/wpa_supplicant
    sed -i 's/default_ssid/'"${WIFI_SSID}"'/g' ${D}/${sysconfdir}/wpa_supplicant/wpa_supplicant.conf
    sed -i 's/default_password/'"${WIFI_PASSWORD}"'/g' ${D}/${sysconfdir}/wpa_supplicant/wpa_supplicant.conf
}

FILES_${PN} += "\
    ${sysconfdir} \
"
