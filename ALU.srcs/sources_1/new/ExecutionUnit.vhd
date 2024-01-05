----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/08/2020 06:06:03 PM
-- Design Name: 
-- Module Name: EX - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity EX is
--  Port ( );
    Port(
    RD1: in std_logic_vector(15 downto 0);
    RD2: in std_logic_vector (15 downto 0);
    ALUSrc: in std_logic;
    Ext_imm: in std_logic_vector(15 downto 0);
    sa: in std_logic;
    func: in std_logic_vector(2 downto 0);
    ALUOp: in std_logic_vector (1 downto 0);
    PCplus1: in std_logic_vector (15 downto 0);
    GT: out std_logic;
    Zero: out std_logic;
    ALURes: out std_logic_vector (15 downto 0);
    BranchAddress: out std_logic_Vector(15 downto 0));
end EX;

architecture Behavioral of EX is

signal rezultat: std_logic_vector(15 downto 0);
signal isZero: std_logic;
signal ALUCtrl: std_logic_vector(3 downto 0);
signal muxOut: std_logic_vector(15 downto 0);

begin
    muxOut<= RD2 when ALUSrc='0' 
        else Ext_imm;
    BranchAddress <= Ext_imm + PCplus1;
    isZero <='1' when rezultat=X"0000" else '0';
    Zero<=isZero;
    GT<= not isZero and not rezultat(15);
    ALURes<=rezultat;
        
    process(ALUOp, func)
    begin
        case ALUOp is
        when "00" => ALUCtrl<= "0" & func;
        when "01" => ALUCtrl<="0000";
        when "10" => ALUCtrl<="0001";
        when "11" => ALUCtrl<="1000"; -- pentru slti '<'
        end case;
    end process;
    
    process(ALUCtrl, RD1, muxOut, sa)
    begin
        case ALUCtrl is
        when "0000" => rezultat <= RD1+muxOut;
        when "0001" => rezultat <= RD1-muxOut;
        when "0010" => if (sa='1') then rezultat<= muxOut(14 downto 0) & '0';
                        else rezultat<=RD1; end if;
        when "0011" => if (sa='1') then rezultat<= '0' & muxOut(15 downto 1);
                        else rezultat<=RD1; end if;
        when "0100" => rezultat <= RD1 and muxOut;
        when "0101" => rezultat <= RD1 or muxOut;
        when "0110" => rezultat <= not(RD1 xor muxOut);
        when "0111" => rezultat <= RD1 xor muxOut;
        when "1000" => if (signed(RD1)<signed(muxOut)) then rezultat<=x"0001";
                        else rezultat<=x"0000"; end if;
        when others => rezultat<=x"0000"; -- nu conteaza, nu ar trebui sa ajuga aici
        end case;
    end process;
    

end Behavioral;
