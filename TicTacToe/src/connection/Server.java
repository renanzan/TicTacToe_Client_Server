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
			System.err.println("Não foi possível inicializar o ServerSocket.\n" + ex.getMessage());
		}
		
		return serverSocket;
	}
	
	private void receiveClient(ServerSocket serverSocket, Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut, JFrame home) {
		try {
			clientSocket = serverSocket.accept();
			System.out.println(CommonFunctions.getTimeNow() + " Conexão do endereço " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
			home.dispose();
		} catch(Exception ex) {
			System.err.println("Não foi possível receber cliente.\n" + ex.getMessage());
		}
		
		initializeCommunication(clientSocket, dataIn, dataOut);
	}
	
	private void initializeCommunication(Socket clientSocket, DataInputStream dataIn, DataOutputStream dataOut) {
		try {
			dataIn = new DataInputStream(clientSocket.getInputStream());
			dataOut = new DataOutputStream(clientSocket.getOutputStream());
			
			receivingMessagesFromTheClient(dataIn, dataOut);
		} catch(Exception ex) {
			System.err.println("Não foi possível inicializar comunicação com o cliente.\n" + ex.getMessage());
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
		
		System.out.println(CommonFunctions.getTimeNow() + " As informações necessárias para iniciar o jogo foram coletadas.");
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
					JOptionPane.showMessageDialog(home, "A conexão com o Cliente foi perdida.", "Erro de conexão", 0);
				} finally {
					try {
						home.close();
						dataIn.close();
						dataOut.close();
						System.out.println(CommonFunctions.getTimeNow() + " Sua conexão foi encerrada.");
					} catch (IOException ex) {
						System.err.println("Não foi possível encerrar sua conexão.\n" + ex.getMessage());
					}
					clientOn=false;
				}
			}
		}).start();
	}
}
