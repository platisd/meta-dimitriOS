require recipes-core/images/core-image-minimal.bb

IMAGE_BASENAME = "cryptopuck"

IMAGE_INSTALL_append = " wpa-supplicant"
#IMAGE_INSTALL_APPEND = " cryptopuck"
