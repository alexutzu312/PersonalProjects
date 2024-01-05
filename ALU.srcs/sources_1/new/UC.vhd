----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/01/2020 05:00:29 PM
-- Design Name: 
-- Module Name: UC - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity UC is
--  Port ( );
Port( Instr:in std_logic_vector(15 downto 0);
    RegDst: out std_logic;
    ExtOp: out std_logic;
    ALUSrc: out std_logic;
    Branch: out std_logic;
    Brgtz: out std_logic;
    Jump: out std_logic;
    ALUOp: out std_logic_vector (1 downto 0);
    MemWrite: out std_logic;
    MemToReg: out std_logic;
    RegWrite: out std_logic);
end UC;

architecture Behavioral of UC is

begin
    RegDst<='0'; ExtOp<='0'; ALUSrc<='0'; Branch<='0'; Jump<='0';
    MemWrite<='0'; MemToReg<='0'; RegWrite<='0'; Brgtz<='0'; ALUOp<="00"; 
    process (Instr)
    begin
        case Instr(15 downto 13) is
        when "000" => RegDst<='1'; RegWrite<='1';
        when "001" => ExtOp<='1'; ALUSrc<='1'; RegWrite<='1'; ALUOp<="01";
        when "010" => ExtOp<='1'; ALUSrc<='1'; MemToReg<='1'; RegWrite<='1'; ALUOp<="01";
        when "011" => ExtOp<='1'; ALUSrc<='1'; MemWrite<='1'; ALUOp<="01";
        when "100" => ExtOp<='1'; ALUSrc<='1'; Branch<='1'; ALUOp<="10";
        when "101" => ExtOp<='1'; ALUSrc<='1'; RegWrite<='1'; ALUOp<="11";
        when "110" => ExtOp<='1'; ALUSrc<='1'; Brgtz<='1'; ALUOp<="10";
        when "111" => Jump<='1'; ALUOp<="01"; --xx
        --e xx deci nu conteaza, dar l-am pus pe 01 ca sa nu ramana 00 si sa fie considerata de tip R, ca sa nu se verifice campul func
        end case;
    end process;

end Behavioral;
