package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.StringReader;

import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.html.HTMLEditorKit;

import connection.CommonFunctions;
import connection.Interpretador;
import models.Player;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;

public class Game extends Window_Template {

	private static Player player;
	private static DataOutputStream dataOut;
	public static boolean playable = true;
	
	private static JFrame frame;
	private static JEditorPane JEditorPaneChat;
	
	private static JLabel btnPosicaoUm;
	private static JLabel btnPosicaoDois;
	private static JLabel btnPosicaoTres;
	private static JLabel btnPosicaoQuatro;
	private static JLabel btnPosicaoCinco;
	private static JLabel btnPosicaoSeis;
	private static JLabel btnPosicaoSete;
	private static JLabel btnPosicaoOito;
	private static JLabel btnPosicaoNove;
	private static JLabel lblContagem;
	private static JLabel lblYourName;
	private static JLabel lblHisName;
	private static JLabel lblContYouWin;
	private static JLabel lblContYouLose;
	private static JLabel lblContHeLose;
	private static JLabel lblContHeWin;
	public static boolean pararContagem = false;
	private static JPanel btnLimparChat;
	private static JLabel lblLimparChat;
	private static JPanel panelHisName;
	private static JLabel lblNewLabel;
	private static JLabel lblDerrotas;
	private static JPanel panel_1;
	private static JLabel label_1;
	private static JLabel label_3;
	private static JPanel panel_2;
	private static JLabel lblDesconectar;
	private static JLabel lblNewLabel_1;
	
	public static void main(String[] args) {
		Player player = new Player("Teste", 'X');
		Game home = new Game(player, null);
	}
	
	public Game(Player player, DataOutputStream dataOut) {
		super(1000, 618, "TIC TAC TOE Jogador " + player.getYourName(), "/view/icons/icon_tic_tac_toe.png");
		
		this.player = player;
		this.dataOut = dataOut;
		this.frame = this;
		
		content(frame);
	}
	
	private static void content(JFrame frame) {
		JLayeredPane usefulSpace = new JLayeredPane();
		usefulSpace.setBounds(1, 32, 1240, 800);
		usefulSpace.setLayout(null);
		frame.getContentPane().add(usefulSpace);
		
		JLabel background = new JLabel("");
		background.setIcon(null);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setBounds(0, 0, 1000, 618);
		usefulSpace.add(background);
		usefulSpace.setLayer(background, -1);
		
		JPanel panelTabuleiro = new JPanel();
		panelTabuleiro.setBorder(new MatteBorder(0, 0, 0, 1, new Color(215, 215, 215)));
		panelTabuleiro.setBackground(new Color(20, 189, 172));
		panelTabuleiro.setBounds(0, 0, 618, 618);
		panelTabuleiro.setLayout(null);
		usefulSpace.add(panelTabuleiro);
		
		JPanel tabuleiro = new JPanel();
		tabuleiro.setBackground(new Color(20, 189, 172));
		tabuleiro.setBounds(59, 59, 500, 500);
		panelTabuleiro.add(tabuleiro);
		tabuleiro.setLayout(null);
		
		btnPosicaoUm = new JLabel(" ");
		btnPosicaoUm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoUm.getText().equals(" ")) {
						btnPosicaoUm.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS1");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoUm.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoUm.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoUm.setBounds(0, 0, 166, 166);
		btnPosicaoUm.setBorder(new MatteBorder(0, 0, 4, 4, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoUm);
		
		btnPosicaoDois = new JLabel(" ");
		btnPosicaoDois.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoDois.getText().equals(" ")) {
						btnPosicaoDois.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS2");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoDois.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoDois.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoDois.setBounds(166, 0, 168, 166);
		btnPosicaoDois.setBorder(new MatteBorder(0, 4, 4, 4, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoDois);
		
		btnPosicaoTres = new JLabel(" ");
		btnPosicaoTres.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoTres.getText().equals(" ")) {
						btnPosicaoTres.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS3");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoTres.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoTres.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoTres.setBounds(334, 0, 166, 166);
		btnPosicaoTres.setBorder(new MatteBorder(0, 4, 4, 0, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoTres);
		
		btnPosicaoQuatro = new JLabel(" ");
		btnPosicaoQuatro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoQuatro.getText().equals(" ")) {
						btnPosicaoQuatro.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS4");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoQuatro.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoQuatro.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoQuatro.setBounds(0, 166, 166, 168);
		btnPosicaoQuatro.setBorder(new MatteBorder(4, 0, 4, 4, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoQuatro);
		
		btnPosicaoCinco = new JLabel(" ");
		btnPosicaoCinco.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoCinco.getText().equals(" ")) {
						btnPosicaoCinco.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS5");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoCinco.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoCinco.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoCinco.setBounds(166, 166, 168, 168);
		btnPosicaoCinco.setBorder(new LineBorder(new Color(13, 161, 146), 4));
		tabuleiro.add(btnPosicaoCinco);
		
		btnPosicaoSeis = new JLabel(" ");
		btnPosicaoSeis.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoSeis.getText().equals(" ")) {
						btnPosicaoSeis.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS6");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoSeis.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoSeis.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoSeis.setBounds(334, 166, 166, 168);
		btnPosicaoSeis.setBorder(new MatteBorder(4, 4, 4, 0, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoSeis);
		
		btnPosicaoSete = new JLabel(" ");
		btnPosicaoSete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoSete.getText().equals(" ")) {
						btnPosicaoSete.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS7");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoSete.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoSete.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoSete.setBounds(0, 334, 166, 166);
		btnPosicaoSete.setBorder(new MatteBorder(4, 0, 0, 4, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoSete);
		
		btnPosicaoOito = new JLabel(" ");
		btnPosicaoOito.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoOito.getText().equals(" ")) {
						btnPosicaoOito.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS8");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoOito.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoOito.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoOito.setBounds(166, 334, 168, 166);
		btnPosicaoOito.setBorder(new MatteBorder(4, 4, 0, 4, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoOito);
		
		btnPosicaoNove = new JLabel(" ");
		btnPosicaoNove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(playable) {
					if(btnPosicaoNove.getText().equals(" ")) {
						btnPosicaoNove.setText(Character.toString(player.getCharPlayer()));
						afterButtonPress("$POS9");
					} else {
						JOptionPane.showMessageDialog(frame, "Você tentou jogar numa posição inválida, tente novamente.", "Alerta de jogada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Aguarde a sua vez de jogar.", "Alerta de turno", 2);
				}
			}
		});
		btnPosicaoNove.setHorizontalAlignment(SwingConstants.CENTER);
		btnPosicaoNove.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 150));
		btnPosicaoNove.setBounds(334, 334, 166, 166);
		btnPosicaoNove.setBorder(new MatteBorder(4, 4, 0, 0, new Color(13, 161, 146)));
		tabuleiro.add(btnPosicaoNove);
		
		lblContagem = new JLabel("-");
		lblContagem.setFont(new Font("Open Sans Extrabold", Font.PLAIN, 32));
		lblContagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblContagem.setBounds(259, 0, 50, 59);
		panelTabuleiro.add(lblContagem);
		
		JLabel lblSegundos_1 = new JLabel("segundos");
		lblSegundos_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSegundos_1.setFont(new Font("Open Sans Light", Font.PLAIN, 11));
		lblSegundos_1.setBounds(309, 0, 50, 59);
		panelTabuleiro.add(lblSegundos_1);
		
		JPanel panelPlacar = new JPanel();
		panelPlacar.setBackground(new Color(248, 248, 248));
		panelPlacar.setBounds(618, 0, 236, 236);
		panelPlacar.setLayout(null);
		usefulSpace.add(panelPlacar);
		
		JPanel panelYourName = new JPanel();
		panelYourName.setBorder(new CompoundBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(20, 189, 172)), new MatteBorder(1, 1, 0, 1, new Color(215, 215, 215))));
		panelYourName.setBackground(Color.WHITE);
		panelYourName.setBounds(36, 15, 164, 40);
		panelPlacar.add(panelYourName);
		panelYourName.setLayout(null);
		
		lblYourName = new JLabel(player.getYourName());
		lblYourName.setBounds(0, 0, 164, 36);
		panelYourName.add(lblYourName);
		lblYourName.setFont(new Font("Open Sans Light", Font.BOLD, 22));
		lblYourName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 1, 1, 1, new Color(215, 215, 215)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(36, 55, 164, 50);
		panelPlacar.add(panel);
		panel.setLayout(null);
		
		lblContYouWin = new JLabel("0");
		lblContYouWin.setBounds(0, 0, 25, 20);
		panel.add(lblContYouWin);
		lblContYouWin.setFont(new Font("Open Sans Extrabold", Font.BOLD, 13));
		lblContYouWin.setForeground(Color.BLACK);
		lblContYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel = new JLabel("partidas ganhas");
		lblNewLabel.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblNewLabel.setBounds(25, 0, 139, 20);
		panel.add(lblNewLabel);
		
		lblContYouLose = new JLabel("0");
		lblContYouLose.setBounds(0, 25, 25, 25);
		panel.add(lblContYouLose);
		lblContYouLose.setForeground(Color.BLACK);
		lblContYouLose.setFont(new Font("Open Sans Extrabold", Font.BOLD, 13));
		lblContYouLose.setVerticalAlignment(SwingConstants.TOP);
		lblContYouLose.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDerrotas = new JLabel("partidas perdidas");
		lblDerrotas.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblDerrotas.setBounds(25, 25, 139, 25);
		panel.add(lblDerrotas);
		
		panelHisName = new JPanel();
		panelHisName.setLayout(null);
		panelHisName.setBorder(new LineBorder(new Color(215, 215, 215)));
		panelHisName.setBackground(Color.WHITE);
		panelHisName.setBounds(36, 120, 164, 40);
		panelPlacar.add(panelHisName);
		
		lblHisName = new JLabel(player.getNameOfOpponent());
		lblHisName.setBounds(0, 0, 164, 40);
		panelHisName.add(lblHisName);
		lblHisName.setFont(new Font("Open Sans Light", Font.PLAIN, 22));
		lblHisName.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(0, 1, 1, 1, new Color(215, 215, 215)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(36, 160, 164, 50);
		panelPlacar.add(panel_1);
		
		label_1 = new JLabel("partidas ganhas");
		label_1.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		label_1.setBounds(25, 0, 139, 20);
		panel_1.add(label_1);
		
		label_3 = new JLabel("partidas perdidas");
		label_3.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		label_3.setBounds(25, 25, 139, 25);
		panel_1.add(label_3);
		
		lblContHeWin = new JLabel("0");
		lblContHeWin.setBounds(0, 0, 25, 25);
		panel_1.add(lblContHeWin);
		lblContHeWin.setForeground(Color.BLACK);
		lblContHeWin.setFont(new Font("Open Sans Extrabold", Font.BOLD, 13));
		lblContHeWin.setVerticalAlignment(SwingConstants.TOP);
		lblContHeWin.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblContHeLose = new JLabel("0");
		lblContHeLose.setBounds(0, 25, 25, 25);
		panel_1.add(lblContHeLose);
		lblContHeLose.setFont(new Font("Open Sans Extrabold", Font.BOLD, 13));
		lblContHeLose.setForeground(Color.BLACK);
		lblContHeLose.setVerticalAlignment(SwingConstants.TOP);
		lblContHeLose.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(248, 248, 248));
		panelMenu.setBounds(854, 0, 146, 236);
		panelMenu.setLayout(null);
		usefulSpace.add(panelMenu);
		
		btnLimparChat = new JPanel();
		btnLimparChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JEditorPaneChat.setFont(new Font("Open Sans Light", Font.PLAIN, 10));
				String fontfamily = JEditorPaneChat.getFont().getFamily();
				JEditorPaneChat.setText("<html><body style=\"font-family: " + fontfamily + "\"<b></b></html>");
			}
		});
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 154, 126, 30);
		panelMenu.add(panel_2);
		
		lblDesconectar = new JLabel("Desconectar");
		lblDesconectar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesconectar.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblDesconectar.setBounds(0, 0, 126, 30);
		panel_2.add(lblDesconectar);
		btnLimparChat.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLimparChat.setBounds(10, 194, 126, 30);
		panelMenu.add(btnLimparChat);
		btnLimparChat.setLayout(null);
		
		lblLimparChat = new JLabel("Limpar Chat");
		lblLimparChat.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblLimparChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimparChat.setBounds(0, 0, 126, 30);
		btnLimparChat.add(lblLimparChat);
		
		JPanel panelChat = new JPanel();
		panelChat.setBackground(new Color(248, 248, 248));
		panelChat.setBounds(618, 236, 382, 382);
		panelChat.setLayout(null);
		usefulSpace.add(panelChat);
		
		JPanel chat = new JPanel();
		chat.setBorder(new LineBorder(new Color(0, 0, 0)));
		chat.setBounds(10, 10, 362, 362);
		chat.setLayout(null);
		panelChat.add(chat);
		
		JPanel panelSendMessage = new JPanel();
		panelSendMessage.setBackground(Color.WHITE);
		panelSendMessage.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panelSendMessage.setBounds(1, 1, 360, 25);
		panelSendMessage.setLayout(null);
		chat.add(panelSendMessage);
		
		JFormattedTextField txtMessageInput = new JFormattedTextField();
		txtMessageInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					String mensagem = "$MSG" + CommonFunctions.getTimeNow() + " " + player.getCodeNameColorful() + " " + txtMessageInput.getText();
					Interpretador.traduzir(mensagem);
					CommonFunctions.sendMessage(dataOut, mensagem);
					txtMessageInput.setText("");
				}
			}
		});
		txtMessageInput.setBounds(5, 0, 270, 24);
		txtMessageInput.setBorder(null);
		panelSendMessage.add(txtMessageInput);
		
		JLabel btnEnviar = new JLabel("ENVIAR");
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String mensagem = "$MSG" + CommonFunctions.getTimeNow() + " " + player.getCodeNameColorful() + " " + txtMessageInput.getText();
				Interpretador.traduzir(mensagem);
				CommonFunctions.sendMessage(dataOut, mensagem);
				txtMessageInput.setText("");
			}
		});
		btnEnviar.setFont(new Font("Open Sans Light", Font.PLAIN, 12));
		btnEnviar.setBounds(280, 0, 80, 25);
		btnEnviar.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panelSendMessage.add(btnEnviar);
		btnEnviar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(1, 26, 359, 335);
		scrollPane.setBorder(null);
		chat.add(scrollPane);
		
		JEditorPaneChat = new JEditorPane();
		JEditorPaneChat.setEditable(false);
		JEditorPaneChat.setContentType("text/html");
		JEditorPaneChat.setFont(new Font("Open Sans Light", Font.PLAIN, 6));
		String fontfamily = JEditorPaneChat.getFont().getFamily();
		JEditorPaneChat.setText("<html><body style=\"font-family: " + fontfamily + "\"<b>"
				+ "<img src='https://www.kizoa.fr/img/e8nZC.gif' height='42' width='42'>"
				+ "Bem vindo ao jogo TicTacToe!"
				+ "<img src='https://www.kizoa.fr/img/e8nZC.gif' height='42' width='42'></b></html>");
		
		scrollPane.setViewportView(JEditorPaneChat);
		
		frame.repaint();
	}
	
	public static void addMessage(String message) {
		HTMLEditorKit editor = (HTMLEditorKit) JEditorPaneChat.getEditorKit();
		StringReader reader = new StringReader(message);

		try {
		  editor.read(reader, JEditorPaneChat.getDocument(), JEditorPaneChat.getDocument().getLength());
		}
		catch(Exception ex) {
		   System.err.println(ex.getMessage());
		}
	}
	
	public static void close() {
		pararContagem=true;
		frame.dispose();
	}

	public static void makeAMove(String move) {
		int positionPlayed = Integer.parseInt(move.substring(0, move.length()-1));
		String charPlayer = move.substring(1, move.length());
		
		switch(positionPlayed) {
			case 1: btnPosicaoUm.setText(charPlayer); break;
			case 2: btnPosicaoDois.setText(charPlayer); break;
			case 3: btnPosicaoTres.setText(charPlayer); break;
			case 4: btnPosicaoQuatro.setText(charPlayer); break;
			case 5: btnPosicaoCinco.setText(charPlayer); break;
			case 6: btnPosicaoSeis.setText(charPlayer); break;
			case 7: btnPosicaoSete.setText(charPlayer); break;
			case 8: btnPosicaoOito.setText(charPlayer); break;
			case 9: btnPosicaoNove.setText(charPlayer); break;
		}
	}
	
	public static void Thread_inicializaContagem() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int segundos = 15;
				while(segundos>=0) {
					if(pararContagem) {
						segundos=-5;
						break;
					}
					lblContagem.setText("" + segundos);
					segundos--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(segundos<-1) {
					lblContagem.setText("-");
				} else {
					JOptionPane.showMessageDialog(Game.frame, "Seu tempo se esgotou.", "Limite de tempo atingido", 2);
					lblContagem.setText("-");
					declareResult(player.getNameOfOpponent());
				}
			}
		}).start();
	}
	
	private static void afterButtonPress(String message) {
		Interpretador.traduzir(message + player.getCharPlayer());
		CommonFunctions.sendMessage(dataOut, message + player.getCharPlayer());
		
		checkMatchOver();
		
		playable=false;
		pararContagem=true;
	}
	
	private static void checkMatchOver() {
		char[] tabuleiro = {btnPosicaoUm.getText().charAt(0), btnPosicaoDois.getText().charAt(0), btnPosicaoTres.getText().charAt(0), btnPosicaoQuatro.getText().charAt(0), btnPosicaoCinco.getText().charAt(0), btnPosicaoSeis.getText().charAt(0), btnPosicaoSete.getText().charAt(0), btnPosicaoOito.getText().charAt(0), btnPosicaoNove.getText().charAt(0)};
		
		if(tabuleiro[0]!=' ' && tabuleiro[0]==tabuleiro[1] && tabuleiro[0]==tabuleiro[2])
			declareResult(player.getYourName());
		else if(tabuleiro[0]!=' ' && tabuleiro[0]==tabuleiro[3] && tabuleiro[0]==tabuleiro[6])
			declareResult(player.getYourName());
		else if(tabuleiro[0]!=' ' && tabuleiro[0]==tabuleiro[4] && tabuleiro[0]==tabuleiro[8])
			declareResult(player.getYourName());
		
		else if(tabuleiro[1]!=' ' && tabuleiro[1]==tabuleiro[4] && tabuleiro[1]==tabuleiro[7])
			declareResult(player.getYourName());
		
		else if(tabuleiro[2]!=' ' && tabuleiro[2]==tabuleiro[4] && tabuleiro[2]==tabuleiro[6])
			declareResult(player.getYourName());
		else if(tabuleiro[2]!=' ' && tabuleiro[2]==tabuleiro[5] && tabuleiro[2]==tabuleiro[8])
			declareResult(player.getYourName());
		
		else if(tabuleiro[3]!=' ' && tabuleiro[3]==tabuleiro[4] && tabuleiro[3]==tabuleiro[5])
			declareResult(player.getYourName());
		else if(tabuleiro[6]!=' ' && tabuleiro[6]==tabuleiro[7] && tabuleiro[6]==tabuleiro[8])
			declareResult(player.getYourName());
		
		else if(tabuleiro[0]!=' ' && tabuleiro[1]!=' ' && tabuleiro[2]!=' ' && tabuleiro[3]!=' ' && tabuleiro[4]!=' ' && tabuleiro[5]!=' ' && tabuleiro[6]!=' ' && tabuleiro[7]!=' ' && tabuleiro[8]!=' ')
			declareResult();
	}
	
	private static void declareResult(String playerWinner) {
		CommonFunctions.sendMessage(dataOut, "$WIN" + playerWinner);
		Interpretador.traduzir("$WIN" + playerWinner);
	}

	private static void declareResult() {
		CommonFunctions.sendMessage(dataOut, "$TIED");
		Interpretador.traduzir("$TIED");
	}
	
	public static void showMessageWithResult(String winner) {
		pararContagem=true;
		newGame();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(frame, "O jogador " + winner + " venceu!", "Resultado do jogo", 1);
				if(lblYourName.getText().equals(winner)) {
					int contYouWin = Integer.parseInt(lblContYouWin.getText()) + 1;
					int contHeLose = Integer.parseInt(lblContHeLose.getText()) + 1;
					lblContYouWin.setText("" + contYouWin);
					lblContHeLose.setText("" + contHeLose);
				} else {
					int contYouLose = Integer.parseInt(lblContYouLose.getText()) + 1;
					int contHeWin = Integer.parseInt(lblContHeWin.getText()) + 1;
					lblContYouLose.setText("" + contYouLose);
					lblContHeWin.setText("" + contHeWin);
				}
			}
		}).start();
	}
	
	public static void showMessageWithResult() {
		pararContagem=true;
		newGame();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(frame, "O jogo empatou!", "Resultado do jogo", 1);
			}
		}).start();
	}
	
	public static void newGame() {
		btnPosicaoUm.setText(" ");
		btnPosicaoDois.setText(" ");
		btnPosicaoTres.setText(" ");
		btnPosicaoQuatro.setText(" ");
		btnPosicaoCinco.setText(" ");
		btnPosicaoSeis.setText(" ");
		btnPosicaoSete.setText(" ");
		btnPosicaoOito.setText(" ");
		btnPosicaoNove.setText(" ");
	}
}
