package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Player;
import view.Game;

public class Client {
	
	private static Player player;
	
	private static Socket clientSocket;
	
	private static DataInputStream dataIn;
	private static DataOutputStream dataOut;
	
	public Client(String HOST, int PORT, Player player, JFrame home) {
		this.player = player;
		
		clientSocket = initializeClientSocket(HOST, PORT, home);
	}
	
	private static Socket initializeClientSocket(String HOST, int PORT, JFrame home) {
		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket(HOST, PORT);
			initializeComunication(clientSocket, dataIn, dataOut);
			home.dispose();
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel conectar com o servidor.", "Erro de conex�o", 0);
		}
		
		return clientSocket;
	}
	
	private static void initializeComunication(Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut) {
		try {
			dataIn = new DataInputStream(clientSocket.getInputStream());
			dataOut = new DataOutputStream(clientSocket.getOutputStream());

			receivingMessagesFromTheServer(dataIn, dataOut);
		} catch(Exception ex) {
			System.err.println("N�o foi poss�vel inicializar comunica��o com servidor.\n" + ex.getMessage());
		}
	}
	
	private static void receivingMessagesFromTheServer(DataInputStream dataIn, DataOutputStream dataOut) {
		System.out.println(CommonFunctions.receiveMessage(dataIn));
		CommonFunctions.sendMessage(dataOut, player.getYourName());
		player.setNameOfOpponent(CommonFunctions.receiveMessage(dataIn));
		
		System.out.println(CommonFunctions.getTimeNow() + " As informa��es necess�rias para iniciar o jogo foram coletadas.");
		Game home = new Game(player, dataOut);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				boolean serverOn=true;
				
				try {
					while(serverOn) {
						String mensagem = dataIn.readUTF();
						
						if(mensagem.startsWith("$POS")) {
							Game.playable=true;
							Game.pararContagem=false;
							Game.Thread_inicializaContagem();
						}
						
						Interpretador.traduzir(mensagem);
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(home, "A conex�o com o Cliente foi perdida.", "Erro de conex�o", 0);
				} finally {
					try {
						home.close();
						clientSocket.close();
						dataIn.close();
						dataOut.close();
						System.out.println(CommonFunctions.getTimeNow() + " Sua conex�o foi encerrada.");
					} catch (IOException ex) {
						System.err.println("N�o foi poss�vel encerrar sua conex�o.\n" + ex.getMessage());
					}
					serverOn=false;
				}
			}
		}).start();
	}
}
