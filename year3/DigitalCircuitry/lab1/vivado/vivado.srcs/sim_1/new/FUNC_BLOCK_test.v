`timescale 1ns / 1ps


module FUNC_BLOCK_test ;

    reg x1_in, x2_in, x3_in, x4_in;
    wire y1_out, y2_out, y3_out, y4_out;
    
FUNC_BLOCK fb (
    .X1( x1_in ) ,
    .X2( x2_in ) ,
    .X3( x3_in ) ,
    .X4( x4_in ) ,
    .Y1( y1_out ) ,
    .Y2( y2_out ) ,
    .Y3( y3_out ) ,
    .Y4( y4_out )
    );
    
    integer i;
    reg [3 : 0]test_val;
    reg [3 : 0] expected_val;
    
    initial begin
    
        for (i = 0; i < 16; i = i + 1) begin
            test_val = i ;
            x1_in = test_val[0];
            x2_in = test_val[1];
            x3_in = test_val[2];
            x4_in = test_val[3];
      
            if (i <= 7) begin
                expected_val = test_val;
            end else begin
                expected_val = test_val - 3;
            end
            
            #10
            
            if ( y1_out == expected_val[0] & y2_out == expected_val[1] & y3_out == expected_val[2] & y4_out == expected_val[3]) begin
                $display ( "The output is correct!!!");
            end else begin
                $display ( "The output is wrong!!! x1_in=%b x2_in=%b x3_in=%b x4_in=%b \n ACTUAL: y1_out=%b y2_out=%b y3_out=%b y4_out=%b \n EXPECTED: y1_out=%b y2_out=%b y3_out=%b y4_out=%b", x1_in, x2_in, x3_in, x4_in, y1_out, y2_out, y3_out, y4_out, expected_val[0], expected_val[1], expected_val[2], expected_val[3]) ;
            end
        end
        #10 $stop;
        
    end
    
endmodule
