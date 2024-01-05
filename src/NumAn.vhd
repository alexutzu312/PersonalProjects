library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity NumAn is 
	port(
		CLR_An : in STD_LOGIC ; 
		C_Luna : in STD_LOGIC ;  
		Cresc_An : in STD_LOGIC ; 
		Set_An : inout STD_LOGIC ; 
		Cout_An : out STD_LOGIC_VECTOR( 10 downto 0 ) 
		);
end NumAn ; 

architecture NumAn of NumAn is 	

signal An : STD_LOGIC_VECTOR( 10 downto 0 ) := "11111100110" ; 

begin
	process( CLR_An, C_Luna, Cresc_An, Set_An ) 
	begin
		
		if Set_An = '1' then 
			if Cresc_An = '1' then 
				An <= An + 1;
			end if;	 
		else		
			if C_Luna = '1' and Set_An = '0' then 
				An <= An + 1;
			end if;
		end if ; 
		
		if C_Luna = '1' then 
			An <= An + 1 ;
		end if ; 	   
		
		if CLR_An = '1' then 
			An <= "11111100110" ;
		end if ;
		
	end process ;
	
	Cout_An <= An ; 		 
	
end NumAn ; 	