package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import connection.Client;
import connection.Server;
import models.Player;

public class Home extends JFrame {

	private static JFrame frame;
	private static JPanel panel_3;
	private JTextField txtEnderecoServidor;
	private JTextField txtPorta;
	private JTextField txtApelido;
	
	private static boolean isClient = true;
	
	public Home() {
		frame = this;
		
		setUndecorated(true);
		setBounds(0, 0, 500, 750);
		getContentPane().setBackground(new Color(20, 189, 172));
		((JComponent) getContentPane()).setBorder(new LineBorder(new Color(24, 131, 215)));
		getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 500, 800);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(464, 10, 25, 25);
		layeredPane.add(panel_2);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(0, 0, 25, 25);
		panel_2.add(lblX);
		lblX.setFont(new Font("Open Sans Light", Font.PLAIN, 20));
		lblX.setForeground(Color.WHITE);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_2.setBorder(new LineBorder(Color.WHITE));
		panel_2.setBackground(new Color(20, 189, 172));
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(Color.WHITE);
				lblX.setForeground(new Color(20, 189, 172));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
				panel_2.setBackground(new Color(20, 189, 172));
			}
		});
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(1, 80, 498, 150);
		layeredPane.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TIC TAC TOE");
		lblNewLabel.setForeground(new Color(20, 189, 172));
		lblNewLabel.setFont(new Font("Open Sans Extrabold", Font.BOLD, 75));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 500, 150);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1, 300, 498, 400);
		layeredPane.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		JPanel switchMenuClienteServer = new JPanel();
		switchMenuClienteServer.setBackground(Color.WHITE);
		switchMenuClienteServer.setBounds(20, 20, 458, 50);
		panel_1.add(switchMenuClienteServer);
		switchMenuClienteServer.setLayout(null);
		
		JPanel btnCliente = new JPanel();
		btnCliente.setBorder(new LineBorder(new Color(20, 189, 172)));
		btnCliente.setBackground(new Color(20, 189, 172));
		btnCliente.setBounds(0, 0, 229, 50);
		switchMenuClienteServer.add(btnCliente);
		btnCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setBounds(0, 0, 229, 50);
		btnCliente.add(lblCliente);
		lblCliente.setFont(new Font("Open Sans Extrabold", Font.BOLD, 15));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel btnServidor = new JPanel();
		btnServidor.setBorder(new LineBorder(new Color(20, 189, 172)));
		btnServidor.setLayout(null);
		btnServidor.setBackground(Color.WHITE);
		btnServidor.setBounds(229, 0, 229, 50);
		switchMenuClienteServer.add(btnServidor);
		
		JLabel lblServidor = new JLabel("SERVIDOR");
		lblServidor.setForeground(new Color(20, 189, 172));
		lblServidor.setBounds(0, 0, 229, 50);
		btnServidor.add(lblServidor);
		lblServidor.setFont(new Font("Open Sans Extrabold", Font.BOLD, 15));
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		
		switchMenuClienteServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				isClient=!isClient;
				
				if(isClient) {
					btnCliente.setBackground(new Color(20, 189, 172));
					lblCliente.setForeground(Color.WHITE);
					btnServidor.setBackground(Color.WHITE);
					lblServidor.setForeground(new Color(20, 189, 172));
				} else {
					btnServidor.setBackground(new Color(20, 189, 172));
					lblServidor.setForeground(Color.WHITE);
					btnCliente.setBackground(Color.WHITE);
					lblCliente.setForeground(new Color(20, 189, 172));
				}
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(20, 70, 458, 310);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSeuApelido = new JLabel("Seu apelido");
		lblSeuApelido.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblSeuApelido.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuApelido.setBounds(10, 20, 438, 30);
		panel_4.add(lblSeuApelido);
		
		JLabel lblEndereoDoServidor = new JLabel("Endere\u00E7o do servidor");
		lblEndereoDoServidor.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblEndereoDoServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereoDoServidor.setBounds(10, 115, 281, 30);
		panel_4.add(lblEndereoDoServidor);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(10, 145, 281, 35);
		panel_4.add(panel_6);
		panel_6.setLayout(null);
		
		txtEnderecoServidor = new JTextField();
		txtEnderecoServidor.setText("localhost");
		txtEnderecoServidor.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnderecoServidor.setFont(new Font("Open Sans Light", Font.PLAIN, 17));
		txtEnderecoServidor.setBounds(4, 1, 272, 33);
		panel_6.add(txtEnderecoServidor);
		txtEnderecoServidor.setColumns(10);
		txtEnderecoServidor.setBorder(null);
		
		JLabel lblPorta = new JLabel("Porta aberta");
		lblPorta.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblPorta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorta.setBounds(305, 115, 142, 30);
		panel_4.add(lblPorta);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(20, 189, 172)));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 255, 438, 45);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tentar conex\u00E3o");
		lblNewLabel_1.setForeground(new Color(20, 189, 172));
		lblNewLabel_1.setFont(new Font("Open Sans Extrabold", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 438, 45);
		panel_5.add(lblNewLabel_1);
		
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_5.setBackground(new Color(20, 189, 172));
				lblNewLabel_1.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_5.setBackground(Color.WHITE);
				lblNewLabel_1.setForeground(new Color(20, 189, 172));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(isClient) {
					Player player = new Player(txtApelido.getText(), 'X');
					new Client(txtEnderecoServidor.getText(), Integer.parseInt(txtPorta.getText()), player, frame);
				} else {
					Player player = new Player(txtApelido.getText(), 'O');
					new Server(Integer.parseInt(txtPorta.getText()), player, frame);
					layeredPane.setLayer(panel_3, 1);
				}
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(303, 145, 145, 35);
		panel_4.add(panel_7);
		
		txtPorta = new JTextField();
		txtPorta.setText("9999");
		txtPorta.setHorizontalAlignment(SwingConstants.CENTER);
		txtPorta.setFont(new Font("Open Sans Light", Font.PLAIN, 17));
		txtPorta.setColumns(10);
		txtPorta.setBorder(null);
		txtPorta.setBounds(4, 1, 137, 33);
		panel_7.add(txtPorta);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(10, 50, 438, 35);
		panel_4.add(panel_8);
		
		txtApelido = new JTextField();
		txtApelido.setHorizontalAlignment(SwingConstants.CENTER);
		txtApelido.setFont(new Font("Open Sans Light", Font.PLAIN, 17));
		txtApelido.setColumns(10);
		txtApelido.setBorder(null);
		txtApelido.setBounds(4, 1, 430, 33);
		panel_8.add(txtApelido);
		
		panel_3 = new JPanel();
		layeredPane.setLayer(panel_3, -1);
		panel_3.setBounds(20, 320, 459, 360);
		layeredPane.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(new Color(243, 243, 243));
		panel_3.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(Color.WHITE));
		panel_9.setBackground(Color.BLACK);
		panel_9.setBounds(40, 95, 378, 170);
		panel_3.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tentando conex\u00E3o, aguarde...");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Open Sans Light", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(0, 0, 378, 70);
		panel_9.add(lblNewLabel_2);
		
		JLabel lblIssoPodeLevar = new JLabel("Isso pode levar algum tempo");
		lblIssoPodeLevar.setVerticalAlignment(SwingConstants.TOP);
		lblIssoPodeLevar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIssoPodeLevar.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblIssoPodeLevar.setForeground(Color.WHITE);
		lblIssoPodeLevar.setBounds(0, 83, 378, 20);
		panel_9.add(lblIssoPodeLevar);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(118, 116, 140, 35);
		panel_9.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setForeground(Color.BLACK);
		lblCancelar.setFont(new Font("Open Sans Light", Font.PLAIN, 13));
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setBounds(0, 0, 140, 35);
		panel_10.add(lblCancelar);
		
		panel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelar.setForeground(Color.WHITE);
				panel_10.setBackground(Color.BLACK);
				panel_10.setBorder(new LineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancelar.setForeground(Color.BLACK);
				panel_10.setBackground(Color.WHITE);
				panel_10.setBorder(new LineBorder(Color.BLACK));
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
