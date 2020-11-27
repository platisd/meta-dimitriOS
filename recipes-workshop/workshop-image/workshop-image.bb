require recipes-core/images/core-image-minimal.bb

IMAGE_BASENAME = "workshop"

IMAGE_INSTALL_append = " wpa-supplicant"
IMAGE_INSTALL_append = " employee-getter"

inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P root root;"
