package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Window_Template extends JFrame {
	
	private Point initialClick;
	
	public Window_Template(int width, int height, String title, String directoryIcon_TopPanel) {
		if(width>=500 && height>=500) {
			setSize(width+2, height+33);
			setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			intialize_topBar(this, width, title, directoryIcon_TopPanel);
			
			setUndecorated(true);
			setResizable(false);
		    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    ((JComponent) getContentPane()).setBorder(new LineBorder(new Color(24, 131, 215)));
			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "O tamanho mínimo de largura e altura para esse modelo de janela é de 500.", "Erro no tamanho da janela", 0);
		}
	}
	
	private void intialize_topBar(JFrame frame, int width, String title, String directoryIcon_TopPanel) {
		JPanel topBar = new JPanel();
		topBar.setBounds(1, 1, width-2, 31);
		topBar.setBackground(Color.WHITE);
		topBar.setBorder(new MatteBorder(0, 0, 1, 0, new Color(218, 219, 220)));
		topBar.setLayout(null);
		topBar.addMouseMotionListener(new MouseAdapter() {
			@Override
	        public void mouseDragged(MouseEvent e) {

	            // get location of Window
	            int thisX = frame.getLocation().x;
	            int thisY = frame.getLocation().y;

	            // Determine how much the mouse moved since the initial click
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            // Move window to this position
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            frame.setLocation(X, Y);
	        }
		});
		topBar.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
		});
		getContentPane().add(topBar);
		
		initialize_TopPanel(topBar, width, title, directoryIcon_TopPanel);
	}

	private void initialize_TopPanel(JPanel topBar, int width, String title, String directoryIcon_TopPanel) {
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setBounds(0, 0, width-2, 30);
		topPanel.setLayout(null);
		topBar.add(topPanel);
		
		// ICONE NA PRIMEIRA BARRA SUPERIOR
		JLabel icon_topPanel = new JLabel("");
		if(directoryIcon_TopPanel!=null) {
			try {
				icon_topPanel.setIcon(new ImageIcon(Window_Template.class.getResource(directoryIcon_TopPanel)));
			} catch(Exception ex) {
				System.err.println("O diretório do icone está incorreto!");
			}
		}
		icon_topPanel.setHorizontalAlignment(SwingConstants.CENTER);
		icon_topPanel.setBounds(0, 0, 30, 30);
		topPanel.add(icon_topPanel);
		
		// TÍTULO NA PRIMEIRA BARRA SUPERIOR
		JLabel lbl_title = new JLabel(title);
		lbl_title.setBounds(40, 0, width-155, 30);
		lbl_title.setFont(new Font("Arial", Font.PLAIN, 13));
		topPanel.add(lbl_title);
		
		JLabel lbl_close = new JLabel(new ImageIcon(Window_Template.class.getResource("/view/icons/icon_close_12x12_black.png")));
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setBounds(0, 0, 50, 30);
		
		JPanel btn_close = new JPanel();
		btn_close.setBackground(Color.WHITE);
		btn_close.setBounds(width-55, 0, 50, 30);
		btn_close.setLayout(null);
		btn_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_close.setBackground(new Color(232, 17, 32));
				lbl_close.setIcon(new ImageIcon(Window_Template.class.getResource("/view/icons/icon_close_12x12_white.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_close.setBackground(Color.WHITE);
				lbl_close.setIcon(new ImageIcon(Window_Template.class.getResource("/view/icons/icon_close_12x12_black.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				action_closeWindow();
			}
		});
		topPanel.add(btn_close);
		btn_close.add(lbl_close);
		
		JLabel lbl_iconified = new JLabel(new ImageIcon(Window_Template.class.getResource("/view/icons/icon_iconified_12x12.png")));
		lbl_iconified.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_iconified.setBounds(0, 0, 50, 30);
		
		JPanel btn_iconified = new JPanel();
		btn_iconified.setBackground(Color.WHITE);
		btn_iconified.setBounds(width-105, 0, 50, 30);
		btn_iconified.setLayout(null);
		btn_iconified.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_iconified.setBackground(new Color(229, 229, 229));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_iconified.setBackground(Color.WHITE);
			}
			public void mousePressed(MouseEvent e) {
				setState(Frame.ICONIFIED);
			};
		});
		topPanel.add(btn_iconified);
		btn_iconified.add(lbl_iconified);
	}
	
	private void action_closeWindow() {
		if(JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja encerrar o programa?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0) {
			dispose();
			System.exit(0);
		}
	}

}
