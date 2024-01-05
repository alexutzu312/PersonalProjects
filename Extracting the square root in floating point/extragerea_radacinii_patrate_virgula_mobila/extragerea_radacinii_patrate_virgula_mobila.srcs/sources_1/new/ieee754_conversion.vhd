-- ieee754_conversion.vhdl
-- Acest modul converte?te datele interne la formatul IEEE 754 pe 32 bi?i.
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee754_pkg.all;

entity ieee754_conversion is
    Port ( input_data : in STD_LOGIC_VECTOR(31 downto 0);
           output_ieee754 : out ieee754_pkg.ieee754_single);
end ieee754_conversion;

architecture Behavioral of ieee754_conversion is
begin
    process
    begin
        -- Implementeaza conversia aici
    end process;
end Behavioral;
