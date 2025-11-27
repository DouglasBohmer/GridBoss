import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;

public class Pagina_CorridaF1 extends JFrame {
	private JLabel LB_Crono;
	private JLabel LB_IMG_Pista;
	private JLabel LB_LocalPista;
	private JLabel LB_TipoPista;
	private JLabel LB_BandeiraPista;
	private JLabel LB_CurvaPista;
	private JLabel LB_ComprimentoPista;
	private JProgressBar ProgBar_Corrida;
	private JLabel LB_QualyEtapa;
	public static JRadioButton RB_Normal_Tempo;
	private JLabel lblClimaAtual;
	private JLabel LB_Clima;
	private JLabel LB_IMG_Clima;
	private JButton btnNewButton;
	private JRadioButton RB_DobroDobro_Tempo;
	
	public static int auxPause=0, TIMER=1000, sec=60, min=60, a=0, fimCorrida = 0;
	public static int aux=0, QTDPitP1 = 0, QTDPitP2 = 0;
	public JButton BT_PlayPause;
	private JRadioButton RB_MetadeMetade_Tempo;
	private JLabel LB_TabelaTempos;
	private JLabel LB_BandeiraPista1;
	private JRadioButton RB_Metade_Tempo;
	private JRadioButton RB_Dobro_Tempo;
	public JButton BT_PitPiloto2;
	public JLabel LB_BandeiraPiloto1;
	public JLabel LB_NunPitPiloto1;
	public JLabel LB_PneuAtualP1;
	public JLabel lblNewLabel_3;
	public JLabel LB_Piloto1;
	public JLabel LB_IMGPit1;
	public JButton BT_PitPiloto1;
	public JLabel LB_BandeiraPiloto2;
	public JLabel LB_Piloto2;
	public JLabel LB_IMGPit2;
	public JLabel LB_NunPitPiloto2;
	public JLabel LB_IMGPneuAtualP2;
	public JLabel lblNewLabel_1;
	public JLabel LB_PneuAtualP2;
	public JProgressBar ProgBar_VidaPneuP1;
	public JProgressBar ProgBar_VidaPneuP2;
	public JButton BT_Teste;
	public JLabel LB_IMGPneuAtualP1;
	public static int Pit;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_CorridaF1 frame = new Pagina_CorridaF1();
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
	public Pagina_CorridaF1() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				
				/*if(auxPause != 15) {
					DarPlay();
					DarPause();
				}*/
				
				if(Pit == 1) {
					PitStop();
					PneuP1();
				}
				
				if(auxPause == 15) {
					DarPause();
				}
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				if(auxPause != 15) {
					DarPause();
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Pit = 0;
				
				Dados dados = new Dados();	
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
				
				LB_IMG_Pista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.LayoutPistaPQ)));
				LB_BandeiraPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
				LB_BandeiraPista1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
				LB_ComprimentoPista.setText(""+Dados.ComprimentoPista);
				LB_CurvaPista.setText(""+Dados.Curvas_Pista);
				LB_LocalPista.setText(""+Dados.LocalPista);
				LB_TipoPista.setText(""+Dados.TipoPista);
				LB_QualyEtapa.setText("IT'S RACEDAY!!! "+PaginaInicial.EtapaAtual+"ª ETAPA DAS "+PaginaInicial.EtapaTotais+" DA TEMPORADA! "+PaginaInicial.Playoffs);
				RB_Normal_Tempo.setSelected(true);
				LB_Crono.setText("VOLTAS DA CORRIDA: 0/"+dados.Voltas_Pista);			
				
				//VOLTAS DA CORRIDA: 
				
				//dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				//Insere dados Pilotos
				
				//dados.SetInfosCorridaF1();
				
				PneuP1();
				PneuP2();
				
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 806);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		LB_IMG_Pista = new JLabel("");
		LB_IMG_Pista.setBounds(10, 79, 250, 200);
		LB_IMG_Pista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Pista.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_IMG_Pista);
		
		LB_QualyEtapa = new JLabel("IT'S RACEDAY!!! ª ETAPA DAS  DA TEMPORADA! ");
		LB_QualyEtapa.setFont(new Font("Arial", Font.PLAIN, 20));
		LB_QualyEtapa.setHorizontalAlignment(SwingConstants.CENTER);
		LB_QualyEtapa.setBounds(10, 11, 864, 23);
		contentPane.add(LB_QualyEtapa);
		
		ProgBar_Corrida = new JProgressBar();
		ProgBar_Corrida.setMaximum(60);
		ProgBar_Corrida.setToolTipText("");
		ProgBar_Corrida.setForeground(new Color(50, 200, 50));
		ProgBar_Corrida.setBounds(270, 147, 604, 23);
		contentPane.add(ProgBar_Corrida);
		
		LB_LocalPista = new JLabel("1");
		LB_LocalPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_LocalPista.setForeground(Color.BLACK);
		LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_LocalPista.setBounds(50, 45, 170, 25);
		contentPane.add(LB_LocalPista);
		
		LB_BandeiraPista = new JLabel("");
		LB_BandeiraPista.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista.setBounds(10, 45, 30, 25);
		contentPane.add(LB_BandeiraPista);
		
		LB_TipoPista = new JLabel("1");
		LB_TipoPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TipoPista.setForeground(Color.BLACK);
		LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TipoPista.setBounds(270, 181, 344, 25);
		contentPane.add(LB_TipoPista);
		
		LB_CurvaPista = new JLabel("1");
		LB_CurvaPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_CurvaPista.setForeground(Color.BLACK);
		LB_CurvaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_CurvaPista.setBounds(270, 205, 344, 25);
		contentPane.add(LB_CurvaPista);
		
		LB_ComprimentoPista = new JLabel("1");
		LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_ComprimentoPista.setForeground(Color.BLACK);
		LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_ComprimentoPista.setBounds(270, 230, 344, 25);
		contentPane.add(LB_ComprimentoPista);
		
		LB_Crono = new JLabel("VOLTAS DA CORRIDA: ");
		LB_Crono.setFont(new Font("Arial", Font.PLAIN, 20));
		LB_Crono.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Crono.setBounds(270, 102, 604, 34);
		contentPane.add(LB_Crono);
		
		RB_Metade_Tempo = new JRadioButton("0.5x");
		RB_Metade_Tempo.setEnabled(false);
		RB_Metade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Metade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Metade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 2000;
				
			}
		});
		RB_Metade_Tempo.setBackground(new Color(255, 255, 255));
		RB_Metade_Tempo.setBounds(337, 72, 66, 23);
		contentPane.add(RB_Metade_Tempo);
		
		RB_Normal_Tempo = new JRadioButton("1x");
		RB_Normal_Tempo.setEnabled(false);
		RB_Normal_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Normal_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 1000;
			}
		});
		RB_Normal_Tempo.setSelected(true);
		RB_Normal_Tempo.setBackground(new Color(255, 255, 255));
		RB_Normal_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Normal_Tempo.setBounds(403, 72, 66, 23);
		contentPane.add(RB_Normal_Tempo);
		
		RB_Dobro_Tempo = new JRadioButton("2x");
		RB_Dobro_Tempo.setEnabled(false);
		RB_Dobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Dobro_Tempo.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 500;
				
			}
		});
		RB_Dobro_Tempo.setBackground(new Color(255, 255, 255));
		RB_Dobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Dobro_Tempo.setBounds(469, 72, 66, 23);
		contentPane.add(RB_Dobro_Tempo);
		
		RB_DobroDobro_Tempo = new JRadioButton("4x");
		RB_DobroDobro_Tempo.setEnabled(false);
		RB_DobroDobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_DobroDobro_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 250;
				
			}
		});
		RB_DobroDobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_DobroDobro_Tempo.setBackground(Color.WHITE);
		RB_DobroDobro_Tempo.setBounds(535, 72, 66, 23);
		contentPane.add(RB_DobroDobro_Tempo);
		
		RB_MetadeMetade_Tempo = new JRadioButton("0.25x");
		RB_MetadeMetade_Tempo.setEnabled(false);
		RB_MetadeMetade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 4000;
				
			}
		});
		RB_MetadeMetade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_MetadeMetade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_MetadeMetade_Tempo.setBackground(Color.WHITE);
		RB_MetadeMetade_Tempo.setBounds(271, 72, 66, 23);
		contentPane.add(RB_MetadeMetade_Tempo);
		
		JLabel lblNewLabel = new JLabel("Velocidade do tempo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(270, 45, 332, 20);
		contentPane.add(lblNewLabel);
		
		lblClimaAtual = new JLabel("CLIMA ATUAL");
		lblClimaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblClimaAtual.setFont(new Font("Arial", Font.ITALIC, 20));
		lblClimaAtual.setBounds(624, 179, 250, 50);
		contentPane.add(lblClimaAtual);
		
		LB_Clima = new JLabel("CLIMA");
		LB_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_Clima.setBounds(624, 229, 183, 50);
		contentPane.add(LB_Clima);
		
		LB_IMG_Clima = new JLabel("");
		LB_IMG_Clima.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_IMG_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_IMG_Clima.setBounds(804, 227, 70, 50);
		contentPane.add(LB_IMG_Clima);
		
		btnNewButton = new JButton("TESTE CLIMA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
			}
		});
		btnNewButton.setBounds(270, 256, 160, 23);
		contentPane.add(btnNewButton);
		
		BT_PlayPause = new JButton("  COMEÇAR CORRIDA");
		BT_PlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(fimCorrida == 1) {
					
					fimCorrida = 0;
					
					CarregaTelaInicial();
					
					dispose();
				}
				
				if(auxPause == 0) {
					
					DarPlay();
					
				}else{
					
					DarPause();
			
				}
				
			}
		});
		BT_PlayPause.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconePlay16px.png")));
		BT_PlayPause.setBounds(612, 45, 262, 23);
		contentPane.add(BT_PlayPause);
		
		JButton btnPular = new JButton("  PULAR CORRIDA");
		btnPular.setEnabled(false);
		btnPular.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePular16px.png")));
		btnPular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 0;
				
			}
		});
		btnPular.setBounds(612, 73, 262, 23);
		contentPane.add(btnPular);
		
		ButtonGroup botao = new ButtonGroup();
		botao.add(RB_Dobro_Tempo);
		botao.add(RB_Normal_Tempo);
		botao.add(RB_Metade_Tempo);
		botao.add(RB_DobroDobro_Tempo);
		botao.add(RB_MetadeMetade_Tempo);
		
		LB_TabelaTempos = new JLabel("");
		LB_TabelaTempos.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/TabelaQualy.png")));
		LB_TabelaTempos.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TabelaTempos.setBounds(10, 290, 604, 364);
		LB_TabelaTempos.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_TabelaTempos);
		
		LB_BandeiraPista1 = new JLabel("");
		LB_BandeiraPista1.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista1.setBounds(230, 45, 30, 25);
		contentPane.add(LB_BandeiraPista1);
		
		BT_PitPiloto1 = new JButton("  CHAMAR PARA PIT STOP P1");
		BT_PitPiloto1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DarPause();
				
				PitStopP1 PitStopP1 = new PitStopP1();
				PitStopP1.setVisible(true);
				PitStopP1.setLocationRelativeTo(null);
				
				PitStopP1.setFocusable(true);
				PitStopP1.setAutoRequestFocus(true);
				PitStopP1.toFront();
				
			}
		});
		BT_PitPiloto1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		BT_PitPiloto1.setBounds(624, 440, 250, 23);
		contentPane.add(BT_PitPiloto1);
		
		BT_PitPiloto2 = new JButton("CHAMAR PARA PIT STOP");
		BT_PitPiloto2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		BT_PitPiloto2.setBounds(624, 631, 250, 23);
		contentPane.add(BT_PitPiloto2);
		
		LB_BandeiraPiloto1 = new JLabel("");
		LB_BandeiraPiloto1.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/Bandeira Alemanha.png")));
		LB_BandeiraPiloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPiloto1.setBounds(624, 290, 53, 34);
		contentPane.add(LB_BandeiraPiloto1);
		
		LB_Piloto1 = new JLabel("");
		LB_Piloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_Piloto1.setBounds(687, 290, 187, 34);
		contentPane.add(LB_Piloto1);
		
		LB_NunPitPiloto1 = new JLabel("Paradas realizada: 0");
		LB_NunPitPiloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NunPitPiloto1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_NunPitPiloto1.setBounds(687, 326, 187, 32);
		contentPane.add(LB_NunPitPiloto1);
		
		LB_PneuAtualP1 = new JLabel("");
		LB_PneuAtualP1.setBackground(new Color(255, 255, 255));
		LB_PneuAtualP1.setForeground(new Color(255, 128, 0));
		LB_PneuAtualP1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		LB_PneuAtualP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuAtualP1.setBounds(687, 381, 187, 26);
		contentPane.add(LB_PneuAtualP1);
		
		lblNewLabel_3 = new JLabel("Pneu Atual");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(687, 358, 187, 23);
		contentPane.add(lblNewLabel_3);
		
		LB_IMGPit1 = new JLabel("");
		LB_IMGPit1.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconePitStop32px.png")));
		LB_IMGPit1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPit1.setBounds(624, 324, 60, 34);
		contentPane.add(LB_IMGPit1);
		
		LB_BandeiraPiloto2 = new JLabel("");
		LB_BandeiraPiloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPiloto2.setBounds(624, 481, 53, 34);
		contentPane.add(LB_BandeiraPiloto2);
		
		LB_Piloto2 = new JLabel("");
		LB_Piloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_Piloto2.setBounds(687, 481, 187, 34);
		contentPane.add(LB_Piloto2);
		
		LB_IMGPit2 = new JLabel("");
		LB_IMGPit2.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconePitStop32px.png")));
		LB_IMGPit2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPit2.setBounds(624, 515, 60, 34);
		contentPane.add(LB_IMGPit2);
		
		LB_NunPitPiloto2 = new JLabel("Paradas realizada: 0");
		LB_NunPitPiloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NunPitPiloto2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_NunPitPiloto2.setBounds(687, 517, 187, 32);
		contentPane.add(LB_NunPitPiloto2);
		
		LB_IMGPneuAtualP2 = new JLabel("");
		LB_IMGPneuAtualP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPneuAtualP2.setBounds(624, 549, 60, 50);
		contentPane.add(LB_IMGPneuAtualP2);
		
		lblNewLabel_1 = new JLabel("Pneu Atual");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(687, 549, 187, 23);
		contentPane.add(lblNewLabel_1);
		
		LB_PneuAtualP2 = new JLabel("");
		LB_PneuAtualP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuAtualP2.setForeground(new Color(255, 128, 0));
		LB_PneuAtualP2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		LB_PneuAtualP2.setBackground(Color.WHITE);
		LB_PneuAtualP2.setBounds(687, 572, 187, 26);
		contentPane.add(LB_PneuAtualP2);
		
		ProgBar_VidaPneuP1 = new JProgressBar();
		ProgBar_VidaPneuP1.setForeground(new Color(50, 200, 50));
		ProgBar_VidaPneuP1.setBounds(624, 417, 250, 14);
		contentPane.add(ProgBar_VidaPneuP1);
		
		ProgBar_VidaPneuP2 = new JProgressBar();
		ProgBar_VidaPneuP2.setForeground(new Color(50, 200, 50));
		ProgBar_VidaPneuP2.setBounds(624, 608, 250, 14);
		contentPane.add(ProgBar_VidaPneuP2);
		
		BT_Teste = new JButton("TESTES");
		BT_Teste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		BT_Teste.setBounds(454, 256, 160, 23);
		contentPane.add(BT_Teste);
		
		LB_IMGPneuAtualP1 = new JLabel("");
		LB_IMGPneuAtualP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPneuAtualP1.setBounds(624, 358, 60, 50);
		contentPane.add(LB_IMGPneuAtualP1);
		
		
	}
	
	public class Temporizador extends Thread{
		public void run() {
			
			Dados dados = new Dados();
			Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
			
			double AuxPneuP1 = ProgBar_VidaPneuP1.getValue(), AuxPneuP2 = ProgBar_VidaPneuP2.getValue();
			
			ProgBar_Corrida.setMaximum(dados.Voltas_Pista);// -1
			
			while (aux  < ProgBar_Corrida.getMaximum() + 1) {
				
				try {
					
					ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/Imagens/IconeTrocaPneu64px.png"));
					
					if (ProgBar_VidaPneuP1.getValue() < ((AuxPneuP1 / 10) * 3) && dados.VidaUtilP1 > 0){
						JOptionPane.showMessageDialog(null, "Os pneus do "+SelecionarEquipe.NomePiloto1+" estão extremamente gastos!"
								+ "\nPense em realizar um Pit Stop e troca-los!",
								"PitStop", JOptionPane.INFORMATION_MESSAGE, icon);
					}
					
					if(dados.VidaUtilP1 < 1) {
						
						LB_PneuAtualP1.setForeground(new Color(255, 255, 255));
						BT_PitPiloto1.setBackground(new Color(255,255,255));
						BT_PitPiloto1.setText("ABANDONOU !");
						BT_PitPiloto1.setEnabled(false);
						ProgBar_VidaPneuP1.setEnabled(false);
						ProgBar_VidaPneuP1.setForeground(new Color(50, 200, 50));
						LB_IMGPneuAtualP1.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
						
					}
					
					if (ProgBar_VidaPneuP2.getValue() < ((AuxPneuP2 / 10) * 3) && dados.VidaUtilP2 > 0){
						JOptionPane.showMessageDialog(null, "Os pneus do "+SelecionarEquipe.NomePiloto2+" estão extremamente gastos!"
								+ "\nPense em realizar um Pit Stop e troca-los!",
								"PitStop", JOptionPane.INFORMATION_MESSAGE, icon);
					}
					
					if(dados.VidaUtilP2 < 1) {
						
						LB_PneuAtualP2.setForeground(new Color(255, 255, 255));
						BT_PitPiloto2.setBackground(new Color(255,255,255));
						BT_PitPiloto2.setText("ABANDONOU !");
						BT_PitPiloto2.setEnabled(false);
						ProgBar_VidaPneuP2.setEnabled(false);
						ProgBar_VidaPneuP2.setForeground(new Color(50, 200, 50));
						LB_IMGPneuAtualP2.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
						
					}
					
					ProgBar_Corrida.setValue(aux);
					
					ProgBar_VidaPneuP1.setValue(dados.VidaUtilP1);
					ProgBar_VidaPneuP2.setValue(dados.VidaUtilP2);
					
					if(aux <= dados.Voltas_Pista ) {
						LB_Crono.setText("VOLTAS DA CORRIDA: "+aux+"/"+dados.Voltas_Pista);
						
						LB_PneuAtualP2.setText(""+dados.PneuAtualP2+ " - "+dados.VidaUtilP2+" Voltas");
						LB_PneuAtualP1.setText(""+dados.PneuAtualP1+ " - "+dados.VidaUtilP1+" Voltas");
					}
					
					aux++;
					dados.VidaUtilP1--;
					dados.VidaUtilP2--;
					
					if (ProgBar_VidaPneuP1.getValue() > ((AuxPneuP1 / 10) * 6)){
						
						ProgBar_VidaPneuP1.setForeground(new Color(50, 200, 50));
						BT_PitPiloto1.setBackground(new Color(240,240,240));
						BT_PitPiloto1.setForeground(new Color(0,0,0));
						
					}else if (ProgBar_VidaPneuP1.getValue() <= ((AuxPneuP1 / 10) * 6) && ProgBar_VidaPneuP1.getValue() > ((AuxPneuP1 / 10) * 4)) {
						
						ProgBar_VidaPneuP1.setForeground(new Color(255, 255, 0));
						
					}else if (ProgBar_VidaPneuP1.getValue() <= ((AuxPneuP1 / 10) * 4) && ProgBar_VidaPneuP1.getValue() > ((AuxPneuP1 / 10) * 2)) {
						
						ProgBar_VidaPneuP1.setForeground(new Color(255, 125, 0));
						
					}else if (ProgBar_VidaPneuP1.getValue() <= ((AuxPneuP1 / 10) * 2)) {
						
						ProgBar_VidaPneuP1.setForeground(new Color(255, 0, 0));
						BT_PitPiloto1.setBackground(new Color(255,0,0));
						BT_PitPiloto1.setForeground(new Color(255,255,255));
						
					}
					
					if (ProgBar_VidaPneuP2.getValue() > ((AuxPneuP2 / 10) * 6)){
						
						ProgBar_VidaPneuP2.setForeground(new Color(50, 200, 50));
						BT_PitPiloto2.setBackground(new Color(240,240,240));
						BT_PitPiloto2.setForeground(new Color(0,0,0));
						
					}else if (ProgBar_VidaPneuP2.getValue() <= ((AuxPneuP2 / 10) * 6) && ProgBar_VidaPneuP2.getValue() > ((AuxPneuP2 / 10) * 4)) {
						
						ProgBar_VidaPneuP2.setForeground(new Color(255, 255, 0));
						
					}else if (ProgBar_VidaPneuP2.getValue() <= ((AuxPneuP2 / 10) * 4) && ProgBar_VidaPneuP2.getValue() > ((AuxPneuP2 / 10) * 2)) {
						
						ProgBar_VidaPneuP2.setForeground(new Color(255, 125, 0));
						
					}else if (ProgBar_VidaPneuP2.getValue() <= ((AuxPneuP2 / 10) * 2)) {
						
						ProgBar_VidaPneuP2.setForeground(new Color(255, 0, 0));
						BT_PitPiloto2.setBackground(new Color(255,0,0));
						BT_PitPiloto2.setForeground(new Color(255,255,255));
						
					}
					
					sleep(TIMER);
					
					a=1;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			auxPause = 15;
			fimCorrida = 1;
			aux = 0;
			
			ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/Imagens/Icone64pxConfirmacao.png"));
			
			JOptionPane.showMessageDialog(null, "FIM DA CORRIDA!","FIM DA CORRIDA!", 
			JOptionPane.ERROR_MESSAGE, icon);
			
			BT_PlayPause.setText("  FIM DA CORRIDA!");
			BT_PlayPause.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/Icone16pxBandeiraDeChegada.png")));

			RB_Dobro_Tempo.setEnabled(false);
			RB_Normal_Tempo.setEnabled(false);
			RB_Metade_Tempo.setEnabled(false);
			RB_DobroDobro_Tempo.setEnabled(false);
			RB_MetadeMetade_Tempo.setEnabled(false);
			
			
		}
	}
	
	public void PneuP1() {
		
		Dados dados = new Dados();	
		
		LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(dados.IMGPneuAtualP1)));
		
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
		
		dados.VidaUtilPneuP1();
		
		LB_Piloto1.setText(""+SelecionarEquipe.NomePiloto1);
		LB_BandeiraPiloto1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
		
		LB_PneuAtualP1.setText(""+dados.PneuAtualP1+ " - "+dados.VidaUtilP1+" Voltas");
		LB_IMGPneuAtualP1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(dados.IMGPneuAtualP1)));
		
		ProgBar_VidaPneuP1.setMaximum(dados.VidaUtilP1);
		ProgBar_VidaPneuP1.setValue(dados.VidaUtilP1);
		
	}
	
	public void PneuP2() {
		
		Dados dados = new Dados();	
		Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();

		dados.VidaUtilPneuP2();
		
		LB_Piloto2.setText(""+SelecionarEquipe.NomePiloto2);
		LB_BandeiraPiloto2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto2+".png")));
		
		LB_PneuAtualP2.setText(""+dados.PneuAtualP2+ " - "+dados.VidaUtilP2+" Voltas");
		LB_IMGPneuAtualP2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(dados.IMGPneuAtualP2)));
		
		ProgBar_VidaPneuP2.setMaximum(dados.VidaUtilP2);
		ProgBar_VidaPneuP2.setValue(dados.VidaUtilP2);
		
	}
	
	public void DarPlay() {
		
		BT_PlayPause.setText("  PAUSAR CORRIDA");
		BT_PlayPause.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconePausa16px.png")));
		auxPause = 1;
		
		TIMER = 1000;
		new Temporizador().start();
		
		RB_Dobro_Tempo.setEnabled(true);
		RB_Normal_Tempo.setEnabled(true);
		RB_Metade_Tempo.setEnabled(true);
		RB_DobroDobro_Tempo.setEnabled(true);
		RB_MetadeMetade_Tempo.setEnabled(true);
		
	}
	
	public void DarPause() {
		
		BT_PlayPause.setText("  CONTINUAR CORRIDA");
		BT_PlayPause.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconePlay16px.png")));
		auxPause = 0;
		
		TIMER = 100000000;
		
		RB_Normal_Tempo.setSelected(true);

	}
	
	public void PitStop() {
		
		Dados dados = new Dados();
		
		dados.PneuAtualP1 = dados.PneuPitP1;
		dados.IMGPneuAtualP1 = dados.IMGPitP1;
		dados.VidaUtilP1 = dados.VidaPitP1;
		
		ProgBar_VidaPneuP1.setForeground(new Color(255, 0, 0));
		BT_PitPiloto1.setBackground(new Color(255,0,0));
		BT_PitPiloto1.setForeground(new Color(255,255,255));
		
		Pit = 0;
		
		LB_NunPitPiloto1.setText("Paradas realizada: "+QTDPitP1);
		
	}
	
	public void CarregaTelaInicial() {
		
		SalvaDado data = new SalvaDado();
		Inicial_Carregar InicialCarregar = new Inicial_Carregar();
		Pagina_Inicial PaginaInicial = new Pagina_Inicial();
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		PaginaInicial.EtapaAtual = PaginaInicial.EtapaAtual +1;
		PaginaInicial.Etapa = PaginaInicial.EtapaAtual;
		PaginaInicial.LB_Temporada.setText("Temporada "+PaginaInicial.TEMPORADAJOGADOR+", Etapa "+PaginaInicial.EtapaAtual+"/"+PaginaInicial.EtapaTotais);
		
		if(Categoria.BT_Cate.equals("Fórmula 1")) {
			
			PaginaInicial.CriaCalendarioF1();
			PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
			
		} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
			
			PaginaInicial.CriaCalendarioFIndy();
			PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
			
		} else if(Categoria.BT_Cate.equals("NASCAR")) {
			
			PaginaInicial.CriaCalendarioNascar();
			PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
		}
		
		if (PaginaInicial.Etapa > 1) {
			PaginaInicial.BT_EtapaAnt.setEnabled(true);
		}
		if (PaginaInicial.Etapa == PaginaInicial.EtapaTotais) {
			PaginaInicial.BT_EtapaProxima.setEnabled(false);
		}
		
		if (PaginaInicial.Etapa == 1) {
			PaginaInicial.BT_EtapaAnt.setEnabled(false);
		}
		if (PaginaInicial.Etapa < PaginaInicial.EtapaTotais) {
			PaginaInicial.BT_EtapaProxima.setEnabled(true);
		}
		
		PaginaInicial.setVisible(true);
		PaginaInicial.setLocationRelativeTo(null);
		
		
	}
	
}


