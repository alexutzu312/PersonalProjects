library IEEE ;
use IEEE.STD_LOGIC_1164.all ; 
use IEEE.STD_LOGIC_UNSIGNED.all ; 

entity NumOre is 
	port(
		CLR_Ora : in STD_LOGIC ;
		C_Min : in STD_LOGIC ; 
		Cresc_Ora : inout STD_LOGIC ;  
		Set_Ora : inout STD_LOGIC ;
		C_Ora : out STD_LOGIC ;  
		Cout_Ora : out STD_LOGIC_VECTOR( 4 downto 0 )  
		); 
end NumOre ;

architecture NumOre of NumOre is 		  	   

signal Ore : STD_LOGIC_VECTOR( 4 downto 0 ) := "00000" ;
signal Cry : STD_LOGIC ; 				   

begin 
	process( CLR_Ora, C_Min, Cresc_Ora, Set_Ora ) 
	begin			
		
		if CLR_Ora = '1' then 	  -- reset
			Ore <= ( others => '0' ) ; 
			Cry <= '0' ;
		end if ; 
	
		if Set_Ora = '1' then 
			Ore <= Ore + 1 ; 
		end if ; 
		
		if C_Min = '1' then 
			Ore <= Ore + 1 ; 
		end if ;
		
		if Ore = "11000" then 	   -- resetam la 24 de ore
			Ore <= "00000" ; 
			Cry <= '1' ; 
		end if ;		  
		  				  
		 if Ore = "11000" and C_Min = '1' then 	   -- resetam la 24 de ore
			Ore <= "00000" ; 
			Cry <= '1' ; 
		end if ;
		
		if Ore < "11000" then 
			Cry <= '0' ;
		end if ;
		
	end process ; 
	
	Cout_Ora <= Ore ; 
	C_Ora <= Cry ; 
	Cresc_Ora <= '0' ;   
	
end NumOre ; 