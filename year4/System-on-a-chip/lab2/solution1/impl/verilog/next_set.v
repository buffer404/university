// ==============================================================
// RTL generated by Vivado(TM) HLS - High-Level Synthesis from C, C++ and SystemC
// Version: 2019.1
// Copyright (C) 1986-2019 Xilinx, Inc. All Rights Reserved.
// 
// ===========================================================

`timescale 1 ns / 1 ps 

module next_set (
        ap_clk,
        ap_rst,
        ap_start,
        ap_done,
        ap_idle,
        ap_ready,
        n,
        permutations_address0,
        permutations_ce0,
        permutations_we0,
        permutations_d0,
        permutations_q0,
        permutations_address1,
        permutations_ce1,
        permutations_we1,
        permutations_d1,
        permutations_q1,
        ap_return
);

parameter    ap_ST_fsm_state1 = 10'd1;
parameter    ap_ST_fsm_state2 = 10'd2;
parameter    ap_ST_fsm_state3 = 10'd4;
parameter    ap_ST_fsm_state4 = 10'd8;
parameter    ap_ST_fsm_state5 = 10'd16;
parameter    ap_ST_fsm_state6 = 10'd32;
parameter    ap_ST_fsm_state7 = 10'd64;
parameter    ap_ST_fsm_state8 = 10'd128;
parameter    ap_ST_fsm_state9 = 10'd256;
parameter    ap_ST_fsm_state10 = 10'd512;

input   ap_clk;
input   ap_rst;
input   ap_start;
output   ap_done;
output   ap_idle;
output   ap_ready;
input  [31:0] n;
output  [2:0] permutations_address0;
output   permutations_ce0;
output   permutations_we0;
output  [31:0] permutations_d0;
input  [31:0] permutations_q0;
output  [2:0] permutations_address1;
output   permutations_ce1;
output   permutations_we1;
output  [31:0] permutations_d1;
input  [31:0] permutations_q1;
output  [0:0] ap_return;

reg ap_done;
reg ap_idle;
reg ap_ready;
reg[2:0] permutations_address0;
reg permutations_ce0;
reg permutations_we0;
reg[2:0] permutations_address1;
reg permutations_ce1;
reg permutations_we1;
reg[0:0] ap_return;

(* fsm_encoding = "none" *) reg   [9:0] ap_CS_fsm;
wire    ap_CS_fsm_state1;
reg   [31:0] reg_125;
wire    ap_CS_fsm_state3;
wire    ap_CS_fsm_state8;
wire   [31:0] j_fu_131_p2;
reg   [2:0] permutations_addr_reg_230;
wire    ap_CS_fsm_state2;
wire   [0:0] icmp_ln23_fu_137_p2;
wire   [31:0] j_1_fu_165_p2;
wire   [0:0] icmp_ln23_1_fu_159_p2;
wire  signed [31:0] k_fu_171_p2;
reg  signed [31:0] k_reg_249;
wire    ap_CS_fsm_state4;
reg   [2:0] permutations_addr_2_reg_254;
wire  signed [31:0] r_fu_188_p2;
reg  signed [31:0] r_reg_262;
wire    ap_CS_fsm_state7;
wire  signed [31:0] l_fu_194_p2;
reg  signed [31:0] l_reg_267;
reg   [2:0] permutations_addr_3_reg_275;
wire   [0:0] icmp_ln30_fu_200_p2;
reg   [2:0] permutations_addr_4_reg_280;
reg  signed [31:0] i_assign_reg_74;
reg   [31:0] k_0_in_reg_84;
wire    ap_CS_fsm_state5;
wire   [0:0] icmp_ln27_fu_182_p2;
reg   [31:0] l_0_in_reg_93;
wire    ap_CS_fsm_state6;
wire    ap_CS_fsm_state9;
reg   [31:0] r_0_in_reg_103;
reg   [0:0] p_0_reg_112;
wire  signed [63:0] sext_ln23_fu_143_p1;
wire  signed [63:0] sext_ln23_1_fu_154_p1;
wire  signed [63:0] sext_ln27_fu_177_p1;
wire  signed [63:0] sext_ln16_fu_206_p1;
wire  signed [63:0] sext_ln17_fu_211_p1;
wire   [31:0] add_ln23_fu_148_p2;
reg   [0:0] ap_return_preg;
wire    ap_CS_fsm_state10;
reg   [9:0] ap_NS_fsm;

// power-on initialization
initial begin
#0 ap_CS_fsm = 10'd1;
#0 ap_return_preg = 1'd0;
end

always @ (posedge ap_clk) begin
    if (ap_rst == 1'b1) begin
        ap_CS_fsm <= ap_ST_fsm_state1;
    end else begin
        ap_CS_fsm <= ap_NS_fsm;
    end
end

always @ (posedge ap_clk) begin
    if (ap_rst == 1'b1) begin
        ap_return_preg <= 1'd0;
    end else begin
        if ((1'b1 == ap_CS_fsm_state10)) begin
            ap_return_preg <= p_0_reg_112;
        end
    end
end

always @ (posedge ap_clk) begin
    if (((icmp_ln23_1_fu_159_p2 == 1'd0) & (1'b1 == ap_CS_fsm_state3))) begin
        i_assign_reg_74 <= j_1_fu_165_p2;
    end else if (((1'b1 == ap_CS_fsm_state1) & (ap_start == 1'b1))) begin
        i_assign_reg_74 <= j_fu_131_p2;
    end
end

always @ (posedge ap_clk) begin
    if (((icmp_ln23_1_fu_159_p2 == 1'd1) & (1'b1 == ap_CS_fsm_state3))) begin
        k_0_in_reg_84 <= n;
    end else if (((icmp_ln27_fu_182_p2 == 1'd0) & (1'b1 == ap_CS_fsm_state5))) begin
        k_0_in_reg_84 <= k_reg_249;
    end
end

always @ (posedge ap_clk) begin
    if ((1'b1 == ap_CS_fsm_state9)) begin
        l_0_in_reg_93 <= l_reg_267;
    end else if ((1'b1 == ap_CS_fsm_state6)) begin
        l_0_in_reg_93 <= i_assign_reg_74;
    end
end

always @ (posedge ap_clk) begin
    if (((icmp_ln23_fu_137_p2 == 1'd1) & (1'b1 == ap_CS_fsm_state2))) begin
        p_0_reg_112 <= 1'd0;
    end else if (((icmp_ln30_fu_200_p2 == 1'd0) & (1'b1 == ap_CS_fsm_state7))) begin
        p_0_reg_112 <= 1'd1;
    end
end

always @ (posedge ap_clk) begin
    if ((1'b1 == ap_CS_fsm_state9)) begin
        r_0_in_reg_103 <= r_reg_262;
    end else if ((1'b1 == ap_CS_fsm_state6)) begin
        r_0_in_reg_103 <= n;
    end
end

always @ (posedge ap_clk) begin
    if ((1'b1 == ap_CS_fsm_state8)) begin
        reg_125 <= permutations_q1;
    end else if ((1'b1 == ap_CS_fsm_state3)) begin
        reg_125 <= permutations_q0;
    end
end

always @ (posedge ap_clk) begin
    if ((1'b1 == ap_CS_fsm_state4)) begin
        k_reg_249 <= k_fu_171_p2;
        permutations_addr_2_reg_254 <= sext_ln27_fu_177_p1;
    end
end

always @ (posedge ap_clk) begin
    if ((1'b1 == ap_CS_fsm_state7)) begin
        l_reg_267 <= l_fu_194_p2;
        r_reg_262 <= r_fu_188_p2;
    end
end

always @ (posedge ap_clk) begin
    if (((1'b1 == ap_CS_fsm_state7) & (icmp_ln30_fu_200_p2 == 1'd1))) begin
        permutations_addr_3_reg_275 <= sext_ln16_fu_206_p1;
        permutations_addr_4_reg_280 <= sext_ln17_fu_211_p1;
    end
end

always @ (posedge ap_clk) begin
    if (((icmp_ln23_fu_137_p2 == 1'd0) & (1'b1 == ap_CS_fsm_state2))) begin
        permutations_addr_reg_230 <= sext_ln23_fu_143_p1;
    end
end

always @ (*) begin
    if (((1'b1 == ap_CS_fsm_state10) | ((ap_start == 1'b0) & (1'b1 == ap_CS_fsm_state1)))) begin
        ap_done = 1'b1;
    end else begin
        ap_done = 1'b0;
    end
end

always @ (*) begin
    if (((ap_start == 1'b0) & (1'b1 == ap_CS_fsm_state1))) begin
        ap_idle = 1'b1;
    end else begin
        ap_idle = 1'b0;
    end
end

always @ (*) begin
    if ((1'b1 == ap_CS_fsm_state10)) begin
        ap_ready = 1'b1;
    end else begin
        ap_ready = 1'b0;
    end
end

always @ (*) begin
    if ((1'b1 == ap_CS_fsm_state10)) begin
        ap_return = p_0_reg_112;
    end else begin
        ap_return = ap_return_preg;
    end
end

always @ (*) begin
    if ((1'b1 == ap_CS_fsm_state9)) begin
        permutations_address0 = permutations_addr_4_reg_280;
    end else if ((1'b1 == ap_CS_fsm_state7)) begin
        permutations_address0 = sext_ln17_fu_211_p1;
    end else if ((1'b1 == ap_CS_fsm_state6)) begin
        permutations_address0 = permutations_addr_2_reg_254;
    end else if ((1'b1 == ap_CS_fsm_state4)) begin
        permutations_address0 = sext_ln27_fu_177_p1;
    end else if ((1'b1 == ap_CS_fsm_state2)) begin
        permutations_address0 = sext_ln23_fu_143_p1;
    end else begin
        permutations_address0 = 'bx;
    end
end

always @ (*) begin
    if ((1'b1 == ap_CS_fsm_state8)) begin
        permutations_address1 = permutations_addr_3_reg_275;
    end else if ((1'b1 == ap_CS_fsm_state7)) begin
        permutations_address1 = sext_ln16_fu_206_p1;
    end else if ((1'b1 == ap_CS_fsm_state5)) begin
        permutations_address1 = permutations_addr_reg_230;
    end else if ((1'b1 == ap_CS_fsm_state2)) begin
        permutations_address1 = sext_ln23_1_fu_154_p1;
    end else begin
        permutations_address1 = 'bx;
    end
end

always @ (*) begin
    if (((1'b1 == ap_CS_fsm_state7) | (1'b1 == ap_CS_fsm_state4) | (1'b1 == ap_CS_fsm_state2) | (1'b1 == ap_CS_fsm_state9) | (1'b1 == ap_CS_fsm_state6))) begin
        permutations_ce0 = 1'b1;
    end else begin
        permutations_ce0 = 1'b0;
    end
end

always @ (*) begin
    if (((1'b1 == ap_CS_fsm_state7) | (1'b1 == ap_CS_fsm_state2) | (1'b1 == ap_CS_fsm_state8) | (1'b1 == ap_CS_fsm_state5))) begin
        permutations_ce1 = 1'b1;
    end else begin
        permutations_ce1 = 1'b0;
    end
end

always @ (*) begin
    if (((1'b1 == ap_CS_fsm_state9) | (1'b1 == ap_CS_fsm_state6))) begin
        permutations_we0 = 1'b1;
    end else begin
        permutations_we0 = 1'b0;
    end
end

always @ (*) begin
    if (((1'b1 == ap_CS_fsm_state8) | ((1'b1 == ap_CS_fsm_state5) & (icmp_ln27_fu_182_p2 == 1'd1)))) begin
        permutations_we1 = 1'b1;
    end else begin
        permutations_we1 = 1'b0;
    end
end

always @ (*) begin
    case (ap_CS_fsm)
        ap_ST_fsm_state1 : begin
            if (((1'b1 == ap_CS_fsm_state1) & (ap_start == 1'b1))) begin
                ap_NS_fsm = ap_ST_fsm_state2;
            end else begin
                ap_NS_fsm = ap_ST_fsm_state1;
            end
        end
        ap_ST_fsm_state2 : begin
            if (((icmp_ln23_fu_137_p2 == 1'd1) & (1'b1 == ap_CS_fsm_state2))) begin
                ap_NS_fsm = ap_ST_fsm_state10;
            end else begin
                ap_NS_fsm = ap_ST_fsm_state3;
            end
        end
        ap_ST_fsm_state3 : begin
            if (((icmp_ln23_1_fu_159_p2 == 1'd1) & (1'b1 == ap_CS_fsm_state3))) begin
                ap_NS_fsm = ap_ST_fsm_state4;
            end else begin
                ap_NS_fsm = ap_ST_fsm_state2;
            end
        end
        ap_ST_fsm_state4 : begin
            ap_NS_fsm = ap_ST_fsm_state5;
        end
        ap_ST_fsm_state5 : begin
            if (((1'b1 == ap_CS_fsm_state5) & (icmp_ln27_fu_182_p2 == 1'd1))) begin
                ap_NS_fsm = ap_ST_fsm_state6;
            end else begin
                ap_NS_fsm = ap_ST_fsm_state4;
            end
        end
        ap_ST_fsm_state6 : begin
            ap_NS_fsm = ap_ST_fsm_state7;
        end
        ap_ST_fsm_state7 : begin
            if (((icmp_ln30_fu_200_p2 == 1'd0) & (1'b1 == ap_CS_fsm_state7))) begin
                ap_NS_fsm = ap_ST_fsm_state10;
            end else begin
                ap_NS_fsm = ap_ST_fsm_state8;
            end
        end
        ap_ST_fsm_state8 : begin
            ap_NS_fsm = ap_ST_fsm_state9;
        end
        ap_ST_fsm_state9 : begin
            ap_NS_fsm = ap_ST_fsm_state7;
        end
        ap_ST_fsm_state10 : begin
            ap_NS_fsm = ap_ST_fsm_state1;
        end
        default : begin
            ap_NS_fsm = 'bx;
        end
    endcase
end

assign add_ln23_fu_148_p2 = ($signed(i_assign_reg_74) + $signed(32'd1));

assign ap_CS_fsm_state1 = ap_CS_fsm[32'd0];

assign ap_CS_fsm_state10 = ap_CS_fsm[32'd9];

assign ap_CS_fsm_state2 = ap_CS_fsm[32'd1];

assign ap_CS_fsm_state3 = ap_CS_fsm[32'd2];

assign ap_CS_fsm_state4 = ap_CS_fsm[32'd3];

assign ap_CS_fsm_state5 = ap_CS_fsm[32'd4];

assign ap_CS_fsm_state6 = ap_CS_fsm[32'd5];

assign ap_CS_fsm_state7 = ap_CS_fsm[32'd6];

assign ap_CS_fsm_state8 = ap_CS_fsm[32'd7];

assign ap_CS_fsm_state9 = ap_CS_fsm[32'd8];

assign icmp_ln23_1_fu_159_p2 = (($signed(permutations_q0) < $signed(permutations_q1)) ? 1'b1 : 1'b0);

assign icmp_ln23_fu_137_p2 = ((i_assign_reg_74 == 32'd4294967295) ? 1'b1 : 1'b0);

assign icmp_ln27_fu_182_p2 = (($signed(reg_125) < $signed(permutations_q0)) ? 1'b1 : 1'b0);

assign icmp_ln30_fu_200_p2 = (($signed(l_fu_194_p2) < $signed(r_fu_188_p2)) ? 1'b1 : 1'b0);

assign j_1_fu_165_p2 = ($signed(i_assign_reg_74) + $signed(32'd4294967295));

assign j_fu_131_p2 = ($signed(n) + $signed(32'd4294967294));

assign k_fu_171_p2 = ($signed(k_0_in_reg_84) + $signed(32'd4294967295));

assign l_fu_194_p2 = (l_0_in_reg_93 + 32'd1);

assign permutations_d0 = reg_125;

assign permutations_d1 = permutations_q0;

assign r_fu_188_p2 = ($signed(r_0_in_reg_103) + $signed(32'd4294967295));

assign sext_ln16_fu_206_p1 = l_fu_194_p2;

assign sext_ln17_fu_211_p1 = r_fu_188_p2;

assign sext_ln23_1_fu_154_p1 = $signed(add_ln23_fu_148_p2);

assign sext_ln23_fu_143_p1 = i_assign_reg_74;

assign sext_ln27_fu_177_p1 = k_fu_171_p2;

endmodule //next_set
