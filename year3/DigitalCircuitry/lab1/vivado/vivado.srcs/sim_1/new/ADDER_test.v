`timescale 1ns / 1ps


module ADDER_test ;

    reg a_in, b_in, c_in;
    wire c_out, s_out;
    
ADDER adder_1 (
    .A( a_in ) ,
    .B( b_in ) ,
    .CIN(c_in),
    .COUT(c_out),
    .S(s_out)
    );
    
    integer i;
    reg [1 : 0]test_val;
    reg expected_val;
    
    initial begin
    
        for (i = 0; i < 4; i = i + 1) begin
            test_val = i ;
            a_in = test_val[0];
            b_in = test_val[1];
            c_in = 1;
      
            expected_val = ^test_val;
            
            #10
            
            if ( s_out == expected_val ) begin
                $display ( "The adder output is correct!!! a_in=%b, b_in=%b, c_in=%b, c_out=%b, s_out=%b" , a_in , b_in , c_in, c_out, s_out);
            end else begin
                $display ( "The adder output is wrong!!! a_in=%b, b_in=%b, c_in=%b, c_out=%b, s_out=%b, expect=%b", a_in , b_in , c_in, c_out, s_out, expected_val) ;
            end
        end
        #10 $stop;
        
    end
    
endmodule