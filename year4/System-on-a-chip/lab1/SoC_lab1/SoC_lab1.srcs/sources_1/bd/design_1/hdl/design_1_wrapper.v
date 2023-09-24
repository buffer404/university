//Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2019.1 (lin64) Build 2552052 Fri May 24 14:47:09 MDT 2019
//Date        : Thu Sep 14 13:54:42 2023
//Host        : leonid-lenovo running 64-bit Ubuntu 22.04.2 LTS
//Command     : generate_target design_1_wrapper.bd
//Design      : design_1_wrapper
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module design_1_wrapper
   (dip_switches_16bits_0_tri_i,
    dip_switches_16bits_tri_o,
    reset,
    sys_clock,
    usb_uart_rxd,
    usb_uart_txd);
  input [15:0]dip_switches_16bits_0_tri_i;
  output [15:0]dip_switches_16bits_tri_o;
  input reset;
  input sys_clock;
  input usb_uart_rxd;
  output usb_uart_txd;

  wire [15:0]dip_switches_16bits_0_tri_i;
  wire [15:0]dip_switches_16bits_tri_o;
  wire reset;
  wire sys_clock;
  wire usb_uart_rxd;
  wire usb_uart_txd;

  design_1 design_1_i
       (.dip_switches_16bits_0_tri_i(dip_switches_16bits_0_tri_i),
        .dip_switches_16bits_tri_o(dip_switches_16bits_tri_o),
        .reset(reset),
        .sys_clock(sys_clock),
        .usb_uart_rxd(usb_uart_rxd),
        .usb_uart_txd(usb_uart_txd));
endmodule
