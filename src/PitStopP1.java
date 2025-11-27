import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PitStopP1 extends JFrame {

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
	private JLabel LB_InfosPneuP1;
	private JLabel LB_InfosPneuP2_1;
	private JLabel LB_InfosPneu;
	private JLabel LB_VoltasCorrida;
	private String Pneu;
	private JButton btnNewButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PitStopP1 frame = new PitStopP1();
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
	public PitStopP1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Dados dados = new Dados();
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				Pagina_CorridaF1 PaginaCorridaF1 = new Pagina_CorridaF1();
				Selecionar_Equipe SelecionarEquipe = new Selecionar_Equipe();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				TestaChuva();
				
				LB_Piloto1.setText(""+SelecionarEquipe.NomePiloto1);
				LB_BandeiraPiloto1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Bandeira "+SelecionarEquipe.PaisPiloto1+".png")));
				
				LB_VoltasCorrida.setText("FALTAM "+(dados.Voltas_Pista - PaginaCorridaF1.aux)+" VOLTAS!");
				
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 276);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		
		BT_IrCorrida = new JButton("  CONFIRMAR PITSTOP");
		BT_IrCorrida.setBounds(320, 167, 250, 40);
		BT_IrCorrida.setIcon(new ImageIcon(PitStopP1.class.getResource("/Imagens/IconePitStop32px.png")));
		BT_IrCorrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				Pagina_CorridaF1 PaginaCorridaF1 = new Pagina_CorridaF1();
				
				PneuEscolhido();
				
				PaginaCorridaF1.setFocusable(true);
				PaginaCorridaF1.setAutoRequestFocus(true);
				PaginaCorridaF1.toFront();
					
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
				
				Pneu = "WET";
				
				VidaUtilPneu();
				
			}
		});
		RB_PneuWetP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuWetP1.setBackground(Color.WHITE);
		RB_PneuWetP1.setBounds(10, 125, 60, 20);
		contentPane.add(RB_PneuWetP1);
		
		RB_PneuInterP1 = new JRadioButton("");
		RB_PneuInterP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pneu = "INTER";
				
				VidaUtilPneu();
				
			}
		});
		RB_PneuInterP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuInterP1.setBackground(Color.WHITE);
		RB_PneuInterP1.setBounds(70, 125, 60, 20);
		contentPane.add(RB_PneuInterP1);
		
		RB_PneuDuroP1 = new JRadioButton("");
		RB_PneuDuroP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pneu = "HARD";
				
				VidaUtilPneu();
				
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
				
				Pneu = "MEDIUM";
				
				VidaUtilPneu();
				
			}
		});
		RB_PneuMedioP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMedioP1.setBackground(Color.WHITE);
		RB_PneuMedioP1.setBounds(190, 125, 60, 20);
		contentPane.add(RB_PneuMedioP1);
		
		RB_PneuMacioP1 = new JRadioButton("");
		RB_PneuMacioP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pneu = "SOFT";
				
				VidaUtilPneu();
				
			}
		});
		RB_PneuMacioP1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMacioP1.setBackground(Color.WHITE);
		RB_PneuMacioP1.setBounds(250, 125, 60, 20);
		contentPane.add(RB_PneuMacioP1);

		
		LB_InfosPneuP1 = new JLabel("Info dos Pneus");
		LB_InfosPneuP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		LB_InfosPneuP1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP1.setBounds(10, 156, 300, 20);
		contentPane.add(LB_InfosPneuP1);
		
		LB_InfosPneu = new JLabel("Vida útil de aprox. X voltas!");
		LB_InfosPneu.setForeground(new Color(255, 0, 0));
		LB_InfosPneu.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneu.setFont(new Font("Arial Narrow", Font.ITALIC, 13));
		LB_InfosPneu.setBounds(10, 176, 300, 20);
		contentPane.add(LB_InfosPneu);
		
		LB_InfosPneuP2_1 = new JLabel("Vida útil de aprox. X voltas!");
		LB_InfosPneuP2_1.setForeground(new Color(255, 0, 0));
		LB_InfosPneuP2_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_InfosPneuP2_1.setFont(new Font("Arial Narrow", Font.ITALIC, 13));
		LB_InfosPneuP2_1.setBounds(574, 176, 300, 20);
		contentPane.add(LB_InfosPneuP2_1);
		
		LB_VoltasCorrida = new JLabel("FALTAM X VOLTAS!");
		LB_VoltasCorrida.setHorizontalAlignment(SwingConstants.CENTER);
		LB_VoltasCorrida.setForeground(Color.RED);
		LB_VoltasCorrida.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		LB_VoltasCorrida.setBounds(10, 195, 300, 20);
		contentPane.add(LB_VoltasCorrida);
		
		ButtonGroup botaoP1 = new ButtonGroup();
		botaoP1.add(RB_PneuMacioP1);
		botaoP1.add(RB_PneuMedioP1);
		botaoP1.add(RB_PneuDuroP1);
		botaoP1.add(RB_PneuInterP1);
		botaoP1.add(RB_PneuWetP1);
		
		btnNewButton = new JButton("Teste");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(250, 207, 89, 23);
		contentPane.add(btnNewButton);
		
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
			RB_PneuWetP1.doClick();
			
			VidaUtilPneu();
			
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
			RB_PneuInterP1.doClick();
			
			VidaUtilPneu();
			
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
			RB_PneuMacioP1.doClick();
			
			VidaUtilPneu();
			
		}
		
	}
	
	public void VidaUtilPneu() {
		
		Dados dados = new Dados();	
		
		if(Pneu == "SOFT") {
			
			LB_InfosPneu.setText("Vida útil de aprox. "+((int) Math.ceil((double)dados.Voltas_Pista / 4)+2)+" voltas!");
			
		}
		
		if(Pneu == "MEDIUM") {
			
			LB_InfosPneu.setText("Vida útil de aprox. "+((int) Math.ceil((double)dados.Voltas_Pista / 2.5)+2)+" voltas!");
			
		}
		
		if(Pneu == "HARD") {
			
			LB_InfosPneu.setText("Vida útil de aprox. "+((int) Math.ceil((double)dados.Voltas_Pista / 2)+4)+" voltas!");
			
		}
		
		if(Pneu == "INTER" || Pneu == "WET") {
			
			LB_InfosPneu.setText("Vida útil de aprox. "+((int) Math.ceil((double)dados.Voltas_Pista / 2)+1)+" voltas!");
			
		}	
	}
	
	public void PneuEscolhido() {
		
		Dados dados = new Dados();
		Pagina_CorridaF1 PaginaCorridaF1 = new Pagina_CorridaF1();
		
		PaginaCorridaF1.QTDPitP1++;
		
		if(RB_PneuMacioP1.isSelected()) {
			
			dados.PneuPitP1="SOFT";
			dados.IMGPitP1 = "/Imagens/PneuMacioF140px.png";
			dados.VidaPitP1 = ((int) Math.ceil((double)dados.Voltas_Pista / 4)+2);
			
		}else if(RB_PneuMedioP1.isSelected()) {
			
			dados.PneuPitP1="MEDIUM";
			dados.IMGPitP1 = "/Imagens/PneuMedioF140px.png";
			dados.VidaPitP1 = ((int) Math.ceil((double)dados.Voltas_Pista / 2.5)+2);
			
			
		}else if(RB_PneuDuroP1.isSelected()) {
			
			dados.PneuPitP1="HARD";
			dados.IMGPitP1 = "/Imagens/PneuDuroF140px.png";
			dados.VidaPitP1 = ((int) Math.ceil((double)dados.Voltas_Pista / 2)+4);
			
		}else if(RB_PneuInterP1.isSelected()) {
			
			dados.PneuPitP1="INTER";
			dados.IMGPitP1 = "/Imagens/PneuIntermediarioF140px.png";
			dados.VidaPitP1 = ((int) Math.ceil((double)dados.Voltas_Pista / 2)+1);	
			

		}else if(RB_PneuWetP1.isSelected()) {
			
			dados.PneuPitP1="WET";
			dados.IMGPitP1 = "/Imagens/PneuChuvaF140px.png";
			dados.VidaPitP1 = ((int) Math.ceil((double)dados.Voltas_Pista / 2)+1);
			
		}
		
		PaginaCorridaF1.Pit = 1;
		
	}
}
