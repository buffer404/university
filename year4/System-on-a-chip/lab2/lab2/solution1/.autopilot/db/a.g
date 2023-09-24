#!/bin/sh
lli=${LLVMINTERP-lli}
exec $lli \
    /home/leonid/Desktop/university/year4/System-on-a-chip/lab2/lab2/solution1/.autopilot/db/a.g.bc ${1+"$@"}
