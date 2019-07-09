package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Player;
import view.Game;

public class Server {

	private static Player player;
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	private static DataInputStream dataIn;
	private static DataOutputStream dataOut;
	
	public Server(int PORT, Player player, JFrame home) {
		this.player = player;
		
		serverSocket = initializeServerSocket(PORT);
		THREAD_WaitClient(serverSocket, clientSocket, dataIn, dataOut, home);
	}
	
	private ServerSocket initializeServerSocket(int PORT) {
		ServerSocket serverSocket = null;
		
		System.out.println(CommonFunctions.getTimeNow() + " Iniciando servidor.");
		try {
			serverSocket = new ServerSocket(PORT);
		} catch(Exception ex) {
			System.err.println("N�o foi poss�vel inicializar o ServerSocket.\n" + ex.getMessage());
		}
		
		return serverSocket;
	}
	
	private void receiveClient(ServerSocket serverSocket, Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut, JFrame home) {
		try {
			clientSocket = serverSocket.accept();
			System.out.println(CommonFunctions.getTimeNow() + " Conex�o do endere�o " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
			home.dispose();
		} catch(Exception ex) {
			System.err.println("N�o foi poss�vel receber cliente.\n" + ex.getMessage());
		}
		
		initializeCommunication(clientSocket, dataIn, dataOut);
	}
	
	private void initializeCommunication(Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut) {
		try {
			dataIn = new DataInputStream(clientSocket.getInputStream());
			dataOut = new DataOutputStream(clientSocket.getOutputStream());
			
			receivingMessagesFromTheClient(dataIn, dataOut);
		} catch(Exception ex) {
			System.err.println("N�o foi poss�vel inicializar comunica��o com o cliente.\n" + ex.getMessage());
		}
	}
	
	private void THREAD_WaitClient(ServerSocket serverSocket, Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut, JFrame home) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				receiveClient(serverSocket, clientSocket, dataIn, dataOut, home);
			}
		}).start();
	}
	
	private static void receivingMessagesFromTheClient(DataInputStream dataIn, DataOutputStream dataOut) {
		CommonFunctions.sendMessage(dataOut, CommonFunctions.getTimeNow() + " Conectado com o servidor.");
		player.setNameOfOpponent(CommonFunctions.receiveMessage(dataIn));
		CommonFunctions.sendMessage(dataOut, player.getYourName());
		
		System.out.println(CommonFunctions.getTimeNow() + " As informa��es necess�rias para iniciar o jogo foram coletadas.");
		Game home = new Game(player, dataOut);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				boolean clientOn = true;
				
				try {
					while(clientOn) {
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
						dataIn.close();
						dataOut.close();
						System.out.println(CommonFunctions.getTimeNow() + " Sua conex�o foi encerrada.");
					} catch (IOException ex) {
						System.err.println("N�o foi poss�vel encerrar sua conex�o.\n" + ex.getMessage());
					}
					clientOn=false;
				}
			}
		}).start();
	}
}
