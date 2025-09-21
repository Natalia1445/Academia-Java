package com.battle;

public class Principal {
	public static void main(String[] args) {
		
		Personaje guerrero = new Guerrero(new Espada());
		Personaje hechicero = new Hechicero(new BastonMagico());
		
		BatallaArena arena = new BatallaArena(guerrero, hechicero);
		arena.pelear();
		
	}

}
