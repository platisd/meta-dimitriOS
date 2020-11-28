FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://systemd-udevd.service \
"

# By default systemd uses it's build time as epoch. This causes problems when
# using yocto cache since systemd build time might be more than day older than
# the actual image build time. If that kind of image is booted with a device
# that does not have backup battery for RTC, the first fsck interprets successful
# result as failure because last mount time is in the future. This can be worked
# around by setting TIME_EPOCH to 0, which causes fsck to detect the system time as
# insane and ignore the mount time error.
EXTRA_OECONF_append = " --with-time-epoch=0"

do_install_append() {
    # Intentionally did not create target directory since it is expected to
    # be there and we should fail if it is not
    install -m 0644 ${WORKDIR}/systemd-udevd.service ${D}${systemd_unitdir}/system
}
