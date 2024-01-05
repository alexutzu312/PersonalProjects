library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity NewtonRaphson_tb is
end NewtonRaphson_tb;

architecture Behavioral of NewtonRaphson_tb is
    signal clk, reset, start : STD_LOGIC;
    signal result_ready : STD_LOGIC;
    signal result : STD_LOGIC_VECTOR(31 downto 0);

    constant clk_period : time := 10 ns; -- Schimba?i la perioada corectã a ceasului

begin
    -- Componenta de test
    uut : entity work.NewtonRaphson
        port map (
            clk => clk,
            reset => reset,
            start => start,
            result_ready => result_ready,
            result => result
        );

    -- Proces pentru generarea semnalelor de control ?i stimulare
    process
    begin
        reset <= '1';
        wait for 20 ns; -- Pauzã ini?ialã
        reset <= '0';
        wait for 10 ns; -- Pauzã între reset ?i start
        start <= '1';
        wait for 100 ns; -- Pauzã pentru execu?ia algoritmului
        start <= '0';
        wait;
    end process;

    -- Proces pentru generarea semnalei de ceas
    process
    begin
        clk <= '0';
        wait for clk_period / 2;
        clk <= '1';
        wait for clk_period / 2;
    end process;

end Behavioral;
