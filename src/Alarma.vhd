library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity Alarma is
	port(
		CLR_Al : in STD_LOGIC ;
		Min_Al : in STD_LOGIC_VECTOR(5 downto 0);
		Ora_Al: in STD_LOGIC_VECTOR(4 downto 0); 	 
		Cresc_Min_Al : inout STD_LOGIC ; 
		Cresc_Ora_Al : inout STD_LOGIC ; 
		Set_Min : inout STD_LOGIC ; 
		Set_Ore : inout STD_LOGIC ;
		Cout_Min_Al : out STD_LOGIC_VECTOR(5 downto 0) := "000000" ;
		Cout_Ore_Al : out STD_LOGIC_VECTOR(4 downto 0) := "00000" ; 
		Cout_Al : out STD_LOGIC ; 
		Set_Al: out std_logic
		);	   
end alarma;		

architecture Alarma of Alarma is

signal X : STD_LOGIC;
signal Minute : STD_LOGIC_VECTOR( 5 downto 0 ) ;
signal Ore : STD_LOGIC_VECTOR( 4 downto 0 ) ; 	

begin  
	process( Min_Al , Ora_Al , Set_Min , Set_Ore , Cresc_Min_Al , Cresc_Ora_Al )	
	begin											 
		if Set_Min = '1' then 
				Minute <= Minute + 1 ;
		end if ;
		
		if Set_Ore = '1' then 
			Ore <= Ore + 1 ;
			if Ore = "10111" then
				Ore <= "00000" ; 
			end if;
		end if ;
		
		if Minute = Min_Al and Ore = Ora_Al then
			X <= '1';
		end if ;
		
		if X = '1' then 
			if Min_Al - Minute = 5 then 
				X <= '0'; 
				Minute <= "000000";	
				Ore <= "00000";	  
				Set_Al <= '0'; 
			end if;	  
		end if ;
		
		if Minute /= Min_Al and Ore /= Ora_Al then
			X <= '0';
		end if;
		
		if CLR_Al = '1' then 
			Minute <= "000000";	
			Ore <= "00000";	 
		end if;
		
		if Minute >= "000000" and Ore >= "00000" then
			Set_Al <= '1';
		end if;
		
	end process;
	
	Cout_Al <= X;	  
	Cout_Min_AL <= Minute ;
	Cout_Ore_Al <= Ore ;
	
end Alarma;
