`timescale 1ns / 1ps

module FUNC_BLOCK(
    input X1,
    input X2,
    input X3,
    input X4,
    output Y1,
    output Y2,
    output Y3,
    output Y4
    );
    
    wire y_nor, c_in2, c_in3, c_in4;
    
    nor(y_nor, 1'b0, X4);
    
    ADDER adder_1 (
    .A( X1 ) ,
    .B( y_nor ) ,
    .CIN( 1'b1 ),
    .COUT(c_in2),
    .S(Y1)
    );
    
    ADDER adder_2 (
    .A( X2 ) ,
    .B( y_nor ) ,
    .CIN(c_in2),
    .COUT(c_in3),
    .S(Y2)
    );
    
    ADDER adder_3 (
    .A( X3 ) ,
    .B( 1'b1 ) ,
    .CIN(c_in3),
    .COUT(c_in4),
    .S(Y3)
    );
    
    ADDER adder_4 (
    .A( X4 ),
    .B( 1'b1 ),
    .CIN(c_in4),
    .COUT(),
    .S(Y4)
    );
    
endmodule