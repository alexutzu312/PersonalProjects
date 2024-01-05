library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity NewtonRaphson is
    Port ( clk : in STD_LOGIC;
           reset : in STD_LOGIC;
           start : in STD_LOGIC;
           a_in : in STD_LOGIC_VECTOR(31 downto 0);
           result_ready : out STD_LOGIC;
           result : out STD_LOGIC_VECTOR(31 downto 0));
end NewtonRaphson;

architecture Behavioral of NewtonRaphson is
    signal x_n, x_n1, a : STD_LOGIC_VECTOR(31 downto 0);
    signal iteration_count : integer := 0;
    constant MAX_ITERATIONS : integer := 10; -- Ajusta?i dup� nevoie

begin
    process(clk, reset)
    begin
        if reset = '1' then
            -- Ini?ializare variabile
            x_n <= (others => '0');
            iteration_count <= 0;
        elsif rising_edge(clk) then
            -- Logica pentru Newton-Raphson
            if start = '1' then
                -- Ini?ializare la prima itera?ie
                if iteration_count = 0 then
                    a <= a_in;
                end if;

                -- Logica pentru fiecare itera?ie a algoritmului Newton-Raphson
                if iteration_count = 0 then
                    -- La prima itera?ie, ini?ializ�m x_n cu o valoare de pornire
                    x_n <= "1000000000000000";  -- Schimba?i cu valoarea de pornire dorit�
                else
                    -- Formula Newton-Raphson: x_n1 = 0.5 * (x_n + a/x_n)
                    -- �nmul?im ?i �mp�r?im cu 2 pentru a evita opera?ii de �mp�r?ire ?i a �mbun�t�?i performan?a
                    x_n1 <= (x_n + (a / x_n)) / 2;
                    x_n <= x_n1;
end if;


                -- Verifica?i condi?ia de oprire (exemplu: num�r maxim de itera?ii)
                if iteration_count = MAX_ITERATIONS then
                    result_ready <= '1';
                else
                    iteration_count <= iteration_count + 1;
                    result_ready <= '0';
                end if;
            else
                result_ready <= '0';
            end if;
        end if;
    end process;

    -- Ie?irea rezultatului
    result <= x_n1; -- Modifica?i la x_n sau x_n1, �n func?ie de ce dori?i s� afi?a?i

end Behavioral;
