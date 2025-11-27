import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;

public class Inicial_Carregar extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	public static File ArquivoCarregado;
	public static String AddExt;
	public static int RESULTADO_SALVA=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial_Carregar frame = new Inicial_Carregar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicial_Carregar() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Inicial_Carregar.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 336);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton BT_Novo_Jogo = new JButton("NOVO JOGO");
		BT_Novo_Jogo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		BT_Novo_Jogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Selecionar_Categoria Categoria = new Selecionar_Categoria();
				Categoria.setVisible(true);
				Categoria.setLocationRelativeTo(null);
				
				dispose();			
				}
		});
		BT_Novo_Jogo.setBounds(279, 237, 185, 21);
		contentPane.add(BT_Novo_Jogo);
		
		
		
		JLabel lblNewLabel = new JLabel("MOTORSPORT MANAGER");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 451, 36);
		contentPane.add(lblNewLabel);
				
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblNewLabel_1.setIcon(new ImageIcon(Inicial_Carregar.class.getResource("/Imagens/Banner F1_OK.png")));
		lblNewLabel_1.setSize(100, 100);
		lblNewLabel_1.setBounds(10, 56, 451, 170);
		contentPane.add(lblNewLabel_1);
		
		
		JButton BT_Carregar = new JButton("CARREGAR JOGO SALVO");
		BT_Carregar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		BT_Carregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CarregaDados();
				
			}
		});
		BT_Carregar.setBounds(279, 268, 185, 21);
		contentPane.add(BT_Carregar);
		
		JButton BT_SAIR = new JButton("SAIR");
		BT_SAIR.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		BT_SAIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		BT_SAIR.setBounds(10, 268, 85, 21);
		contentPane.add(BT_SAIR);
		
	}
	
	public void CarregaDados() {
		
		JFileChooser fc = new JFileChooser(new java.io.File("/Imagens/Icone16px.png"));
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".save", "save");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(filtro);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
	//	fc.setCurrentDirectory(new File ("\\\\USUARIO-PC\\arquivos compartilhados\\Calcula Piso\\Motorsport_Manager\\Saves"));
		fc.setCurrentDirectory(new File ("C:\\\\Users\\ctcja\\OneDrive\\Área de Trabalho\\Programação\\Motorsport_Manager\\Saves")); //PC
		
		//Diretório Padrão
		//fc.setCurrentDirectory(new File ("C:\\"));
		
		//Abrir
		//fc.showOpenDialog(fc);
		int resultado = fc.showOpenDialog(fc);
		//Salvar
		//fc.showSaveDialog(fc);
		
		ArquivoCarregado = fc.getSelectedFile();
		
		if (resultado == JFileChooser.CANCEL_OPTION){  
	           
			RESULTADO_SALVA = 5;
			
			return;
	           
		}else {
			RESULTADO_SALVA = 4;
		}
		
		//aqui o Try
		
		try {
			
			SalvaDado data = (SalvaDado) GerenciarRecursos.Consultar(""+ArquivoCarregado);
			
//A partir daqui é tudo igual
			
			Pagina_Inicial PaginaInicial = new Pagina_Inicial();
			Selecionar_Categoria Categoria = new Selecionar_Categoria();
			Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
			
			PaginaInicial.setVisible(true);
			PaginaInicial.setLocationRelativeTo(null);
			
			PaginaInicial.LB_NomeDirigente.setText(""+data.Save_NomeDirigente);
			SelecionarEquipe.NomeDirigente = data.Save_NomeDirigente;
			
			//Colocar as outras variáveis aqui como no exemplo:
			
			Categoria.BT_Cate = data.Save_Categoria;
			SelecionarEquipe.ano = data.Save_Ano;
			//PaginaInicial.LBTeste.setText("Ano: "+data.Save_Ano+ " Categoria: "+Categoria.BT_Cate);
			
			PaginaInicial.EtapaAtual = data.Save_EtapaAtual;
			PaginaInicial.EtapaTotais = data.Save_EtapaTotais;
			PaginaInicial.TEMPORADAJOGADOR = data.Save_TemporadaJogador;
			
			SelecionarEquipe.LogoEquipeEscolhida = data.Save_LB_LogoEquipe;
			SelecionarEquipe.LogoEquipeEscolhida = data.Save_LB_LogoEquipe;
			SelecionarEquipe.LogoMotorPQEscolhido = data.Save_LB_LogoMotorPQ;
			SelecionarEquipe.BandeiraEquipeEscolhida = data.Save_LB_BandeiraEquipe;
			SelecionarEquipe.BandeiraMotorEscolhido = data.Save_LB_BandeiraMotor;
			SelecionarEquipe.BandeiraSedeEquipeEscolhida = data.Save_LB_BandeiraSede;
				
			SelecionarEquipe.NomeEquipeEscolhida = data.Save_LB_NomeEquipe;
			SelecionarEquipe.SedeEquipeEscolhida = data.Save_LB_SedeEquipe;
			SelecionarEquipe.NomeMotorEscolhido = data.Save_LB_Motor;
			SelecionarEquipe.ano = data.Save_LB_Ano;
			
			SelecionarEquipe.NomePiloto1 = data.Save_LB_NomeP1;
			SelecionarEquipe.PaisPiloto1 = data.Save_LB_BandeiraP1;
			SelecionarEquipe.NumeroPiloto1 = data.Save_LB_NumP1;
			SelecionarEquipe.IdadePiloto1 = data.Save_LB_IdadeP1;
			PaginaInicial.ContratoP1 = data.Save_LB_TempoContratoP1;

			SelecionarEquipe.NomePiloto2 = data.Save_LB_NomeP2;
			SelecionarEquipe.PaisPiloto2 = data.Save_LB_BandeiraP2;
			SelecionarEquipe.NumeroPiloto2 = data.Save_LB_NumP2;
			SelecionarEquipe.IdadePiloto2 = data.Save_LB_IdadeP2;
			PaginaInicial.ContratoP2 = data.Save_LB_TempoContratoP2;
			
			SelecionarEquipe.NomePiloto3 = data.Save_LB_NomeP3;
			SelecionarEquipe.PaisPiloto3 = data.Save_LB_BandeiraP3;
			SelecionarEquipe.NumeroPiloto3 = data.Save_LB_NumP3;
			SelecionarEquipe.IdadePiloto3 = data.Save_LB_IdadeP3;
			PaginaInicial.ContratoP3 = data.Save_LB_TempoContratoP3;
			
			SelecionarEquipe.NomePiloto4 = data.Save_LB_NomeP4;
			SelecionarEquipe.PaisPiloto4 = data.Save_LB_BandeiraP4;
			SelecionarEquipe.NumeroPiloto4 = data.Save_LB_NumP4;
			SelecionarEquipe.IdadePiloto4 = data.Save_LB_IdadeP4;
			PaginaInicial.ContratoP4 = data.Save_LB_TempoContratoP4;
			
			SelecionarEquipe.NomePiloto5 = data.Save_LB_NomeP5;
			SelecionarEquipe.PaisPiloto5 = data.Save_LB_BandeiraP5;
			SelecionarEquipe.NumeroPiloto5 = data.Save_LB_NumP5;
			SelecionarEquipe.IdadePiloto5 = data.Save_LB_IdadeP5;
			PaginaInicial.ContratoP5 = data.Save_LB_TempoContratoP5;

			if (PaginaInicial.TEMPORADAJOGADOR == 0) {
				PaginaInicial.TEMPORADAJOGADOR = 1;
			}
			
			if (PaginaInicial.EtapaAtual == 0) {
				PaginaInicial.EtapaAtual = 1;
			}
			
			if (PaginaInicial.Etapa == 0) {
				PaginaInicial.Etapa = 1;
			}
			
			PaginaInicial.LB_Temporada.setText("Temporada "+PaginaInicial.TEMPORADAJOGADOR+", Etapa "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais);
			PaginaInicial.LB_PistaEtapas.setText("PRÓXIMA ETAPA, "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais);
			PaginaInicial.LB_Ano.setText("Ano "+data.Save_Ano);
			
//Igual até aqui!
			
			dispose();					
			
		}catch(Exception eee) {
			
		}
		
	}
	
	public void SalvaDados() {
		
		SalvaDado data = new SalvaDado();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Pagina_Inicial PaginaInicial = new Pagina_Inicial();
		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".save", "save");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(filtro);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//fc.setCurrentDirectory(new File ("\\\\USUARIO-PC\\arquivos compartilhados\\Calcula Piso\\Motorsport_Manager\\Saves")); //Loja
		fc.setCurrentDirectory(new File ("C:\\\\Users\\ctcja\\OneDrive\\Área de Trabalho\\Programação\\Motorsport_Manager\\Saves")); //PC
		
		//fc.setCurrentDirectory(new File ("C:\\"));
		
		//Salvar
		int resultado = fc.showSaveDialog(fc);
		
		if (resultado == JFileChooser.CANCEL_OPTION){  
	           
			RESULTADO_SALVA = 0;
			
			return;
			
		}else {
			
			RESULTADO_SALVA = 0;
			
		}
	
		//100% cambiarra aqui, mas funcionou
		PaginaInicial.ArquivoSalvo = fc.getSelectedFile();
		AddExt = ""+PaginaInicial.ArquivoSalvo+".save";
		data.Save = AddExt;
			
		//Colocar as outras variáveis aqui como no exemplo:
		
		data.Save_NomeDirigente = SelecionarEquipe.NomeDirigente;
		data.Save_Ano = SelecionarEquipe.ano;
		data.Save_Categoria = Categoria.BT_Cate;
		data.Save_EtapaAtual = PaginaInicial.EtapaAtual;
		data.Save_EtapaTotais = PaginaInicial.EtapaTotais;
		data.Save_TemporadaJogador = PaginaInicial.TEMPORADAJOGADOR;

		data.Save_LB_LogoEquipe = SelecionarEquipe.LogoEquipeEscolhida;
		data.Save_LB_LogoMotorPQ = SelecionarEquipe.LogoMotorPQEscolhido;
		data.Save_LB_BandeiraEquipe = SelecionarEquipe.BandeiraEquipeEscolhida;
		data.Save_LB_BandeiraMotor = SelecionarEquipe.BandeiraMotorEscolhido;
		data.Save_LB_BandeiraSede = SelecionarEquipe.BandeiraSedeEquipeEscolhida;
			
		data.Save_LB_NomeEquipe = SelecionarEquipe.NomeEquipeEscolhida;
		data.Save_LB_SedeEquipe = SelecionarEquipe.SedeEquipeEscolhida;
		data.Save_LB_Motor = SelecionarEquipe.NomeMotorEscolhido;
		data.Save_LB_Ano = SelecionarEquipe.ano;
		
		data.Save_LB_NomeP1 = SelecionarEquipe.NomePiloto1;
		data.Save_LB_BandeiraP1 = SelecionarEquipe.PaisPiloto1;
		data.Save_LB_NumP1 = SelecionarEquipe.NumeroPiloto1;
		data.Save_LB_IdadeP1 = SelecionarEquipe.IdadePiloto1;
		data.Save_LB_TempoContratoP1 = PaginaInicial.ContratoP1;
	
		data.Save_LB_NomeP2 = SelecionarEquipe.NomePiloto2;
		data.Save_LB_BandeiraP2 = SelecionarEquipe.PaisPiloto2;
		data.Save_LB_NumP2 = SelecionarEquipe.NumeroPiloto2;
		data.Save_LB_IdadeP2 = SelecionarEquipe.IdadePiloto2;
		data.Save_LB_TempoContratoP2 = PaginaInicial.ContratoP2;
		
		data.Save_LB_NomeP3 = SelecionarEquipe.NomePiloto3;
		data.Save_LB_BandeiraP3 = SelecionarEquipe.PaisPiloto3;
		data.Save_LB_NumP3 = SelecionarEquipe.NumeroPiloto3;
		data.Save_LB_IdadeP3 = SelecionarEquipe.IdadePiloto3;
		data.Save_LB_TempoContratoP3 = PaginaInicial.ContratoP3;
		
		data.Save_LB_NomeP4 = SelecionarEquipe.NomePiloto4;
		data.Save_LB_BandeiraP4 = SelecionarEquipe.PaisPiloto4;
		data.Save_LB_NumP4 = SelecionarEquipe.NumeroPiloto4;
		data.Save_LB_IdadeP4 = SelecionarEquipe.IdadePiloto4;
		data.Save_LB_TempoContratoP4 = PaginaInicial.ContratoP4;
		
		data.Save_LB_NomeP5 = SelecionarEquipe.NomePiloto5;
		data.Save_LB_BandeiraP5 = SelecionarEquipe.PaisPiloto5;
		data.Save_LB_NumP5 = SelecionarEquipe.NumeroPiloto5;
		data.Save_LB_IdadeP5 = SelecionarEquipe.IdadePiloto5;
		data.Save_LB_TempoContratoP5 = PaginaInicial.ContratoP5;

		try {
			GerenciarRecursos.Salvar(data, ""+data.Save);
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Não é possível salvar! Erro: "+ ee.getMessage());
		}
		
		ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/Imagens/Icone64pxConfirmacao.png"));
		
		JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!","Salvo com sucesso!", 
		JOptionPane.ERROR_MESSAGE, icon);

	}

	public void ReCarrega(){

		SalvaDado dat = new SalvaDado();

			try {
				
			SalvaDado data = (SalvaDado) GerenciarRecursos.Consultar(""+AddExt);
			
//A partir daqui é tudo igual!
			
			Pagina_Inicial PaginaInicial = new Pagina_Inicial();
			Selecionar_Categoria Categoria = new Selecionar_Categoria();
			Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
			
			PaginaInicial.setVisible(true);
			PaginaInicial.setLocationRelativeTo(null);
			
			PaginaInicial.LB_NomeDirigente.setText(""+data.Save_NomeDirigente);
			//Colocar as outras variáveis aqui como no exemplo:
			
			Categoria.BT_Cate = data.Save_Categoria;
			SelecionarEquipe.ano = data.Save_Ano;
			//PaginaInicial.LBTeste.setText("Ano: "+data.Save_Ano+ " Categoria: "+Categoria.BT_Cate);
			
			PaginaInicial.EtapaAtual = data.Save_EtapaAtual;
			PaginaInicial.EtapaTotais = data.Save_EtapaTotais;
			PaginaInicial.TEMPORADAJOGADOR = data.Save_TemporadaJogador;

			SelecionarEquipe.LogoEquipeEscolhida = data.Save_LB_LogoEquipe;
			
			SelecionarEquipe.LogoEquipeEscolhida = data.Save_LB_LogoEquipe;
			SelecionarEquipe.LogoMotorPQEscolhido = data.Save_LB_LogoMotorPQ;
			SelecionarEquipe.BandeiraEquipeEscolhida = data.Save_LB_BandeiraEquipe;
			SelecionarEquipe.BandeiraMotorEscolhido = data.Save_LB_BandeiraMotor;
			SelecionarEquipe.BandeiraSedeEquipeEscolhida = data.Save_LB_BandeiraSede;
				
			SelecionarEquipe.NomeEquipeEscolhida = data.Save_LB_NomeEquipe;
			SelecionarEquipe.SedeEquipeEscolhida = data.Save_LB_SedeEquipe;
			SelecionarEquipe.NomeMotorEscolhido = data.Save_LB_Motor;
			SelecionarEquipe.ano = data.Save_LB_Ano;
			
			SelecionarEquipe.NomePiloto1 = data.Save_LB_NomeP1;
			SelecionarEquipe.PaisPiloto1 = data.Save_LB_BandeiraP1;
			SelecionarEquipe.NumeroPiloto1 = data.Save_LB_NumP1;
			SelecionarEquipe.IdadePiloto1 = data.Save_LB_IdadeP1;
			PaginaInicial.ContratoP1 = data.Save_LB_TempoContratoP1;
			
			SelecionarEquipe.NomePiloto2 = data.Save_LB_NomeP2;
			SelecionarEquipe.PaisPiloto2 = data.Save_LB_BandeiraP2;
			SelecionarEquipe.NumeroPiloto2 = data.Save_LB_NumP2;
			SelecionarEquipe.IdadePiloto2 = data.Save_LB_IdadeP2;
			PaginaInicial.ContratoP2 = data.Save_LB_TempoContratoP2;
			
			SelecionarEquipe.NomePiloto3 = data.Save_LB_NomeP3;
			SelecionarEquipe.PaisPiloto3 = data.Save_LB_BandeiraP3;
			SelecionarEquipe.NumeroPiloto3 = data.Save_LB_NumP3;
			SelecionarEquipe.IdadePiloto3 = data.Save_LB_IdadeP3;
			PaginaInicial.ContratoP3 = data.Save_LB_TempoContratoP3;
			
			SelecionarEquipe.NomePiloto4 = data.Save_LB_NomeP4;
			SelecionarEquipe.PaisPiloto4 = data.Save_LB_BandeiraP4;
			SelecionarEquipe.NumeroPiloto4 = data.Save_LB_NumP4;
			SelecionarEquipe.IdadePiloto4 = data.Save_LB_IdadeP4;
			PaginaInicial.ContratoP4 = data.Save_LB_TempoContratoP4;
			
			SelecionarEquipe.NomePiloto5 = data.Save_LB_NomeP5;
			SelecionarEquipe.PaisPiloto5 = data.Save_LB_BandeiraP5;
			SelecionarEquipe.NumeroPiloto5 = data.Save_LB_NumP5;
			SelecionarEquipe.IdadePiloto5 = data.Save_LB_IdadeP5;
			PaginaInicial.ContratoP5 = data.Save_LB_TempoContratoP5;
			
			if (PaginaInicial.TEMPORADAJOGADOR == 0) {
				PaginaInicial.TEMPORADAJOGADOR = 1;
			}
			
			if (PaginaInicial.EtapaAtual == 0) {
				PaginaInicial.EtapaAtual = 1;
			}
			
			if (PaginaInicial.Etapa == 0) {
				PaginaInicial.Etapa = 1;
			}
			
			PaginaInicial.LB_Temporada.setText("Temporada "+PaginaInicial.TEMPORADAJOGADOR+", Etapa "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais);
			PaginaInicial.LB_PistaEtapas.setText("PRÓXIMA ETAPA, "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais);
			PaginaInicial.LB_Ano.setText("Ano "+data.Save_Ano);

//Igual até aqui!
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
}
