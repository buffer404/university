`timescale 1ns / 1ps

module BCD_2_BIN(
    input x1,
    input x2,
    input x3,
    input x4,
    input x5,
    input x6,
    input x7,
    input x8,
    output y1,
    output y2,
    output y3,
    output y4,
    output y5,
    output y6,
    output y7,
    output y8
    );
    
    wire y2_x1_1, y2_x1_2, y2_x1_3, y3_x2_1, y3_x2_2, y3_x2_3, y4_x3_1, y4_x3_2, y4_x3_3;
    
    assign y1 = x1;
    
    FUNC_BLOCK fb_1 (
    .X1( x2 ) ,
    .X2( x3 ) ,
    .X3( x4 ) ,
    .X4( x5 ) ,
    .Y1( y2 ) ,
    .Y2( y2_x1_1 ) ,
    .Y3( y3_x2_1 ) ,
    .Y4( y4_x3_1 )
    );
    
    FUNC_BLOCK fb_2 (
    .X1( y2_x1_1 ) ,
    .X2( y3_x2_1 ) ,
    .X3( y4_x3_1 ) ,
    .X4( x6 ) ,
    .Y1( y3 ) ,
    .Y2( y2_x1_2 ) ,
    .Y3( y3_x2_2 ) ,
    .Y4( y4_x3_2 )
    );
    
    FUNC_BLOCK fb_3 (
    .X1( y2_x1_2 ) ,
    .X2( y3_x2_2 ) ,
    .X3( y4_x3_2 ) ,
    .X4( x7 ) ,
    .Y1( y4 ) ,
    .Y2( y2_x1_3 ) ,
    .Y3( y3_x2_3 ) ,
    .Y4( y4_x3_3 )
    );
 
    FUNC_BLOCK fb_4 (
    .X1(y2_x1_3) ,
    .X2(y3_x2_3) ,
    .X3(y4_x3_3) ,
    .X4(x8) ,
    .Y1(y5) ,
    .Y2(y6) ,
    .Y3(y7) ,
    .Y4(y8)
    );
    
endmodule