----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/29/2016 07:49:08 PM
-- Design Name: 
-- Module Name: Numarator - Behavioral
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

entity ALU is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
            led : out STD_LOGIC_VECTOR (15 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end ALU;

architecture Behavioral of ALU is

signal tmp:STD_LOGIC_VECTOR(1 downto 0):="00";

signal x: STD_LOGIC_VECTOR(15 downto 0):="0000000000000000";
signal y: STD_LOGIC_VECTOR(15 downto 0):="0000000000000000";
signal z: STD_LOGIC_VECTOR(15 downto 0):="0000000000000000";
signal rez:STD_LOGIC_VECTOR(15 downto 0):="0000000000000000";
type ROM is array(0 to 255) of std_logic_vector(15 downto 0);
signal mem_rom :ROM:=(x"0009",x"0043",x"0006",others=>x"0000");
signal cnt_ROM : std_logic_vector(7 downto 0):="00000000";
signal en_ROM: std_logic;
signal data_ROM:std_logic_vector(15 downto 0);


signal enable_reg: std_logic;
signal reg_btn: std_logic;
signal reg_reset: std_logic;
signal reg_count: std_logic_vector(3 downto 0):="0000";
signal wd :  std_logic_vector (15 downto 0):=x"0000";
signal rd1 : std_logic_vector (15 downto 0):=x"0000";
signal rd2 : std_logic_vector (15 downto 0):=x"0000";


type RAM is array (0 to 255) of std_logic_vector(15 downto 0);
--signal memorie_RAM:ROM:=(x"0003",x"0025",x"0018",others=>x"0000");
signal addr_RAM:STD_LOGIC_VECTOR (7 downto 0):="00000000";
signal enable_RAM: std_logic;
signal di_RAM:std_logic_vector(15 downto 0);
signal do_RAM:std_logic_vector(15 downto 0);
signal we_RAM: std_logic;
signal ram_count_btn: std_logic;
signal ram_count_btn_reset: std_logic;

component mpg is
    Port ( btn : in STD_LOGIC;
           clk : in STD_LOGIC;
           enable : out STD_LOGIC);
end component;

component SSD is
    Port ( digit0 : in STD_LOGIC_VECTOR (3 downto 0);
           digit1 : in STD_LOGIC_VECTOR (3 downto 0);
           digit2 : in STD_LOGIC_VECTOR (3 downto 0);
           digit3 : in STD_LOGIC_VECTOR (3 downto 0);
           clk : in STD_LOGIC;
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end component;

component Bloc_de_registre is
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
end component;
component IFF1 is
    Port ( clk: in std_logic;
            en_PC: in std_logic;
            en_reset: in std_logic;
            branch_address: in std_logic_vector(15 downto 0);
            jump_address: in std_logic_vector(15 downto 0);
            jump: in std_logic;
            PCSrc: in std_logic;
            instruction: out std_logic_vector(15 downto 0);
            next_instruction_address: out std_logic_vector(15 downto 0));
end component;
signal en1: std_logic;
signal cat1: STD_LOGIC_VECTOR(6 downto 0);
signal an1 : STD_LOGIC_VECTOR(3 downto 0);
signal en_reset1: std_logic;
signal digits: std_logic_vector(15 downto 0);
signal next_instruction_address1: std_logic_vector(15 downto 0);
signal instruction1: std_logic_vector(15 downto 0);
begin


--component IFF1 is
--    Port ( clk : in STD_LOGIC;
--           branchAddress : in STD_LOGIC_VECTOR (15 downto 0);
--           jumpAddress : in STD_LOGIC_VECTOR (15 downto 0);
--           jump : in STD_LOGIC;
--           pcsrc : in STD_LOGIC;
--           pc4 : out STD_LOGIC_VECTOR (15 downto 0);
--           instruction : out STD_LOGIC_VECTOR (2 downto 0));
--end component;
--signal en:STD_LOGIC;
--signal rst:STD_LOGIC;
--signal br:STD_LOGIC_VECTOR(15 downto 0):="0001001000110100";
--signal jmp:STD_LOGIC_VECTOR(15 downto 0):="0101011001111000";
--signal instr:STD_LOGIC_VECTOR (15 downto 0);
--signal next_inst:STD_LOGIC_VECTOR (2 downto 0);
--signal digits:STD_LOGIC_VECTOR (15 downto 0);
--signal clock: STD_LOGIC;


--S: SSD port map(digits(15 downto 12),digits(11 downto 8),digits(7 downto 4),digits(3 downto 0),clk,cat1,an1);
--M: mpg port map(btn(0),clk,en1);
--M1: mpg port map(btn(1),clk,en1);
--I: IFF1 port map(clk,en1,en_reset1,x"0004",x"0000",sw(0),sw(1),instruction1,next_instruction_address1);
process(sw(7))
begin
    case sw(7) is
        when '0' => digits <= instruction1;
        when '1' => digits <= next_instruction_address1;
        when others => digits <= x"0000";
   end case;
end process;

----digits<= instr when sw(2)='0' else next_inst;



end Behavioral;

--x(3 downto 0)<=sw(3 downto 0);
--y(3 downto 0)<=sw(7 downto 4);
--z(7 downto 0)<=sw(15 downto 8);



--process(clk)
--begin
--if rising_edge(clk) then
 --   if en='1' then
   --    if sw(0)='1' then
    --     tmp<=tmp+1;
     --  else  
       --   tmp<=tmp-1;
       --end if;
     -- end if;1
--end if;
--end process;


--process(tmp,x,y,z)
--begin
--case tmp is
-- "00" =>  rez<=x+y;
--when "01" =>  rez<=x-y;
--when "10" =>  rez(15 downto 2)<=z(13 downto 0);
 --             rez(1 downto 0)<="00";
--when  others =>  rez(13 downto 0)<=z(15 downto 2);
 --                rez(15 downto 14)<="00";
--end case;
--end process;



--Bloc de registre

--a2: SSD port map(wd(3 downto 0), wd(7 downto 4), wd(11 downto 8), wd(15 downto 12),clk,cat,an);
--a1: mpg port map(btn(1),clk,enable_reg);
--a3: mpg port map(btn(0),clk,reg_btn);
--a4: mpg port map(btn(2),clk,reg_reset);
--Bloc_Registre: Bloc_de_registre port map(clk, reg_count,reg_count,reg_count,wd,enable_reg,rd1,rd2);
--process(reg_btn,reg_reset)
--begin
 -- if reg_reset='1' then
   --     reg_count<="0000";  
  --elsif rising_edge(reg_btn) then
 --       reg_count <= reg_count + 1;
 -- end if;
--end process;
--led(3 downto 0)<=reg_count;
--process(enable_reg)
--begin
  -- if rising_edge(enable_reg) then
    --   wd<=rd1+rd2;
  -- end if;
  --end process;


--ROM
--a1: mpg port map(btn(0),clk,en_ROM);
--process(en_ROM)
--begin
--  if rising_edge(en_ROM) then
--    cnt_ROM<=cnt_ROM+1;
--   end if;
--end process;
--data_ROM<=mem_ROM(CONV_INTEGER(cnt_ROM));
---a2: SSD port map(data_ROM(3 downto 0),data_ROM(7 downto 4),data_ROM(11 downto 8),data_ROM(15 downto 12),clk,cat,an);

--led(7)<=conv_integer(rez) xor 1;


--RAM
--a1: SSD port map(do_RAM(3 downto 0), do_RAM(7 downto 4), do_RAM(11 downto 8), do_RAM(15 downto 12),clk,cat,an);

--a2t: mpg port map(btn(0),clk,ram_count_btn);
---a3: mpg port map(btn(1),clk,ram_count_btn_reset);

--process(ram_count_btn)
--begin
  --  if ram_count_btn_reset = '1' then
    --     addr_RAM <= "00000000";
    --elsif rising_edge(ram_count_btn) then
      --   addr_RAM <= addr_RAM + 1;
   --end if;
--end process;

---di_RAM <= "00" & sw(13 downto 0);

--enable_RAM <= sw(15);
---we_RAM <= sw(14);

--led(7 downto 0) <= addr_RAM;

---process(clk)
--begin 
   -- if rising_edge(clk) then
      --  if enable_RAM = '1' then
         --   if we_RAM = '1' then
           --    memorie_RAM(CONV_INTEGER(addr_RAM))<= di_RAM;
            --    do_RAM <= di_RAM;
           -- else
           --     do_RAM <= memorie_RAM(CONV_INTEGER(addr_RAM));
          --  end if;
       --- end if;
   --end if;
--end process;

