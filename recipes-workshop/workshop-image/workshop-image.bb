require recipes-core/images/core-image-minimal.bb

IMAGE_BASENAME = "workshop"

IMAGE_INSTALL_append = " employee-getter"
IMAGE_INSTALL_append = " wifi-autoconnect"

EXTRA_USERS_PARAMS = "usermod -P root root;"

inherit extrausers
