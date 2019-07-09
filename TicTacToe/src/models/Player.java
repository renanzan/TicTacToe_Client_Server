package models;

import java.util.Random;

public class Player {
	
	private String yourName;
	private String nameOfOpponent;
	private String colorName;
	private char charPlayer;
	
	public Player(String nome, char charPlayer) {
		this.yourName = nome;
		this.charPlayer = charPlayer;
		
		initializeRandomColor();
	}
	
	private void initializeRandomColor() {
		Random rand = new Random();
		int num = rand.nextInt(5);
		
		switch(num) {
			case 0: colorName="red"; break;
			case 1: colorName="green"; break;
			case 2: colorName="yellow"; break;
			case 3: colorName="blue"; break;
			case 4: colorName="orange"; break;
		}
	}

	public String getCodeNameColorful() {
		return "<b color=\"" + colorName + "\">" + yourName + "</b>";
	}
	
	// ENCAPSULAMENTO
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	
	public String getNameOfOpponent() {
		return nameOfOpponent;
	}
	public void setNameOfOpponent(String nameOfOpponent) {
		this.nameOfOpponent = nameOfOpponent;
	}
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
	public char getCharPlayer() {
		return charPlayer;
	}
	public void setCharPlayer(char charPlayer) {
		this.charPlayer = charPlayer;
	}
}
