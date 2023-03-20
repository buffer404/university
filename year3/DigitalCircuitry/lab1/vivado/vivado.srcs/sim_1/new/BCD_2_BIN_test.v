`timescale 1ns / 1ps


module BCD_2_BIN_test ;
 
    reg X1, X2, X3, X4, X5, X6, X7, X8;
    wire Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8;
     
    BCD_2_BIN b_2_b (
    .x1( X1 ) ,
    .x2( X2 ) ,
    .x3( X3 ) ,
    .x4( X4 ) ,
    .x5( X5 ) ,
    .x6( X6 ) ,
    .x7( X7 ) ,
    .x8( X8 ) ,
    .y1( Y1 ) ,
    .y2( Y2 ) ,
    .y3( Y3 ) ,
    .y4( Y4 ) ,
    .y5( Y5 ) ,
    .y6( Y6 ) ,
    .y7( Y7 ) ,
    .y8( Y8 )     
    );
    
    reg [7:0] test, expected;
    
    initial begin
        // test 1
        test = 8'b00000000;
        expected = 8'b00000000;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 1");
        end else begin
            $display("Wrong 1");
            $display("expected: %b", expected);
            $display("%b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end
    
        
        // test 2
        test = 8'b00001001;
        expected = 8'b00001001;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 2");
        end else begin
            $display("Wrong 2");
            $display("Expected: %b", expected);
            $display("Actual: %b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end
        
        
    
        // test 3
        test = 8'b00010000;
        expected = 8'b00001010;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 3");
        end else begin
            $display("Wrong 3");
            $display("Expected: %b", expected);
            $display("Actual: %b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end
        
        
        // test 4
        test = 8'b01010000;
        expected = 8'b00110010;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 4");
        end else begin
            $display("Wrong 4");
            $display("Expected: %b", expected);
            $display("Actual: %b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end
        
        
        // test 5
        test = 8'b01110101;
        expected = 8'b01001011;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 5");
        end else begin
            $display("Wrong 5");
            $display("Expected: %b", expected);
            $display("Actual: %b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end

        
        
        // test 6
        test = 8'b10011001;
        expected = 8'b01100011;
        X1=test[0]; X2=test[1]; X3=test[2]; X4=test[3]; X5=test[4]; X6=test[5]; X7=test[6]; X8=test[7];
        #10
        
        if (Y1==expected[0] & Y2==expected[1] & Y3==expected[2] & Y4==expected[3] & Y5==expected[4] & Y6==expected[5] & Y7==expected[6] & Y8==expected[7]) begin
            $display("Correct 6");
        end else begin
            $display("Wrong 6");
            $display("Expected: %b", expected);
            $display("Actual: %b%b%b%b %b%b%b%b", Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8);
        end
        $stop;
        
    end
    
endmodule