TARGETS = console-setup mountkernfs.sh alsa-utils ufw plymouth-log hostname.sh x11-common pppd-dns dns-clean apparmor udev keyboard-setup resolvconf mountdevsubfs.sh procps brltty networking hwclock.sh urandom checkroot.sh checkroot-bootclean.sh bootmisc.sh mountall-bootclean.sh mountall.sh checkfs.sh kmod mountnfs.sh mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
resolvconf: dns-clean
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
brltty: mountkernfs.sh udev
networking: mountkernfs.sh urandom resolvconf procps dns-clean
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh udev mountnfs-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
