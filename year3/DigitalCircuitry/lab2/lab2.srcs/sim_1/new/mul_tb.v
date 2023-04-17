`timescale 1ns / 1ps

module mul_tb;

    reg clk = 0;
    reg rst;
    reg [7:0] a;
    reg [15:0] b;
    reg start;

    wire busy;
    wire [23:0] y;
    
    mul mul1(
        .a_bi(a),
        .b_bi(b),
        .start_i(start),
        .clk_i(clk),
        .rst_i(rst),
        
        .busy_o(busy),
        .y_bo(y)
    );
    
   
    
    initial begin
    
        //clk = 0;    
        assign a = 8'b10101010; // b h92, d145 
        assign b = 16'hAA; // he3e, d3646
        // res = h81F5C, d528670
        assign rst = 0;
        assign start = 1;   
 
        
    end   
    
    always 
        #5  clk =  ! clk;    //создание clk  
            

endmodule

//  a           b                   y
//  0           0                   0
//  1           1                   1
//  11110000    1111111100000000    ef1000
//  10101010    1010101010101010    7154e4
//  01011011    0010101010101101    f2b7f
//  11111111    1111111111111111    feff01