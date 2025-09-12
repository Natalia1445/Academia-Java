package com.battle;

public class BatallaArena {
	
	private Personaje jugador1;
	private Personaje jugador2;

	public BatallaArena(Personaje jugador1, Personaje jugador2){
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	public void pelear() {
		jugador1.atacar();
		jugador2.defender();
		jugador2.atacar();
		jugador1.atacar();
	}
}
