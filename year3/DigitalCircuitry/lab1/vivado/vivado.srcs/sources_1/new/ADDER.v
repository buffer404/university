`timescale 1ns / 1ps

module ADDER(
    input A,
    input B,
    input CIN,
    output S,
    output COUT
    );
    
    wire y1, y2, y3, y4, y5, y6, y7;
    
    nor(y1, A, B);
    nor(y2, y1, A);
    nor(y3, y1, B);
    nor(y4, y2, y3);
    nor(y5, y4, CIN);
    nor(y6, y4, y5);
    nor(y7, y5, CIN);
    nor(COUT, y1, y5);
    nor(S, y6, y7);
    
    
endmodule
