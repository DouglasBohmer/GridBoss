import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Pagina_EscolhePneuF1 extends JFrame {

	private JPanel contentPane;
	private JLabel LB_Clima;
	private JLabel LB_IMG_Clima;
	private JButton BT_IrCorrida;
	private JLabel LB_BandeiraPiloto1;
	private JLabel LB_Piloto1;
	private JRadioButton RB_PneuWetP1;
	private JRadioButton RB_PneuInterP1;
	private JRadioButton RB_PneuDuroP1;
	private JRadioButton RB_PneuMedioP1;
	private JRadioButton RB_PneuMacioP1;
	private JLabel LB_PneuWetP1;
	private JLabel LB_PneuInterP1;
	private JLabel LB_PneuDuroP1;
	private JLabel LB_PneuMedioP1;
	private JLabel LB_PneuMacioP1;
	private JLabel LB_PneuWetP2;
	private JLabel LB_PneuInterP2;
	private JLabel LB_PneuMedioP2;
	private JLabel LB_PneuDuroP2;
	private JLabel LB_PneuMacioP2;
	private JRadioButton RB_PneuMacioP2;
	private JRadioButton RB_PneuWetP2;
	private JRadioButton RB_PneuInterP2;
	private JRadioButton RB_PneuDuroP2;
	private JRadioButton RB_PneuMedioP2;
	private JLabel LB_InfosPneuP1;
	private JLabel LB_InfosPneuP2;
	private JLabel LB_Piloto2;
	private JLabel LB_BandeiraPiloto2;
	private JLabel LB_InfosPneuP2_1;
	private JLabel LB_InfosPneuP1_1;
	private JLabel LB_VoltasCorrida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_EscolhePneuF1 frame = new Pagina_EscolhePneuF1();
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
	public Pagina_EscolhePneuF1() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Dados dados = new Dados();
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();

				Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				TestaChuva();
				
				LB_Piloto1.setText(""+SelecionarEquipe.NomePiloto1);
				LB_BandeiraPiloto1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
				
				LB_Piloto2.setText(""+SelecionarEquipe.NomePiloto2);
				LB_BandeiraPiloto2.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto2+".png")));
			
				if(RB_PneuMacioP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu mais rápido, porém de alto desgaste!");
					LB_InfosPneuP2.setText("Pneu mais rápido, porém de alto desgaste!");
					
					dados.PneuAtualP1 = "SOFT";
					dados.PneuAtualP2 = "SOFT";
					
				}
				if(RB_PneuInterP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu usado em condição de pista úmida!");
					LB_InfosPneuP2.setText("Pneu usado em condição de pista úmida!");
					dados.PneuAtualP1 = "INTER";
					dados.PneuAtualP2 = "INTER";
				}
				if(RB_PneuWetP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu usado em condição de chuva extrema!");
					LB_InfosPneuP2.setText("Pneu usado em condição de chuva extrema!");
					dados.PneuAtualP1 = "WET";
					dados.PneuAtualP2 = "WET";
				}
				
				dados.VidaUtilPneuP1();
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				
				LB_VoltasCorrida.setText("CORRIDA TERÁ "+dados.Voltas_Pista+" VOLTAS!");
				

				
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 279);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		BT_IrCorrida = new JButton("  IR PARA A CORRIDA");
		BT_IrCorrida.setBounds(320, 156, 250, 20);
		BT_IrCorrida.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconeCorrida16px.png")));
		BT_IrCorrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pagina_CorridaF1 PaginaCorridaF1 = new Pagina_CorridaF1();
				PaginaCorridaF1.setVisible(true);
				PaginaCorridaF1.setLocationRelativeTo(null);
				
				VerificaPneu();
				
				dispose();
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(BT_IrCorrida);
		
		JLabel lblClimaAtual = new JLabel("CLIMA ATUAL");
		lblClimaAtual.setForeground(new Color(255, 0, 0));
		lblClimaAtual.setBounds(320, 10, 250, 40);
		lblClimaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblClimaAtual.setFont(new Font("Arial", Font.ITALIC, 20));
		contentPane.add(lblClimaAtual);
		
		LB_Clima = new JLabel("CLIMA");
		LB_Clima.setBounds(320, 50, 218, 95);
		LB_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		contentPane.add(LB_Clima);
		
		LB_IMG_Clima = new JLabel("");
		LB_IMG_Clima.setBounds(500, 50, 70, 95);
		LB_IMG_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		contentPane.add(LB_IMG_Clima);
		
		LB_BandeiraPiloto1 = new JLabel("");
		LB_BandeiraPiloto1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/Bandeira BR.png")));
		LB_BandeiraPiloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPiloto1.setBounds(10, 10, 57, 34);
		contentPane.add(LB_BandeiraPiloto1);
		
		LB_Piloto1 = new JLabel("PILOTO 1");
		LB_Piloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_Piloto1.setBounds(10, 10, 300, 34);
		contentPane.add(LB_Piloto1);
		
		LB_PneuWetP1 = new JLabel("");
		LB_PneuWetP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuChuvaF140px.png")));
		LB_PneuWetP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuWetP1.setBounds(10, 55, 60, 50);
		contentPane.add(LB_PneuWetP1);
		
		LB_PneuInterP1 = new JLabel("");
		LB_PneuInterP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIntermediarioF140px.png")));
		LB_PneuInterP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuInterP1.setBounds(70, 55, 60, 50);
		contentPane.add(LB_PneuInterP1);
		
		LB_PneuDuroP1 = new JLabel("");
		LB_PneuDuroP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuDuroF140px.png")));
		LB_PneuDuroP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuDuroP1.setBounds(130, 55, 60, 50);
		contentPane.add(LB_PneuDuroP1);
		
		LB_PneuMedioP1 = new JLabel("");
		LB_PneuMedioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMedioF140px.png")));
		LB_PneuMedioP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMedioP1.setBounds(190, 55, 60, 50);
		contentPane.add(LB_PneuMedioP1);
		
		LB_PneuMacioP1 = new JLabel("");
		LB_PneuMacioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMacioF140px.png")));
		LB_PneuMacioP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMacioP1.setBounds(250, 55, 60, 50);
		contentPane.add(LB_PneuMacioP1);
		
		JLabel lblNewLabel_3 = new JLabel("WET");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 105, 60, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("INTER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 105, 60, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("HARD");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(130, 105, 60, 20);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("MEDIUM");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(190, 105, 60, 20);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("SOFT");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(250, 105, 60, 20);
		contentPane.add(lblNewLabel_11);
		
		RB_PneuWetP1 = new JRadioButton("");
		RB_PneuWetP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP1 = "WET";
				dados.VidaUtilPneuP1();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP1.setText("Pneu usado em condição de chuva extrema!");
			}
		});
		RB_PneuWetP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuWetP1.setBackground(Color.WHITE);
		RB_PneuWetP1.setBounds(10, 125, 60, 20);
		contentPane.add(RB_PneuWetP1);
		
		RB_PneuInterP1 = new JRadioButton("");
		RB_PneuInterP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP1 = "INTER";
				dados.VidaUtilPneuP1();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP1.setText("Pneu usado em condição de pista úmida!");
			}
		});
		RB_PneuInterP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuInterP1.setBackground(Color.WHITE);
		RB_PneuInterP1.setBounds(70, 125, 60, 20);
		contentPane.add(RB_PneuInterP1);
		
		RB_PneuDuroP1 = new JRadioButton("");
		RB_PneuDuroP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP1 = "HARD";
				dados.VidaUtilPneuP1();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP1.setText("Pneu mais lento, porém de baixo desgaste!");
			}
		});
		RB_PneuDuroP1.setSelected(true);
		RB_PneuDuroP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuDuroP1.setBackground(Color.WHITE);
		RB_PneuDuroP1.setBounds(130, 125, 60, 20);
		contentPane.add(RB_PneuDuroP1);
		
		RB_PneuMedioP1 = new JRadioButton("");
		RB_PneuMedioP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP1 = "MEDIUM";
				dados.VidaUtilPneuP1();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP1.setText("Pneu equilibrado em aderencia e desgaste!");
			}
		});
		RB_PneuMedioP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMedioP1.setBackground(Color.WHITE);
		RB_PneuMedioP1.setBounds(190, 125, 60, 20);
		contentPane.add(RB_PneuMedioP1);
		
		RB_PneuMacioP1 = new JRadioButton("");
		RB_PneuMacioP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP1 = "SOFT";
				dados.VidaUtilPneuP1();
				
				LB_InfosPneuP1_1.setText("Vida útil de aprox. "+dados.VidaUtilP1+" voltas!");
				LB_InfosPneuP1.setText("Pneu mais rápido, porém de alto desgaste!");
			}
		});
		RB_PneuMacioP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMacioP1.setBackground(Color.WHITE);
		RB_PneuMacioP1.setBounds(250, 125, 60, 20);
		contentPane.add(RB_PneuMacioP1);
		
		LB_BandeiraPiloto2 = new JLabel("");
		LB_BandeiraPiloto2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/Bandeira Argentina.png")));
		LB_BandeiraPiloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPiloto2.setBounds(580, 10, 57, 34);
		contentPane.add(LB_BandeiraPiloto2);
		
		LB_Piloto2 = new JLabel("PILOTO 2");
		LB_Piloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		LB_Piloto2.setBounds(580, 10, 300, 34);
		contentPane.add(LB_Piloto2);
		
		LB_PneuWetP2 = new JLabel("");
		LB_PneuWetP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuChuvaF140px.png")));
		LB_PneuWetP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuWetP2.setBounds(580, 55, 60, 50);
		contentPane.add(LB_PneuWetP2);
		
		LB_PneuInterP2 = new JLabel("");
		LB_PneuInterP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIntermediarioF140px.png")));
		LB_PneuInterP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuInterP2.setBounds(640, 55, 60, 50);
		contentPane.add(LB_PneuInterP2);
		
		LB_PneuDuroP2 = new JLabel("");
		LB_PneuDuroP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuDuroF140px.png")));
		LB_PneuDuroP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuDuroP2.setBounds(700, 55, 60, 50);
		contentPane.add(LB_PneuDuroP2);
		
		LB_PneuMedioP2 = new JLabel("");
		LB_PneuMedioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMedioF140px.png")));
		LB_PneuMedioP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMedioP2.setBounds(760, 55, 60, 50);
		contentPane.add(LB_PneuMedioP2);
		
		LB_PneuMacioP2 = new JLabel("");
		LB_PneuMacioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMacioF140px.png")));
		LB_PneuMacioP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMacioP2.setBounds(820, 55, 60, 50);
		contentPane.add(LB_PneuMacioP2);
		
		JLabel lblNewLabel_3_1 = new JLabel("WET");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(580, 105, 60, 20);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("INTER");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(640, 105, 60, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_9_1 = new JLabel("HARD");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1.setBounds(700, 105, 60, 20);
		contentPane.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_10_1 = new JLabel("MEDIUM");
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10_1.setBounds(760, 105, 60, 20);
		contentPane.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_11_1 = new JLabel("SOFT");
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1.setBounds(820, 105, 60, 20);
		contentPane.add(lblNewLabel_11_1);
		
		RB_PneuWetP2 = new JRadioButton("");
		RB_PneuWetP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP2 = "WET";
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				LB_InfosPneuP2.setText("Pneu usado em condição de chuva extrema!");
			}
		});
		RB_PneuWetP2.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuWetP2.setBackground(Color.WHITE);
		RB_PneuWetP2.setBounds(580, 125, 60, 20);
		contentPane.add(RB_PneuWetP2);
		
		RB_PneuInterP2 = new JRadioButton("");
		RB_PneuInterP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP2 = "INTER";
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				LB_InfosPneuP2.setText("Pneu usado em condição de pista úmida!");
			}
		});
		RB_PneuInterP2.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuInterP2.setBackground(Color.WHITE);
		RB_PneuInterP2.setBounds(640, 125, 60, 20);
		contentPane.add(RB_PneuInterP2);
		
		RB_PneuDuroP2 = new JRadioButton("");
		RB_PneuDuroP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP2 = "HARD";
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				LB_InfosPneuP2.setText("Pneu mais lento, porém de baixo desgaste!");
			}
		});
		RB_PneuDuroP2.setSelected(true);
		RB_PneuDuroP2.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuDuroP2.setBackground(Color.WHITE);
		RB_PneuDuroP2.setBounds(700, 125, 60, 20);
		contentPane.add(RB_PneuDuroP2);
		
		RB_PneuMedioP2 = new JRadioButton("");
		RB_PneuMedioP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP2 = "MEDIUM";
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				LB_InfosPneuP2.setText("Pneu equilibrado em aderencia e desgaste!");
			}
		});
		RB_PneuMedioP2.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMedioP2.setBackground(Color.WHITE);
		RB_PneuMedioP2.setBounds(760, 125, 60, 20);
		contentPane.add(RB_PneuMedioP2);
		
		RB_PneuMacioP2 = new JRadioButton("");
		RB_PneuMacioP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				dados.PneuAtualP2 = "SOFT";
				dados.VidaUtilPneuP2();
				
				LB_InfosPneuP2_1.setText("Vida útil de aprox. "+dados.VidaUtilP2+" voltas!");
				LB_InfosPneuP2.setText("Pneu mais rápido, porém de alto desgaste!");
			}
		});
		RB_PneuMacioP2.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMacioP2.setBackground(Color.WHITE);
		RB_PneuMacioP2.setBounds(820, 125, 60, 20);
		contentPane.add(RB_PneuMacioP2);
		
		
		ButtonGroup botaoP1 = new ButtonGroup();
		botaoP1.add(RB_PneuMacioP1);
		botaoP1.add(RB_PneuMedioP1);
		botaoP1.add(RB_PneuDuroP1);
		botaoP1.add(RB_PneuInterP1);
		botaoP1.add(RB_PneuWetP1);
		
		ButtonGroup botao2 = new ButtonGroup();
		botao2.add(RB_PneuMacioP2);
		botao2.add(RB_PneuMedioP2);
		botao2.add(RB_PneuDuroP2);
		botao2.add(RB_PneuInterP2);
		botao2.add(RB_PneuWetP2);
		
		JButton btnNewButton = new JButton("TESTE CLIMA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				TestaChuva();
				
				if(RB_PneuMacioP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu mais rápido, porém de alto desgaste!");
					LB_InfosPneuP2.setText("Pneu mais rápido, porém de alto desgaste!");
				}
				if(RB_PneuInterP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu usado em condição de pista úmida!");
					LB_InfosPneuP2.setText("Pneu usado em condição de pista úmida!");
				}
				if(RB_PneuWetP1.isSelected()) {
					LB_InfosPneuP1.setText("Pneu usado em condição de chuva extrema!");
					LB_InfosPneuP2.setText("Pneu usado em condição de chuva extrema!");
				}
			
			}
		});
		btnNewButton.setBounds(320, 207, 250, 23);
		contentPane.add(btnNewButton);
		
		LB_InfosPneuP1 = new JLabel("Info dos Pneus");
		LB_InfosPneuP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		LB_InfosPneuP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP1.setBounds(10, 156, 300, 20);
		contentPane.add(LB_InfosPneuP1);
		
		LB_InfosPneuP2 = new JLabel("Info dos Pneus");
		LB_InfosPneuP2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		LB_InfosPneuP2.setBounds(574, 156, 300, 20);
		contentPane.add(LB_InfosPneuP2);
		
		LB_InfosPneuP1_1 = new JLabel("Vida útil de aprox. X voltas!");
		LB_InfosPneuP1_1.setForeground(new Color(255, 0, 0));
		LB_InfosPneuP1_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP1_1.setFont(new Font("Arial Narrow", Font.ITALIC, 13));
		LB_InfosPneuP1_1.setBounds(10, 176, 300, 20);
		contentPane.add(LB_InfosPneuP1_1);
		
		LB_InfosPneuP2_1 = new JLabel("Vida útil de aprox. X voltas!");
		LB_InfosPneuP2_1.setForeground(new Color(255, 0, 0));
		LB_InfosPneuP2_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP2_1.setFont(new Font("Arial Narrow", Font.ITALIC, 13));
		LB_InfosPneuP2_1.setBounds(574, 176, 300, 20);
		contentPane.add(LB_InfosPneuP2_1);
		
		LB_VoltasCorrida = new JLabel("CORRIDA TERÁ X VOLTAS!");
		LB_VoltasCorrida.setHorizontalAlignment(SwingConstants.CENTER);
		LB_VoltasCorrida.setForeground(Color.RED);
		LB_VoltasCorrida.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		LB_VoltasCorrida.setBounds(320, 176, 250, 20);
		contentPane.add(LB_VoltasCorrida);
		
	}
	
	public void TestaChuva() {
		
		if (LB_Clima.getText().equals("Chuva Forte")) {
			
			LB_PneuInterP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuWetP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuChuvaF140px.png")));
			LB_PneuDuroP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMedioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMacioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			
			RB_PneuWetP1.setEnabled(true);
			RB_PneuInterP1.setEnabled(false);
			RB_PneuMacioP1.setEnabled(false);
			RB_PneuMedioP1.setEnabled(false);
			RB_PneuDuroP1.setEnabled(false);
			
			RB_PneuWetP1.setSelected(true);
			
			//
			
			LB_PneuInterP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuWetP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuChuvaF140px.png")));
			LB_PneuDuroP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMedioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMacioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			
			RB_PneuWetP2.setEnabled(true);
			RB_PneuInterP2.setEnabled(false);
			RB_PneuMacioP2.setEnabled(false);
			RB_PneuMedioP2.setEnabled(false);
			RB_PneuDuroP2.setEnabled(false);
			
			RB_PneuWetP2.setSelected(true);
			
		}else if (LB_Clima.getText().equals("Chuva Fraca")) {
			
			LB_PneuInterP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIntermediarioF140px.png")));
			LB_PneuWetP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuDuroP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMedioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMacioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			
			RB_PneuWetP1.setEnabled(false);
			RB_PneuInterP1.setEnabled(true);
			RB_PneuMacioP1.setEnabled(false);
			RB_PneuMedioP1.setEnabled(false);
			RB_PneuDuroP1.setEnabled(false);
			
			RB_PneuInterP1.setSelected(true);
			
			//
			
			LB_PneuInterP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIntermediarioF140px.png")));
			LB_PneuWetP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuDuroP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMedioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuMacioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			
			RB_PneuWetP2.setEnabled(false);
			RB_PneuInterP2.setEnabled(true);
			RB_PneuMacioP2.setEnabled(false);
			RB_PneuMedioP2.setEnabled(false);
			RB_PneuDuroP2.setEnabled(false);
			
			RB_PneuInterP2.setSelected(true);

		}else {
			
			LB_PneuInterP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuWetP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuDuroP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuDuroF140px.png")));
			LB_PneuMedioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMedioF140px.png")));
			LB_PneuMacioP1.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMacioF140px.png")));
			
			RB_PneuWetP1.setEnabled(false);
			RB_PneuInterP1.setEnabled(false);
			RB_PneuMacioP1.setEnabled(true);
			RB_PneuMedioP1.setEnabled(true);
			RB_PneuDuroP1.setEnabled(true);
			
			RB_PneuMacioP1.setSelected(true);
			
			//
			
			LB_PneuInterP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuWetP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuIndisponivelF140px.png")));
			LB_PneuDuroP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuDuroF140px.png")));
			LB_PneuMedioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMedioF140px.png")));
			LB_PneuMacioP2.setIcon(new ImageIcon(Pagina_EscolhePneuF1.class.getResource("/Imagens/PneuMacioF140px.png")));
			
			RB_PneuWetP2.setEnabled(false);
			RB_PneuInterP2.setEnabled(false);
			RB_PneuMacioP2.setEnabled(true);
			RB_PneuMedioP2.setEnabled(true);
			RB_PneuDuroP2.setEnabled(true);
			
			RB_PneuMacioP2.setSelected(true);
			
		}
		
	}
	

	public void VerificaPneu() {
		Dados dados = new Dados();
		
		//Piloto 1 
		
		if(RB_PneuMacioP1.isSelected()) {
			
			dados.PneuAtualP1="SOFT";
			dados.IMGPneuAtualP1 = "/Imagens/PneuMacioF140px.png";
			
		}else if(RB_PneuMedioP1.isSelected()) {
			dados.PneuAtualP1="MEDIUM";
			dados.IMGPneuAtualP1 = "/Imagens/PneuMedioF140px.png";
			
		}else if(RB_PneuDuroP1.isSelected()) {
			dados.PneuAtualP1="HARD";
			dados.IMGPneuAtualP1 = "/Imagens/PneuDuroF140px.png";

		}else if(RB_PneuInterP1.isSelected()) {
			dados.PneuAtualP1="INTER";
			dados.IMGPneuAtualP1 = "/Imagens/PneuIntermediarioF140px.png";

		}else if(RB_PneuWetP1.isSelected()) {
			dados.PneuAtualP1="WET";
			dados.IMGPneuAtualP1 = "/Imagens/PneuChuvaF140px.png";

		}
		
		//Piloto 2
		
		if(RB_PneuMacioP2.isSelected()) {
			dados.PneuAtualP2="SOFT";
			dados.IMGPneuAtualP2 = "/Imagens/PneuMacioF140px.png";

		}else if(RB_PneuMedioP2.isSelected()) {
			dados.PneuAtualP2="MEDIUM";
			dados.IMGPneuAtualP2 = "/Imagens/PneuMedioF140px.png";

		}else if(RB_PneuDuroP2.isSelected()) {
			dados.PneuAtualP2="HARD";
			dados.IMGPneuAtualP2 = "/Imagens/PneuDuroF140px.png";
			
		}else if(RB_PneuInterP2.isSelected()) {
			dados.PneuAtualP2="INTER";
			dados.IMGPneuAtualP2 = "/Imagens/PneuIntermediarioF140px.png";
			
		}else if(RB_PneuWetP2.isSelected()) {
			dados.PneuAtualP2="WET";
			dados.IMGPneuAtualP2 = "/Imagens/PneuChuvaF140px.png";
		}
	}
}