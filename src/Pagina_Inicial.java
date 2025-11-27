import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.function.ToLongBiFunction;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JFileChooser;

public class Pagina_Inicial extends JFrame {

	private JPanel contentPane;
	public static JLabel lblNewLabel;
	public static JLabel lblNewLabel_1;
	public static JLabel lblNewLabel_2;
	public static JLabel lblNewLabel_3;
	public static JLabel lblNewLabel_4;
	public static JLabel LB_LogoEquipe;
	public static JLabel LB_LogoMotorPQ;
	public static JLabel LB_BandeiraMotor;
	public static JLabel LB_BandeiraEquipe;
	public static JLabel LB_BandeiraSede;
	public static JLabel LB_BandeiraP1;
	public static JLabel LB_BandeiraP2;
	public static JLabel LB_BandeiraP3;
	public static JLabel LB_BandeiraP4;
	public static JLabel LB_BandeiraP5;
	public static JLabel LB_CategoriaEscolhida;
	public static JLabel LB_NomeEquipe;
	public static JLabel LB_SedeEquipe;
	public static JLabel LB_Motor;
	public static JLabel LB_Ano;
	public static JLabel LB_Temporada;
	public static JLabel lblNewLabel_22;
	public static JLabel LB_NumP1;
	public static JLabel LB_NumP2;
	public static JLabel LB_NumP3;
	public static JLabel LB_NumP5;
	public static JLabel LB_NumP4;
	public static JLabel LB_NomeP1;
	public static JLabel LB_NomeP2;
	public static JLabel LB_NomeP3;
	public static JLabel LB_NomeP4;
	public static JLabel LB_NomeP5;
	public static JLabel LB_IdadeP1;
	public static JLabel LB_IdadeP2;
	public static JLabel LB_IdadeP3;
	public static JLabel LB_IdadeP4;
	public static JLabel LB_IdadeP5;
	public static JLabel LB_TempoContratoP1;
	public static JLabel LB_TempoContratoP2;
	public static JLabel LB_TempoContratoP3;
	public static JLabel LB_TempoContratoP4;
	public static JLabel LB_TempoContratoP5;
	public static JLabel LB_PistaEtapas;
	public static int ano_pista, Etapa=1, EtapaTotais=1, teste, TEMPORADAJOGADOR=1, EtapaAtual=1, 
			ContratoP1=1 ,ContratoP2=1,ContratoP3=1,ContratoP4=1,ContratoP5=1;
	public static JLabel LB_CapacidadePista;
	public static JLabel LB_TipoPista;
	public static JLabel LB_InauguracaoPista;
	public static JLabel LB_CurvaPista;
	public static JLabel LB_ComprimentoPista;
	public static JLabel LB_VoltaPista;
	public static JLabel LB_LocalPista;
	public static JButton BT_EtapaAnt;
	private JButton BT_DetalhesPista;
	public static JButton BT_EtapaProxima;
	private static JLabel LB_BandeiraPista;
	private JLabel LB_Classificacoes;
	private JTabbedPane tabbedPane;
	private JPanel Tab_ClassicacaoEquipes;
	private JPanel Tab_ClassicacaoPilotos;
	private JPanel panel;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JFileChooser fileChooser;
	public static File ArquivoSalvo;
	private File ArquivoCarregado;
	public static JLabel LB_NomeDirigente;
	public static JLabel LB_ImagemPista;
	public static String Playoffs = "";
	private JButton BT_VoltarAtual;
	private JLabel LB_CarrosCat;
	int confirmacao =1;
	public static JLabel LB_Orc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_Inicial frame = new Pagina_Inicial();
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
	public Pagina_Inicial() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
				Selecionar_Categoria Categoria = new Selecionar_Categoria();
				Dados dados = new Dados();
				SalvaDado data = new SalvaDado();
				
				if(EtapaAtual <= 1) {
					LB_Classificacoes.setText("CLASSIFICAÇÃO DOS CAMPEONATOS");		
				}else if(EtapaAtual == 2) {
					LB_Classificacoes.setText("CLASSIFICAÇÃO DOS CAMPEONATOS APÓS 1 ETAPA");		
				}else if(EtapaAtual >2) {
					LB_Classificacoes.setText("CLASSIFICAÇÃO DOS CAMPEONATOS APÓS "+(EtapaAtual - 1)+" ETAPAS");
				}
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					LB_CategoriaEscolhida.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo Novo_F1_OKPQ.png")));
					//LB_CarrosCat.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Banner F1_OK.png")));
					LB_TipoPista.setForeground(new Color(0, 0, 0));
					CriaCalendarioF1();
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					LB_CategoriaEscolhida.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo Indy_OKPQ.png")));
					//LB_CarrosCat.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Banner F1_OK.png")));
					LB_TipoPista.setForeground(new Color(0, 0, 255));
					CriaCalendarioFIndy();
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					LB_CategoriaEscolhida.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo Nascar_OKPQ.png")));
					//LB_CarrosCat.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Talladega-1.jpg")));
					LB_LogoEquipe.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.LogoEquipeEscolhida)));
					LB_TipoPista.setForeground(new Color(0, 0, 255));
					CriaCalendarioNascar();
					
				}
				
				SetDadosMinhaEquipe();
				
				if (TEMPORADAJOGADOR == 0) {
					TEMPORADAJOGADOR = 1;
				}
				
				if (EtapaAtual == 0) {
					EtapaAtual = 1;
				}
				
				if (Etapa == 0) {
					Etapa = 1;
				}
				

				if (Etapa > EtapaTotais) {
					Etapa=1;
				}
				
				CarregaEtapaAtual();
				
				LB_Temporada.setText("Temporada "+TEMPORADAJOGADOR+", Etapa "+EtapaAtual+"/"+EtapaTotais);
				LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 674);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		setJMenuBar(menuBar);
		
		JMenu Menu_Geral = new JMenu("Geral");
		JMenu Menu_Equipe = new JMenu("Equipe");
		JMenu Menu_Piloto = new JMenu("Pilotos");
		JMenu Menu_Financeiro = new JMenu("Financeiro");
		JMenu Menu_Historia = new JMenu("História");

		JMenuItem MenuItem_NovoJogo = new JMenuItem("Iniciar Novo Jogo");
		MenuItem_NovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EtapaAtual = 0;
				
				Selecionar_Categoria Categoria = new Selecionar_Categoria();
				Categoria.setVisible(true);
				Categoria.setLocationRelativeTo(null);
				
				dispose();	
			}
		});
		
		JMenuItem MenuItem_Salvar = new JMenuItem("Salvar Jogo");
		MenuItem_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Inicial_Carregar InicialCarregar = new Inicial_Carregar();
				
				InicialCarregar.SalvaDados();
				
				if (InicialCarregar.RESULTADO_SALVA == 0) {
					
				}else {
				
				InicialCarregar.ReCarrega();
				
				dispose();
				
				}
				
			}
		});
		
		JMenuItem MenuItem_Carregar = new JMenuItem("Carregar Jogo");
		MenuItem_Carregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inicial_Carregar InicialCarregar = new Inicial_Carregar();
				
					InicialCarregar.CarregaDados();
					
					LB_PistaEtapas.setForeground(new Color(0, 0, 0));
					Playoffs = "";
					
					Etapa = EtapaAtual;
					
					if (InicialCarregar.RESULTADO_SALVA == 4) {
			
						dispose();
						
					}
			}
		});
		
		menuBar.add(Menu_Geral);
		menuBar.add(Menu_Equipe);
		menuBar.add(Menu_Piloto);
		menuBar.add(Menu_Financeiro);
		menuBar.add(Menu_Historia);

		JMenuItem MenuItem_ContaEquipe = new JMenuItem("Contas da Equipe");
		
		JMenuItem MenuItem_Fabrica = new JMenuItem("Fábrica");
		JMenuItem MenuItem_Patrocinio = new JMenuItem("Patrocínios");
		JMenuItem MenuItem_MeusPilotos = new JMenuItem("Meus Pilotos");
		JMenuItem MenuItem_MercadoPilotos = new JMenuItem("Mercado de Pilotos");
		JMenuItem MenuItem_Emprestimo = new JMenuItem("Empréstimo");
		JMenuItem MenuItem_HistoriaEquipes = new JMenuItem("Equipes Campeãs");
		JMenuItem MenuItem_HistoriaPilotos = new JMenuItem("Pilotos Campeões");

		Menu_Geral.add(MenuItem_NovoJogo);
		Menu_Geral.add(MenuItem_Salvar);
		Menu_Geral.add(MenuItem_Carregar);
		Menu_Equipe.add(MenuItem_Fabrica);
		Menu_Equipe.add(MenuItem_Patrocinio);
		Menu_Piloto.add(MenuItem_MeusPilotos);
		Menu_Piloto.add(MenuItem_MercadoPilotos);
		Menu_Financeiro.add(MenuItem_ContaEquipe);
		Menu_Financeiro.add(MenuItem_Emprestimo);
		Menu_Historia.add(MenuItem_HistoriaEquipes);
		Menu_Historia.add(MenuItem_HistoriaPilotos);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		LB_LogoEquipe = new JLabel("");
		LB_LogoEquipe.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_LogoEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_LogoEquipe.setBackground(new Color(255, 255, 255));
		LB_LogoEquipe.setBounds(10, 0, 200, 100);
		contentPane.add(LB_LogoEquipe);
		
		LB_LogoMotorPQ = new JLabel("");
		LB_LogoMotorPQ.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_LogoMotorPQ.setHorizontalAlignment(SwingConstants.CENTER);
		LB_LogoMotorPQ.setBounds(270, 34, 45, 33);
		contentPane.add(LB_LogoMotorPQ);
		
		LB_BandeiraSede = new JLabel("");
		LB_BandeiraSede.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraSede.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraSede.setBounds(210, 67, 65, 33);
		contentPane.add(LB_BandeiraSede);
		
		LB_BandeiraEquipe = new JLabel("");
		LB_BandeiraEquipe.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraEquipe.setBounds(210, 0, 65, 33);
		contentPane.add(LB_BandeiraEquipe);
		
		LB_BandeiraMotor = new JLabel("");
		LB_BandeiraMotor.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraMotor.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraMotor.setBounds(210, 34, 65, 33);
		contentPane.add(LB_BandeiraMotor);
		
		LB_BandeiraP1 = new JLabel("");
		LB_BandeiraP1.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraP1.setBounds(10, 135, 25, 25);
		contentPane.add(LB_BandeiraP1);
		
		LB_BandeiraP2 = new JLabel("");
		LB_BandeiraP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraP2.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraP2.setBounds(10, 160, 25, 25);
		contentPane.add(LB_BandeiraP2);
		
		LB_BandeiraP3 = new JLabel("");
		LB_BandeiraP3.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraP3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraP3.setBounds(10, 185, 25, 25);
		contentPane.add(LB_BandeiraP3);
		
		LB_BandeiraP4 = new JLabel("");
		LB_BandeiraP4.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraP4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraP4.setBounds(10, 210, 25, 25);
		contentPane.add(LB_BandeiraP4);
		
		LB_BandeiraP5 = new JLabel("");
		LB_BandeiraP5.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraP5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraP5.setBounds(10, 235, 25, 25);
		contentPane.add(LB_BandeiraP5);
		
		LB_CategoriaEscolhida = new JLabel("");
		LB_CategoriaEscolhida.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_CategoriaEscolhida.setHorizontalAlignment(SwingConstants.CENTER);
		LB_CategoriaEscolhida.setBackground(Color.WHITE);
		LB_CategoriaEscolhida.setBounds(487, 0, 215, 100);
		contentPane.add(LB_CategoriaEscolhida);
		
		LB_NomeEquipe = new JLabel("Equipe X");
		LB_NomeEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NomeEquipe.setBounds(275, 0, 200, 33);
		contentPane.add(LB_NomeEquipe);
		
		LB_SedeEquipe = new JLabel("Sede X");
		LB_SedeEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_SedeEquipe.setBounds(275, 67, 200, 33);
		contentPane.add(LB_SedeEquipe);
		
		LB_Motor = new JLabel("Motor X");
		LB_Motor.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Motor.setBounds(275, 34, 200, 33);
		contentPane.add(LB_Motor);
		
		LB_Ano = new JLabel("Ano");
		LB_Ano.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_Ano.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Ano.setBounds(700, 50, 100, 25);
		contentPane.add(LB_Ano);
		
		LB_Temporada = new JLabel("Temporada 1, Etapa 1/4");
		LB_Temporada.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_Temporada.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Temporada.setBounds(700, 75, 250, 25);
		contentPane.add(LB_Temporada);
		
		lblNewLabel_22 = new JLabel("MEUS PILOTOS");
		lblNewLabel_22.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_22.setBounds(10, 110, 465, 25);
		contentPane.add(lblNewLabel_22);
		
		LB_NumP1 = new JLabel("#100");
		LB_NumP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NumP1.setBounds(35, 135, 35, 25);
		contentPane.add(LB_NumP1);
		
		LB_NumP2 = new JLabel("#100");
		LB_NumP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NumP2.setBounds(35, 160, 35, 25);
		contentPane.add(LB_NumP2);
		
		LB_NumP3 = new JLabel("#100");
		LB_NumP3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NumP3.setBounds(35, 185, 35, 25);
		contentPane.add(LB_NumP3);
		
		LB_NumP5 = new JLabel("#100");
		LB_NumP5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NumP5.setBounds(35, 235, 35, 25);
		contentPane.add(LB_NumP5);
		
		LB_NumP4 = new JLabel("#100");
		LB_NumP4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NumP4.setBounds(35, 210, 35, 25);
		contentPane.add(LB_NumP4);
		
		LB_NomeP1 = new JLabel("New label");
		LB_NomeP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14));
		LB_NomeP1.setBounds(70, 135, 205, 25);
		contentPane.add(LB_NomeP1);
		
		LB_NomeP2 = new JLabel("New label");
		LB_NomeP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14));
		LB_NomeP2.setBounds(70, 160, 205, 25);
		contentPane.add(LB_NomeP2);
		
		LB_NomeP3 = new JLabel("New label");
		LB_NomeP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14));
		LB_NomeP3.setBounds(70, 185, 205, 25);
		contentPane.add(LB_NomeP3);
		
		LB_NomeP4 = new JLabel("New label");
		LB_NomeP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14));
		LB_NomeP4.setBounds(70, 210, 205, 25);
		contentPane.add(LB_NomeP4);
		
		LB_NomeP5 = new JLabel("New label");
		LB_NomeP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14));
		LB_NomeP5.setBounds(70, 235, 205, 25);
		contentPane.add(LB_NomeP5);
		
		LB_IdadeP1 = new JLabel("27 anos");
		LB_IdadeP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_IdadeP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IdadeP1.setBounds(275, 135, 50, 25);
		contentPane.add(LB_IdadeP1);
		
		LB_IdadeP2 = new JLabel("New label");
		LB_IdadeP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IdadeP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_IdadeP2.setBounds(275, 160, 50, 25);
		contentPane.add(LB_IdadeP2);
		
		LB_IdadeP3 = new JLabel("New label");
		LB_IdadeP3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IdadeP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_IdadeP3.setBounds(275, 185, 50, 25);
		contentPane.add(LB_IdadeP3);
		
		LB_IdadeP4 = new JLabel("New label");
		LB_IdadeP4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IdadeP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_IdadeP4.setBounds(275, 210, 50, 25);
		contentPane.add(LB_IdadeP4);
		
		LB_IdadeP5 = new JLabel("New label");
		LB_IdadeP5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IdadeP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_IdadeP5.setBounds(275, 235, 50, 25);
		contentPane.add(LB_IdadeP5);
		
		LB_TempoContratoP1 = new JLabel("Contrato até 2024");
		LB_TempoContratoP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TempoContratoP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TempoContratoP1.setBounds(330, 135, 145, 25);
		contentPane.add(LB_TempoContratoP1);
		
		LB_TempoContratoP2 = new JLabel("Contrato até 2024");
		LB_TempoContratoP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TempoContratoP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TempoContratoP2.setBounds(330, 160, 145, 25);
		contentPane.add(LB_TempoContratoP2);
		
		LB_TempoContratoP3 = new JLabel("Contrato até 2024");
		LB_TempoContratoP3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TempoContratoP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TempoContratoP3.setBounds(330, 185, 145, 25);
		contentPane.add(LB_TempoContratoP3);
		
		LB_TempoContratoP4 = new JLabel("Contrato até 2024");
		LB_TempoContratoP4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TempoContratoP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TempoContratoP4.setBounds(330, 210, 145, 25);
		contentPane.add(LB_TempoContratoP4);
		
		LB_TempoContratoP5 = new JLabel("Contrato até 2024");
		LB_TempoContratoP5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TempoContratoP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TempoContratoP5.setBounds(330, 235, 145, 25);
		contentPane.add(LB_TempoContratoP5);

		LB_PistaEtapas = new JLabel("CALENDÁRIO, ETAPA 1/1 ");
		LB_PistaEtapas.setForeground(new Color(0, 0, 0));
		LB_PistaEtapas.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PistaEtapas.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		LB_PistaEtapas.setBounds(484, 111, 465, 25);
		contentPane.add(LB_PistaEtapas);
		
		LB_CapacidadePista = new JLabel("1");
		LB_CapacidadePista.setForeground(new Color(0, 0, 0));
		LB_CapacidadePista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_CapacidadePista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_CapacidadePista.setBounds(744, 327, 205, 30);
		contentPane.add(LB_CapacidadePista);
		
		LB_InauguracaoPista = new JLabel("1");
		LB_InauguracaoPista.setForeground(new Color(0, 0, 0));
		LB_InauguracaoPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_InauguracaoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_InauguracaoPista.setBounds(744, 207, 205, 30);
		contentPane.add(LB_InauguracaoPista);
		
		LB_TipoPista = new JLabel("1");
		LB_TipoPista.setForeground(new Color(0, 0, 0));
		LB_TipoPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TipoPista.setBounds(744, 177, 205, 30);
		contentPane.add(LB_TipoPista);
		
		LB_CurvaPista = new JLabel("1");
		LB_CurvaPista.setForeground(new Color(0, 0, 0));
		LB_CurvaPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_CurvaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_CurvaPista.setBounds(744, 237, 205, 30);
		contentPane.add(LB_CurvaPista);
		
		LB_ComprimentoPista = new JLabel("1");
		LB_ComprimentoPista.setForeground(new Color(0, 0, 0));
		LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_ComprimentoPista.setBounds(744, 267, 205, 30);
		contentPane.add(LB_ComprimentoPista);
		
		LB_VoltaPista = new JLabel("1");
		LB_VoltaPista.setForeground(new Color(0, 0, 0));
		LB_VoltaPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_VoltaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_VoltaPista.setBounds(744, 297, 205, 30);
		contentPane.add(LB_VoltaPista);
		
		LB_LocalPista = new JLabel("1");
		LB_LocalPista.setForeground(new Color(0, 0, 0));
		LB_LocalPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_LocalPista.setBounds(744, 147, 180, 30);
		contentPane.add(LB_LocalPista);
		
		BT_EtapaAnt = new JButton("Etapa Anterior");
		BT_EtapaAnt.setEnabled(false);
		BT_EtapaAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				
				Etapa = Etapa - 1;
				//LB_PistaEtapas.setText("PRÓXIMA ETAPA, "+Etapa+"/"+EtapaTotais);
				if (Etapa == 1) {
					BT_EtapaAnt.setEnabled(false);
				}
				if (Etapa < EtapaTotais) {
					BT_EtapaProxima.setEnabled(true);
				}

				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					CriaCalendarioF1();
					
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					CriaCalendarioFIndy();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					CriaCalendarioNascar();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
				}

			}
		});
		BT_EtapaAnt.setBounds(487, 368, 225, 23);
		contentPane.add(BT_EtapaAnt);
		
		BT_EtapaProxima = new JButton("Próxima Etapa");
		BT_EtapaProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				
				Etapa = Etapa + 1;
				
				if (Etapa > 1) {
					BT_EtapaAnt.setEnabled(true);
				}
				if (Etapa == EtapaTotais) {
					BT_EtapaProxima.setEnabled(false);
				}
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					CriaCalendarioF1();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					CriaCalendarioFIndy();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					CriaCalendarioNascar();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
				}
				
			}
		});
		BT_EtapaProxima.setBounds(722, 368, 225, 23);
		contentPane.add(BT_EtapaProxima);
		
		BT_DetalhesPista = new JButton("Ver Traçado Detalhado");
		BT_DetalhesPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DetalhesPista DetalhePista = new DetalhesPista();
				DetalhePista.setVisible(true);
				DetalhePista.setLocationRelativeTo(null);
			}
		});
		BT_DetalhesPista.setBounds(487, 402, 225, 23);
		contentPane.add(BT_DetalhesPista);
		
		LB_BandeiraPista = new JLabel("");
		LB_BandeiraPista.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista.setBounds(916, 147, 45, 33);
		contentPane.add(LB_BandeiraPista);
		
		LB_Classificacoes = new JLabel("CLASSIFICAÇÃO DOS CAMPEONATOS APÓS X ETAPAS");
		LB_Classificacoes.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Classificacoes.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		LB_Classificacoes.setBounds(10, 271, 465, 25);
		contentPane.add(LB_Classificacoes);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 307, 465, 299);
		contentPane.add(tabbedPane);
		
		Tab_ClassicacaoPilotos = new JPanel();
		tabbedPane.addTab("Classificação Pilotos", new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone24pxPiloto.png")), Tab_ClassicacaoPilotos, null);
		Tab_ClassicacaoPilotos.setLayout(null);
		
		lblNewLabel_6 = new JLabel("Classificação de pilotos");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 11, 440, 177);
		Tab_ClassicacaoPilotos.add(lblNewLabel_6);
		
		Tab_ClassicacaoEquipes = new JPanel();
		tabbedPane.addTab("Classificação Equipes", new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone24pxEquipe.png")), Tab_ClassicacaoEquipes, null);
		Tab_ClassicacaoEquipes.setLayout(null);
		
		lblNewLabel_5 = new JLabel("Classificação de equipes");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 11, 440, 177);
		Tab_ClassicacaoEquipes.add(lblNewLabel_5);
		
		panel = new JPanel();
		tabbedPane.addTab("Resultados", new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone24pxTrofeu.png")), panel, null);
		panel.setLayout(null);
		
		lblNewLabel_7 = new JLabel("Resultados da temporada");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 11, 440, 177);
		panel.add(lblNewLabel_7);
		
		LB_NomeDirigente = new JLabel("Nome Dirigente");
		LB_NomeDirigente.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		LB_NomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NomeDirigente.setBounds(712, 0, 239, 49);
		contentPane.add(LB_NomeDirigente);
		
		LB_ImagemPista = new JLabel("");
		LB_ImagemPista.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_ImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_ImagemPista.setBounds(484, 147, 250, 203);
		LB_ImagemPista.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_ImagemPista);
		
		BT_VoltarAtual = new JButton("Voltar Etapa Atual");
		BT_VoltarAtual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CarregaEtapaAtual();
				
				/*
				Etapa = EtapaAtual;
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					CriaCalendarioF1();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					CriaCalendarioFIndy();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					CriaCalendarioNascar();
					LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
				}
				
				*/
			}
		});
		BT_VoltarAtual.setBounds(722, 402, 225, 23);
		contentPane.add(BT_VoltarAtual);
		
		JButton BT_DetalhesPista_1 = new JButton("IR PARA A CORRIDA!");
		BT_DetalhesPista_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pagina_Treino PaginaTreino = new Pagina_Treino();
				PaginaTreino.setVisible(true);
				PaginaTreino.setLocationRelativeTo(null);

				dispose();
			}
		});
		BT_DetalhesPista_1.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Icone24pxBandeiraDeChegada.png")));
		BT_DetalhesPista_1.setBounds(487, 573, 460, 33);
		contentPane.add(BT_DetalhesPista_1);
		
		LB_CarrosCat = new JLabel("");
		LB_CarrosCat.setVerticalAlignment(SwingConstants.TOP);
		LB_CarrosCat.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/Banner F1_OK.png")));
		LB_CarrosCat.setHorizontalAlignment(SwingConstants.CENTER);
		LB_CarrosCat.setBounds(486, 436, 464, 126);
		contentPane.add(LB_CarrosCat);
		
		LB_Orc = new JLabel("€ milhões");
		LB_Orc.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Orc.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_Orc.setBounds(804, 50, 100, 25);
		contentPane.add(LB_Orc);
		
		JLabel LB_BandeiraPista_1 = new JLabel("");
		LB_BandeiraPista_1.setIcon(new ImageIcon(Pagina_Inicial.class.getResource("/Imagens/IconeEuro24px.png")));
		LB_BandeiraPista_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista_1.setBounds(906, 50, 45, 25);
		contentPane.add(LB_BandeiraPista_1);
		
	}
	
	public static void CarregaEtapaAtual() {
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		Etapa = EtapaAtual;
		
		if(Categoria.BT_Cate.equals("Fórmula 1")) {
			
			CriaCalendarioF1();
			LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
			
		} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
			
			CriaCalendarioFIndy();
			LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
			
		} else if(Categoria.BT_Cate.equals("NASCAR")) {
			
			CriaCalendarioNascar();
			VerificaPlayoffs ();
			LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+Etapa+"/"+EtapaTotais+""+Playoffs);
		}
		
		if (Etapa > 1) {
			BT_EtapaAnt.setEnabled(true);
		}
		if (Etapa == EtapaTotais) {
			BT_EtapaProxima.setEnabled(false);
		}
		
		if (Etapa == 1) {
			BT_EtapaAnt.setEnabled(false);
		}
		if (Etapa < EtapaTotais) {
			BT_EtapaProxima.setEnabled(true);
		}
		
	}
	
	public static void VerificaPlayoffs () {
		
		if(Etapa <= 26) {
			LB_PistaEtapas.setForeground(new Color(0, 0, 0));
			Playoffs = "";
			SetDadosPista();
		}else if(Etapa == 27) {
			Playoffs = " - PLAYOFFS Round of 16, 1/3";
			LB_PistaEtapas.setForeground(new Color(255, 0, 0));
			SetDadosPista();
		}else if(Etapa == 28) {
			Playoffs = " - PLAYOFFS Round of 16, 2/3";
			SetDadosPista();
		}else if(Etapa == 29) {
			Playoffs = " - PLAYOFFS Round of 16, 3/3";
			SetDadosPista();
		}else if(Etapa == 30) {
			Playoffs = " - PLAYOFFS Round of 12, 1/3";
			SetDadosPista();
		}else if(Etapa == 31) {
			Playoffs = " - PLAYOFFS Round of 12, 2/3";
			SetDadosPista();
		}else if(Etapa == 32) {
			Playoffs = " - PLAYOFFS Round of 12, 3/3";
			SetDadosPista();
		}else if(Etapa == 33) {
			Playoffs = " - PLAYOFFS Round of 8, 1/3";
			SetDadosPista();
		}else if(Etapa == 34) {
			Playoffs = " - PLAYOFFS Round of 8, 2/3";
			SetDadosPista();
		}else if(Etapa == 35) {
			Playoffs = " - PLAYOFFS Round of 8, 3/3";
			SetDadosPista();
		}else if(Etapa == 36) {
			Playoffs = " - PLAYOFFS Championship 4";
			SetDadosPista();
		}
		
	}
	
	public static void SetDadosPista() {
		
		LB_ImagemPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.LayoutPistaPQ)));	
		LB_LocalPista.setText(""+Dados.LocalPista);
		LB_CapacidadePista.setText(""+Dados.CapacidadePista);
		LB_InauguracaoPista.setText(""+Dados.InauguracaoPista);
		LB_TipoPista.setText(""+Dados.TipoPista);
		LB_ComprimentoPista.setText(""+Dados.ComprimentoPista);
		LB_CurvaPista.setText(""+Dados.Curvas_Pista);
		LB_VoltaPista.setText(""+Dados.Voltas_Pista+" voltas");
		LB_BandeiraPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
		
	}
	
	public static void SetDadosMinhaEquipe() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		LB_NomeDirigente.setText(""+SelecionarEquipe.NomeDirigente);
		
		LB_LogoEquipe.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.LogoEquipeEscolhida)));
	//	LB_LogoMotor.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.LogoMotorEscolhido)));
		LB_LogoMotorPQ.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.LogoMotorPQEscolhido)));
		LB_BandeiraEquipe.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.BandeiraEquipeEscolhida)));
		LB_BandeiraMotor.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.BandeiraMotorEscolhido)));
		LB_BandeiraSede.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+SelecionarEquipe.BandeiraSedeEquipeEscolhida)));
			
	//	TFEquipeSelec.setText(""+SelecionarEquipe.EquipeEscolhida);
		LB_NomeEquipe.setText(""+SelecionarEquipe.NomeEquipeEscolhida);
		LB_SedeEquipe.setText(""+SelecionarEquipe.SedeEquipeEscolhida);
		LB_Motor.setText("Motor "+SelecionarEquipe.NomeMotorEscolhido);
	//	TFFund.setText(""+SelecionarEquipe.FundacaoEquipeEscolhida);
		LB_Ano.setText("Ano "+SelecionarEquipe.ano);
		LB_Orc.setText("€"+Dados.Orcamento+" milhões");
		
		LB_NomeP1.setText(""+SelecionarEquipe.NomePiloto1);
		LB_BandeiraP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
		LB_NumP1.setText(""+SelecionarEquipe.NumeroPiloto1);
	//	Born1.setText(""+SelecionarEquipe.NascimentoPiloto1);
		LB_IdadeP1.setText(""+SelecionarEquipe.IdadePiloto1+" anos");
		LB_TempoContratoP1.setText("Contrato até "+(ContratoP1 + SelecionarEquipe.ano));
		
		if (SelecionarEquipe.NomePiloto2.equals("")) {
			LB_NomeP2.setText("");
			LB_BandeiraP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("")));
			LB_NumP2.setText("");
		//	Born2.setText("");
			LB_IdadeP2.setText("");
			LB_TempoContratoP2.setText("");
			LB_TempoContratoP1.setText("");
		}else {
			LB_NomeP2.setText(""+SelecionarEquipe.NomePiloto2);
			LB_BandeiraP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto2+".png")));
			LB_NumP2.setText(""+SelecionarEquipe.NumeroPiloto2);
		//	Born2.setText(""+SelecionarEquipe.NascimentoPiloto2);
			LB_IdadeP2.setText(""+SelecionarEquipe.IdadePiloto2+" anos");
			LB_TempoContratoP2.setText("Contrato até "+(ContratoP2 + SelecionarEquipe.ano));
		}
		
		
		if (SelecionarEquipe.NomePiloto3.equals("")) {
			LB_NomeP3.setText("");
			LB_BandeiraP3.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("")));
			LB_NumP3.setText("");
		//	Born3.setText("");
			LB_IdadeP3.setText("");
			LB_TempoContratoP3.setText("");
			LB_TempoContratoP3.setText("");
		}else {
			LB_NomeP3.setText(""+SelecionarEquipe.NomePiloto3);
			LB_BandeiraP3.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto3+".png")));
			LB_NumP3.setText(""+SelecionarEquipe.NumeroPiloto3);
		//	Born3.setText(""+SelecionarEquipe.NascimentoPiloto3);
			LB_IdadeP3.setText(SelecionarEquipe.IdadePiloto3+" anos");
			LB_TempoContratoP3.setText("Contrato até "+(ContratoP3 + SelecionarEquipe.ano));
		}
		
		if (SelecionarEquipe.NomePiloto4.equals("")) {
			LB_NomeP4.setText("");
			LB_BandeiraP4.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("")));
			LB_NumP4.setText("");
		//	Born2.setText("");
			LB_IdadeP4.setText("");
			LB_TempoContratoP4.setText("");
			LB_TempoContratoP4.setText("");
		}else {
			LB_NomeP4.setText(""+SelecionarEquipe.NomePiloto4);
			LB_BandeiraP4.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto4+".png")));
			LB_NumP4.setText(""+SelecionarEquipe.NumeroPiloto4);
		//	Born4.setText(""+SelecionarEquipe.NascimentoPiloto4);
			LB_IdadeP4.setText(""+SelecionarEquipe.IdadePiloto4+" anos");
			LB_TempoContratoP4.setText("Contrato até "+(ContratoP4 + SelecionarEquipe.ano));
		}
			
		if (SelecionarEquipe.NomePiloto5.equals("")) {
			LB_NomeP5.setText("");
			LB_BandeiraP5.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("")));
			LB_NumP5.setText("");
		//	Born5.setText("");
			LB_IdadeP5.setText("");
			LB_TempoContratoP5.setText("");
			LB_TempoContratoP5.setText("");
		}else {
			LB_NomeP5.setText(""+SelecionarEquipe.NomePiloto5);
			LB_BandeiraP5.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto5+".png")));
			LB_NumP5.setText(""+SelecionarEquipe.NumeroPiloto5);
		//	Born5.setText(""+SelecionarEquipe.NascimentoPiloto5);
			LB_IdadeP5.setText(""+SelecionarEquipe.IdadePiloto5+" anos");
			LB_TempoContratoP5.setText("Contrato até "+(ContratoP5 + SelecionarEquipe.ano));
		}
		
		
	}
	
	public static void CriaCalendarioF1() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		
		if(SelecionarEquipe.ano == 2024) {
			CalendarioF12024();
		}
		if(SelecionarEquipe.ano == 2023) {
			CalendarioF12023();
		}
		if(SelecionarEquipe.ano == 2022) {
			CalendarioF12022();
		}
		if(SelecionarEquipe.ano == 2021) {
			CalendarioF12021();
		}
		
	}
	
	public static void CriaCalendarioFIndy() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		
		if(SelecionarEquipe.ano == 2024) {
			CalendarioIndy2024();
		}
		
		if(SelecionarEquipe.ano == 2023) {

		}
		
		if(SelecionarEquipe.ano == 2022) {

		}
		
		if(SelecionarEquipe.ano == 2021) {

		}
		
	}
	
	public static void CriaCalendarioNascar() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		
		if(SelecionarEquipe.ano == 2024) {
			CalendarioNascar2024();
		}
		
		if(SelecionarEquipe.ano == 2023) {

		}
		
		if(SelecionarEquipe.ano == 2022) {

		}
		
		if(SelecionarEquipe.ano == 2021) {

		}
		
	}

/*Exemplo Calendário
 
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria Categoria = new Selecionar_Categoria();
	Dados dados = new Dados();
	
	EtapaTotais = 36;
	
	if(Etapa == 1) {
		
		SetDadosPista();
	}else if(Etapa == 2) {
		
		SetDadosPista();
	}else if(Etapa == 3) {

		SetDadosPista();
	}else if(Etapa == 4) {

		SetDadosPista();
	}else if(Etapa == 5) {

		SetDadosPista();
	}else if(Etapa == 6) {

		SetDadosPista();
	}else if(Etapa == 7) {

		SetDadosPista();
	}else if(Etapa == 8) {

		SetDadosPista();
	}else if(Etapa == 9) {

		SetDadosPista();
	}else if(Etapa == 10) {

		SetDadosPista();
	}else if(Etapa == 11) {

		SetDadosPista();
	}else if(Etapa == 12) {

		SetDadosPista();
	}else if(Etapa == 13) {

		SetDadosPista();
	}else if(Etapa == 14) {

		SetDadosPista();
	}else if(Etapa == 15) {

		SetDadosPista();
	}else if(Etapa == 16) {

		SetDadosPista();
	}else if(Etapa == 17) {

		SetDadosPista();
	}else if(Etapa == 18) {

		SetDadosPista();
	}else if(Etapa == 19) {

		SetDadosPista();
	}else if(Etapa == 20) {

		SetDadosPista();
	}else if(Etapa == 21) {

		SetDadosPista();
	}else if(Etapa == 22) {

		SetDadosPista();
	}else if(Etapa == 23) {

		SetDadosPista();
	}else if(Etapa == 24) {

		SetDadosPista();
	}else if(Etapa == 25) {

		SetDadosPista();
	}else if(Etapa == 26) {

		SetDadosPista();
	}else if(Etapa == 27) {

		SetDadosPista();
	}else if(Etapa == 28) {

		SetDadosPista();
	}else if(Etapa == 29) {

		SetDadosPista();
	}else if(Etapa == 30) {

		SetDadosPista();
	}else if(Etapa == 31) {

		SetDadosPista();
	}else if(Etapa == 32) {

		SetDadosPista();
	}else if(Etapa == 33) {

		SetDadosPista();
	}else if(Etapa == 34) {

		SetDadosPista();
	}else if(Etapa == 35) {

		SetDadosPista();
	}else if(Etapa == 36) {

		SetDadosPista();
	}	
	*/
	
	
	
//Calendario F1
	
	public static void CalendarioF12024() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		EtapaTotais = 24;
		
		if(Etapa == 1) {
			dados.Bahrain();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.Jeddah();
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.Melbourne();
			SetDadosPista();
		}else if(Etapa == 4) {
			dados.Suzuka();
			SetDadosPista();
		}else if(Etapa == 5) {
			dados.Shanghai();
			SetDadosPista();
		}else if(Etapa == 6) {
			dados.Miami();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.Imola();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.Monaco();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.Montreal();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.Catalunha();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.Austria();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.Silverstone();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Hungaroring();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Spa();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.Zandvoort();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Monza();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.Baku();
			SetDadosPista();
		}else if(Etapa == 18) {
			dados.Singapura();
			SetDadosPista();
		}else if(Etapa == 19) {
			dados.Austin();
			SetDadosPista();
		}else if(Etapa == 20) {
			dados.Mexico();
			SetDadosPista();
		}else if(Etapa == 21) {
			dados.Interlagos();
			SetDadosPista();
		}else if(Etapa == 22) {
			dados.LasVegas();
			SetDadosPista();
		}else if(Etapa == 23) {
			dados.Lusail();
			SetDadosPista();
		}else if(Etapa == 24) {
			dados.YasMarina();
			SetDadosPista();
		}
		
	}

	public static void CalendarioF12023() {
		
	Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
	Selecionar_Categoria Categoria = new Selecionar_Categoria();
	Dados dados = new Dados();
	
		EtapaTotais = 22;
		if(Etapa == 1) {
			dados.Bahrain();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.Jeddah();
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.Melbourne();
			SetDadosPista();
		}else if(Etapa == 4) {
			dados.Baku();
			SetDadosPista();
		}else if(Etapa == 5) {
			dados.Miami();
			SetDadosPista();
		}else if(Etapa == 6) {
			dados.Monaco();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.Catalunha();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.Montreal();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.Austria();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.Silverstone();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.Hungaroring();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.Spa();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Zandvoort();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Monza();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.Singapura();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Suzuka();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.Lusail();
			SetDadosPista();
		}else if(Etapa == 18) {
			dados.Austin();
			SetDadosPista();
		}else if(Etapa == 19) {
			dados.Mexico();
			SetDadosPista();
		}else if(Etapa == 20) {
			dados.Interlagos();
			SetDadosPista();
		}else if(Etapa == 21) {
			dados.LasVegas();
			SetDadosPista();
		}else if(Etapa == 22) {
			dados.YasMarina();
			SetDadosPista();
		}
	
	}

	public static void CalendarioF12022() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		EtapaTotais = 22;
		if(Etapa == 1) {
			dados.Bahrain();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.Jeddah();
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.Melbourne();
			SetDadosPista();
		}else if(Etapa == 4) {
			dados.Imola();
			SetDadosPista();
		}else if(Etapa == 5) {
			dados.Miami();
			SetDadosPista();
		}else if(Etapa == 6) {
			dados.Catalunha();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.Monaco();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.Baku();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.Montreal();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.Silverstone();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.Austria();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.PaulRicard();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Hungaroring();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Spa();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.Zandvoort();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Monza();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.Singapura();
			SetDadosPista();
		}else if(Etapa == 18) {
			dados.Suzuka();
			SetDadosPista();
		}else if(Etapa == 19) {
			dados.Austin();
			SetDadosPista();
		}else if(Etapa == 20) {
			dados.Mexico();
			SetDadosPista();
		}else if(Etapa == 21) {
			dados.Interlagos();
			SetDadosPista();
		}else if(Etapa == 22) {
			dados.YasMarina();
			SetDadosPista();
		}
		
	}
	
	public static void CalendarioF12021() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		EtapaTotais = 22;
		if(Etapa == 1) {
			dados.Bahrain();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.Imola();
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.Portimao();
			SetDadosPista();
		}else if(Etapa == 4) {
			dados.Catalunha();
			SetDadosPista();
		}else if(Etapa == 5) {
			dados.Monaco();
			SetDadosPista();
		}else if(Etapa == 6) {
			dados.Baku();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.PaulRicard();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.Austria();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.Austria();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.Silverstone();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.Hungaroring();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.Spa();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Zandvoort();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Monza();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.Sochi();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Istanbul();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.Austin();
			SetDadosPista();
		}else if(Etapa == 18) {
			dados.Mexico();
			SetDadosPista();
		}else if(Etapa == 19) {
			dados.Interlagos();
			SetDadosPista();
		}else if(Etapa == 20) {
			dados.Lusail();
			SetDadosPista();
		}else if(Etapa == 21) {
			dados.Jeddah();
			SetDadosPista();
		}else if(Etapa == 22) {
			dados.YasMarina();
			SetDadosPista();
		}
		
	}

//Calendario NASCAR

	public static void CalendarioNascar2024() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		EtapaTotais = 36;
		
		if(Etapa == 1) {
			dados.Daytona500();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.AtlantaAmbetter();;
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.LasVegasPennzoil();
			SetDadosPista();
		}else if(Etapa == 4) {
			dados.Phoenix();
			SetDadosPista();
		}else if(Etapa == 5) {
			dados.Bristol();
			SetDadosPista();
		}else if(Etapa == 6) {
			dados.AustinNascar();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.Richmond();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.MartinvilleCookOut();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.Texas();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.TalladegaGeico();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.Dover();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.KansasAdventHealth();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Darlington400();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Charlotte();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.GatewayNascar();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Sonoma();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.IowaNascar();
			SetDadosPista();
		}else if(Etapa == 18) {
			dados.NewHampshire();
			SetDadosPista();
		}else if(Etapa == 19) {
			dados.NashvilleNascar();
			SetDadosPista();
		}else if(Etapa == 20) {
			dados.ChicagoStreet();
			SetDadosPista();
		}else if(Etapa == 21) {
			dados.Pocono();
			SetDadosPista();
		}else if(Etapa == 22) {
			dados.Indy400();
			SetDadosPista();
		}else if(Etapa == 23) {
			dados.Richmond();
			SetDadosPista();
		}else if(Etapa == 24) {
			dados.Michigan();
			SetDadosPista();
		}else if(Etapa == 25) {
			dados.Daytona400();
			SetDadosPista();
		}else if(Etapa == 26) {
			VerificaPlayoffs ();
			dados.Darlington500();
			SetDadosPista();
		}else if(Etapa == 27) {
			VerificaPlayoffs ();
			dados.AtlantaQuaker();
			SetDadosPista();
		}else if(Etapa == 28) {
			VerificaPlayoffs ();
			dados.WatkinsGlen();
			SetDadosPista();
		}else if(Etapa == 29) {
			VerificaPlayoffs ();
			dados.BristolNight();
			SetDadosPista();
		}else if(Etapa == 30) {
			VerificaPlayoffs ();
			dados.KansasHollywood();
			SetDadosPista();
		}else if(Etapa == 31) {
			VerificaPlayoffs ();
			dados.TalladegaYellaWood();
			SetDadosPista();
		}else if(Etapa == 32) {
			VerificaPlayoffs ();
			dados.CharlotteRoval();
			SetDadosPista();
		}else if(Etapa == 33) {
			VerificaPlayoffs ();
			dados.LasVegasSouthPoint();
			SetDadosPista();
		}else if(Etapa == 34) {
			VerificaPlayoffs ();
			dados.HomestedMiami();
			SetDadosPista();
		}else if(Etapa == 35) {
			VerificaPlayoffs ();
			dados.MartinvilleXfinity();
			SetDadosPista();
		}else if(Etapa == 36) {
			VerificaPlayoffs ();
			dados.PhoenixChampionship();
			SetDadosPista();
		}
		
	}
 
//Calendario Indy
	
	public static void CalendarioIndy2024() {
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		Dados dados = new Dados();
		
		EtapaTotais = 17;
		
		if(Etapa == 1) {
			dados.StPetersburg();
			SetDadosPista();
		}else if(Etapa == 2) {
			dados.LongBeach();
			SetDadosPista();
		}else if(Etapa == 3) {
			dados.Barber();
			SetDadosPista();
		}else if(Etapa == 4) {
			LB_PistaEtapas.setForeground(new Color(0, 0, 0));
			Playoffs = "";
			dados.IMSGP();
			SetDadosPista();
		}else if(Etapa == 5) {
			Playoffs = " - INDY 500";
			LB_PistaEtapas.setForeground(new Color(255, 0, 0));
			dados.Indy500();
			SetDadosPista();
		}else if(Etapa == 6) {
			LB_PistaEtapas.setForeground(new Color(0, 0, 0));
			Playoffs = "";
			dados.DetroitIndy();
			SetDadosPista();
		}else if(Etapa == 7) {
			dados.RoadAmerica();
			SetDadosPista();
		}else if(Etapa == 8) {
			dados.LagunaSeca();
			SetDadosPista();
		}else if(Etapa == 9) {
			dados.MidOhio();
			SetDadosPista();
		}else if(Etapa == 10) {
			dados.IowaIndy();
			SetDadosPista();
		}else if(Etapa == 11) {
			dados.IowaIndy();
			SetDadosPista();
		}else if(Etapa == 12) {
			dados.Toronto();
			SetDadosPista();
		}else if(Etapa == 13) {
			dados.Getaway();
			SetDadosPista();
		}else if(Etapa == 14) {
			dados.Portland();
			SetDadosPista();
		}else if(Etapa == 15) {
			dados.Milwaukee();
			SetDadosPista();
		}else if(Etapa == 16) {
			dados.Milwaukee();
			SetDadosPista();
		}else if(Etapa == 17) {
			dados.NashvilleIndy();
			SetDadosPista();
		}
		
	}
}
