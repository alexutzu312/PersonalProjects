----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/08/2020 06:55:16 PM
-- Design Name: 
-- Module Name: MEM - Behavioral
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

entity MEM is
--  Port ( );
    Port(
    MemWrite: in std_logic;
    ALUResIn: in std_logic_vector(15 downto 0);
    RD2: in std_logic_vector (15 downto 0);
    clk: in std_logic;
    en: in std_logic;
    MemData: out std_logic_vector(15 downto 0);
    ALUResOut: out std_logic_vector (15 downto 0));
end MEM;

architecture Behavioral of MEM is

type memorie is array (0 to 32) of std_logic_vector (15 downto 0);
signal MEM: memorie:=(others => x"0000");

begin
    
    ALUResOut<=ALUResIn;
    MemData<= MEM(conv_integer(ALUResIn(4 downto 0)));
    process (clk)
    begin
        if rising_edge(clk) then
            if en='1' and MemWrite='1' then 
                MEM(conv_integer(ALUResIn(4 downto 0))) <= RD2;
            end if;
        end if;
     end process;
          

end Behavioral;
