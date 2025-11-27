import java.io.Serializable;

import javax.swing.ImageIcon;

public class SalvaDado implements Serializable{
	private static final long serialVersionUID = 1L; 
	//você pode escrever qualquer numero que quiser, mas tem que ser um numero do tipo long.
	// é o comando usado para fazer com que as estruturas de dados possam voltar ao seu original. O que nos economiza bastante tempo, 
	//para que não tenhamos que fazer isso manualmente.
	
	//Dados Gerais
	public String Save_NomeDirigente, Save, Save_Categoria;
	public int Save_TemporadaJogador, Save_Ano, Save_EtapaAtual, Save_EtapaTotais;
	//Dados da Equipe Escolhida
	public String Save_LB_LogoEquipe,Save_LB_LogoMotorPQ,Save_LB_BandeiraEquipe,Save_LB_BandeiraMotor;
	public String Save_LB_BandeiraSede,Save_LB_NomeEquipe,Save_LB_SedeEquipe,Save_LB_Motor;
	public int Save_LB_Ano;
	//Dados dos Pilotos da Equipe
	public int Save_LB_TempoContratoP1,Save_LB_TempoContratoP2,Save_LB_TempoContratoP3,Save_LB_TempoContratoP4,Save_LB_TempoContratoP5;
	public int Save_LB_IdadeP1,Save_LB_IdadeP2,Save_LB_IdadeP3,Save_LB_IdadeP4,Save_LB_IdadeP5;
	public String Save_LB_NomeP1,Save_LB_BandeiraP1, Save_LB_NumP1;
	public String Save_LB_NomeP2,Save_LB_BandeiraP2, Save_LB_NumP2;
	public String Save_LB_NomeP3,Save_LB_BandeiraP3, Save_LB_NumP3;
	public String Save_LB_NomeP4,Save_LB_BandeiraP4, Save_LB_NumP4;
	public String Save_LB_NomeP5,Save_LB_BandeiraP5, Save_LB_NumP5;

}
