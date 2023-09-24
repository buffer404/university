-- ==============================================================
-- RTL generated by Vivado(TM) HLS - High-Level Synthesis from C, C++ and SystemC
-- Version: 2019.1
-- Copyright (C) 1986-2019 Xilinx, Inc. All Rights Reserved.
-- 
-- ===========================================================

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity main is
port (
    ap_clk : IN STD_LOGIC;
    ap_rst : IN STD_LOGIC;
    ap_start : IN STD_LOGIC;
    ap_done : OUT STD_LOGIC;
    ap_idle : OUT STD_LOGIC;
    ap_ready : OUT STD_LOGIC );
end;


architecture behav of main is 
    attribute CORE_GENERATION_INFO : STRING;
    attribute CORE_GENERATION_INFO of behav : architecture is
    "main,hls_ip_2019_1,{HLS_INPUT_TYPE=c,HLS_INPUT_FLOAT=0,HLS_INPUT_FIXED=0,HLS_INPUT_PART=xc7a100t-csg324-1,HLS_INPUT_CLOCK=10.000000,HLS_INPUT_ARCH=others,HLS_SYN_CLOCK=5.140000,HLS_SYN_LAT=-1,HLS_SYN_TPT=none,HLS_SYN_MEM=2,HLS_SYN_DSP=0,HLS_SYN_FF=288,HLS_SYN_LUT=566,HLS_VERSION=2019_1}";
    constant ap_const_logic_1 : STD_LOGIC := '1';
    constant ap_const_logic_0 : STD_LOGIC := '0';
    constant ap_ST_fsm_state1 : STD_LOGIC_VECTOR (3 downto 0) := "0001";
    constant ap_ST_fsm_state2 : STD_LOGIC_VECTOR (3 downto 0) := "0010";
    constant ap_ST_fsm_state3 : STD_LOGIC_VECTOR (3 downto 0) := "0100";
    constant ap_ST_fsm_state4 : STD_LOGIC_VECTOR (3 downto 0) := "1000";
    constant ap_const_lv32_0 : STD_LOGIC_VECTOR (31 downto 0) := "00000000000000000000000000000000";
    constant ap_const_lv32_1 : STD_LOGIC_VECTOR (31 downto 0) := "00000000000000000000000000000001";
    constant ap_const_lv3_0 : STD_LOGIC_VECTOR (2 downto 0) := "000";
    constant ap_const_lv1_0 : STD_LOGIC_VECTOR (0 downto 0) := "0";
    constant ap_const_lv32_2 : STD_LOGIC_VECTOR (31 downto 0) := "00000000000000000000000000000010";
    constant ap_const_lv32_3 : STD_LOGIC_VECTOR (31 downto 0) := "00000000000000000000000000000011";
    constant ap_const_lv3_7 : STD_LOGIC_VECTOR (2 downto 0) := "111";
    constant ap_const_lv3_1 : STD_LOGIC_VECTOR (2 downto 0) := "001";
    constant ap_const_lv1_1 : STD_LOGIC_VECTOR (0 downto 0) := "1";
    constant ap_const_boolean_1 : BOOLEAN := true;

    signal ap_CS_fsm : STD_LOGIC_VECTOR (3 downto 0) := "0001";
    attribute fsm_encoding : string;
    attribute fsm_encoding of ap_CS_fsm : signal is "none";
    signal ap_CS_fsm_state1 : STD_LOGIC;
    attribute fsm_encoding of ap_CS_fsm_state1 : signal is "none";
    signal permutations_address0 : STD_LOGIC_VECTOR (2 downto 0);
    signal permutations_ce0 : STD_LOGIC;
    signal permutations_we0 : STD_LOGIC;
    signal permutations_d0 : STD_LOGIC_VECTOR (31 downto 0);
    signal permutations_q0 : STD_LOGIC_VECTOR (31 downto 0);
    signal permutations_ce1 : STD_LOGIC;
    signal permutations_we1 : STD_LOGIC;
    signal permutations_q1 : STD_LOGIC_VECTOR (31 downto 0);
    signal i_fu_61_p2 : STD_LOGIC_VECTOR (2 downto 0);
    signal ap_CS_fsm_state2 : STD_LOGIC;
    attribute fsm_encoding of ap_CS_fsm_state2 : signal is "none";
    signal grp_next_set_fu_44_ap_start : STD_LOGIC;
    signal grp_next_set_fu_44_ap_done : STD_LOGIC;
    signal grp_next_set_fu_44_ap_idle : STD_LOGIC;
    signal grp_next_set_fu_44_ap_ready : STD_LOGIC;
    signal grp_next_set_fu_44_permutations_address0 : STD_LOGIC_VECTOR (2 downto 0);
    signal grp_next_set_fu_44_permutations_ce0 : STD_LOGIC;
    signal grp_next_set_fu_44_permutations_we0 : STD_LOGIC;
    signal grp_next_set_fu_44_permutations_d0 : STD_LOGIC_VECTOR (31 downto 0);
    signal grp_next_set_fu_44_permutations_address1 : STD_LOGIC_VECTOR (2 downto 0);
    signal grp_next_set_fu_44_permutations_ce1 : STD_LOGIC;
    signal grp_next_set_fu_44_permutations_we1 : STD_LOGIC;
    signal grp_next_set_fu_44_permutations_d1 : STD_LOGIC_VECTOR (31 downto 0);
    signal grp_next_set_fu_44_ap_return : STD_LOGIC_VECTOR (0 downto 0);
    signal i_0_i_i_reg_33 : STD_LOGIC_VECTOR (2 downto 0);
    signal icmp_ln80_fu_55_p2 : STD_LOGIC_VECTOR (0 downto 0);
    signal grp_next_set_fu_44_ap_start_reg : STD_LOGIC := '0';
    signal ap_CS_fsm_state3 : STD_LOGIC;
    attribute fsm_encoding of ap_CS_fsm_state3 : signal is "none";
    signal ap_CS_fsm_state4 : STD_LOGIC;
    attribute fsm_encoding of ap_CS_fsm_state4 : signal is "none";
    signal zext_ln81_fu_67_p1 : STD_LOGIC_VECTOR (63 downto 0);
    signal zext_ln80_fu_50_p1 : STD_LOGIC_VECTOR (31 downto 0);
    signal ap_NS_fsm : STD_LOGIC_VECTOR (3 downto 0);

    component next_set IS
    port (
        ap_clk : IN STD_LOGIC;
        ap_rst : IN STD_LOGIC;
        ap_start : IN STD_LOGIC;
        ap_done : OUT STD_LOGIC;
        ap_idle : OUT STD_LOGIC;
        ap_ready : OUT STD_LOGIC;
        permutations_address0 : OUT STD_LOGIC_VECTOR (2 downto 0);
        permutations_ce0 : OUT STD_LOGIC;
        permutations_we0 : OUT STD_LOGIC;
        permutations_d0 : OUT STD_LOGIC_VECTOR (31 downto 0);
        permutations_q0 : IN STD_LOGIC_VECTOR (31 downto 0);
        permutations_address1 : OUT STD_LOGIC_VECTOR (2 downto 0);
        permutations_ce1 : OUT STD_LOGIC;
        permutations_we1 : OUT STD_LOGIC;
        permutations_d1 : OUT STD_LOGIC_VECTOR (31 downto 0);
        permutations_q1 : IN STD_LOGIC_VECTOR (31 downto 0);
        ap_return : OUT STD_LOGIC_VECTOR (0 downto 0) );
    end component;


    component main_permutations IS
    generic (
        DataWidth : INTEGER;
        AddressRange : INTEGER;
        AddressWidth : INTEGER );
    port (
        clk : IN STD_LOGIC;
        reset : IN STD_LOGIC;
        address0 : IN STD_LOGIC_VECTOR (2 downto 0);
        ce0 : IN STD_LOGIC;
        we0 : IN STD_LOGIC;
        d0 : IN STD_LOGIC_VECTOR (31 downto 0);
        q0 : OUT STD_LOGIC_VECTOR (31 downto 0);
        address1 : IN STD_LOGIC_VECTOR (2 downto 0);
        ce1 : IN STD_LOGIC;
        we1 : IN STD_LOGIC;
        d1 : IN STD_LOGIC_VECTOR (31 downto 0);
        q1 : OUT STD_LOGIC_VECTOR (31 downto 0) );
    end component;



begin
    permutations_U : component main_permutations
    generic map (
        DataWidth => 32,
        AddressRange => 7,
        AddressWidth => 3)
    port map (
        clk => ap_clk,
        reset => ap_rst,
        address0 => permutations_address0,
        ce0 => permutations_ce0,
        we0 => permutations_we0,
        d0 => permutations_d0,
        q0 => permutations_q0,
        address1 => grp_next_set_fu_44_permutations_address1,
        ce1 => permutations_ce1,
        we1 => permutations_we1,
        d1 => grp_next_set_fu_44_permutations_d1,
        q1 => permutations_q1);

    grp_next_set_fu_44 : component next_set
    port map (
        ap_clk => ap_clk,
        ap_rst => ap_rst,
        ap_start => grp_next_set_fu_44_ap_start,
        ap_done => grp_next_set_fu_44_ap_done,
        ap_idle => grp_next_set_fu_44_ap_idle,
        ap_ready => grp_next_set_fu_44_ap_ready,
        permutations_address0 => grp_next_set_fu_44_permutations_address0,
        permutations_ce0 => grp_next_set_fu_44_permutations_ce0,
        permutations_we0 => grp_next_set_fu_44_permutations_we0,
        permutations_d0 => grp_next_set_fu_44_permutations_d0,
        permutations_q0 => permutations_q0,
        permutations_address1 => grp_next_set_fu_44_permutations_address1,
        permutations_ce1 => grp_next_set_fu_44_permutations_ce1,
        permutations_we1 => grp_next_set_fu_44_permutations_we1,
        permutations_d1 => grp_next_set_fu_44_permutations_d1,
        permutations_q1 => permutations_q1,
        ap_return => grp_next_set_fu_44_ap_return);





    ap_CS_fsm_assign_proc : process(ap_clk)
    begin
        if (ap_clk'event and ap_clk =  '1') then
            if (ap_rst = '1') then
                ap_CS_fsm <= ap_ST_fsm_state1;
            else
                ap_CS_fsm <= ap_NS_fsm;
            end if;
        end if;
    end process;


    grp_next_set_fu_44_ap_start_reg_assign_proc : process(ap_clk)
    begin
        if (ap_clk'event and ap_clk =  '1') then
            if (ap_rst = '1') then
                grp_next_set_fu_44_ap_start_reg <= ap_const_logic_0;
            else
                if ((ap_const_logic_1 = ap_CS_fsm_state3)) then 
                    grp_next_set_fu_44_ap_start_reg <= ap_const_logic_1;
                elsif ((grp_next_set_fu_44_ap_ready = ap_const_logic_1)) then 
                    grp_next_set_fu_44_ap_start_reg <= ap_const_logic_0;
                end if; 
            end if;
        end if;
    end process;


    i_0_i_i_reg_33_assign_proc : process (ap_clk)
    begin
        if (ap_clk'event and ap_clk = '1') then
            if (((icmp_ln80_fu_55_p2 = ap_const_lv1_0) and (ap_const_logic_1 = ap_CS_fsm_state2))) then 
                i_0_i_i_reg_33 <= i_fu_61_p2;
            elsif (((ap_start = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state1))) then 
                i_0_i_i_reg_33 <= ap_const_lv3_0;
            end if; 
        end if;
    end process;

    ap_NS_fsm_assign_proc : process (ap_start, ap_CS_fsm, ap_CS_fsm_state1, ap_CS_fsm_state2, grp_next_set_fu_44_ap_done, grp_next_set_fu_44_ap_return, icmp_ln80_fu_55_p2, ap_CS_fsm_state4)
    begin
        case ap_CS_fsm is
            when ap_ST_fsm_state1 => 
                if (((ap_start = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state1))) then
                    ap_NS_fsm <= ap_ST_fsm_state2;
                else
                    ap_NS_fsm <= ap_ST_fsm_state1;
                end if;
            when ap_ST_fsm_state2 => 
                if (((icmp_ln80_fu_55_p2 = ap_const_lv1_1) and (ap_const_logic_1 = ap_CS_fsm_state2))) then
                    ap_NS_fsm <= ap_ST_fsm_state3;
                else
                    ap_NS_fsm <= ap_ST_fsm_state2;
                end if;
            when ap_ST_fsm_state3 => 
                ap_NS_fsm <= ap_ST_fsm_state4;
            when ap_ST_fsm_state4 => 
                if (((grp_next_set_fu_44_ap_return = ap_const_lv1_0) and (grp_next_set_fu_44_ap_done = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state4))) then
                    ap_NS_fsm <= ap_ST_fsm_state1;
                elsif (((grp_next_set_fu_44_ap_return = ap_const_lv1_1) and (grp_next_set_fu_44_ap_done = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state4))) then
                    ap_NS_fsm <= ap_ST_fsm_state3;
                else
                    ap_NS_fsm <= ap_ST_fsm_state4;
                end if;
            when others =>  
                ap_NS_fsm <= "XXXX";
        end case;
    end process;
    ap_CS_fsm_state1 <= ap_CS_fsm(0);
    ap_CS_fsm_state2 <= ap_CS_fsm(1);
    ap_CS_fsm_state3 <= ap_CS_fsm(2);
    ap_CS_fsm_state4 <= ap_CS_fsm(3);

    ap_done_assign_proc : process(grp_next_set_fu_44_ap_done, grp_next_set_fu_44_ap_return, ap_CS_fsm_state4)
    begin
        if (((grp_next_set_fu_44_ap_return = ap_const_lv1_0) and (grp_next_set_fu_44_ap_done = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state4))) then 
            ap_done <= ap_const_logic_1;
        else 
            ap_done <= ap_const_logic_0;
        end if; 
    end process;


    ap_idle_assign_proc : process(ap_start, ap_CS_fsm_state1)
    begin
        if (((ap_start = ap_const_logic_0) and (ap_const_logic_1 = ap_CS_fsm_state1))) then 
            ap_idle <= ap_const_logic_1;
        else 
            ap_idle <= ap_const_logic_0;
        end if; 
    end process;


    ap_ready_assign_proc : process(grp_next_set_fu_44_ap_done, grp_next_set_fu_44_ap_return, ap_CS_fsm_state4)
    begin
        if (((grp_next_set_fu_44_ap_return = ap_const_lv1_0) and (grp_next_set_fu_44_ap_done = ap_const_logic_1) and (ap_const_logic_1 = ap_CS_fsm_state4))) then 
            ap_ready <= ap_const_logic_1;
        else 
            ap_ready <= ap_const_logic_0;
        end if; 
    end process;

    grp_next_set_fu_44_ap_start <= grp_next_set_fu_44_ap_start_reg;
    i_fu_61_p2 <= std_logic_vector(unsigned(i_0_i_i_reg_33) + unsigned(ap_const_lv3_1));
    icmp_ln80_fu_55_p2 <= "1" when (i_0_i_i_reg_33 = ap_const_lv3_7) else "0";

    permutations_address0_assign_proc : process(ap_CS_fsm_state2, grp_next_set_fu_44_permutations_address0, ap_CS_fsm_state4, zext_ln81_fu_67_p1)
    begin
        if ((ap_const_logic_1 = ap_CS_fsm_state2)) then 
            permutations_address0 <= zext_ln81_fu_67_p1(3 - 1 downto 0);
        elsif ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_address0 <= grp_next_set_fu_44_permutations_address0;
        else 
            permutations_address0 <= "XXX";
        end if; 
    end process;


    permutations_ce0_assign_proc : process(ap_CS_fsm_state2, grp_next_set_fu_44_permutations_ce0, ap_CS_fsm_state4)
    begin
        if ((ap_const_logic_1 = ap_CS_fsm_state2)) then 
            permutations_ce0 <= ap_const_logic_1;
        elsif ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_ce0 <= grp_next_set_fu_44_permutations_ce0;
        else 
            permutations_ce0 <= ap_const_logic_0;
        end if; 
    end process;


    permutations_ce1_assign_proc : process(grp_next_set_fu_44_permutations_ce1, ap_CS_fsm_state4)
    begin
        if ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_ce1 <= grp_next_set_fu_44_permutations_ce1;
        else 
            permutations_ce1 <= ap_const_logic_0;
        end if; 
    end process;


    permutations_d0_assign_proc : process(ap_CS_fsm_state2, grp_next_set_fu_44_permutations_d0, ap_CS_fsm_state4, zext_ln80_fu_50_p1)
    begin
        if ((ap_const_logic_1 = ap_CS_fsm_state2)) then 
            permutations_d0 <= zext_ln80_fu_50_p1;
        elsif ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_d0 <= grp_next_set_fu_44_permutations_d0;
        else 
            permutations_d0 <= "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        end if; 
    end process;


    permutations_we0_assign_proc : process(ap_CS_fsm_state2, grp_next_set_fu_44_permutations_we0, icmp_ln80_fu_55_p2, ap_CS_fsm_state4)
    begin
        if (((icmp_ln80_fu_55_p2 = ap_const_lv1_0) and (ap_const_logic_1 = ap_CS_fsm_state2))) then 
            permutations_we0 <= ap_const_logic_1;
        elsif ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_we0 <= grp_next_set_fu_44_permutations_we0;
        else 
            permutations_we0 <= ap_const_logic_0;
        end if; 
    end process;


    permutations_we1_assign_proc : process(grp_next_set_fu_44_permutations_we1, ap_CS_fsm_state4)
    begin
        if ((ap_const_logic_1 = ap_CS_fsm_state4)) then 
            permutations_we1 <= grp_next_set_fu_44_permutations_we1;
        else 
            permutations_we1 <= ap_const_logic_0;
        end if; 
    end process;

    zext_ln80_fu_50_p1 <= std_logic_vector(IEEE.numeric_std.resize(unsigned(i_0_i_i_reg_33),32));
    zext_ln81_fu_67_p1 <= std_logic_vector(IEEE.numeric_std.resize(unsigned(i_0_i_i_reg_33),64));
end behav;
