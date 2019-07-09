package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

import view.Game;

public class CommonFunctions {

	public static String receiveMessage(DataInputStream dataIn) {
		String mensagem = null;
		
		if(dataIn!=null) {
			try {
				mensagem = dataIn.readUTF();
			} catch(Exception ex) {
				System.err.println("Não foi possível receber a mensagem.\n" + ex.getMessage());
			}
		} else
			System.err.println("Data input Stream não foi inicializado.");
		
		return mensagem;
	}
	
	public static void sendMessage(DataOutputStream dataOut, String message) {
		if(dataOut!=null) {
			try {
				dataOut.writeUTF(message);
				Game.playable = true;
			} catch(Exception ex) {
				System.err.println(CommonFunctions.getTimeNow() + " Não foi possível enviar mensagem.\n" + ex.getMessage());
			}
		} else
			System.err.println("Data Output Stream não foi inicializado corretamente.");
	}
	
	public static String getTimeNow() {
		return "[" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND) + "]";
	}
	
}
