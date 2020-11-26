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
    git://git@github.com/platisd/example-dimitriOS-cmake-project;protocol=ssh;branch=master \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit cmake
