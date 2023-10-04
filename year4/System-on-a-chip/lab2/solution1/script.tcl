############################################################
## This file is generated automatically by Vivado HLS.
## Please DO NOT edit it.
## Copyright (C) 1986-2019 Xilinx, Inc. All Rights Reserved.
############################################################
open_project lab2
set_top calc_result
add_files lab2/.apc/main.c
add_files lab2/.apc/.tb/main.h
add_files -tb lab2/.apc/main_tb.c
open_solution "solution1"
set_part {xc7a100t-csg324-1} -tool vivado
create_clock -period 10 -name default
#source "./lab2/solution1/directives.tcl"
csim_design
csynth_design
cosim_design
export_design -format ip_catalog
