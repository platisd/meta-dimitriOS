require recipes-core/images/core-image-minimal.bb

IMAGE_BASENAME = "workshop"

IMAGE_INSTALL_append = " employee-getter"
IMAGE_INSTALL_append = " wifi-autoconnect"

IMAGE_INSTALL_append = " smartcar-mqtt"

EXTRA_USERS_PARAMS = "\
    useradd -P workshop grcpp; \
"

inherit extrausers
