import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Selecionar_Categoria extends JFrame {

	private JPanel contentPane;
	public static String BT_Cate = "Fórmula 1", BT_Temp = "Temporada 2024", BT_Imagem = "/Imagens/Logo F1 Novo_OK.png";
	private JRadioButton RB_F1;
	private JRadioButton RB_Indy;
	private JRadioButton RB_Nascar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selecionar_Categoria frame = new Selecionar_Categoria();
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
	public Selecionar_Categoria() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				RB_F1.isSelected();
				BT_Cate = "Fórmula 1";
				BT_Imagem = "/Imagens/Logo F1 Novo_OK.png";
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Categoria.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 716);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BT_Voltar = new JButton("VOLTAR");
		BT_Voltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		BT_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inicial_Carregar Inicial = new Inicial_Carregar();
				Inicial.setVisible(true);
				Inicial.setLocationRelativeTo(null);
								
				dispose();
				
			}
		});
		BT_Voltar.setBounds(20, 638, 172, 21);
		contentPane.add(BT_Voltar);
		
		//Lista_Temp.add("Temporada 1950");
		
		JLabel lblNewLabel = new JLabel("SELECIONE A TEMPORADA");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 570, 450, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel1 = new JLabel("MOTORSPORT MANAGER");
		lblNewLabel1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(25, 10, 460, 36);
		contentPane.add(lblNewLabel1);
		
		JComboBox<?> CB_Lista_Temp = new JComboBox();
		CB_Lista_Temp.setMaximumRowCount(6);
		CB_Lista_Temp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021"}));
		
		CB_Lista_Temp.setBounds(20, 607, 450, 21);
		contentPane.add(CB_Lista_Temp);
		setLocationRelativeTo(null);
				
		JButton BT_Comecar = new JButton("ESCOLHER EQUIPE");
		BT_Comecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		BT_Comecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Selecionar_Equipe Equipe = new Selecionar_Equipe();
				
				Equipe.setVisible(true);
				Equipe.setLocationRelativeTo(null);
				
				dispose();
				
				CB_Lista_Temp.getSelectedItem();
				
				BT_Temp = ""+CB_Lista_Temp.getSelectedItem();
				
			}
		});
		BT_Comecar.setBounds(298, 638, 172, 21);
		contentPane.add(BT_Comecar);
		
		JLabel LB_Carro = new JLabel("");
		LB_Carro.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Carro.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/F1 1.png")));
		LB_Carro.setSize(100, 100);
		LB_Carro.setBounds(20, 214, 450, 274);
		contentPane.add(LB_Carro);
		
		JLabel LB_Logo = new JLabel("");
		LB_Logo.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo F1 Novo_OK.png")));
		LB_Logo.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Logo.setBounds(20, 56, 450, 148);
		contentPane.add(LB_Logo);
		
		JLabel lblSelecioneACategoria = new JLabel("SELECIONE A CATEGORIA");
		lblSelecioneACategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneACategoria.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblSelecioneACategoria.setBounds(20, 498, 450, 27);
		contentPane.add(lblSelecioneACategoria);
		
		RB_F1 = new JRadioButton("FÓRMULA 1");
		RB_F1.setBackground(new Color(255, 255, 255));
		RB_F1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//setBT_Categoria("Fórmula 1");
				BT_Cate = "Fórmula 1";
				BT_Imagem = "/Imagens/Logo F1 Novo_OK.png";
				
				int RandonF1;
				Random random = new Random();
				RandonF1 = random.nextInt(7);
				//JOptionPane.showMessageDialog(null, ""+RandonF1);
				
				LB_Carro.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/F1 "+(RandonF1+1)+".png")));
				LB_Logo.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo F1 Novo_OK.png")));
				
				/*
				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021", 
						"Temporada 2020", "Temporada 2019", "Temporada 2018", "Temporada 2017", "Temporada 2016", "Temporada 2015", "Temporada 2014", 
						"Temporada 2013", "Temporada 2012", "Temporada 2011", "Temporada 2010", "Temporada 2009", "Temporada 2008", "Temporada 2007", 
						"Temporada 2006", "Temporada 2005", "Temporada 2004", "Temporada 2003", "Temporada 2002", "Temporada 2001", "Temporada 2000", 
						"Temporada 1999", "Temporada 1998", "Temporada 1997", "Temporada 1996", "Temporada 1995", "Temporada 1994", "Temporada 1993", 
						"Temporada 1992", "Temporada 1991", "Temporada 1990", "Temporada 1989", "Temporada 1988", "Temporada 1987", "Temporada 1986", 
						"Temporada 1985", "Temporada 1984", "Temporada 1983", "Temporada 1982", "Temporada 1981", "Temporada 1980", "Temporada 1979", 
						"Temporada 1978", "Temporada 1977", "Temporada 1976", "Temporada 1975", "Temporada 1974", "Temporada 1973", "Temporada 1972", 
						"Temporada 1971", "Temporada 1970", "Temporada 1969", "Temporada 1968", "Temporada 1967", "Temporada 1966", "Temporada 1965", 
						"Temporada 1964", "Temporada 1963", "Temporada 1962", "Temporada 1961", "Temporada 1960", "Temporada 1959", "Temporada 1958", 
						"Temporada 1957", "Temporada 1956", "Temporada 1955", "Temporada 1954", "Temporada 1953", "Temporada 1952", "Temporada 1951", 
						"Temporada 1950"}));
				
				*/
				
				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021"}));
				
			}
		});
		RB_F1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		RB_F1.setHorizontalAlignment(SwingConstants.CENTER);
		RB_F1.setSelected(true);
		RB_F1.setBounds(20, 543, 103, 21);
		contentPane.add(RB_F1);
		
		RB_Indy = new JRadioButton("INDY");
		RB_Indy.setBackground(new Color(255, 255, 255));
		RB_Indy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//setBT_Categoria("Fórmula INDY");
				BT_Cate = "Fórmula INDY";
				BT_Imagem = "/Imagens/Logo Indy_OK.png";
				
				int RandonIndy;
				Random random = new Random();
				RandonIndy = random.nextInt(8);
				
				LB_Carro.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Indy "+(RandonIndy+1)+".png")));
				LB_Logo.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo Indy_OK.png")));
				
				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024"}));
				
				/*
				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021", 
						"Temporada 2020", "Temporada 2019", "Temporada 2018", "Temporada 2017", "Temporada 2016", "Temporada 2015", "Temporada 2014", 
						"Temporada 2013", "Temporada 2012", "Temporada 2011", "Temporada 2010", "Temporada 2009", "Temporada 2008", "Temporada 2007", 
						"Temporada 2006", "Temporada 2005", "Temporada 2004", "Temporada 2003", "Temporada 2002", "Temporada 2001", "Temporada 2000", 
						"Temporada 1999", "Temporada 1998", "Temporada 1997", "Temporada 1996"}));
				*/				
			}
		});
		RB_Indy.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		RB_Indy.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Indy.setBounds(197, 543, 103, 21);
		contentPane.add(RB_Indy);
		
		RB_Nascar = new JRadioButton("NASCAR");
		RB_Nascar.setBackground(new Color(255, 255, 255));
		RB_Nascar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//setBT_Categoria("NASCAR");
				BT_Cate = "NASCAR";
				
				BT_Imagem = "/Imagens/Logo Nascar_OK.png";
				
				int RandonNascar;
				Random random = new Random();
				RandonNascar = random.nextInt(7);
				
				LB_Carro.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Cup Series "+(RandonNascar+1)+".png")));
				
				LB_Logo.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource("/Imagens/Logo Nascar_OK.png")));

				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024"}));
				
				/*
				
				CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021", 
						"Temporada 2020", "Temporada 2019", "Temporada 2018", "Temporada 2017", "Temporada 2016", "Temporada 2015", "Temporada 2014", 
						"Temporada 2013", "Temporada 2012", "Temporada 2011", "Temporada 2010", "Temporada 2009", "Temporada 2008", "Temporada 2007", 
						"Temporada 2006", "Temporada 2005", "Temporada 2004", "Temporada 2003", "Temporada 2002", "Temporada 2001", "Temporada 2000", 
						"Temporada 1999", "Temporada 1998", "Temporada 1997", "Temporada 1996", "Temporada 1995", "Temporada 1994", "Temporada 1993", 
						"Temporada 1992", "Temporada 1991", "Temporada 1990", "Temporada 1989", "Temporada 1988", "Temporada 1987", "Temporada 1986", 
						"Temporada 1985", "Temporada 1984", "Temporada 1983", "Temporada 1982", "Temporada 1981", "Temporada 1980", "Temporada 1979", 
						"Temporada 1978", "Temporada 1977", "Temporada 1976", "Temporada 1975", "Temporada 1974", "Temporada 1973", "Temporada 1972", 
						"Temporada 1971", "Temporada 1970", "Temporada 1969", "Temporada 1968", "Temporada 1967", "Temporada 1966", "Temporada 1965", 
						"Temporada 1964", "Temporada 1963", "Temporada 1962", "Temporada 1961", "Temporada 1960", "Temporada 1959", "Temporada 1958", 
						"Temporada 1957", "Temporada 1956", "Temporada 1955", "Temporada 1954", "Temporada 1953", "Temporada 1952", "Temporada 1951", 
						"Temporada 1950", "Temporada 1949"}));

				 */
				
			}
		});
		RB_Nascar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		RB_Nascar.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Nascar.setBounds(367, 543, 103, 21);
		contentPane.add(RB_Nascar);

		ButtonGroup botao = new ButtonGroup();
		botao.add(RB_Nascar);
		botao.add(RB_F1);
		botao.add(RB_Indy);
		
	}
	
	 public static String setBT_Categoria(String BT_Cat) {
         return BT_Cate = BT_Cat;
    }
	 
	 public static String getBT_Categoria() {
         return BT_Cate;
    }

}
