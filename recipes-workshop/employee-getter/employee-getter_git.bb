LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4026df227ddec6816bfa724910052ed"

DEPENDS = "\
    curl \
    nlohmann-json \
"

RDEPENDS_${PN} = "\
    libcurl \
"

SRC_URI = "\
    git://git@github.com/platisd/example-dimitriOS-cmake-project;protocol=https;branch=master \
    file://employee-getter.service \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/employee-getter.service ${D}/${systemd_system_unitdir}
}

FILES_${PN} += "\
    ${systemd_system_unitdir} \
"

REQUIRED_DISTRO_FEATURES= "systemd"
SYSTEMD_SERVICE_${PN} = "employee-getter.service"

inherit cmake features_check systemd
