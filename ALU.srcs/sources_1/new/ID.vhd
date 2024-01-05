----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/01/2020 04:55:21 PM
-- Design Name: 
-- Module Name: ID - Behavioral
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

entity ID is
--  Port ( );
    Port(
        RegWrite: in std_logic;
        Instr: in std_logic_vector(15 downto 0);
        RegDst: in std_logic;
        clk: in std_logic;
        en:in std_logic;
        ExtOp: in std_logic;
        WD:in std_logic_vector(15 downto 0);
        RD1: out std_logic_vector(15 downto 0);
        RD2: out std_logic_vector (15 downto 0);
        Ext_imm: out std_logic_vector (15 downto 0);
        func: out std_logic_vector(2 downto 0);
        sa : out std_logic
    );
end ID;

architecture Behavioral of ID is

type mem is array (0 to 7) of std_logic_vector(15 downto 0);
signal RF: mem:= (x"0001", x"0002", x"0003", others => x"0000");
signal WriteAddress: std_logic_vector(2 downto 0);
signal extbit: std_logic_vector (8 downto 0);

begin
    with RegDst Select
    WriteAddress<=Instr(9 downto 7) when '0',
                    Instr(6 downto 4) when '1';
    
    func<=Instr(2 downto 0);
    sa<= Instr(3);
    
    
    with Instr(6) Select
    extbit<="000000000" when '0',
            "111111111" when '1';
    
    with ExtOp Select
    Ext_imm<="000000000"&Instr(6 downto 0) when '0',
             extbit&Instr(6 downto 0) when '1';
                    
    RD1<=RF(conv_integer(Instr(12 downto 10)));
    RD2<=RF(conv_integer(Instr(9 downto 7)));
    process(clk)
    begin
    if rising_edge(clk) then
        if en='1' and RegWrite='1' then
            RF(conv_integer(WriteAddress))<=WD;
        end if;
    end if;
    end process;

end Behavioral;
