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

entity test1 is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test1;

architecture Behavioral of test1 is

component MPG is
  Port ( btn : in STD_LOGIC;
           clk : in STD_LOGIC;
           enable : out STD_LOGIC);
end component;

component SSD is
  Port (digit0, digit1, digit2, digit3 : in std_logic_vector(3 downto 0);
  clk : in std_logic;
  cat : out std_logic_vector(6 downto 0);
  an : out std_logic_vector(3 downto 0) );
end component;

component IFF1 is
      Port ( Jump : in std_logic;
            JumpAddress : in std_logic_vector (15 downto 0);
             PCSrc :in std_logic;  
           BranchAddress :in std_logic_vector (15 downto 0);
           EN : in std_logic;
          RST: in std_logic;
           CLK : in std_logic;
           PCplus1: out std_logic_vector(15 downto 0);
            Instruction: out std_logic_vector (15 downto 0));  
end component;

component ID is
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
end component;

component UC is
Port( Instr:in std_logic_vector(15 downto 0);
    RegDst: out std_logic;
    ExtOp: out std_logic;
    ALUSrc: out std_logic;
    Branch: out std_logic;
    Brgtz: out std_logic;
    Jump: out std_logic;
    ALUOp: out std_logic_vector (1 downto 0);
    MemWrite: out std_logic;
    MemToReg: out std_logic;
    RegWrite: out std_logic);
end component;

component EX is
    Port(
    RD1: in std_logic_vector(15 downto 0);
    RD2: in std_logic_vector (15 downto 0);
    ALUSrc: in std_logic;
    Ext_imm: in std_logic_vector(15 downto 0);
    sa: in std_logic;
    func: in std_logic_vector(2 downto 0);
    ALUOp: in std_logic_vector (1 downto 0);
    PCplus1: in std_logic_vector (15 downto 0);
    GT: out std_logic;
    Zero: out std_logic;
    ALURes: out std_logic_vector (15 downto 0);
    BranchAddress: out std_logic_Vector(15 downto 0));
end component;

component MEM is
    Port(
    MemWrite: in std_logic;
    ALUResIn: in std_logic_vector(15 downto 0);
    RD2: in std_logic_vector (15 downto 0);
    clk: in std_logic;
    en: in std_logic;
    MemData: out std_logic_vector(15 downto 0);
    ALUResOut: out std_logic_vector (15 downto 0));
end component;




signal out1 : std_logic_vector (15 downto 0);
signal enable1, enable2: std_logic;
signal PCplus1:  std_logic_vector(15 downto 0);
signal Instruction:  std_logic_vector (15 downto 0);
signal Jump, MemToReg, MemWrite, Branch, ALUSrc, Brgtz :std_logic;
signal RegWrite, RegDst, ExtOp, sa: std_logic;
signal ALUOp: std_logic_vector (1 downto 0);
signal RD1, RD2, ExtImm, WD: std_logic_vector (15 downto 0);
signal func: std_logic_vector (2 downto 0);
signal JumpAddress, BranchAddress, ALURes, MemData, ALUResOut: std_logic_vector(15 downto 0);
signal PCSrc, Zero, GT :std_logic;



begin


led(10 downto 0)<=Brgtz & ALUOp & RegDst & ExtOp & ALUSrc & Branch & Jump & MemWrite & MemToReg & RegWrite; 

JumpAddress<=PCplus1(15 downto 13) & Instruction(12 downto 0);
WD<=ALUResOut when MemToReg='0' else MemData;
PCSrc<= (GT and Brgtz) or (Branch and Zero);

 MPG1 : MPG port map( btn(0), clk,enable1); 
MPG2 : MPG port map(btn(1), clk,enable2);

IF1 : IFF1 port map (Jump, JumpAddress, PCSrc, BranchAddress, enable1, enable2, clk, PCplus1, Instruction);

ID1: ID port map (RegWrite, Instruction, RegDst, clk, enable1, ExtOp, WD, RD1, RD2, ExtImm, func, sa);

UC1: UC port map (Instruction, RegDst, ExtOp, ALUSrc, Branch, Brgtz, Jump, ALUOp, MemWrite, MemToReg, RegWrite);

EX1: EX port map (RD1, RD2, ALUSrc, ExtImm, sa, func, ALUOp, PCplus1, GT, Zero, ALURes, BranchAddress);

MEM1: MEM port map (MemWrite, ALURes, RD2, clk, enable1, MemData, ALUResOut);

SSD1 : SSD port map(out1(3 downto 0), out1(7 downto 4), out1(11 downto 8), out1(15 downto 12), clk, cat, an);

with sw(7 downto 5) Select
out1<=Instruction when "000",
    PCPlus1 when "001",
    RD1 when "010",
    RD2 when "011",
    ExtImm when "100",
    ALURes when "101",
    MemData when "110",
    WD when "111";
    
end Behavioral;