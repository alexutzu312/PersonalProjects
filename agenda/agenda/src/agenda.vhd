library IEEE ; 
use IEEE.STD_LOGIC_1164.all ;
use IEEE.STD_LOGIC_UNSIGNED.all ; 
use IEEE.STD_LOGIC_ARITH.all ;

entity Agenda is 
	port(
			Set : inout STD_LOGIC;
	 		CU : inout STD_LOGIC;
			Reset : inout STD_LOGIC;
			Cout_M : inout STD_LOGIC_VECTOR(5 downto 0);
			Cout_O : inout STD_LOGIC_VECTOR(4 downto 0); 
			Cout_M_Al : inout STD_LOGIC_VECTOR(5 downto 0 );
			Cout_O_Al : inout STD_LOGIC_VECTOR(4 downto 0);
			Cout_Z : out STD_LOGIC_VECTOR(4 downto 0);
			Cout_Z_Lit : out STRING(1 to 8);
			Cout_L : out STD_LOGIC_VECTOR(3 downto 0);
			Cout_A : out STD_LOGIC_VECTOR(10 downto 0);
			Cout_G : out STD_LOGIC_VECTOR(4 downto 0)
		);	
end Agenda;

architecture  Agenda of Agenda is

signal CLK_Minute : STD_LOGIC  ; 
signal CLK_G : STD_LOGIC  ;
signal Enable : STD_LOGIC := '1' ; 
signal CarryMin : STD_LOGIC ;
signal CarryOre : STD_LOGIC  ;
signal CarryLuna : STD_LOGIC 	;
signal Suna_Al : STD_LOGIC ;	 
signal Set_Alarma : STD_LOGIC ;	 
signal Temperatura : std_logic_vector(5 downto 0) := "001111";
signal S_Ora : STD_LOGIC := '0' ; 				  
signal S_Zi : STD_LOGIC := '0' ; 
signal S_Luna : STD_LOGIC := '0' ;
signal S_An : STD_LOGIC := '0' ;
signal S_M_Al : STD_LOGIC := '0' ; 
signal S_O_Al : STD_LOGIC := '0' ;

component NumMin is 
	port(
		CLK_Min : in STD_lOGIC ; 
		CLR_Min : in STD_LOGIC ; 
		Cresc_Min : inout STD_LOGIC ; 					
		Set_Min : inout STD_LOGIC ;
		C_Min : out STD_LOGIC ; 
		Cout_Min : out STD_LOGIC_VECTOR( 5 downto 0 )    
		); 
end component NumMin ;

component NumOre is 
	port(
		CLR_Ora : in STD_LOGIC ;
		C_Min : in STD_LOGIC ;
		Cresc_Ora : inout STD_LOGIC ;  
		Set_Ora : inout STD_LOGIC ;	   
		C_Ora : out STD_LOGIC ;
		Cout_Ora : out STD_LOGIC_VECTOR( 4 downto 0 )    
		); 
end component NumOre ;

component NumZile is 
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
end component NumZile ;				

component NumAn is 
	port( 						
		CLR_An : in STD_LOGIC ; 
		C_Luna : in STD_LOGIC ; 
		Cresc_An : in STD_LOGIC ; 
		Set_An : inout STD_LOGIC ; 
		Cout_An : out STD_LOGIC_VECTOR( 10 downto 0 ) 
		);
end component NumAn ; 

component Alarma is
	port( 
		CLR_Al : in STD_LOGIC ;
		Min_Al : in STD_LOGIC_VECTOR(5 downto 0);
		Ora_Al: in STD_LOGIC_VECTOR(4 downto 0);
		Cresc_Min_Al : inout STD_LOGIC ; 
		Cresc_Ora_Al : inout STD_LOGIC ; 
		Set_Min : inout STD_LOGIC ; 
		Set_Ore : inout STD_LOGIC ;
		Cout_Min_AL : out STD_LOGIC_VECTOR(5 downto 0) := "000000" ;
		Cout_Ore_AL : out STD_LOGIC_VECTOR(4 downto 0) := "00000" ; 
		Cout_Al : out STD_LOGIC ;
		Set_Al: out std_logic
		);	   
end component Alarma;	

component Grade is 
	Port( 
		CLK_Grade : in STD_LOGIC;
       	CLR_Grade : in STD_LOGIC;
       	EN : in STD_LOGIC := '1' ;
       	Grade : out STD_LOGIC_VECTOR (4 downto 0)
	   	); 
end component Grade ;  	

	begin
		C1 : NumMin port map( 
		  		  CLK_Min => CLK_Minute ,   
				  CLR_Min => Reset , 
				  Cresc_Min => CU , 
				  Set_Min => Set ,
				  C_Min => CarryMin , 
				  Cout_Min => Cout_M  
			  );
			  
		C2 : NumOre port map( 
				  CLR_Ora => Reset ,
				  C_Min => CarryMin , 
				  Cresc_Ora => CU ,  
				  Set_Ora => S_Ora ,
				  C_Ora => CarryOre ,
				  Cout_Ora => Cout_O  
		 	  );
			   
		C3 : NumZile port map( 			  
		
				  CLR_Zi => Reset ,
			      CLR_Luna => Reset ,
				  C_Ora => CarryOre ,		
				  Cresc_Zi => CU ,
				  Cresc_Luna => CU ,
				  Set_Zi => S_Zi ,
				  Set_Luna => S_Luna , 		 
				  C_Luna => CarryLuna ,
				  Cout_Luna => Cout_L ,
				  Cout_Zile_Cif => Cout_Z , 
				  Cout_Zile_Lit => Cout_Z_Lit  
			  ); 
				    
		C4 : NumAn port map(
				CLR_An => Reset ,
				C_Luna => CarryLuna ,  
				Cresc_An => CU ,
				Set_An => S_An ,
				Cout_An => Cout_A 
			);
				
		C5 : Alarma port map(
		
				CLR_Al => Reset,
				Min_Al => Cout_M ,
				Ora_Al => Cout_O ,
				Cresc_Min_Al => CU , 
				Cresc_Ora_Al => CU , 
				Set_Min => S_M_Al , 
				Set_Ore => S_O_Al ,
				Cout_Min_AL => Cout_M_Al ,
				Cout_Ore_AL => Cout_O_Al ,
				Cout_Al => Suna_Al ,
				Set_Al => Set_Alarma
			);	

		CLK_G <= CarryOre	;  	
			
		C6 : Grade port map(
			CLK_Grade => CLK_G , 
       		CLR_Grade => Reset , 
       		EN => Enable , 
       		Grade => Cout_G
			);
			
end Agenda;