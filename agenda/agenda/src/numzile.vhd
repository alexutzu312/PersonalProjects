library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity NumZile is 
	port( 			
		CLR_Zi : in STD_LOGIC ;
		CLR_Luna : in STD_LOGIC ;
		C_Ora : in STD_LOGIC ;
		Cresc_Zi : inout STD_LOGIC ; 
		Cresc_Luna : inout STD_LOGIC ; 
		Set_Zi : inout STD_LOGIC ;
		Set_Luna : inout STD_LOGIC ; 
	 	C_Luna : out STD_LOGIC ;
		Cout_Luna : out STD_LOGIC_VECTOR( 3 downto 0 ) ;  
		Cout_Zile_Cif : out STD_LOGIC_VECTOR( 4 downto 0 ) ; 
		Cout_Zile_Lit : out STRING( 1 to 8 )  
		);
end NumZile ;
		
architecture NumZile of NumZile is 						

signal Zile : STD_LOGIC_VECTOR( 4 downto 0 ) := "00001" ; 
signal Luna : STD_LOGIC_VECTOR( 3 downto 0 ) := "0001" ; 
signal Zi_Sapt : STD_LOGIC_VECTOR( 2 downto 0 ) := "001" ; 

begin 
	process( CLR_Zi, CLR_Luna, C_Ora, Set_Zi, Set_Luna, Cresc_Zi, Cresc_Luna ) 
	begin
		
		if Set_Zi = '1' then  
			if Cresc_Zi = '1' then  
				Zile <= Zile + 1;
				Zi_Sapt <= Zi_Sapt + 1;
				C_Luna <= '0';
			end if;
		end if;		
		
		if C_Ora = '1' then 
			Zile <= Zile + 1;
			Zi_Sapt <= Zi_Sapt + 1; 
		else
			if Zi_Sapt = "001" then
				Cout_Zile_Lit <= "  LUNI  ";	
			end if;
		
			if Zi_Sapt = "010" then 
				Cout_Zile_Lit <= "  MARTI ";
			end if;		
		
			if Zi_Sapt = "011" then 
				Cout_Zile_Lit <= "MIERCURI";
			end if;	  
		
			if Zi_Sapt = "100" then 
				Cout_Zile_Lit <= "  JOI   ";	   	
			end if;
		
			if Zi_Sapt = "101" then 
				Cout_Zile_Lit <= " VINERI ";		
			end if;
		
			if Zi_Sapt = "110" then 
				Cout_Zile_Lit <= " SAMBATA";		
			end if;
		
			if Zi_Sapt = "111" then 
				Cout_Zile_Lit <= "DUMINICA";	
			end if;
		
			if Zi_Sapt = "000" then 
				Zi_Sapt <= Zi_Sapt + 1; 
				Cout_Zile_Lit <= "  LUNI  ";	
			end if;	
		end if;
		
		
		if Set_Luna = '1' then  
			if Cresc_Luna = '1' then 
				Luna <= Luna + 1;
				C_Luna <= '0';
			end if;
		end if;
		
		if Luna = "0001" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;		  
		
       	if Luna = "0010" and Zile = "11101" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;
		 
		if Luna = "0011" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "0100" and Zile = "11111" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "0101" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "0110" and Zile = "11111" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "0111" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "1000" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "1001" and Zile = "11111" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "1010" and Zile = "00000" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "1011" and Zile = "11111" then 
			Luna <= Luna + 1;
			Zile <= "00001";
		end if;	
		
		if Luna = "1100" and Zile = "00000" then 
			Luna <= "0001";
			Zile <= "00001";  
			C_Luna <= '1';
		end if;	  
		
			   
		
		if Luna < "1100" then 
			C_Luna <= '0';
		end if;		
		
		if CLR_Luna = '1' or CLR_Zi = '1' then 
			Set_Zi <= '0' ;
			Zile <= "00001" ; 
			Luna <= "0001" ; 
			Set_Luna <= '0' ; 
		end if ;
		
	end process ;			  
	
	Cresc_Zi	<= '0' ; 
	Cresc_Luna <= '0' ;
	Cout_Zile_Cif	<= Zile ; 
	Cout_Luna <= Luna ;
	
end NumZile ; 