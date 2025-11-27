import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Dados {
	public static String Nome = "", NomeEquipe = "", NomeMotor = "", BandeiraP = "", Sede = "", BandeiraSede = "", BandeiraEquipe = "", BandeiraMotor = "", LogoEquipe = "", LogoMotor, LogoMotorPQ, Pais;
	public static Double  Experiencia, Ritmo, Agressividade, Fisico, Largada, Ultrapassagem, Hab_Chuva, Hab_Rua, Hab_Misto, Hab_Oval, Over;
	public static int ano_nasc = 0, fundacao = 0, n_piloto, contador = 1000;
	public static double Orcamento;

	static String Curvas_Pista, Clima, IMG_Clima, PneuAtual, IMGPneuAtual;
	static String PneuAtualP1="SOFT", PneuAtualP2="SOFT", PneuAtualP3="SOFT", PneuAtualP4="SOFT", PneuAtualP5="SOFT";
	static String IMGPneuAtualP1, IMGPneuAtualP2, IMGPneuAtualP3, IMGPneuAtualP4, IMGPneuAtualP5;
	static String PneuPitP1, PneuPitP2, PneuPitP3, PneuPitP4, PneuPitP5, IMGPitP1, IMGPitP2, IMGPitP3, IMGPitP4, IMGPitP5;
	static int VidaPitP1, VidaPitP2, VidaPitP3, VidaPitP4, VidaPitP5;
	
	public static int Voltas_Pista, VidaUtilP1, VidaUtilP2, VidaUtilP3, VidaUtilP4, VidaUtilP5;
	public static String InauguracaoPista, LocalPista, TipoPista, CapacidadePista, ComprimentoPista, LayoutPista, LayoutPistaPQ, PaisPista;
	
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		
	// INFOS SALVAS PRA COPIAR
	
	/*
	 
	public static void Motor () {
		Nome = "";
		BandeiraMotor = "/Imagens/Bandeira .png";
		LogoMotor  = "/Imagens/Motor_.png";
		LogoMotorPQ  = "/Imagens/Motor_PQ.png";
	}
	
	*/
	
	/*
	 
	public static void Equipe () {
		NomeEquipe="";
		Sede = "";
		BandeiraSede = "/Imagens/Bandeira .png";
		BandeiraEquipe = "/Imagens/Bandeira .png";
		LogoEquipe = "/Imagens/Motor_ .png";
		fundacao = 0;
	}
	
	*/
	
	////Sem Piloto apenas 1 Número
	/*
	 
	public static void SemPiloto () {	
		Nome="";
		BandeiraP = "/Imagens/Bandeira .png";
		ano_nasc = 0;
		n_piloto=0;
		Pais="";
	}
	
	*/
	
	//Sem Piloto + Números
	/*
	 
	public static void SemPiloto () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="";
		BandeiraP = "/Imagens/Bandeira .png";
		ano_nasc = 0;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "";
	}
	
	*/
	
	/*
	 				
	Dados.Equipe();
			
			Dados.SemPiloto();
			SemP1();
			
			Dados.SemPiloto();
			SemP2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.Motor();
			DadosEquipeMotor();
			Orcamento = 10;
	*/

	/*
	"Equipe Selecionada: "+EquipeEscolhida+
	"\nnNome da equipe: "+NomeEquipeEscolhida+
	"\nSede equipe: "+SedeEquipeEscolhida+
	"\nBandeira Sede: "+BandeiraSedeEquipeEscolhida
	"\nBandeira Equipe: "+BandeiraEquipeEscolhida+
	"\nLogo Equipe: "+LogoEquipeEscolhida +
	"\nFundação: "+FundacaoEquipeEscolhida+
	"\nMotor: "+NomeMotorEscolhido+
	"\nBandeira Motor: "+BandeiraMotorEscolhido+
	"\nLogo Motor: "+LogoMotorEscolhido+
	"\nLogo MotorPQ: "+LogoMotorPQEscolhido+
	
	"\n\nNome Piloto1 : "+NomePiloto1+
	"\nBandeira Piloto1 : "+BandeiraPiloto1+
	"\nNome Piloto1 : "+NumeroPiloto1+
	"\nBorn Piloto1 : "+NascimentoPiloto1+
	
	"\n\nNome Piloto2 : "+NomePiloto2+
	"\nBandeira Piloto2 : "+BandeiraPiloto2+
	"\nNumero Piloto2 : "+NumeroPiloto2+
	"\nBorn Piloto2 : "+NascimentoPiloto2+
	
	"\n\nNome Piloto3 : "+NomePiloto3+
	"\nBandeira Piloto3 : "+BandeiraPiloto3+
	"\nNumero Piloto3 : "+NumeroPiloto3+
	"\nBorn Piloto3 : "+NascimentoPiloto3+
	
	"\n\nNome Piloto4 : "+NomePiloto4+
	"\nBandeira Piloto4 : "+BandeiraPiloto4+
	"\nNumero Piloto4 : "+NumeroPiloto4+
	"\nBorn Piloto4 : "+NascimentoPiloto4+
	
	"\n\nNome Piloto5 : "+NomePiloto5+
	"\nBandeira Piloto5 : "+BandeiraPiloto5+
	"\nNumero Piloto5 : "+NumeroPiloto5+
	"\nBorn Piloto5 : "+NascimentoPiloto5
	
	NumeroPiloto5 = Integer.parseInt(LB_NumPiloto5.getText());
	NumeroPiloto4 = Integer.parseInt(LB_NumPiloto4.getText());
	NumeroPiloto3 = Integer.parseInt(LB_NumPiloto3.getText());
	NumeroPiloto2 = Integer.parseInt(LB_NumPiloto2.getText());
	
	*/
	
	/*
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em ";
		LocalPista = "";
		TipoPista  = "Circuito de Rua Permanente";
		CapacidadePista = "Capacidade de  pessoas";
		
		if(SelecionarEquipe.ano >= ) {
			
			Curvas_Pista = ;
			Voltas_Pista = ;
			ComprimentoPista = "Comprimento de  mts";
			LayoutPista = "/Imagens/.png";
			LayoutPistaPQ = "/Imagens/.png";
			
		}else if(SelecionarEquipe.ano >=  && SelecionarEquipe.ano <= ) {
			
			Curvas_Pista = 16;
			Voltas_Pista = 58;
			ComprimentoPista = "Comprimento de  mts";
			LayoutPista = "/Imagens/.png";
			LayoutPistaPQ = "/Imagens/.png";
	}
	*/
	
	//Imagens Padrão 	- 200x100
	//Imagens Motor PQ  - 40x20
	//Imagens Bandeiras - 20x20
	
	//Infos Gerais
	
	public static void GeraClima() {
		
		int RandonClima;
		
		Random random = new Random();
		RandonClima = random.nextInt(100);
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();

		if(Categoria.BT_Cate.equals("Fórmula 1")) {
			
			if (RandonClima <= 45) {
				
				Clima = "Ensolarado";
				IMG_Clima = "/Imagens/ClimaEnsolarado32px.png";
				
				PneuAtual = "SOFT";
				IMGPneuAtual ="/Imagens/PneuMacioF140px.png";
			}
			
			if (RandonClima > 45 && RandonClima <= 70) {
				
				Clima = "Sol entre nuvens";
				IMG_Clima = "/Imagens/ClimaSolEntreNuvens32px.png";
				
				PneuAtual = "SOFT";
				IMGPneuAtual ="/Imagens/PneuMacioF140px.png";
			}
			
			if (RandonClima > 70 && RandonClima <= 85) {
				
				Clima = "Nublado";
				IMG_Clima = "/Imagens/ClimaNublado32px.png";
				
				PneuAtual = "SOFT";
				IMGPneuAtual ="/Imagens/PneuMacioF140px.png";
			}
			
			if (RandonClima > 85 && RandonClima <= 95) {
				
				Clima = "Chuva Fraca";
				IMG_Clima = "/Imagens/ClimaChuvaFraca32px.png";
				
				PneuAtual = "INTERMEDIÁRIO";
				IMGPneuAtual ="/Imagens/PneuIntermediarioF140px.png";
			}
			
			if (RandonClima > 95) {
				
				Clima = "Chuva Forte";
				IMG_Clima = "/Imagens/ClimaChuvaForte32px.png";
				
				PneuAtual = "CHUVA";
				IMGPneuAtual ="/Imagens/PneuChuvaF140px.png";
			}	
			
		} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
			
			Pagina_Treino PaginaTreino = new Pagina_Treino();
			//FAZER O MESMO COM A PAGINA DE CORRIDA QUANDO CRIAR
			
			if(CapacidadePista.equals("Circuito Oval")) {
				
				if (RandonClima <= 50) {
					
					Clima = "Ensolarado";
					IMG_Clima = "/Imagens/ClimaEnsolarado32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				
				if (RandonClima > 50 && RandonClima <= 75) {
					
					Clima = "Sol entre nuvens";
					IMG_Clima = "/Imagens/ClimaSolEntreNuvens32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				
				if (RandonClima > 75) {
					
					Clima = "Nublado";
					IMG_Clima = "/Imagens/ClimaNublado32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				
			}else {
				
				if (RandonClima <= 50) {
					
					Clima = "Ensolarado";
					IMG_Clima = "/Imagens/ClimaEnsolarado32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				
				if (RandonClima > 50 && RandonClima <= 70) {
					
					Clima = "Sol entre nuvens";
					IMG_Clima = "/Imagens/ClimaSolEntreNuvens32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				
				if (RandonClima > 70 && RandonClima <= 90) {
					
					Clima = "Nublado";
					IMG_Clima = "/Imagens/ClimaNublado32px.png";
					
					PneuAtual = "SOFT";
					IMGPneuAtual ="/Imagens/PneuMacioIndy50px.png";
				}
				if (RandonClima > 90) {
					
					Clima = "Chuva Forte";
					IMG_Clima = "/Imagens/ClimaChuvaForte32px.png";
					
					PneuAtual = "CHUVA";
					IMGPneuAtual ="/Imagens/PneuChuvaIndy50px.png";
				}	
				
			}
			
			
			
		} else if(Categoria.BT_Cate.equals("NASCAR")) {
			
			
			if (RandonClima <= 50) {
				
				Clima = "Ensolarado";
				IMG_Clima = "/Imagens/ClimaEnsolarado32px.png";
				
				PneuAtual = "Eagle Prime";
				IMGPneuAtual ="/Imagens/PneuNASCAR50px.png";
			}
			
			if (RandonClima > 50 && RandonClima <= 75) {
				
				Clima = "Sol entre nuvens";
				IMG_Clima = "/Imagens/ClimaSolEntreNuvens32px.png";
				
				PneuAtual = "Eagle Prime";
				IMGPneuAtual ="/Imagens/PneuNASCAR50px.png";
			}
			
			if (RandonClima > 75) {
				
				Clima = "Nublado";
				IMG_Clima = "/Imagens/ClimaNublado32px.png";
				
				PneuAtual = "Eagle Prime";
				IMGPneuAtual ="/Imagens/PneuNASCAR50px.png";
			}
			
		}
		
	}

	public static void SetInfosCorridaF1() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Pagina_CorridaF1 PaginaCorridaF1 = new Pagina_CorridaF1();
		
		PaginaCorridaF1.LB_BandeiraPiloto1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
		PaginaCorridaF1.LB_Piloto1.setText(""+SelecionarEquipe.NomePiloto1);

		PaginaCorridaF1.LB_BandeiraPiloto2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto2+".png")));
		PaginaCorridaF1.LB_Piloto2.setText(""+SelecionarEquipe.NomePiloto2);
		
		if(PneuAtualP1.equals("SOFT")) {
			PaginaCorridaF1.LB_PneuAtualP1.setText("SOFT");
			PaginaCorridaF1.LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuMacioF140px.png")));
		}
		if(PneuAtualP1.equals("MEDIUM")) {
			PaginaCorridaF1.LB_PneuAtualP1.setText("MEDIUM");
			PaginaCorridaF1.LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuMedioF140px.png")));
		}
		if(PneuAtualP1.equals("HARD")) {
			PaginaCorridaF1.LB_PneuAtualP1.setText("HARD");
			PaginaCorridaF1.LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuDuroF140px.png")));
		}
		if(PneuAtualP1.equals("INTERMEDIÁRIO")) {
			PaginaCorridaF1.LB_PneuAtualP1.setText("INTERMEDIÁRIO");
			PaginaCorridaF1.LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuChuvaF140px.png")));
		}
		if(PneuAtualP1.equals("CHUVA")) {
			PaginaCorridaF1.LB_PneuAtualP1.setText("CHUVA");
			PaginaCorridaF1.LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuChuvaF140px.png")));
		}
		
		
		if(PneuAtualP2.equals("SOFT")) {
			PaginaCorridaF1.LB_PneuAtualP2.setText("SOFT");
			PaginaCorridaF1.LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuMacioF140px.png")));
		}
		if(PneuAtualP2.equals("MEDIUM")) {
			PaginaCorridaF1.LB_PneuAtualP2.setText("MEDIUM");
			PaginaCorridaF1.LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuMedioF140px.png")));
		}
		if(PneuAtualP2.equals("HARD")) {
			PaginaCorridaF1.LB_PneuAtualP2.setText("HARD");
			PaginaCorridaF1.LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuDuroF140px.png")));
		}
		if(PneuAtualP2.equals("INTERMEDIÁRIO")) {
			PaginaCorridaF1.LB_PneuAtualP2.setText("INTERMEDIÁRIO");
			PaginaCorridaF1.LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuChuvaF140px.png")));
		}
		if(PneuAtualP2.equals("CHUVA")) {
			PaginaCorridaF1.LB_PneuAtualP2.setText("CHUVA");
			PaginaCorridaF1.LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/PneuChuvaF140px.png")));
		}
		
		PaginaCorridaF1.LB_Piloto1.setText(""+SelecionarEquipe.NomePiloto1);
		PaginaCorridaF1.LB_BandeiraPiloto1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
		
		PaginaCorridaF1.LB_Piloto2.setText(""+SelecionarEquipe.NomePiloto2);
		PaginaCorridaF1.LB_BandeiraPiloto2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto2+".png")));
		
	}

	public static void VidaUtilPneuP1() {
	
		//P1
		
		if(PneuAtualP1 == "SOFT") {
			VidaUtilP1 = ((int) Math.ceil((double)Voltas_Pista / 4)+2);
		}
		
		if(PneuAtualP1 == "MEDIUM") {
			VidaUtilP1 = ((int) Math.ceil((double)Voltas_Pista / 2.5)+2);
		}
		
		if(PneuAtualP1 == "HARD") {
			VidaUtilP1 = ((int) Math.ceil((double)Voltas_Pista / 2)+4);
		}
		
		if(PneuAtualP1 == "INTER" || PneuAtualP1 == "WET") {
			VidaUtilP1 = ((int) Math.ceil((double)Voltas_Pista / 2)+1);
		}	
		
	}

	public static void VidaUtilPneuP2() {

		if(PneuAtualP2 == "SOFT") {
			VidaUtilP2 = ((int) Math.ceil((double)Voltas_Pista / 4)+2);
		}
		
		if(PneuAtualP2 == "MEDIUM") {
			VidaUtilP2 = ((int) Math.ceil((double)Voltas_Pista / 2.5)+2);
		}
		
		if(PneuAtualP2 == "HARD") {
			VidaUtilP2 = ((int) Math.ceil((double)Voltas_Pista / 2)+4);
		}
		
		if(PneuAtualP2 == "INTER" || PneuAtualP2 == "WET") {
			VidaUtilP2 = ((int) Math.ceil((double)Voltas_Pista / 2)+1);
		}	
	}
	
	public static void VidaUtilPneuP3() {

		
		
	}
	
	public static void VidaUtilPneuP4() {

		
		
	}
	
	public static void VidaUtilPneuP5() {

		
		
	}
	
	public static void PitStopP1() {

		
		
	}
	
	public static void PitStopP2() {

		
		
	}
	
	public static void PitStopP3() {

		
		
	}
	
	public static void PitStopP4() {

		
		
	}
	
	public static void PitStopP5() {

		
		
	}
	
	//Motor
	
	public static void MotorFerrari () {
		NomeMotor = "Ferrari";
		BandeiraMotor = "/Imagens/Bandeira Italia.png";
		LogoMotor  = "/Imagens/Motor_Ferrari.png";
		LogoMotorPQ  = "/Imagens/Motor_FerrariPQ.png";
	}
	
	public static void MotorHonda () {
		NomeMotor = "Honda";
		BandeiraMotor = "/Imagens/Bandeira Japao.png";
		LogoMotor  = "/Imagens/Motor_Honda.png";
		LogoMotorPQ  = "/Imagens/Motor_HondaPQ.png";
	}
	
	public static void MotorRenault () {
		NomeMotor = "Renault";
		BandeiraMotor = "/Imagens/Bandeira Franca.png";
		LogoMotor  = "/Imagens/Motor_Renault.png";
		LogoMotorPQ  = "/Imagens/Motor_RenaultPQ.png";
	}
	
	public static void MotorMercedes () {
		NomeMotor = "Mercedes";
		BandeiraMotor = "/Imagens/Bandeira Alemanha.png";
		LogoMotor  = "/Imagens/Motor_Mercedes.png";
		LogoMotorPQ  = "/Imagens/Motor_MercedesPQ.png";
	}
	
	public static void MotorChevrolet () {
		NomeMotor = "Chevrolet";
		BandeiraMotor = "/Imagens/Bandeira Estados Unidos.png";
		LogoMotor  = "/Imagens/Motor_Chevrolet.png";
		LogoMotorPQ  = "/Imagens/Motor_ChevroletPQ.png";
	}
		
	public static void MotorToyota () {
		NomeMotor = "Toyota";
		BandeiraMotor = "/Imagens/Bandeira Japao.png";
		LogoMotor  = "/Imagens/Imagens/Motor_Toyota.png";
		LogoMotorPQ  = "/Imagens/Motor_ToyotaPQ.png";
	}
	
	public static void MotorFord () {
		NomeMotor = "Ford";
		BandeiraMotor = "/Imagens/Bandeira Estados Unidos.png";
		LogoMotor  = "/Imagens/Imagens/Motor_Ford.png";
		LogoMotorPQ  = "/Imagens/Motor_FordPQ.png";
	}
	
	//Equipes
	
//Equipes F1		
	
	public static void EquipeStakeKick () {
		NomeEquipe="Stake F1 Team Kick Sauber";
		Sede = "Hinwil, Zurique";
		BandeiraSede = "/Imagens/Bandeira Suica.png";
		BandeiraEquipe = "/Imagens/Bandeira Suica.png";
		LogoEquipe = "/Imagens/Equipe_F1_Stake.png";
		fundacao = 2024;
		Orcamento = 25;
	}
	
	public static void EquipeAlfaRomeo () {
		NomeEquipe="Alfa Romeo F1 Team";
		Sede = "Hinwil, Zurique";
		BandeiraSede = "/Imagens/Bandeira Suica.png";
		BandeiraEquipe = "/Imagens/Bandeira Italia.png";
		LogoEquipe = "/Imagens/Equipe_F1_AlphaRomeo.png";
		fundacao = 2020;
		Orcamento = 20;
	}
	
	public static void EquipeRBR () {
		NomeEquipe="Red Bull Racing";
		Sede = "Milton Keynes, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Austria.png";
		LogoEquipe = "/Imagens/Equipe_F1_RBR.png";
		fundacao = 2005;
		Orcamento = 85;
	}
	
	public static void EquipeAlpine () {
		NomeEquipe="Alpine F1 Team";
		Sede = "Enstone, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Franca.png";
		LogoEquipe = "/Imagens/Equipe_F1_Alpine.png";
		fundacao = 2021;
		Orcamento = 35;
	}
	
	public static void EquipeFerrari () {
		NomeEquipe="Scuderia Ferrari";
		Sede = "Maranello, Itália";
		BandeiraSede = "/Imagens/Bandeira Italia.png";
		BandeiraEquipe = "/Imagens/Bandeira Italia.png";
		LogoEquipe = "/Imagens/Equipe_F1_Ferrari.png";
		fundacao = 1929;
		Orcamento = 95;
	}
	
	public static void EquipeAstonMartin () {
		NomeEquipe="Aston Martin F1 Team";
		Sede = "Silverstone, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Reino Unido.png";
		LogoEquipe = "/Imagens/Equipe_F1_Aston Martin.png";
		fundacao = 2021;
		Orcamento = 55;
	}	
	
	public static void EquipeMercedes () {
		NomeEquipe="Mercedes-AMG Petronas F1 Team";
		Sede = "Brackley, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Alemanha.png";
		LogoEquipe = "/Imagens/Equipe_F1_Mercedes.png";
		fundacao = 1954;
		Orcamento = 85;
	}	
	
	public static void EquipeWilliams () {
		NomeEquipe="Williams Racing";
		Sede = "Grove, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Reino Unido.png";
		LogoEquipe = "/Imagens/Equipe_F1_Williams.png";
		fundacao = 1977;
		Orcamento = 25;
	}
	
	public static void EquipeRacingBulls () {
		NomeEquipe="Visa Cash App RB F1 Team";
		Sede = "Faença, Itália";
		BandeiraSede = "/Imagens/Bandeira Italia.png";
		BandeiraEquipe = "/Imagens/Bandeira Italia.png";
		LogoEquipe = "/Imagens/Equipe_F1_RB.png";
		fundacao = 2024;
		Orcamento = 20;
	}
	
	public static void EquipeAlphaTauri () {
		NomeEquipe="Scuderia AlphaTauri";
		Sede = "Faença, Itália";
		BandeiraSede = "/Imagens/Bandeira Italia.png";
		BandeiraEquipe = "/Imagens/Bandeira Italia.png";
		LogoEquipe = "/Imagens/Equipe_F1_AlphaTauri.png";
		fundacao = 2020;
		Orcamento = 18;
	}
	
	public static void EquipeSTR () {
		NomeEquipe="Scuderia Toro Rosso";
		Sede = "Faença, Itália";
		BandeiraSede = "/Imagens/Bandeira Italia.png";
		BandeiraEquipe = "/Imagens/Bandeira Italia.png";
		LogoEquipe = "/Imagens/Equipe_F1_STR.png";
		fundacao = 2005;
		Orcamento = 20;
	}
	
	public static void EquipeMcLaren () {
		NomeEquipe="McLaren F1 Team";
		Sede = "Woking, Reino Unido";
		BandeiraSede = "/Imagens/Bandeira Reino Unido.png";
		BandeiraEquipe = "/Imagens/Bandeira Reino Unido.png";
		LogoEquipe = "/Imagens/Equipe_F1_McLaren.png";
		fundacao = 1963;
		Orcamento = 65.5;
	}
	
	public static void EquipeHaas () {
		NomeEquipe="Haas F1 Team";
		Sede = "Kannapolis, Estados Unidos";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_F1_Haas.png";
		fundacao = 2016;
		Orcamento = 30.250;
	}

//Equipes Indy	
	
	public static void EquipeAbelMotorsports () {
		NomeEquipe="Abel Motorsports";
		Sede = "Indianápolis, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Abel.png";
		fundacao = 1972;
		Orcamento = 12;
	}
	
	public static void EquipeAJFoyt () {
		NomeEquipe="A. J. Foyt Racing";
		Sede = "Waller, Texas";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_AJ Foyt.png";
		fundacao = 1965;
		Orcamento = 11;
	}
	
	public static void EquipeAndretti () {
		NomeEquipe="Andretti Global";
		Sede = "Fishers, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Andretti.png";
		fundacao = 1993;
		Orcamento = 35;
	}
	
	public static void EquipeArrowMcLaren () {
		NomeEquipe="Arrow McLaren";
		Sede = "Indianápolis, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Arrow McLaren.png";
		fundacao = 2001;
		Orcamento = 25;
	}
	
	public static void EquipeChipGanassi () {
		NomeEquipe="Chip Ganassi Racing";
		Sede = "Indianápolis, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Chip Ganassi.png";
		fundacao = 1990;
		Orcamento = 32;
	}
	
	public static void EquipeDaleCoyne () {
		NomeEquipe="Dale Coyne Racing";
		Sede = "Plainfield, Illinois";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Dale Coyne.png";
		fundacao = 1984;
		Orcamento = 10;
	}	
	
	public static void EquipeDreyer () {
		NomeEquipe="Dreyer & Reinbold Racing";
		Sede = "Carmel, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Dreyer.png";
		fundacao = 1999;
		Orcamento = 12;
	}
	
	public static void EquipeEdCarpenter() {
		NomeEquipe="Ed Carpenter Racing";
		Sede = "Indianápolis, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_EdCarpterRacing.png";
		fundacao = 2012;
		Orcamento = 8;
	}
	
	public static void EquipeJuncos() {
		NomeEquipe="Juncos Hollinger Racing";
		Sede = "Indianápolis, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Juncos.png";
		fundacao = 2017;
		Orcamento = 9.5;
	}
	
	public static void EquipeMeyer() {
		NomeEquipe="Meyer Shank Racing";
		Sede = "Pataskala, Ohio";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Meyer Shank.png";
		fundacao = 2017;
		Orcamento = 10;
	}
	
	public static void EquipeRahal() {
		NomeEquipe="Rahal Letterman Lanigan Racing";
		Sede = "Brownsburg, Indiana";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Rahal.png";
		fundacao = 1992;
		Orcamento = 12;
	}
	
//Equipes Nascar/Indy	
	
	public static void EquipePenske() {
		NomeEquipe="Team Penske";
		Sede = "Mooresville, Carolina do Norte";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Equipe_Indy_Penske.png";
		fundacao = 1966;
		Orcamento = 35;
	}
	
//Equipes NASCAR
	
	public static void EquipeHendrick() {
		NomeEquipe="Hendrick Motorsports";
		Sede = "Charlotte, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarHendrick.png";
		fundacao = 1984;
		Orcamento = 40;
	}

	public static void EquipeJTG() {
		NomeEquipe="JTG Daugherty Racing";
		Sede = "Harrisburg, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarJTG.png";
		fundacao = 1995;
		Orcamento = 10;
	}
	
	public static void EquipeKaulig() {
		NomeEquipe="Kaulig Racing";
		Sede = "Welcome, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarKaulig.png";
		fundacao = 2016;
		Orcamento = 15;
	}
	
	public static void EquipeRCR() {
		NomeEquipe="Richard Childress Racing";
		Sede = "Welcome, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarRCR.png";
		fundacao = 1969;
		Orcamento = 23;
	}
	
	public static void EquipeSpire() {
		NomeEquipe="Spire Motorsports";
		Sede = "Mooresville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarSpire.png";
		fundacao = 2018;
		Orcamento = 12;
	}
	
	public static void EquipeTrackhouse() {
		NomeEquipe="Trackhouse Racing";
		Sede = "Concord, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarTrackhouse.png";
		fundacao = 2020;
		Orcamento = 15;
	}	
	
	public static void EquipeFrontRow() {
		NomeEquipe="Front Row Motorsports";
		Sede = "Mooresville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarFrontRow.png";
		fundacao = 2004;
		Orcamento = 13;
	}
	
	public static void EquipeRFK() {
		NomeEquipe="Roush Fenway Keselowski Racing";
		Sede = "Concord, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarRFK.png";
		fundacao = 1988;
		Orcamento = 20;
	}
	
	public static void EquipeRickWare() {
		NomeEquipe="Rick Ware Racing";
		Sede = "Concord, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarRickWare.png";
		fundacao = 1998;
		Orcamento = 13;
	}
	
	public static void EquipeStewartHaas() {
		NomeEquipe="Stewart–Haas Racing";
		Sede = "Kannapolis, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarStewartHaas.png";
		fundacao = 2002;
		Orcamento = 20;
	}
	
	public static void EquipeWoodBrothers() {
		NomeEquipe="Wood Brothers Racing";
		Sede = "Mooresville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarWoodBrothers.png";
		fundacao = 1950;
		Orcamento = 13;
	}	
	
	public static void Equipe23XI() {
		NomeEquipe="23XI Racing";
		Sede = "Mooresville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/Nascar23XI.png";
		fundacao = 2020;
		Orcamento = 20;
	}
	
	public static void EquipeJoeGibbs() {
		NomeEquipe="Joe Gibbs Racing";
		Sede = "Huntersville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarJoeGibbs.png";
		fundacao = 1992;
		Orcamento = 30;
	}
	
	public static void EquipeLegacy() {
		NomeEquipe="Legacy Motor Club";
		Sede = "Statesville, North Carolina";
		BandeiraSede = "/Imagens/Bandeira EUA.png";
		BandeiraEquipe = "/Imagens/Bandeira EUA.png";
		LogoEquipe = "/Imagens/NascarLegacy.png";
		fundacao = 2021;
		Orcamento = 12;
	}
	
//Pilotos
	
	public static void SemPiloto () {
		Nome="";
		BandeiraP = "";
		ano_nasc = 0;
		n_piloto=100;
		Pais = "BRANCA";
	}

//INDY
	
	public static void JosefNewgarden () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Josef Newgarden";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1990;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=2;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ScottMcLaughlin () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Scott McLaughlin";
		BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
		ano_nasc = 1993;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=3;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Nova Zelandia";
	}
	
	public static void WillPower () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Will Power";
		BandeiraP = "/Imagens/Bandeira Australia.png";
		ano_nasc = 1981;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=12;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Australia";
	}
	
	public static void GrahamRahal () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Graham Rahal";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1989;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=15;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}

	public static void PietroFittipaldiIndy () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Pietro Fittipaldi";
		BandeiraP = "/Imagens/Bandeira BR.png";
		ano_nasc = 1996;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=30;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Brasil";
	}

	public static void ChristianLundgaard () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Christian Lundgaard";
		BandeiraP = "/Imagens/Bandeira Dinamarca.png";
		ano_nasc = 2001;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=45;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Dinamarca";
	}

	public static void TakumaSatoIndy () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Takuma Sato";
		BandeiraP = "/Imagens/Bandeira Japao.png";
		ano_nasc = 1977;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=75;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Japao";
	}
	
	public static void HelioCastroneves () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Hélio Castroneves";
		BandeiraP = "/Imagens/Bandeira BR.png";
		ano_nasc = 1975;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=6;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Brasil";
	}
	
	public static void FelixRosenqvist () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Felix Rosenqvist";
		BandeiraP = "/Imagens/Bandeira Suecia.png";
		ano_nasc = 1991;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=60;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Suecia";
	}
	
	public static void TomBlomqvist () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Tom Blomqvist";
		BandeiraP = "/Imagens/Bandeira UK.png";
		ano_nasc =1993;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=66;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Reino Unido";
	}
	
	public static void RomainGrosjean () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Romain Grosjean";
		BandeiraP = "/Imagens/Bandeira Franca.png";
		ano_nasc = 1986;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=77;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Franca";
	}
	
	public static void AgustinCanapino () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Agustín Canapino";
		BandeiraP = "/Imagens/Bandeira Argentina.png";
		ano_nasc = 1990;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=78;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Argentina";
	}
	
	public static void EdCarpenter () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ed Carpenter";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1980;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=20;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}

	public static void RinusVeeKay () {
	
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	Nome="Rinus VeeKay";
	BandeiraP = "/Imagens/Bandeira Holanda.png";
	ano_nasc = 2000;
	
	if(SelecionarEquipe.ano == 2024) {
		n_piloto=21;
	}else if(SelecionarEquipe.ano == 2023) {
		n_piloto=100;
	}
	Pais = "Holanda";
}
	
	public static void RyanHunterReay () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ryan Hunter-Reay";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1998;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=24;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ConorDaly () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Conor Daly";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1991;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=25;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void JackHarvey () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Jack Harvey";
		BandeiraP = "/Imagens/Bandeira UK.png";
		ano_nasc = 1993;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=18;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Reino Unido";
	}
	
	public static void ColinBraun () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Colin Braun";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1988;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=51;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void KyffinSimpson () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Kyffin Simpson";
		BandeiraP = "/Imagens/Bandeira Caima.png";
		ano_nasc = 2004;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=4;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Caima";
	}	
	
	public static void LinusLundqvist () {	
	
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Linus Lundqvist";
		BandeiraP = "/Imagens/Bandeira Suecia.png";
		ano_nasc = 1999;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=8;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Suecia";
	}	
	
	public static void ScottDixon () {	
	
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Scott Dixon";
		BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
		ano_nasc = 1980;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=9;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Nova Zelandia";
	}	
	
	public static void MarcusArmstrong () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Marcus Armstrong";
		BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
		ano_nasc = 2000;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=11;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Nova Zelandia";
	}	
	
	public static void MarcusEricssonIndy () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Marcus Ericsson";
		BandeiraP = "/Imagens/Bandeira Suecia.png";
		ano_nasc = 1990;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=28;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Suecia";
	}
	
	public static void ColtonHerta () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		Nome="Colton Herta";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 2000;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=26;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void KyleKirkwood () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		Nome="Kyle Kirkwood";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1998;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=27;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}

	public static void MarcoAndretti () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		Nome="Marco Andretti";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1987;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=98;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}

	public static void RichardChadEnerson () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		Nome="Richard Chad 'RC' Enerson";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1997;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=50;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void SantinoFerrucci () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Santino Ferrucci";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1998;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=14;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}

	public static void StingRayRobb () {
	
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Sting Ray Robb";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 2001;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=41;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void AlexPalouIndy () {	
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Álex Palou";
		BandeiraP = "/Imagens/Bandeira Espanha.png";
		ano_nasc = 1997;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=10;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Espanha";
	}

	public static void PatricioOWardFIndy () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Patricio O'Ward";
		BandeiraP = "/Imagens/Bandeira Mexico.png";
		ano_nasc = 1999;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=5;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Mexico";
	}		
	
	public static void DavidMalukas () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="David Malukas";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 2001;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=6;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void AlexanderRossiIndy () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Alexander Rossi";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1991;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=7;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
		
	public static void KyleLarsonIndy () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Kyle Larson";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1992;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=17;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	

//NASCAR	
	
	public static void KyleLarsonNASCAR () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Kyle Larson";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1992;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=5;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ChaseElliott () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="ChaseElliott";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1995;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=9;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void WilliamByron () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="William Byron";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1997;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=24;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void AlexBowman () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Alex Bowman";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1993;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=48;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void AJAlmendinger () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="A. J. Allmendinger";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1981;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=13;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ShanevanGisbergen () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="	Shane van Gisbergen";
		BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
		ano_nasc = 1989;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=16;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Nova Zelandia";
	}
	
	public static void DanielHemric () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Daniel Hemric";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1991;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=31;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void AustinDillon () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Austin Dillon";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1990;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=3;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void KyleBusch() {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Kyle Busch";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1985;
		if(SelecionarEquipe.ano >= 2023) {
			n_piloto=8;
		}else if(SelecionarEquipe.ano <= 2022) {
			n_piloto=18;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void CoreyLaJoie () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Corey LaJoie";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1991;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=7;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void ZaneSmith () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Zane Smith";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1999;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=71;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void CarsonHocevar () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Carson Hocevar";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 2003;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=77;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void RossChastain () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ross Chastain";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1992;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=1;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void DanielSuarez () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Daniel Suárez";
		BandeiraP = "/Imagens/Bandeira Mexico.png";
		ano_nasc = 1992;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=99;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Mexico";
	}
	
	public static void MichaelMcDowell () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Michael McDowell";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1984;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=34;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ToddGilliland () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Todd Gilliland";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 2000;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=38;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void BradKeselowski () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Brad Keselowski";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1984;
		if(SelecionarEquipe.ano >= 2023) {
			n_piloto=6;
		}else if(SelecionarEquipe.ano <= 2022) {
			n_piloto=2;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ChrisBuescher () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Chris Buescher";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1992;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=17;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void JustinHaley () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Justin Haley";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1999;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=51;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void KazGrala () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Kaz Grala";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1998;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=15;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void JoshBerry () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Josh Berry";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1990;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=4;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ChaseBriscoe () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Chase Briscoe";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1994;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=14;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void NoahGragson () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Noah Gragson";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1998;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=10;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void RyanPreece () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ryan Preece";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1990;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=41;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void AustinCindric () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Austin Cindric";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1998;
		if(SelecionarEquipe.ano >= 2023) {
			n_piloto=2;
		}else if(SelecionarEquipe.ano <= 2022) {
			n_piloto=33;
		}
		Pais = "Estados Unidos";
	}
	
	public static void RyanBlaney () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ryan Blaney";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1993;
		if(SelecionarEquipe.ano >= 2018) {
			n_piloto=12;
		}else if(SelecionarEquipe.ano <= 2017) {
			n_piloto=21;
		}
		Pais = "Estados Unidos";
	}	

	public static void JoeyLogano () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Joey Logano";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1990;
		if(SelecionarEquipe.ano >= 2013) {
			n_piloto=22;
		}else if(SelecionarEquipe.ano <= 2012) {
			n_piloto=20;
		}
		Pais = "Estados Unidos";
	}	
		
	public static void HarrisonBurton () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Harrison Burton";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 2000;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=21;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void BubbaWallace () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Bubba Wallace";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1993;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=23;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}	
	
	public static void TylerReddick () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Tyler Reddick";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1996;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=45;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void DennyHamlin () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Denny Hamlin";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1980;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=11;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void MartinTruexJr () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Martin Truex Jr.";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1980;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=19;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ChristopherBell () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Christopher Bell";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1994;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=20;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void TyGibbs () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ty Gibbs";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 2002;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=54;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void JohnHunterNemechek () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="John Hunter Nemechek";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1997;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=42;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void ErikJones () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Erik Jones";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1996;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=43;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void JimmieJohnson () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Jimmie Johnson";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1975;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=84;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
	public static void RickyStenhouseJr () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Ricky Stenhouse Jr";
		BandeiraP = "/Imagens/Bandeira Estados Unidos.png";
		ano_nasc = 1987;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=47;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Estados Unidos";
	}
	
//F1
	
	/*public static void GuanyuZhou () {
		Nome="Guanyu Zhou";
		BandeiraP = "/Imagens/Bandeira China.png";
		ano_nasc = 1999;
		n_piloto=24;
		Pais = "China";
	}*/
	
	
	public static void GuanyuZhou() {
	    Nome = "Guanyu Zhou";
	    BandeiraP = "/Imagens/Bandeira China.png";
	    ano_nasc = 1999;
	    n_piloto = 24;
	    Pais = "China";

	    // Notas baseadas no desempenho na F1 e F2 até o momento
	    Experiencia = 6.5; // Piloto novato na F1, com pouca experiência comparado a veteranos
	    Ritmo = 7.0; // Rápido, mas ainda tem dificuldades para se comparar com pilotos mais experientes
	    Agressividade = 6.5; // Mostrou alguma agressividade, mas sem ser excessivamente agressivo
	    Fisico = 7.0; // Boa condição física para a F1, mas sem se destacar
	    Largada = 7.0; // Consistente em largadas, mas sem grandes resultados
	    Ultrapassagem = 6.5; // Capaz de ultrapassagens, mas precisa melhorar sua consistência
	    Hab_Chuva = 6.0; // Não teve grande destaque em condições de chuva até agora
	    Hab_Rua = 6.5; // Competente em circuitos de rua, mas sem grandes performances
	    Hab_Misto = 7.0; // Capaz em circuitos tradicionais, mas ainda precisa se adaptar totalmente à F1
	    Hab_Oval = 5.0; // Sem experiência profissional em ovais

	    // Calculando a variável Over (soma das notas dividida por 10)
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void ValtteriBottas() {
	    Nome = "Valtteri Bottas";
	    BandeiraP = "/Imagens/Bandeira Finlandia.png";
	    ano_nasc = 1989;
	    n_piloto = 77;
	    Pais = "Finlandia";

	    // Notas baseadas no desempenho na F1
	    Experiencia = 9.5; // Muitos anos de experiência na F1, com várias vitórias e pódios
	    Ritmo = 8.5; // Rápido, especialmente em qualificação, mas com variação em corridas
	    Agressividade = 7.5; // Geralmente conservador, mas pode ser agressivo quando necessário
	    Fisico = 8.5; // Boa condição física, preparada para corridas longas
	    Largada = 8.0; // Consistente em largadas, mas não é o melhor do grid
	    Ultrapassagem = 8.0; // Bom em ultrapassagens, especialmente quando está em ritmo forte
	    Hab_Chuva = 7.5; // Não tem destaque grande, mas é competente na chuva
	    Hab_Rua = 7.0; // Boa performance em pistas de rua, mas sem grandes vitórias
	    Hab_Misto = 8.5; // Forte em pistas tradicionais, onde obteve seus melhores resultados
	    Hab_Oval = 6.0; // Nunca competiu em ovais profissionalmente

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void ZaneMaloney() {
	    Nome = "Zane Maloney";
	    BandeiraP = "/Imagens/Bandeira Barbados.png";
	    ano_nasc = 2003;
	    n_piloto = 100;
	    Pais = "Barbados";

	    // Notas baseadas na experiência de Zane Maloney na F2 até agora
	    Experiencia = 5.5; // Está começando a carreira na F2, sem muito histórico na F1
	    Ritmo = 7.0; // Mostrou um bom ritmo nas corridas de F2, mas ainda em fase de adaptação
	    Agressividade = 7.5; // Agressivo e competitivo, principalmente em disputas fechadas
	    Fisico = 7.5; // Bom preparo físico para o nível da F2
	    Largada = 7.0; // Competente, mas com variações em largadas
	    Ultrapassagem = 7.0; // Capaz de realizar boas ultrapassagens, mas precisa de mais consistência
	    Hab_Chuva = 6.5; // Sem muitos destaques na chuva até o momento
	    Hab_Rua = 7.0; // Bom em circuitos de rua, mas ainda precisa se provar
	    Hab_Misto = 7.5; // Competente em circuitos tradicionais
	    Hab_Oval = 5.0; // Sem experiência em circuitos ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void ThéoPourchaire() {
	    Nome = "Théo Pourchaire";
	    BandeiraP = "/Imagens/Bandeira Franca.png";
	    ano_nasc = 2003;
	    n_piloto = 98;
	    Pais = "Franca";

	    // Notas baseadas na carreira de Pourchaire na F2 e seu desempenho geral
	    Experiencia = 7.5; // Tem uma boa experiência na F2 e está começando a se destacar
	    Ritmo = 8.0; // Muito rápido, principalmente em corridas de longa duração
	    Agressividade = 7.5; // Combina inteligência e agressividade nas disputas
	    Fisico = 7.5; // Bem preparado, mas ainda jovem e em fase de desenvolvimento
	    Largada = 7.5; // Boa consistência em largadas, com alguns ganhos importantes
	    Ultrapassagem = 7.5; // Hábil em ultrapassagens, principalmente nas disputas mais fechadas
	    Hab_Chuva = 7.0; // Mostrou bom desempenho, mas sem performances de destaque
	    Hab_Rua = 7.5; // Competente em pistas de rua, com resultados positivos
	    Hab_Misto = 8.0; // Muito forte em circuitos tradicionais e mistos
	    Hab_Oval = 5.0; // Sem experiência em ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void MaxVerstappen() {
	    Nome = "Max Verstappen";
	    BandeiraP = "/Imagens/Bandeira Holanda.png";
	    ano_nasc = 1997;
	    n_piloto = 1;
	    Pais = "Holanda";

	    // Notas baseadas no desempenho na F1
	    Experiencia = 10.0; // Um dos pilotos mais experientes e dominantes da atual geração
	    Ritmo = 10.0; // Atualmente, o piloto mais rápido e dominante da F1
	    Agressividade = 9.5; // Extremamente agressivo, sem medo de fazer manobras ousadas
	    Fisico = 9.5; // Excelente preparação física, capaz de lidar com as exigências físicas da F1
	    Largada = 9.0; // Consistentemente bom em largadas, mas não é seu ponto mais forte
	    Ultrapassagem = 10.0; // Mestre nas ultrapassagens, sempre com estratégias agressivas e eficazes
	    Hab_Chuva = 9.5; // Excepcional na chuva, com várias vitórias e pódios em condições adversas
	    Hab_Rua = 8.5; // Muito bom em pistas de rua, embora não tenha tantas vitórias quanto em circuitos tradicionais
	    Hab_Misto = 9.5; // Forte em diversos tipos de pista, com vitórias consistentes
	    Hab_Oval = 5.5; // Sem experiência significativa em circuitos ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void SergioPerez() {
	    Nome = "Sergio Perez";
	    BandeiraP = "/Imagens/Bandeira Mexico.png";
	    ano_nasc = 1990;
	    n_piloto = 11;
	    Pais = "Mexico";

	    // Notas baseadas no desempenho na F1
	    Experiencia = 9.0; // Experiência sólida na F1, com vitórias importantes e um bom histórico
	    Ritmo = 8.5; // Competitivo, mas geralmente fica atrás de Max Verstappen
	    Agressividade = 8.5; // Boa agressividade, especialmente em corridas com disputas intensas
	    Fisico = 8.5; // Boa preparação física, capaz de lidar com a pressão nas corridas
	    Largada = 8.0; // Consistentemente bom em largadas, mas não é o melhor
	    Ultrapassagem = 8.0; // Bom em ultrapassagens, especialmente em corridas com mais disputas
	    Hab_Chuva = 8.5; // Competente na chuva, com boas performances
	    Hab_Rua = 8.0; // Bom em circuitos de rua, com algumas vitórias notáveis
	    Hab_Misto = 8.5; // Forte em pistas tradicionais, com várias boas corridas
	    Hab_Oval = 6.0; // Não tem muita experiência com circuitos ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void LiamLawson() {
	    Nome = "Liam Lawson";
	    BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
	    ano_nasc = 2002;
	    n_piloto = 40;
	    Pais = "Nova Zelandia";

	    // Notas baseadas na experiência de Lawson na F2 e na sua experiência na F1 até o momento
	    Experiencia = 5.5; // Ainda está começando sua carreira na F1, com um bom potencial
	    Ritmo = 7.5; // Demonstrou bom ritmo em sua curta passagem pela F1
	    Agressividade = 7.0; // Capaz de ser agressivo, mas ainda precisa se ajustar ao nível da F1
	    Fisico = 7.5; // Bem preparado fisicamente, mas ainda em desenvolvimento
	    Largada = 7.0; // Competente em largadas, mas ainda pode melhorar
	    Ultrapassagem = 7.0; // Mostrou habilidade, mas sem grandes ultrapassagens
	    Hab_Chuva = 6.5; // Não teve destaque na chuva até agora
	    Hab_Rua = 7.0; // Boa performance em pistas de rua, mas precisa evoluir
	    Hab_Misto = 7.5; // Bom desempenho em circuitos tradicionais
	    Hab_Oval = 5.0; // Sem experiência em ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void EstebanOcon() {
	    Nome = "Esteban Ocon";
	    BandeiraP = "/Imagens/Bandeira Franca.png";
	    ano_nasc = 1996;
	    n_piloto = 31;
	    Pais = "Franca";

	    // Notas baseadas no desempenho na F1
	    Experiencia = 8.5; // Com bastante experiência na F1, mas sem grandes vitórias
	    Ritmo = 8.5; // Competitivo, especialmente em classificações
	    Agressividade = 8.0; // Focado e inteligente, mas não tão agressivo quanto outros pilotos
	    Fisico = 8.0; // Bem preparado fisicamente
	    Largada = 8.0; // Consistente em largadas, mas não é o melhor
	    Ultrapassagem = 8.5; // Bom em ultrapassagens, especialmente em corridas com disputas fechadas
	    Hab_Chuva = 7.5; // Competente em condições de chuva, mas sem grandes resultados
	    Hab_Rua = 7.5; // Bom em circuitos de rua, com algumas boas corridas
	    Hab_Misto = 8.0; // Bom em pistas mistas, especialmente em corridas de resistência
	    Hab_Oval = 5.0; // Sem experiência em ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void PierreGasly() {
	    Nome = "Pierre Gasly";
	    BandeiraP = "/Imagens/Bandeira Franca.png";
	    ano_nasc = 1996;
	    n_piloto = 10;
	    Pais = "Franca";

	    // Notas baseadas no desempenho na F1
	    Experiencia = 8.0; // Com várias temporadas na F1, mas sem grandes vitórias
	    Ritmo = 8.5; // Competitivo, especialmente em corridas de longa duração
	    Agressividade = 8.0; // Muito agressivo, mas equilibrado
	    Fisico = 8.0; // Bem preparado fisicamente
	    Largada = 8.0; // Bom em largadas, mas não o melhor
	    Ultrapassagem = 8.5; // Hábil em ultrapassagens, com bom ritmo em tráfego
	    Hab_Chuva = 8.0; // Competente na chuva, com bons resultados em condições adversas
	    Hab_Rua = 7.5; // Bom em circuitos de rua, mas sem grandes vitórias
	    Hab_Misto = 8.0; // Forte em circuitos tradicionais, com várias boas corridas
	    Hab_Oval = 5.0; // Sem experiência significativa em ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void JackDoohan() {
	    Nome = "Jack Doohan";
	    BandeiraP = "/Imagens/Bandeira Nova Zelandia.png";
	    ano_nasc = 2003;
	    n_piloto = 82;
	    Pais = "Nova Zelandia";

	    // Notas baseadas na experiência de Doohan na F2 e seus desenvolvimentos até agora
	    Experiencia = 5.0; // Ainda em desenvolvimento na F2, sem experiência na F1
	    Ritmo = 7.0; // Bom ritmo na F2, mas ainda é uma incógnita na F1
	    Agressividade = 7.5; // Agressivo e competitivo na F2
	    Fisico = 7.5; // Boa preparação física para a F2
	    Largada = 7.0; // Competente, mas ainda precisa melhorar
	    Ultrapassagem = 7.5; // Capaz de boas ultrapassagens, mas ainda não é constante
	    Hab_Chuva = 6.5; // Sem destaque na chuva até agora
	    Hab_Rua = 7.0; // Bom em circuitos de rua, mas ainda sem grandes resultados
	    Hab_Misto = 7.5; // Competente em circuitos tradicionais
	    Hab_Oval = 5.0; // Sem experiência em ovais

	    // Calculando a variável Over
	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void CharlesLeclerc() {
	    Nome = "Charles Leclerc";
	    BandeiraP = "/Imagens/Bandeira Monaco.png";
	    ano_nasc = 1996;
	    n_piloto = 16;
	    Pais = "Monaco";
	    
	    // Notas baseadas no desempenho na F1
	    Experiencia = 9.0;
	    Ritmo = 9.5;
	    Agressividade = 8.5;
	    Fisico = 8.5;
	    Largada = 8.5;
	    Ultrapassagem = 8.5;
	    Hab_Chuva = 8.0;
	    Hab_Rua = 8.5;
	    Hab_Misto = 9.0;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void CarlosSainzJr() {
	    Nome = "Carlos Sainz Jr";
	    BandeiraP = "/Imagens/Bandeira Espanha.png";
	    ano_nasc = 1994;
	    n_piloto = 55;
	    Pais = "Espanha";
	    
	    // Notas baseadas no desempenho na F1
	    Experiencia = 8.0;
	    Ritmo = 8.5;
	    Agressividade = 8.0;
	    Fisico = 8.0;
	    Largada = 8.0;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 8.5;
	    Hab_Misto = 8.0;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void AntonioGiovinazzi() {
	    Nome = "Antonio Giovinazzi";
	    BandeiraP = "/Imagens/Bandeira Italia.png";
	    ano_nasc = 1993;
	    n_piloto = 99;
	    Pais = "Italia";
	    
	    // Notas baseadas no desempenho na F1 e experiências limitadas
	    Experiencia = 6.5;
	    Ritmo = 7.0;
	    Agressividade = 7.0;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void RobertShwartzman() {
	    Nome = "Robert Shwartzman";
	    BandeiraP = "/Imagens/Bandeira Israel.png";
	    ano_nasc = 1999;
	    n_piloto = 39;
	    Pais = "Israel";
	    
	    // Notas baseadas em sua performance nas categorias de base e sua estreia na F1
	    Experiencia = 6.0;
	    Ritmo = 7.0;
	    Agressividade = 7.5;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.0;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void OliverBearman() {
	    Nome = "Oliver Bearman";
	    BandeiraP = "/Imagens/Bandeira UK.png";
	    ano_nasc = 2005;
	    n_piloto = 38;
	    Pais = "Reino Unido";
	    
	    // Notas baseadas em seu desempenho nas categorias de base (F2)
	    Experiencia = 5.0;
	    Ritmo = 7.5;
	    Agressividade = 7.0;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void FernandoAlonso() {
	    Nome = "Fernando Alonso";
	    BandeiraP = "/Imagens/Bandeira Espanha.png";
	    ano_nasc = 1981;
	    n_piloto = 14;
	    Pais = "Espanha";
	    
	    // Notas baseadas na extensa carreira e experiência
	    Experiencia = 10.0;
	    Ritmo = 9.5;
	    Agressividade = 9.5;
	    Fisico = 9.5;
	    Largada = 8.5;
	    Ultrapassagem = 9.0;
	    Hab_Chuva = 9.5;
	    Hab_Rua = 9.0;
	    Hab_Misto = 9.5;
	    Hab_Oval = 6.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void LanceStroll() {
	    Nome = "Lance Stroll";
	    BandeiraP = "/Imagens/Bandeira Canada.png";
	    ano_nasc = 1990;
	    n_piloto = 18;
	    Pais = "Canada";
	    
	    // Notas baseadas na performance na F1
	    Experiencia = 7.5;
	    Ritmo = 7.5;
	    Agressividade = 7.0;
	    Fisico = 7.0;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void FelipeDrugovich() {
	    Nome = "Felipe Drugovich";
	    BandeiraP = "/Imagens/Bandeira BR.png";
	    ano_nasc = 2000;
	    n_piloto = 34;
	    Pais = "Brasil";
	    
	    // Notas baseadas na sua ascensão nas categorias de base (F2)
	    Experiencia = 6.0;
	    Ritmo = 7.0;
	    Agressividade = 7.5;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 6.0;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.0;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void LewisHamilton() {
	    Nome = "Lewis Hamilton";
	    BandeiraP = "/Imagens/Bandeira UK.png";
	    ano_nasc = 1985;
	    n_piloto = 44;
	    Pais = "Reino Unido";
	    
	    // Notas baseadas em sua carreira histórica na F1
	    Experiencia = 10.0;
	    Ritmo = 9.5;
	    Agressividade = 9.5;
	    Fisico = 9.5;
	    Largada = 9.5;
	    Ultrapassagem = 9.5;
	    Hab_Chuva = 9.0;
	    Hab_Rua = 9.5;
	    Hab_Misto = 9.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void GeorgeRussell() {
	    Nome = "George Russell";
	    BandeiraP = "/Imagens/Bandeira UK.png";
	    ano_nasc = 1998;
	    n_piloto = 63;
	    Pais = "Reino Unido";
	    
	    // Notas baseadas no desempenho recente e sua ascensão na F1
	    Experiencia = 8.0;
	    Ritmo = 9.0;
	    Agressividade = 8.0;
	    Fisico = 8.5;
	    Largada = 8.0;
	    Ultrapassagem = 8.5;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 8.0;
	    Hab_Misto = 8.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}
	 
	public static void MickSchumacher() {
	    Nome = "Mick Schumacher";
	    BandeiraP = "/Imagens/Bandeira Alemanha.png";
	    ano_nasc = 1995;
	    n_piloto = 47;
	    Pais = "Alemanha";
	    
	    // Notas baseadas no desempenho nas categorias e F1
	    Experiencia = 6.5;
	    Ritmo = 7.5;
	    Agressividade = 7.0;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.0;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.0;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void FrederikVesti() {
	    Nome = "Frederik Vesti";
	    BandeiraP = "/Imagens/Bandeira Dinamarca.png";
	    ano_nasc = 2002;
	    n_piloto = 21;
	    Pais = "Dinamarca";
	    
	    // Notas baseadas nas categorias de base (F2) e desempenho recente
	    Experiencia = 5.5;
	    Ritmo = 7.0;
	    Agressividade = 7.5;
	    Fisico = 7.0;
	    Largada = 7.0;
	    Ultrapassagem = 7.0;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.0;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void LoganSargeant() {
	    Nome = "Logan Sargeant";
	    BandeiraP = "/Imagens/Bandeira EUA.png";
	    ano_nasc = 2000;
	    n_piloto = 2;
	    Pais = "Estados Unidos";
	    
	    // Notas baseadas em seu desempenho na F1 e ascensão nas categorias de base
	    Experiencia = 6.5;
	    Ritmo = 7.0;
	    Agressividade = 7.0;
	    Fisico = 7.0;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 6.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void AlexanderAlbon() {
	    Nome = "Alexander Albon";
	    BandeiraP = "/Imagens/Bandeira Tailandia.png";
	    ano_nasc = 1996;
	    n_piloto = 23;
	    Pais = "Tailandia";
	    
	    // Notas baseadas na sua performance na F1
	    Experiencia = 8.0;
	    Ritmo = 8.5;
	    Agressividade = 7.5;
	    Fisico = 8.0;
	    Largada = 8.0;
	    Ultrapassagem = 8.5;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 8.0;
	    Hab_Misto = 8.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void YukiTsunoda() {
	    Nome = "Yuki Tsunoda";
	    BandeiraP = "/Imagens/Bandeira Japao.png";
	    ano_nasc = 2000;
	    n_piloto = 22;
	    Pais = "Japao";
	    
	    // Notas baseadas na sua experiência na F1
	    Experiencia = 7.0;
	    Ritmo = 7.5;
	    Agressividade = 8.0;
	    Fisico = 7.5;
	    Largada = 7.5;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}
	 
	public static void DanielRicciardo() {
	    Nome = "Daniel Ricciardo";
	    BandeiraP = "/Imagens/Bandeira Australia.png";
	    ano_nasc = 1989;
	    n_piloto = 3;
	    Pais = "Australia";
	    
	    // Notas baseadas na sua experiência na F1
	    Experiencia = 9.0;
	    Ritmo = 8.5;
	    Agressividade = 8.0;
	    Fisico = 8.5;
	    Largada = 8.0;
	    Ultrapassagem = 8.5;
	    Hab_Chuva = 8.0;
	    Hab_Rua = 7.5;
	    Hab_Misto = 8.0;
	    Hab_Oval = 6.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void KevinMagnussen() {    
	    Nome = "Kevin Magnussen";
	    BandeiraP = "/Imagens/Bandeira Dinamarca.png";
	    ano_nasc = 1992;
	    n_piloto = 20;
	    Pais = "Dinamarca";
	    
	    // Notas baseadas no desempenho na F1
	    Experiencia = 8.0;
	    Ritmo = 7.5;
	    Agressividade = 8.0;
	    Fisico = 7.5;
	    Largada = 8.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 7.0;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.5;
	    Hab_Oval = 6.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void NicoHulkenberg() {    
	    Nome = "Nico Hülkenberg";
	    BandeiraP = "/Imagens/Bandeira Alemanha.png";
	    ano_nasc = 1988;
	    n_piloto = 27;
	    Pais = "Alemanha";
	    
	    // Notas baseadas na experiência na F1
	    Experiencia = 9.5;
	    Ritmo = 8.5;
	    Agressividade = 7.5;
	    Fisico = 8.5;
	    Largada = 8.0;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 8.5;
	    Hab_Rua = 8.0;
	    Hab_Misto = 8.0;
	    Hab_Oval = 6.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void PietroFittipaldiF1() {    
	    Nome = "Pietro Fittipaldi";
	    BandeiraP = "/Imagens/Bandeira BR.png";
	    ano_nasc = 1996;
	    n_piloto = 51;
	    Pais = "Brasil";
	    
	    // Notas baseadas nas corridas F1 e experiência
	    Experiencia = 6.5;
	    Ritmo = 7.0;
	    Agressividade = 7.5;
	    Fisico = 7.0;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 7.0;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 6.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void LandoNorris() {    
	    Nome = "Lando Norris";
	    BandeiraP = "/Imagens/Bandeira UK.png";
	    ano_nasc = 1999;
	    n_piloto = 4;
	    Pais = "Reino Unido";
	    
	    // Notas baseadas no desempenho de Norris na F1
	    Experiencia = 8.5;
	    Ritmo = 8.5;
	    Agressividade = 8.0;
	    Fisico = 8.0;
	    Largada = 8.0;
	    Ultrapassagem = 8.5;
	    Hab_Chuva = 8.5;
	    Hab_Rua = 8.0;
	    Hab_Misto = 8.5;
	    Hab_Oval = 5.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void OscarPiastri() {    
	    Nome = "Oscar Piatri";
	    BandeiraP = "/Imagens/Bandeira Australia.png";
	    ano_nasc = 2002;
	    n_piloto = 81;
	    Pais = "Australia";
	    
	    // Notas baseadas nas performances iniciais na F1
	    Experiencia = 7.0;
	    Ritmo = 8.0;
	    Agressividade = 8.0;
	    Fisico = 8.0;
	    Largada = 8.5;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 8.0;
	    Hab_Misto = 8.0;
	    Hab_Oval = 5.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void RyoHirakawa() {    
	    Nome = "Ryo Hirakawa";
	    BandeiraP = "/Imagens/Bandeira Japao.png";
	    ano_nasc = 1994;
	    n_piloto = 100;
	    Pais = "Japao";
	    
	    // Notas baseadas na carreira em categorias como WEC e desempenho nas provas
	    Experiencia = 7.0;
	    Ritmo = 7.5;
	    Agressividade = 7.0;
	    Fisico = 7.0;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.0;
	    Hab_Oval = 6.0;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void PatricioOWardF1() {    
	    Nome = "Patricio O'Ward";
	    BandeiraP = "/Imagens/Bandeira Mexico.png";
	    ano_nasc = 1999;
	    n_piloto = 100;
	    Pais = "Mexico";
	    
	    // Notas baseadas nas corridas na Indy e desempenho
	    Experiencia = 7.5;
	    Ritmo = 8.0;
	    Agressividade = 7.5;
	    Fisico = 7.5;
	    Largada = 7.5;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 7.0;
	    Hab_Rua = 7.0;
	    Hab_Misto = 8.0;
	    Hab_Oval = 8.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void AlexPalouF1() {    
	    Nome = "Álex Palou";
	    BandeiraP = "/Imagens/Bandeira Espanha.png";
	    ano_nasc = 1997;
	    n_piloto = 100;
	    Pais = "Espanha";
	    
	    // Notas baseadas na experiência de Palou nas categorias de base e Indy
	    Experiencia = 7.5;
	    Ritmo = 8.0;
	    Agressividade = 7.5;
	    Fisico = 8.0;
	    Largada = 7.5;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 7.5;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 8.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void StoffelVandoorne() {    
	    Nome = "Stoffel Vandoorne";
	    BandeiraP = "/Imagens/Bandeira Belgica.png";
	    ano_nasc = 1992;
	    n_piloto = 2;
	    Pais = "Belgica";
	    
	    // Notas baseadas nas suas passagens pela F1 e Formula E
	    Experiencia = 8.0;
	    Ritmo = 7.5;
	    Agressividade = 7.0;
	    Fisico = 7.5;
	    Largada = 7.0;
	    Ultrapassagem = 7.5;
	    Hab_Chuva = 8.0;
	    Hab_Rua = 7.0;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	public static void SebastienBuemi() {    
	    Nome = "Sébastien Buemi";
	    BandeiraP = "/Imagens/Bandeira Suica.png";
	    ano_nasc = 1988;
	    n_piloto = 100;
	    Pais = "Suica";
	    
	    // Notas baseadas na sua experiência na F1 e em outras competições (Formula E, etc)
	    Experiencia = 8.5;
	    Ritmo = 8.0;
	    Agressividade = 7.5;
	    Fisico = 8.0;
	    Largada = 7.5;
	    Ultrapassagem = 8.0;
	    Hab_Chuva = 8.0;
	    Hab_Rua = 7.5;
	    Hab_Misto = 7.5;
	    Hab_Oval = 5.5;

	    Over = (Experiencia + Ritmo + Agressividade + Fisico + Largada + Ultrapassagem + Hab_Chuva + Hab_Rua + Hab_Misto + Hab_Oval) / 10;
	}

	
	//Falta arrumar anos e números
	public static void AlexanderRossiF1 () {	
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		Nome="Alexander Rossi";
		BandeiraP = "/Imagens/Bandeira EUA.png";
		ano_nasc = 1991;
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=100;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Suecia";
	}	
	
	public static void MarcusEricssonF1 () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		Nome="Marcus Ericsson";
		BandeiraP = "/Imagens/Bandeira Suecia.png";
		ano_nasc = 1990;
		
		if(SelecionarEquipe.ano == 2024) {
			n_piloto=100;
		}else if(SelecionarEquipe.ano == 2023) {
			n_piloto=100;
		}
		Pais = "Suecia";
	}
	
//Pistas
	
//Calendario2024	

//Pistas F1
		
	public static void Bahrain () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Dezembro de 2002";
		LocalPista = "Sakhir, Bahrain";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 70.000 pessoas";
		PaisPista = "/Imagens/Bandeira Bahrain.png";
		
		if(SelecionarEquipe.ano != 2010) {
			
			Curvas_Pista = "15 curvas";
			Voltas_Pista = 57;
			ComprimentoPista = "Comprimento de 5,412 mts";
			LayoutPista = "/Imagens/CircuitoBahrain.png";
			LayoutPistaPQ = "/Imagens/CircuitoBahrainPQ.png";
			
		}else if(SelecionarEquipe.ano == 2010) {
			
			Curvas_Pista = "23 curvas";
			Voltas_Pista = 49;
			ComprimentoPista = "Comprimento 6,299 mts";
			LayoutPista = "/Imagens/CircuitoBahrain2010.png";
			LayoutPistaPQ = "/Imagens/CircuitoBahrain2010PQ.png";
		}

	}
	
	public static void Jeddah () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Dezembro de 2021";
		LocalPista = "Jeddah, Arábia Saudita";
		TipoPista  = "Circuito de Rua Temporário";
		CapacidadePista = "";
		
			Curvas_Pista = "27 curvas";
			Voltas_Pista = 50;
			ComprimentoPista = "Comprimento de 6,175 mts";
			LayoutPista = "/Imagens/CircuitoJeddah.png";
			LayoutPistaPQ = "/Imagens/CircuitoJeddahPQ.png";
			PaisPista = "/Imagens/Bandeira Arabia Saudita.png";
	}
	
	public static void Melbourne () {
			
			Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
			Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
			
			InauguracaoPista = "Inaugurado em Novembro de 1953";
			LocalPista = "Melbourne, Vitória, Austrália";
			TipoPista  = "Circuito de Rua Permanente";
			CapacidadePista = "Capacidade de 80.000 pessoas";
			PaisPista = "/Imagens/Bandeira Australia.png";
			
			if(SelecionarEquipe.ano >= 2021) {
				
				Curvas_Pista = "14 curvas";
				Voltas_Pista = 58;
				ComprimentoPista = "Comprimento de 5,278 mts";
				LayoutPista = "/Imagens/CircuitoMelbourne2021+.png";
				LayoutPistaPQ = "/Imagens/CircuitoMelbourne2021+PQ.png";
				
			}else if(SelecionarEquipe.ano >= 1996 && SelecionarEquipe.ano <= 2020) {
				
				Curvas_Pista = "16 curvas";
				Voltas_Pista = 58;
				ComprimentoPista = "Comprimento de 5,303 mts";
				LayoutPista = "/Imagens/CircuitoMelbourne1996.2020.png";
				LayoutPistaPQ = "/Imagens/CircuitoMelbourne1996.2020PQ.png";
			}
	
		}
	
	public static void Baku () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2016";
		LocalPista = "Baku, Azerbaijão";
		TipoPista  = "Circuito de Rua Temporário";
		CapacidadePista = "";
		
			Curvas_Pista = "20 curvas";
			Voltas_Pista = 51;
			ComprimentoPista = "Comprimento de 6,003 mts";
			LayoutPista = "/Imagens/CircuitoBaku.png";
			LayoutPistaPQ = "/Imagens/CircuitoBakuPQ.png";
			PaisPista = "/Imagens/Bandeira Azerbaijao.png";
	}
	
	public static void Suzuka () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1962";
		LocalPista = "Suzuka, Mie, Japão";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 155.000 pessoas";
		
			Curvas_Pista = "18 curvas";
			Voltas_Pista = 53;
			ComprimentoPista = "Comprimento de 5,807 mts";
			LayoutPista = "/Imagens/CircuitoSuzuka.png";
			LayoutPistaPQ = "/Imagens/CircuitoSuzukaPQ.png";
			PaisPista = "/Imagens/Bandeira Japao.png";
		
	}

	public static void Shanghai() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Setembro de 2004";
		LocalPista = "Shanghai, China";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 200.000 pessoas";
		
			Curvas_Pista = "16 curvas";
			Voltas_Pista = 56;
			ComprimentoPista = "Comprimento de 5,451 mts";
			LayoutPista = "/Imagens/CircuitoShangai.png";
			LayoutPistaPQ = "/Imagens/CircuitoShangaiPQ.png";
			PaisPista = "/Imagens/Bandeira China.png";
		
	}
	
	public static void Miami() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2022";
		LocalPista = "	Miami Gardens, EUA";
		TipoPista  = "Circuito de Rua Temporário";
		CapacidadePista = "";
		
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 57;
			ComprimentoPista = "Comprimento de 5.412 mts";
			LayoutPista = "/Imagens/CircuitoMiami.png";
			LayoutPistaPQ = "/Imagens/CircuitoMiamiPQ.png";
			PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
	}
	
	public static void Monaco () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1929";
		LocalPista = "Monte Carlo, Mônaco";
		TipoPista  = "Circuito de Rua Temporário";
		CapacidadePista = "Capacidade de 37.000 pessoas";
		
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 78;
			ComprimentoPista = "Comprimento de 3,337 mts";
			LayoutPista = "/Imagens/CircuitoMonaco.png";
			LayoutPistaPQ = "/Imagens/CircuitoMonacoPQ.png";
			PaisPista = "/Imagens/Bandeira Monaco.png";
		
	}
	
	public static void Imola () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Novembro de 1953";
		LocalPista = "Ímola, Emília-Romanha, Itália";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 78.000 pessoas";
		PaisPista = "/Imagens/Bandeira Italia.png";
		
		if(SelecionarEquipe.ano >= 2008) {
			
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 63;
			ComprimentoPista = "Comprimento de 4,909 mts";
			LayoutPista = "/Imagens/CircuitoImola2008+.png";
			LayoutPistaPQ = "/Imagens/CircuitoImola2008+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 1995 && SelecionarEquipe.ano <= 2006) {
			
			Curvas_Pista = "17 curvas";
			Voltas_Pista = 62;
			ComprimentoPista = "Comprimento de 4,959 mts";
			LayoutPista = "/Imagens/CircuitoMelbourne1996.2020.png";
			LayoutPistaPQ = "/Imagens/CircuitoMelbourne1996.2020PQ.png";
		}

	}
	
	public static void Montreal () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1978";
		LocalPista = "Montreal, Québec, Canadá";
		TipoPista  = "Circuito Temporário/Permanente";
		CapacidadePista = "Capacidade de 100.000 pessoas";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 70;
			ComprimentoPista = "Comprimento de 4,361 mts";
			LayoutPista = "/Imagens/CircuitoMontreal.png";
			LayoutPistaPQ = "/Imagens/CircuitoMontrealPQ.png";
			PaisPista = "/Imagens/Bandeira Canada.png";
		
	}
	
	public static void Austria () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1969";
		LocalPista = "Spielberg, Estíria, Áustria";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 40.000 pessoas";
		PaisPista = "/Imagens/Bandeira Austria.png";
		
		if(SelecionarEquipe.ano >= 2016) {
			
			Curvas_Pista = "10 curvas";
			Voltas_Pista = 71;
			ComprimentoPista = "Comprimento de 4,318 mts";
			LayoutPista = "/Imagens/CircuitoAustria2016+.png";
			LayoutPistaPQ = "/Imagens/CircuitoAustria2016+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 1996 && SelecionarEquipe.ano <= 2004) {
			
			Curvas_Pista = "9 curvas";
			Voltas_Pista = 71;
			ComprimentoPista = "Comprimento de 4,326 mts";
			LayoutPista = "/Imagens/CircuitoAustria1996.2004.png";
			LayoutPistaPQ = "/Imagens/CircuitoAustria1996.2004PQ.png";
		}
		
	}
	
	public static void Catalunha() {
			
			Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
			Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
			
			InauguracaoPista = "Inaugurado em 1992";
			LocalPista = "Montmeló, Catalunha, Espanha";
			TipoPista  = "Circuito Misto Permanente";
			CapacidadePista = "Capacidade de 140.700 pessoas";
			PaisPista = "/Imagens/Bandeira Espanha.png";
			
			if(SelecionarEquipe.ano >= 2023) {
				
				Curvas_Pista = "14 curvas";
				Voltas_Pista = 66;
				ComprimentoPista = "Comprimento de 4.657 mts";
				LayoutPista = "/Imagens/CircuitoCatalunha2023+.png";
				LayoutPistaPQ = "/Imagens/CircuitoCatalunha2023+PQ.png";
				
			}else if(SelecionarEquipe.ano >= 2007 && SelecionarEquipe.ano <= 2022) {
				
				Curvas_Pista = "16 curvas";
				Voltas_Pista = 66;
				ComprimentoPista = "Comprimento de 4,655 mts";
				LayoutPista = "/Imagens/CircuitoCatalunha2007.2022.png";
				LayoutPistaPQ = "/Imagens/CircuitoCatalunha2007.2022PQ.png";
				
			}else if(SelecionarEquipe.ano >= 1995 && SelecionarEquipe.ano <= 2006) {
				
				Curvas_Pista = "14 curvas";
				Voltas_Pista = 66;
				ComprimentoPista = "Comprimento de 4,627 mts";
				LayoutPista = "/Imagens/CircuitoCatalunha1995.2006.png";
				LayoutPistaPQ = "/Imagens/CircuitoCatalunha1995.2006PQ.png";
				
			}
	
	}
	
	public static void Silverstone () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1948";
		LocalPista = "Northamptonshire, Inglaterra";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 150.000 pessoas";
		PaisPista = "/Imagens/Bandeira Reino Unido.png";
		
		if(SelecionarEquipe.ano >= 2010) {
			
			Curvas_Pista = "18 curvas";
			Voltas_Pista = 52;
			ComprimentoPista = "Comprimento de 5,891 mts";
			LayoutPista = "/Imagens/CircuitoSilverstone2010+.png";
			LayoutPistaPQ = "/Imagens/CircuitoSilverstone2010+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 1994 && SelecionarEquipe.ano <= 2009) {
			
			Curvas_Pista = "17 curvas";
			Voltas_Pista = 60;
			ComprimentoPista = "Comprimento de 5,141 mts";
			LayoutPista = "/Imagens/CircuitoAustria1994.2009.png";
			LayoutPistaPQ = "/Imagens/CircuitoAustria1994.2009PQ.png";
		}

	}
	
	public static void Hungaroring () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1985";
		LocalPista = "Mogyoród, Hungria";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 70.000 pessoas";
		PaisPista = "/Imagens/Bandeira Hungria.png";
		
		if(SelecionarEquipe.ano >= 2003) {
			
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 70;
			ComprimentoPista = "Comprimento de 4,381 mts";
			LayoutPista = "/Imagens/CircuitoHungaroring2003+.png";
			LayoutPistaPQ = "/Imagens/CircuitoHungaroring2003+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 1989 && SelecionarEquipe.ano <= 2002) {
			
			Curvas_Pista = "13 curvas";
			Voltas_Pista = 77;
			ComprimentoPista = "Comprimento de 3,975 mts";
			LayoutPista = "/Imagens/CircuitoHungaroring1989.2002.png";
			LayoutPistaPQ = "/Imagens/CircuitoHungaroring1989.2002PQ.png";
		}
		
	}

	public static void Spa () {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1985";
		LocalPista = "Stavelot, Bélgica";
		TipoPista  = "Circuito Misto Permanente";
		CapacidadePista = "Capacidade de 70.000 pessoas";
		PaisPista = "/Imagens/Bandeira Belgica.png";
		
		if(SelecionarEquipe.ano >= 2007) {
			
			Curvas_Pista = "20 curvas";
			Voltas_Pista = 44;
			ComprimentoPista = "Comprimento de 7,004 mts";
			LayoutPista = "/Imagens/CircuitoSpa2007+.png";
			LayoutPistaPQ = "/Imagens/CircuitoSpa2007+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 2004 && SelecionarEquipe.ano <= 2006) {
			
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 44;
			ComprimentoPista = "Comprimento de 6,976 mts";
			LayoutPista = "/Imagens/CircuitoHungaroring2004.2006.png";
			LayoutPistaPQ = "/Imagens/CircuitoHungaroring2004.2006PQ.png";
			
		}else if(SelecionarEquipe.ano >= 1981 && SelecionarEquipe.ano <= 2003) {
			
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 44;
			ComprimentoPista = "Comprimento de 6,968 mts";
			LayoutPista = "/Imagens/CircuitoHungaroring1989.2002.png";
			LayoutPistaPQ = "/Imagens/CircuitoHungaroring1989.2002PQ.png";
		}
		
	}
	
	public static void Zandvoort() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1948";
		LocalPista = "Zandvoort, Países Baixos";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 105.000 pessoas";
		PaisPista = "/Imagens/Bandeira Holanda.png";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 72;
			ComprimentoPista = "Comprimento de 4,259 mts";
			LayoutPista = "/Imagens/CircuitoZandvoort.png";
			LayoutPistaPQ = "/Imagens/CircuitoZandvoortPQ.png";
		
	}
	
	public static void Monza() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Setembro de 1922";
		LocalPista = "Monza, Itália";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 108.000 pessoas";
		PaisPista = "/Imagens/Bandeira Italia.png";
		
			Curvas_Pista = "11 curvas";
			Voltas_Pista = 53;
			ComprimentoPista = "Comprimento de 5,793 mts";
			LayoutPista = "/Imagens/CircuitoMonza.png";
			LayoutPistaPQ = "/Imagens/CircuitoMonzaPQ.png";
		
	}
	
	public static void Singapura() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Setembro de 2007";
		LocalPista = "Kallang, Singapura";
		TipoPista  = "Circuito de Rua Temporário";
		CapacidadePista = "Capacidade de 90.000 pessoas";
		PaisPista = "/Imagens/Bandeira Singapura.png";
		
		if(SelecionarEquipe.ano >= 2023) {
			
			Curvas_Pista = "19 curvas";
			Voltas_Pista = 62;
			ComprimentoPista = "Comprimento de 4,938 mts";
			LayoutPista = "/Imagens/CircuitoSingapura2023+.png";
			LayoutPistaPQ = "/Imagens/CircuitoSingapura2023+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 2015 && SelecionarEquipe.ano <= 2022) {
			
			Curvas_Pista = "23 curvas";
			Voltas_Pista = 61;
			ComprimentoPista = "Comprimento de 5,065 mts";
			LayoutPista = "/Imagens/CircuitoSingapura2015.2022.png";
			LayoutPistaPQ = "/Imagens/CircuitoSingapura2015.2022PQ.png";
			
		}else if(SelecionarEquipe.ano >= 2008 && SelecionarEquipe.ano <= 2014) {
			
			Curvas_Pista = "23 curvas";
			Voltas_Pista = 61;
			ComprimentoPista = "Comprimento de 5,073 mts";
			LayoutPista = "/Imagens/CircuitoSingapura2008.2014.png";
			LayoutPistaPQ = "/Imagens/CircuitoSingapura2008.2014PQ.png";
			
		}

}
	
	public static void Austin() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Dezembro de 2010";
		LocalPista = "Austin, Texas, EUA";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 120.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "20 curvas";
			Voltas_Pista = 56;
			ComprimentoPista = "Comprimento de 5,513 mts";
			LayoutPista = "/Imagens/CircuitoAustin.png";
			LayoutPistaPQ = "/Imagens/CircuitoAustinPQ.png";
		
	}
	
	public static void Mexico() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1959";
		LocalPista = "	Cidade do México, México";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 110.000 pessoas";
		PaisPista = "/Imagens/Bandeira Mexico.png";
		
			Curvas_Pista = "17 curvas";
			Voltas_Pista = 71;
			ComprimentoPista = "Comprimento de 4,304 mts";
			LayoutPista = "/Imagens/CircuitoMexico.png";
			LayoutPistaPQ = "/Imagens/CircuitoMexicoPQ.png";
		
	}

	public static void Interlagos() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1938";
		LocalPista = "São Paulo, Brasil";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 60.000 pessoas";
		PaisPista = "/Imagens/Bandeira Brasil.png";
		
			Curvas_Pista = "15 curvas";
			Voltas_Pista = 71;
			ComprimentoPista = "Comprimento de 4,309 mts";
			LayoutPista = "/Imagens/CircuitoInterlagos.png";
			LayoutPistaPQ = "/Imagens/CircuitoInterlagosPQ.png";
		
	}
	
	public static void LasVegas() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2023";
		LocalPista = "Las Vegas, Nevada, EUA";
		TipoPista  = "Circuito de Misto Temporário";
		CapacidadePista = "Capacidade de 100.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "17 curvas";
			Voltas_Pista = 50;
			ComprimentoPista = "Comprimento de 6,201 mts";
			LayoutPista = "/Imagens/CircuitoLasVegas.png";
			LayoutPistaPQ = "/Imagens/CircuitoLasVegasPQ.png";
		
	}
	
	public static void Lusail() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2004";
		LocalPista = "Lusail, Catar";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 52.000 pessoas";
		PaisPista = "/Imagens/Bandeira Qatar.png";
		
			Curvas_Pista = "16 curvas";
			Voltas_Pista = 57;
			ComprimentoPista = "Comprimento de 5,380 mts";
			LayoutPista = "/Imagens/CircuitoLusail.png";
			LayoutPistaPQ = "/Imagens/CircuitoLusailPQ.png";
		
	}
	
	public static void YasMarina() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Maio de 2007";
		LocalPista = "Abu Dhabi, Emirados Árabes";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 60.000 pessoas";
		PaisPista = "/Imagens/Bandeira Emirados Arabes Unidos.png";
		
		if(SelecionarEquipe.ano >= 2021) {
			
			Curvas_Pista = "16 curvas";
			Voltas_Pista = 58;
			ComprimentoPista = "Comprimento de 5,281 mts";
			LayoutPista = "/Imagens/CircuitoYasMarina2021+.png";
			LayoutPistaPQ = "/Imagens/CircuitoYasMarina2021+PQ.png";
			
		}else if(SelecionarEquipe.ano >= 2009 && SelecionarEquipe.ano <= 2020) {
			
			Curvas_Pista = "21 curvas";
			Voltas_Pista = 55;
			ComprimentoPista = "Comprimento de 5,554 mts";
			LayoutPista = "/Imagens/CircuitoYasMarina2009.2020.png";
			LayoutPistaPQ = "/Imagens/CircuitoYasMarina2009.2020PQ.png";
			
		}
	}
	
	public static void PaulRicard() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1969";
		LocalPista = "Le Castellet, França";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 90.000 pessoas";
		PaisPista = "/Imagens/Bandeira Franca.png";
		
			Curvas_Pista = "15 curvas";
			Voltas_Pista = 53;
			ComprimentoPista = "Comprimento de 5,842 mts";
			LayoutPista = "/Imagens/CircuitoPaulRicard.png";
			LayoutPistaPQ = "/Imagens/CircuitoPaulRicardPQ.png";
		
	}	
	
	public static void Portimao() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Outubro de 2008";
		LocalPista = "Portimão, Algarve, Portugal";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 80.000 pessoas";
		PaisPista = "/Imagens/Bandeira Portugal.png";
		
			Curvas_Pista = "15 curvas";
			Voltas_Pista = 66;
			ComprimentoPista = "Comprimento de 4,653 mts";
			LayoutPista = "/Imagens/CircuitoPortimao.png";
			LayoutPistaPQ = "/Imagens/CircuitoPortimaoPQ.png";
		
	}
	
	public static void Sochi() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Julho de 2011";
		LocalPista = "Sóchi, Rússia";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 55.000 pessoas";
		PaisPista = "/Imagens/Bandeira Russia.png";
		
			Curvas_Pista = "18 curvas";
			Voltas_Pista = 53;
			ComprimentoPista = "Comprimento de 5,848 mts";
			LayoutPista = "/Imagens/CircuitoSochi.png";
			LayoutPistaPQ = "/Imagens/CircuitoSochiPQ.png";
		
	}	
	
	public static void Istanbul() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2005";
		LocalPista = "Tuzla, Istambul, Turquia";
		TipoPista  = "Circuito de Misto Permanente";
		CapacidadePista = "Capacidade de 125.000 pessoas";
		PaisPista = "/Imagens/Bandeira Turquia.png";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 58;
			ComprimentoPista = "Comprimento de 5,338 mts";
			LayoutPista = "/Imagens/CircuitoIstanbul.png";
			LayoutPistaPQ = "/Imagens/CircuitoIstanbulPQ.png";
		
	}	
	
//Pistas Indy
	
	public static void StPetersburg() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1985";
		LocalPista = "São Petersburgo, Flórida";
		TipoPista  = "Grand Prix of St. Petersburg";
		CapacidadePista = "Circuito de Rua Temporário";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 100;
			ComprimentoPista = "Comprimento de 2,897 mts";
			LayoutPista = "/Imagens/CircuitoStPetersburg.png";
			LayoutPistaPQ = "/Imagens/CircuitoStPetersburgPQ.png";
		
	}
	 
	public static void LongBeach() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1975";
		LocalPista = "Long Beach, Califórnia";
		TipoPista  = "Grand Prix of Long Beach";
		CapacidadePista = "Circuito de Rua Temporário";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "11 curvas";
			Voltas_Pista = 85;
			ComprimentoPista = "Comprimento de 3,167 mts";
			LayoutPista = "/Imagens/CircuitoLongBeach.png";
			LayoutPistaPQ = "/Imagens/CircuitoLongBeachPQ.png";
		
	}

	public static void Barber() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2010";
		LocalPista = "Birmingham, Alabama";
		TipoPista  = "Grand Prix of Alabama";
		CapacidadePista = "Circuito de Misto Permanente";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "15 curvas";
			Voltas_Pista = 90;
			ComprimentoPista = "Comprimento de 3,830 mts";
			LayoutPista = "/Imagens/CircuitoBarber.png";
			LayoutPistaPQ = "/Imagens/CircuitoBarberPQ.png";
		
	}
	
	public static void IMSGP() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1909";
		LocalPista = "Speedway, Indiana";
		TipoPista  = "IndyCar Grand Prix";
		CapacidadePista = "Circuito de Misto Permanente";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 85;
			ComprimentoPista = "Comprimento de 3,925 mts";
			LayoutPista = "/Imagens/CircuitoIndianapolisMistoIndy.png";
			LayoutPistaPQ = "/Imagens/CircuitoIndianapolisMistoIndyPQ.png";
		
	}
	
	public static void Indy500() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1909";
		LocalPista = "Speedway, Indiana";
		TipoPista  = "Indy 500";
		CapacidadePista = "Circuito Oval";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "4 curvas";
			Voltas_Pista = 200;
			ComprimentoPista = "Comprimento de 4,000 mts";
			LayoutPista = "/Imagens/CircuitoIndy500.png";
			LayoutPistaPQ = "/Imagens/CircuitoIndy500PQ.png";
		
	}
	
	public static void DetroitIndy () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1989";
		LocalPista = "Detroit, Michigan";
		TipoPista  = "Detroit Grand Prix";
		CapacidadePista = "Circuito Rua Temporário";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "9 curvas";
			Voltas_Pista = 100;
			ComprimentoPista = "Comprimento de 2,647 mts";
			LayoutPista = "/Imagens/CircuitoDetroitIndy.png";
			LayoutPistaPQ = "/Imagens/CircuitoDetroitIndyPQ.png";
		
	}
	
	public static void RoadAmerica() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1955";
		LocalPista = "Elkhart Lake, Wisconsin";
		TipoPista  = "Grand Prix of Road America";
		CapacidadePista = "Circuito Misto Permanente";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "14 curvas";
			Voltas_Pista = 55;
			ComprimentoPista = "Comprimento de 6,515 mts";
			LayoutPista = "/Imagens/CircuitoRoadAmerica.png";
			LayoutPistaPQ = "/Imagens/CircuitoRoadAmericaPQ.png";
		
	}
	
  	public static void LagunaSeca() {
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	
	InauguracaoPista = "Inaugurado em 1957";
	LocalPista = "Monterey, Califórnia";
	TipoPista  = "Grand Prix of Monterey";
	CapacidadePista = "Circuito Misto Permanente";
	PaisPista = "/Imagens/Bandeira Estados Unidos.png";
	
		Curvas_Pista = "11 curvas";
		Voltas_Pista = 95;
		ComprimentoPista = "Comprimento de 3,602 mts";
		LayoutPista = "/Imagens/CircuitoLagunaSeca.png";
		LayoutPistaPQ = "/Imagens/CircuitoLagunaSecaPQ.png";
	
}
	
  	public static void MidOhio() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1962";
		LocalPista = "Lexington, Ohio";
		TipoPista  = "Honda Indy 200";
		CapacidadePista = "Circuito Misto Permanente";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "13 curvas";
			Voltas_Pista = 90;
			ComprimentoPista = "Comprimento de 3,860 mts";
			LayoutPista = "/Imagens/CircuitoMidOhio.png";
			LayoutPistaPQ = "/Imagens/CircuitoMidOhioPQ.png";
		
	}
  	
  	public static void IowaIndy() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2005";
		LocalPista = "Newton, Iowa";
		TipoPista  = "Iowa IndyCar 250";
		CapacidadePista = "Circuito Oval";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "4 curvas";
			Voltas_Pista = 250;
			ComprimentoPista = "Comprimento de 1,438 mts";
			LayoutPista = "/Imagens/CircuitoIowa.png";
			LayoutPistaPQ = "/Imagens/CircuitoIowaPQ.png";
		
	}
  	
  	public static void Toronto() {
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	
	InauguracaoPista = "Inaugurado em 1986";
	LocalPista = "	Exhibition Place, Torono";
	TipoPista  = "Honda Indy Toronto";
	CapacidadePista = "Circuito de Rua Temporário";
	PaisPista = "/Imagens/Bandeira Canada.png";
	
		Curvas_Pista = "11 curvas";
		Voltas_Pista = 90;
		ComprimentoPista = "Comprimento de 2,874 mts";
		LayoutPista = "/Imagens/CircuitoToronto.png";
		LayoutPistaPQ = "/Imagens/CircuitoTorontoPQ.png";
	
}
  	
  	public static void Getaway() {
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	
	InauguracaoPista = "Inaugurado em 1967";
	LocalPista = "Madison, Illinois";
	TipoPista  = "Bommarito Automotive Group 500";
	CapacidadePista = "Circuito Oval";
	PaisPista = "/Imagens/Bandeira Estados Unidos.png";
	
		Curvas_Pista = "4 curvas";
		Voltas_Pista = 248;
		ComprimentoPista = "Comprimento de 2,000 mts";
		LayoutPista = "/Imagens/CircuitoGetaway.png";
		LayoutPistaPQ = "/Imagens/CircuitoGetawayPQ.png";
	
}
  	
  	public static void Portland() {
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	
	InauguracaoPista = "Inaugurado em 1960";
	LocalPista = "Portland, Oregon";
	TipoPista  = "Grand Prix of Portland";
	CapacidadePista = "Circuito Misto Permanente";
	PaisPista = "/Imagens/Bandeira Estados Unidos.png";
	
		Curvas_Pista = "12 curvas";
		Voltas_Pista = 110;
		ComprimentoPista = "Comprimento de 3,166 mts";
		LayoutPista = "/Imagens/CircuitoPorland.png";
		LayoutPistaPQ = "/Imagens/CircuitoPorlandPQ.png";
	
}
  	
  	public static void Milwaukee() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1903";
		LocalPista = "West Allis, Wisconsin";
		TipoPista  = "Hy-Vee Milwaukee Mile 250s";
		CapacidadePista = "Circuito Oval";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "4 curvas";
			Voltas_Pista = 250;
			ComprimentoPista = "Comprimento de 1,603 mts";
			LayoutPista = "/Imagens/CircuitoMilwaukee.png";
			LayoutPistaPQ = "/Imagens/CircuitoMilwaukeePQ.png";
		
	}
  	
  	public static void NashvilleIndy() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2001";
		LocalPista = "Lebanon, Tennessee";
		TipoPista  = "Firestone Indy 200";
		CapacidadePista = "Circuito Oval";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "4 curvas";
			Voltas_Pista = 200;
			ComprimentoPista = "Comprimento de 2,145 mts";
			LayoutPista = "/Imagens/CircuitoNashville.png";
			LayoutPistaPQ = "/Imagens/CircuitoNashvillePQ.png";
		
	}
  	
  	//Falta Completar
	public static void GatewayIndy () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1997";
		LocalPista = "Madison, Illinois";
		TipoPista  = ""; //Nome da Corrida
		CapacidadePista = "Circuito Misto";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "";
			Voltas_Pista = 0;
			ComprimentoPista = "Volta de mts";
			LayoutPista = "/Imagens/Circuito.png";
			LayoutPistaPQ = "/Imagens/Circuito.png";
		
	}
  	

	
	/*
	  	public static void () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em ";
		LocalPista = "";
		TipoPista  = "Indy 500";
		CapacidadePista = "Circuito ";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = " curvas";
			Voltas_Pista = ;
			ComprimentoPista = "Comprimento de  mts";
			LayoutPista = "/Imagens/.png";
			LayoutPistaPQ = "/Imagens/.png";
		
	}
	
	 */
	
	
	
//Pistas NASCAR
	
	public static void Daytona500() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1959";
		LocalPista = "Daytona Beach, Florida";
		TipoPista  = "Daytona 500";
		CapacidadePista = "Capacidade de 168.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Daytona Superspeedway - Oval";
			Voltas_Pista = 200;
			ComprimentoPista = "Volta de 4km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoDaytona.png";
			LayoutPistaPQ = "/Imagens/CircuitoDaytonaPQ.png";
		
	}
	
	public static void Daytona400() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1959";
		LocalPista = "Daytona Beach, Florida";
		TipoPista  = "Coke Zero Sugar 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 168.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Daytona Superspeedway - Oval"; //Tipo de pista
			Voltas_Pista = 160;
			ComprimentoPista = "Volta de 4km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoDaytona.png";
			LayoutPistaPQ = "/Imagens/CircuitoDaytonaPQ.png";
		
	}
	
	public static void AtlantaAmbetter() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1960";
		LocalPista = "Hampton, Georgia";
		TipoPista  = "Ambetter Health 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 71.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Atlanta Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 260;
			ComprimentoPista = "Volta de 2,5 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoAtlanta.png";
			LayoutPistaPQ = "/Imagens/CircuitoAtlantaPQ.png";
		
	}
	
	public static void AtlantaQuaker() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1960";
		LocalPista = "Hampton, Georgia";
		TipoPista  = "Quaker State 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 71.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Atlanta Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 260;
			ComprimentoPista = "Volta de 2,5 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoAtlanta.png";
			LayoutPistaPQ = "/Imagens/CircuitoAtlantaPQ.png";
		
	}

	public static void LasVegasPennzoil () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1971";
		LocalPista = "Las Vegas, Nevada";
		TipoPista  = "Pennzoil 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 80.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Las Vegas Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoLasVegasSpeedway.png";
			LayoutPistaPQ = "/Imagens/CircuitoLasVegasSpeedwayPQ.png";
		
	}
	
	public static void LasVegasSouthPoint () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1971";
		LocalPista = "Las Vegas, Nevada";
		TipoPista  = "South Point 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 80.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Las Vegas Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoLasVegasSpeedway.png";
			LayoutPistaPQ = "/Imagens/CircuitoLasVegasSpeedwayPQ.png";
		
	}
	
	public static void Phoenix() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1964";
		LocalPista = "Avondale, Arizona";
		TipoPista  = "Shriners Children's 500"; //Nome da Corrida
		CapacidadePista = "Capacidade de 42.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Phoenix Raceway - Oval"; //Tipo de pista
			Voltas_Pista = 312;
			ComprimentoPista = "Volta de 1,64 km (1 milha)";
			LayoutPista = "/Imagens/CircuitoPhoenix.png";
			LayoutPistaPQ = "/Imagens/CircuitoPhoenixPQ.png";
		
	}
	
	public static void PhoenixChampionship() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1964";
		LocalPista = "Avondale, Arizona";
		TipoPista  = "NASCAR Championship Race"; //Nome da Corrida
		CapacidadePista = "Capacidade de 42.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Phoenix Raceway - Oval"; //Tipo de pista
			Voltas_Pista = 312;
			ComprimentoPista = "Volta de 1,64 km (1 milha)";
			LayoutPista = "/Imagens/CircuitoPhoenix.png";
			LayoutPistaPQ = "/Imagens/CircuitoPhoenixPQ.png";
		
	}
	
	public static void Bristol () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1961";
		LocalPista = "Bristol, Tennessee";
		TipoPista  = "Food City 500"; //Nome da Corrida
		CapacidadePista = "Capacidade de 146.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Bristol Speedway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 500;
			ComprimentoPista = "Volta de 0,85 km (0,53 milhas)";
			LayoutPista = "/Imagens/CircuitoBristol.png";
			LayoutPistaPQ = "/Imagens/CircuitoBristolPQ.png";
		
	}
	
	public static void BristolNight () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1961";
		LocalPista = "Bristol, Tennessee";
		TipoPista  = "Bass Pro Shops Night Race"; //Nome da Corrida
		CapacidadePista = "Capacidade de 146.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Bristol Speedway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 500;
			ComprimentoPista = "Volta de 0,85 km (0,53 milhas)";
			LayoutPista = "/Imagens/CircuitoBristol.png";
			LayoutPistaPQ = "/Imagens/CircuitoBristolPQ.png";
		
	}

	public static void AustinNascar() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em Dezembro de 2010";
		LocalPista = "Austin, Texas";
		TipoPista  = "EchoPark Automotive";
		CapacidadePista = "Capacidade de 120.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "COTA - Circuito de Misto";
			Voltas_Pista = 68;
			ComprimentoPista = "Volta de 5,51 km (3,42 Milhas)";
			LayoutPista = "/Imagens/CircuitoAustin.png";
			LayoutPistaPQ = "/Imagens/CircuitoAustinPQ.png";
		
	}
	
	public static void Richmond () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1946";
		LocalPista = "Richmond, Virginia";
		TipoPista  = "Toyota Owners 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 51.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Richmond Raceway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 400;
			ComprimentoPista = "Volta de 1,2 km (0,75 milhas)";
			LayoutPista = "/Imagens/CircuitoRichmond.png";
			LayoutPistaPQ = "/Imagens/CircuitoRichmondPQ.png";
		
	}
	
	public static void RichmondNigth () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1946";
		LocalPista = "Richmond, Virginia";
		TipoPista  = "Cook Out 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 51.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Richmond Raceway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 400;
			ComprimentoPista = "Volta de 1,2 km (0,75 milhas)";
			LayoutPista = "/Imagens/CircuitoRichmond.png";
			LayoutPistaPQ = "/Imagens/CircuitoRichmondPQ.png";
		
	}
	
	public static void MartinvilleCookOut () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1947";
		LocalPista = "Ridgeway, Virginia";
		TipoPista  = "Cook Out 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 65.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Martinsville Speedway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 500;
			ComprimentoPista = "Volta de 0,85 km (0,53 milhas)";
			LayoutPista = "/Imagens/CircuitoMartinsville.png";
			LayoutPistaPQ = "/Imagens/CircuitoMartinsvillePQ.png";
		
	}
	
	public static void MartinvilleXfinity () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1947";
		LocalPista = "Ridgeway, Virginia";
		TipoPista  = "Xfinity 500"; //Nome da Corrida
		CapacidadePista = "Capacidade de 65.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Martinsville Speedway - Oval Curto"; //Tipo de pista
			Voltas_Pista = 500;
			ComprimentoPista = "Volta de 0,85 km (0,53 milhas)";
			LayoutPista = "/Imagens/CircuitoMartinsville.png";
			LayoutPistaPQ = "/Imagens/CircuitoMartinsvillePQ.png";
		
	}
	
  	public static void IowaNascar() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2005";
		LocalPista = "Newton, Iowa";
		TipoPista  = "Iowa Corn 350";
		CapacidadePista = "Capacidade de 30.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Iowa Speedway - Oval Curto";
			Voltas_Pista = 350;
			ComprimentoPista = "Volta de 1,43 km (0,75 milhas)";
			LayoutPista = "/Imagens/CircuitoIowa.png";
			LayoutPistaPQ = "/Imagens/CircuitoIowaPQ.png";
		
	}
	
  	public static void NashvilleNascar() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2001";
		LocalPista = "Lebanon, Tennessee";
		TipoPista  = "Ally 400";
		CapacidadePista = "Capacidade de 25.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Nashville Speedway - Oval";
			Voltas_Pista = 300;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoNashville.png";
			LayoutPistaPQ = "/Imagens/CircuitoNashvillePQ.png";
		
	}
  	
	public static void Texas() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1996";
		LocalPista = "Fort Worth, Texas";
		TipoPista  = "Autotrader EchoPark 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 113.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Texas Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoTexas.png";
			LayoutPistaPQ = "/Imagens/CircuitoTexasPQ.png";
		
	}
	
	public static void TalladegaGeico() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1969";
		LocalPista = "Lincoln, Alabama";
		TipoPista  = "Geico 500";
		CapacidadePista = "Capacidade de 175.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Talladega Superspeedway - Oval";
			Voltas_Pista = 188;
			ComprimentoPista = "Volta de 4km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoTalladega.png";
			LayoutPistaPQ = "/Imagens/CircuitoTalladegaPQ.png";
		
	}

	public static void TalladegaYellaWood() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1969";
		LocalPista = "Lincoln, Alabama";
		TipoPista  = "YellaWood 500";
		CapacidadePista = "Capacidade de 175.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Talladega Superspeedway - Oval";
			Voltas_Pista = 188;
			ComprimentoPista = "Volta de 4km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoTalladega.png";
			LayoutPistaPQ = "/Imagens/CircuitoTalladegaPQ.png";
		
	}
	
	public static void Dover() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1969";
		LocalPista = "Dover, Delaware";
		TipoPista  = "Würth 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 95.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Dover Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 400;
			ComprimentoPista = "Volta de 1,66 km (1,03 milhas)";
			LayoutPista = "/Imagens/CircuitoDover.png";
			LayoutPistaPQ = "/Imagens/CircuitoDoverPQ.png";
		
	}
	
	public static void KansasAdventHealth() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2001";
		LocalPista = "Kansas City, Kansas";
		TipoPista  = "AdventHealth 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 48.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Kansas Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoKansas.png";
			LayoutPistaPQ = "/Imagens/CircuitoKansasPQ.png";
		
	}
	
	public static void KansasHollywood() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2001";
		LocalPista = "Kansas City, Kansas";
		TipoPista  = "Hollywood Casino 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 48.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Kansas Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoKansas.png";
			LayoutPistaPQ = "/Imagens/CircuitoKansasPQ.png";
		
	}
	
	public static void Darlington400() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1950";
		LocalPista = "Darlington, South Carolina";
		TipoPista  = "Goodyear 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 50.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Darlington Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 293;
			ComprimentoPista = "Volta de 2,19 km (1,366 milhas)";
			LayoutPista = "/Imagens/CircuitoDarlington.png";
			LayoutPistaPQ = "/Imagens/CircuitoDarlingtonPQ.png";
		
	}

	public static void Darlington500() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1950";
		LocalPista = "Darlington, South Carolina";
		TipoPista  = "Cook Out Southern 500"; //Nome da Corrida
		CapacidadePista = "Capacidade de 50.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Darlington Speedway Oval"; //Tipo de pista
			Voltas_Pista = 367;
			ComprimentoPista = "Volta de 2,19 km (1,366 milhas)";
			LayoutPista = "/Imagens/CircuitoDarlington.png";
			LayoutPistaPQ = "/Imagens/CircuitoDarlingtonPQ.png";
		
	}
	
	public static void Charlotte() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1960";
		LocalPista = "Concord, North Carolina";
		TipoPista  = "Coca-Cola 600"; //Nome da Corrida
		CapacidadePista = "Capacidade de 95.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Charlotte Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 400;
			ComprimentoPista = "Volta de 2,41 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoCharlotte.png";
			LayoutPistaPQ = "/Imagens/CircuitoCharlottePQ.png";
		
	}
	
	public static void CharlotteRoval() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1960";
		LocalPista = "Concord, North Carolina";
		TipoPista  = "Bank of America Roval 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 95.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Charlotte Speedway - Roval"; //Tipo de pista
			Voltas_Pista = 109;
			ComprimentoPista = "Volta de 3,66 km (2,28 milhas)";
			LayoutPista = "/Imagens/CircuitoCharlotteRoval.png";
			LayoutPistaPQ = "/Imagens/CircuitoCharlotteRovalPQ.png";
		
	}
	
	public static void Indy400() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1909";
		LocalPista = "Speedway, Indiana";
		TipoPista  = "Brickyard 400";
		CapacidadePista = "Capacidade de 400.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Indianapolis Speedway - Oval";
			Voltas_Pista = 160;
			ComprimentoPista = "Volta de 4,00 km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoIndy500.png";
			LayoutPistaPQ = "/Imagens/CircuitoIndy500PQ.png";
		
	}
	
	public static void GatewayNascar () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1997";
		LocalPista = "Madison, Illinois";
		TipoPista  = "Enjoy Illinois 300"; //Nome da Corrida
		CapacidadePista = "Capacidade de 57.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "WWT Raceway - Oval"; //Tipo de pista
			Voltas_Pista = 240;
			ComprimentoPista = "Volta de 2,00 km (1,250 milhas)";
			LayoutPista = "/Imagens/CircuitoGatewayNascar.png";
			LayoutPistaPQ = "/Imagens/CircuitoGatewayNascarPQ.png";
		
	}
	
	public static void Sonoma () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1968";
		LocalPista = "Sonoma, California";
		TipoPista  = "Toyota/Save Mart 350"; //Nome da Corrida
		CapacidadePista = "Capacidade de 120.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		Curvas_Pista = "Sonoma Raceway - Circuito Misto"; //Tipo de pista
		
			if(SelecionarEquipe.ano <= 2018 || SelecionarEquipe.ano >= 2023) {
				
				Voltas_Pista = 110;
				ComprimentoPista = "Volta de 3,20 km (1,99 milhas)";
				LayoutPista = "/Imagens/CircuitoSonoma.png";
				LayoutPistaPQ = "/Imagens/CircuitoSonomaPQ.png";
				
			}else if(SelecionarEquipe.ano >= 2019 && SelecionarEquipe.ano <= 2022) {
				
				Voltas_Pista = 90;
				ComprimentoPista = "Volta de 4,06 km (2,52 milhas)";
				LayoutPista = "/Imagens/CircuitoSonoma18-22.png";
				LayoutPistaPQ = "/Imagens/CircuitoSonoma18-22PQ.png";
				
			}
		
	}
	
  	public static void NewHampshire() {
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
	
	InauguracaoPista = "Inaugurado em 1964";
	LocalPista = "North Loudon, New Hampshire";
	TipoPista  = "USA Today 301";
	CapacidadePista = "Capacidade de 73.000 pessoas";
	PaisPista = "/Imagens/Bandeira Estados Unidos.png";
	
		Curvas_Pista = "New Hampshire Speedway - Oval";
		Voltas_Pista = 301;
		ComprimentoPista = "Volta de 1,70 km (1,058 milhas)";
		LayoutPista = "/Imagens/CircuitoNewHampshire.png";
		LayoutPistaPQ = "/Imagens/CircuitoNewHampshirePQ.png";
	
}
  	
	public static void ChicagoStreet() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 2023";
		LocalPista = "Chicago, Illinois";
		TipoPista  = "Grant Park 165"; //Nome da Corrida
		CapacidadePista = "";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Chicago Street Course - Misto"; //Tipo de pista
			Voltas_Pista = 75;
			ComprimentoPista = "Volta de 3,541 km (2,200 milhas)";
			LayoutPista = "/Imagens/CircuitoChicagoStreet.png";
			LayoutPistaPQ = "/Imagens/CircuitoChicagoStreetPQ.png";
		
	}
  	
	public static void Pocono() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1968";
		LocalPista = "	Long Pond, Pennsylvania";
		TipoPista  = "The Great American Getaway 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 77.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Pocono Raceway - Tri-Oval"; //Tipo de pista
			Voltas_Pista = 160;
			ComprimentoPista = "Volta de 4,02 km (2,5 milhas)";
			LayoutPista = "/Imagens/CircuitoPocono.png";
			LayoutPistaPQ = "/Imagens/CircuitoPoconoPQ.png";
		
	}
	
	public static void Michigan() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1968";
		LocalPista = "Brooklyn, Michigan";
		TipoPista  = "FireKeepers Casino 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 138.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Michigan Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 200;
			ComprimentoPista = "Volta de 3,22 km (2 milhas)";
			LayoutPista = "/Imagens/CircuitoMichigan.png";
			LayoutPistaPQ = "/Imagens/CircuitoMichiganPQ.png";
		
	}
  	
	public static void WatkinsGlen () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1956";
		LocalPista = "Watkins Glen, New York";
		TipoPista  = "Go Bowling at The Glen"; //Nome da Corrida
		CapacidadePista = "Capacidade de 38.900 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Watkins Glen International - Misto"; //Tipo de pista
			Voltas_Pista = 90;
			ComprimentoPista = "Volta de 3,94 km (2,45 milhas)";
			LayoutPista = "/Imagens/CircuitoWatkinsGlen.png";
			LayoutPistaPQ = "/Imagens/CircuitoWatkinsGlenPQ.png";
		
	}
  	
	public static void HomestedMiami() {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em 1995";
		LocalPista = "Homestead, Florida";
		TipoPista  = "Straight Talk Wireless 400"; //Nome da Corrida
		CapacidadePista = "Capacidade de 55.000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Homestead Speedway - Oval"; //Tipo de pista
			Voltas_Pista = 267;
			ComprimentoPista = "Volta de 2,42 km (1,5 milhas)";
			LayoutPista = "/Imagens/CircuitoHomestedMiami.png";
			LayoutPistaPQ = "/Imagens/CircuitoHomestedMiamiPQ.png";
		
	}
  	
  	/*
	 
	public static void () {
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
		
		InauguracaoPista = "Inaugurado em ";
		LocalPista = "";
		TipoPista  = ""; //Nome da Corrida
		CapacidadePista = "Capacidade de .000 pessoas";
		PaisPista = "/Imagens/Bandeira Estados Unidos.png";
		
			Curvas_Pista = "Nome da Pista - Oval"; //Tipo de pista
			Voltas_Pista = ;
			ComprimentoPista = "Volta de , km (, milhas)";
			LayoutPista = "/Imagens/Circuito.png";
			LayoutPistaPQ = "/Imagens/CircuitoPQ.png";
		
	}
	
	 */
	
}