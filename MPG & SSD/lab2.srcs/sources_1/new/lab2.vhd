----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/02/2023 11:09:24 PM
-- Design Name: 
-- Module Name: saqwdf - Behavioral
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

entity lab2 is
    Port ( clk : in STD_LOGIC;
         btn : in STD_LOGIC_VECTOR (4 downto 0);
         sw : in STD_LOGIC_VECTOR (15 downto 0);
         led : out STD_LOGIC_VECTOR (15 downto 0);
         an : out STD_LOGIC_VECTOR (3 downto 0);
         cat : out STD_LOGIC_VECTOR (6 downto 0));
end entity;

architecture Behavioral of lab2 is

    signal cnt: std_logic_vector(1 downto 0):="00";
    signal en: std_logic;
    --signal dir: std_logic;
    signal zero1: std_logic_vector(15 downto 0);
    signal zero2: std_logic_vector(15 downto 0); 
    signal zero3: std_logic_vector(15 downto 0);
    
    signal sum: std_logic_vector(15 downto 0);
    signal dif: std_logic_vector(15 downto 0);
    signal shiftL: std_logic_vector(15 downto 0);
    signal shiftR: std_logic_vector(15 downto 0);
    signal digits: std_logic_vector(15 downto 0);
    
    component MPG is
        Port ( btn : in STD_LOGIC;
               clk : in STD_LOGIC;
               en : out STD_LOGIC);
    end component;
    
    component SSD is
    Port ( digit0 : in STD_LOGIC_VECTOR (3 downto 0);
           digit1 : in STD_LOGIC_VECTOR (3 downto 0);
           digit2 : in STD_LOGIC_VECTOR (3 downto 0);
           digit3 : in STD_LOGIC_VECTOR (3 downto 0);
           clk : in STD_LOGIC;
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
    end component; 
    
begin
    M1: MPG port map(btn(0),clk,en);
    SSD1: SSD port map(digits(3 downto 0), digits(7 downto 4), digits(11 downto 8), digits(15 downto 12), clk, an, cat);
    
    process(clk, en)
    begin
        if rising_edge(clk) then
            if en='1' then
                cnt <= cnt + 1;
            end if;
        end if;
    end process;
    
    process(sw(7 downto 0))
    begin
        zero1<= "000000000000" & sw(3 downto 0);
        zero2<= "000000000000" & sw(7 downto 4);
        zero3<= "00000000" & sw(7 downto 0);
    end process;
    
    sum<=zero1+zero2;
    dif<=zero1-zero2;
    shiftL<=zero3(13 downto 0) & "00";
    shiftR<="00" & zero3(15 downto 2);
    
    process(sum, dif, shiftL, shiftR, cnt)
    begin
        case cnt is
            when "00"=> digits<=sum;
            when "01"=> digits<=dif;
            when "10"=> digits<=shiftL;
            when others=> digits<=shiftR;
        end case;
    end process;
    
    led(7)<='1' when digits=X"0000" else '0';
    

--    process(sw)
--    begin 
--    end process
    -- dir<=sw(0);
--    process(clk, en)
--    begin
--        if rising_edge(clk) then
--            if en='1' then
--                if dir = '1' then
--                    cnt <= cnt + 1;
--                else
--                    cnt <= cnt - 1;
--                end if;
--            end if;
--        end if;
--    end process;
--    led <= cnt;
end Behavioral;
