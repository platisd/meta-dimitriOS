require recipes-core/images/core-image-minimal.bb

IMAGE_BASENAME = "vasttrapi"

IMAGE_INSTALL_append = " wpa-supplicant"
#IMAGE_INSTALL_APPEND = " vasttrapi"
