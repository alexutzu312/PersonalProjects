----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/25/2020 04:59:15 PM
-- Design Name: 
-- Module Name: InstructionFetch - Behavioral
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

entity IFF1 is
      Port ( Jump : in std_logic;
      JumpAddress : in std_logic_vector (15 downto 0);
      PCSrc :in std_logic;  
      BranchAddress :in std_logic_vector (15 downto 0);
      EN : in std_logic;
      RST: in std_logic;
      CLK : in std_logic;
      PCplus1: out std_logic_vector(15 downto 0);
      Instruction: out std_logic_vector (15 downto 0));
end IFF1;

architecture Behavioral of IFF1 is
signal d, q, mux_out:std_logic_vector(15 downto 0);

--0. addi $1, $0, 4  initializeaza R1=R0+4=4
--1. addi $2, $1, 5  initializeaza R2=4+5=9
--2. sw $2, $1, 0    stocheaza in memorie la adresa M[R1+0], R2 => M[4]=9
--3. sub $4, $1, $2  initializeaza R4=R1-R2=-5
--4. lw $3, $1, 0    initializeaza R3 cu M[R1+0] => R3=M[4]=9
--5. beq $3, $2, 1   daca R3==R2 atunci trece la linia 7
--6. sub $3, $3, $2  se sare peste aceasta instructiune (R3=R3-R2=0)
--7. add $3, $1, $4  R3=R1+R4=-1
--8. srl $2, $2, 1   R2 primeste R2 deplasat la dreapta cu 1 bit (R2=4)
--9. sll $4, $1, 1  R4 primeste R1 deplasat la stanga cu 1 bit (R4=8)
--10. and $5, $4, $2 initializeaza R5=R4 and R2 (R5=0)  
--11. or $4, $5, $3  R4=R5 or R3 (R4=-1)
--12. addi $4, $4, 1 R4=R4+1=0
--13. bgtz $4, -2    daca R4>0 sare la linia 12 (PC=PC+1-2)
--14. slti $6, $3, 1 initializeaza R6 cu 1 daca R3<1 sau cu 0 altfel (R6=1)


type mem_rom is array (0 to 14) of std_logic_vector (15 downto 0);
signal ROM : mem_rom:= ( 
B"001_000_001_0000100", --addi $1, $0, 4     X<2084>
B"001_001_010_0000101", --addi $2, $1, 5     X<2505>
B"011_010_001_0000000", --sw $2, $1, 0      X<6880> 
B"000_001_010_100_0_001", --sub $4, $1, $2      X<0541>
B"010_001_011_0000000", -- lw $3, $1, 0     X<4580>
B"100_010_100_0000001", --beq $3, $2, 1     X<8A01>
B"000_101_010_101_0_001", --sub $3, $3, $2      X<1551>
B"000_001_100_011_0_000", --add $3, $1, $4       X<0630>
B"000_000_010_010_1_011", --srl $2, $2, 1       X<012B>
B"000_000_001_100_1_010", --sll $4, $1, 1       X<00CA>
B"000_100_010_101_0_100", --and $5, $4, $2      X<1154>
B"000_101_011_100_0_101", --or $4, $5, $3       X<15C5>
B"001_100_100_00000001", --addi $4, $4, 1       X<3201>
B"110_100_000_1111110", --bgtz $4, -2       X<D07E>
B"101_011_110_0000001" --slti $6, $3, 1     X<AF01>
);

begin

PCplus1<=q+1;
Instruction<=ROM(conv_integer (q(3 downto 0)));

--primul mux
with PCSrc Select
mux_out<=BranchAddress when '1',
    q+1 when '0';
    
--mux2
with Jump Select
d<=JumpAddress when '1',
    mux_out when '0';
    
--registru
process (clk)
begin
if rising_edge(clk) then
    if rst='1' then
        q<="0000000000000000";
    elsif en='1' then
        q<= d;
    end if;
end if;
end process;
    
   
end Behavioral;
