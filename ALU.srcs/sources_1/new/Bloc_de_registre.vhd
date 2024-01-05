----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/25/2023 12:33:15 PM
-- Design Name: 
-- Module Name: Bloc_de_registre - Behavioral
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

entity Bloc_de_registre is
 port (
   clk : in std_logic;
   ra1 : in std_logic_vector (3 downto 0);
   ra2 : in std_logic_vector (3 downto 0);
   wa : in std_logic_vector (3 downto 0);
   wd : in std_logic_vector (15 downto 0);
   wen : in std_logic;
   rd1 : out std_logic_vector (15 downto 0);
   rd2 : out std_logic_vector (15 downto 0)
   );
end Bloc_de_registre;

architecture Behavioral of Bloc_de_registre is
    type reg_f is array(0 to 15) of std_logic_vector(15 downto 0);
    signal reg :reg_f;
begin
    process(clk)
    begin
        if rising_edge(clk) then
            if wen='1' then
                reg(conv_integer(wa))<=wd;
               end if;
          end if;            
    end process;
    rd1<=reg(conv_integer(ra1));
    rd2<=reg(conv_integer(ra2));
end Behavioral;
