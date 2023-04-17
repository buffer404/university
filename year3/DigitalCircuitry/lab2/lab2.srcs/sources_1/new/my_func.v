`timescale 1ns / 1ps

module my_func(
    input clk_i,
    input rst_i,
    input [7:0] a_i,
    input [7:0] b_i,
    input start_i,

    output reg [23:0] y_o
    );

    localparam START = 2'b00;
    localparam MUL_AB = 2'b01;
    localparam MUL_AAA = 2'b10;
    localparam ADD = 2'b11;
    
    reg [1:0] state = START;
    reg [7:0] a = {8'b0};
    reg [15:0] b = {8'b0};
    reg [15:0] ab;
    reg [23:0] aaa;

    reg start_mul = 0;
    wire busy_mul;
    wire [23:0] mul_res;

    reg [1:0] a_mul_cnt = 0;

    mul my_mul(
        .clk_i(clk_i),
        .rst_i(rst_i),
        .a_bi(a),
        .b_bi(b),
        .start_i(start_mul),

        .busy_o(busy_mul),
        .y_bo(mul_res)
    );   

    // initial begin
    //     b = {8'b0, b_i};
    // end   
    always @(posedge clk_i) begin     
        if (rst_i) begin
            //assign y_o = 0;
            a_mul_cnt <= 0; 
            state <= START;
        end else if (start_mul != 0) begin 
            start_mul = 0;
        end    
        else begin
            case (state)
                START: begin
                    a <= a_i;
                    b <= {8'b0, b_i};
                    if(start_i) begin
                        state <= MUL_AB;
                        a <= a_i;
                        b <= b_i;
                        start_mul = 1;
                    end 
                end    

                MUL_AB:
                    if(busy_mul == 0) begin
                        start_mul = 1;
                        ab <= mul_res;
                        state <= MUL_AAA;
                        b <= a;
                     end
                MUL_AAA:
                    if (a_mul_cnt == 0 && busy_mul == 0) begin
                        b <= mul_res;
                        a_mul_cnt = a_mul_cnt + 1; 
                        //$display("new b = a*a is: %b", b);
                        start_mul = 1;
                    end
                    else if (a_mul_cnt == 1 && busy_mul == 0) begin
                        aaa <= mul_res;
                        state <= ADD;
                        a_mul_cnt = 0;
                    end  
                ADD: begin
                    //$display("ab is: %b  aaa is: %b \n", ab, aaa);
                    y_o <= aaa + ab;
                    state <= START;

                end    
              endcase
        end
        //$display("mul_res is: %b busy_o is: %b state is: %b", mul_res, busy_mul, state);
        //$display("a is: %b, b is %b ", a, b);
        //$display("ab is: %b  aaa is: %b \n", ab, aaa);
    end        
endmodule

