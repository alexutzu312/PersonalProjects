-- ieee754_pkg.vhdl
--Acesta este un pachet VHDL care define?te tipul de date pentru reprezentarea IEEE 754 pe 32 bi?i.
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

package ieee754_pkg is
    type ieee754_single is record
        sign : STD_LOGIC;
        exponent : STD_LOGIC_VECTOR(7 downto 0);
        fraction : STD_LOGIC_VECTOR(22 downto 0);
    end record;
end package ieee754_pkg;
