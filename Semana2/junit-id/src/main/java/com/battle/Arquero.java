package com.battle;

public class Arquero implements Personaje {
	
	private Arma arma;
	//preguntar
	public Arquero(Arma arma) {
		this.arma=arma;
	}
	
	@Override
	public void atacar() {
		System.out.print("El arquero lanza flechas con ");
		arma.usar();
	}
	
	@Override
	public void defender() {
		System.out.println("El arquero esquiva rápidamente");
	}
}
