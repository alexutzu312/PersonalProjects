library IEEE ;
use IEEE.STD_LOGIC_1164.all ; 
use IEEE.STD_LOGIC_UNSIGNED.all ; 

entity NumMin is 
	port(
		CLK_Min : in STD_lOGIC ; 
		CLR_Min : in STD_LOGIC ; 
		Cresc_Min : inout STD_LOGIC ; 
		Set_Min : inout STD_LOGIC ;
		C_Min : out STD_LOGIC ; 
		Cout_Min : out STD_LOGIC_VECTOR( 5 downto 0 )  
		); 
end NumMin ; 

architecture NumMin of NumMin is   

signal Min : STD_LOGIC_VECTOR( 5 downto 0 ) := "000000" ; 	

begin 
	process( CLK_Min, CLR_Min, Cresc_Min, Set_Min ) 
	begin   			 
		
		if Set_Min = '1' then 
			if CLK_Min'EVENT and CLK_Min = '1' and Cresc_Min = '1' then 
				Min <= Min + 1 ; 
			end if ;
		end if ;	   	  
		
		if CLR_Min = '1' then 		   --reset
			Min <= "000000" ;				   
			C_Min <= '0' ; 
			Set_Min <= '0' ;
		end if ;		 
		
		if CLK_Min'EVENT and CLK_Min = '1' and Min = "111011" then --cand ajungem la 60, modificam carry signal resetam  
				C_Min <= '1' ;
				Min <= "000000" ; 	
		end if ;
		
		if CLK_Min'EVENT and CLK_Min = '1' and Min = "000001" then  -- resetam carry pe 0  	 
			C_Min <= '0' ; 
		end if ;
		
	end process ;
	
	Cout_Min <= Min ; 
	Cresc_Min <= '0' ; 	
	
end NumMin ; 