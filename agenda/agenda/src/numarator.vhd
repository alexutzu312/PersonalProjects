library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity NrPseudo is
	Port ( clock : in STD_LOGIC;
       		reset : in STD_LOGIC;
       		en : in STD_LOGIC;
       		Q : out STD_LOGIC_VECTOR (5 downto 0)
	   );
end NrPseudo;

architecture NrPseudo of NrPseudo is	

signal Qt: STD_LOGIC_VECTOR(5 downto 0) := "000001";  

begin
	process( clock ) 
	
	variable tmp : STD_LOGIC := '0';
	
	begin
		if rising_edge(clock) then
   			if (reset = '1') then
      			Qt <= "000001"; 
   			elsif en = '1' then
      			tmp := Qt(4) XOR Qt(3) XOR Qt(2) XOR Qt(0);
      			Qt <= tmp & Qt(5 downto 1);
  		 	end if ;
 	 	end if ;
   	end process ;
	   
 end NrPseudo ; 