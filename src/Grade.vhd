library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity Grade is
	Port( 
		CLK_Grade : in STD_LOGIC;
       	CLR_Grade : in STD_LOGIC;
       	EN : in STD_LOGIC := '1' ;
       	Grade : out STD_LOGIC_VECTOR (4 downto 0)
	   	);
end Grade;

architecture Grade of Grade is

signal Qt: STD_LOGIC_VECTOR(4 downto 0) := "00001"; 

begin
	process ( CLK_Grade )	   
	
	variable temp : STD_LOGIC := '0';
	
	begin						
		
		if CLK_Grade'EVENT and CLK_Grade = '1' then
   			if CLR_Grade = '1' then
      			Qt <= "00001"; 
   			elsif EN = '1' then
      			temp := Qt(4) XOR Qt(3) XOR Qt(2) XOR Qt(0);
      			Qt <= temp & Qt(4 downto 1);
  		 	end if ;
 	  	end if ;
		
		if Qt > "10001" and Qt < "11010" then
			Grade <= Qt ;
		else
			Grade <= "10100";
		end if;
		   
	end process ;						   
	
	
end Grade ; 
