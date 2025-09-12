package com.battle;

public class Hechicero implements Personaje{
	
	private Arma arma;
	
	public Hechicero(Arma arma) {
		this.arma=arma;
	}
	
	@Override
	public void atacar() {
		System.out.print("El hechicero lanza un hechizo usando ");
		arma.usar();
		
	}
	
	@Override
	public void defender() {
		System.out.println("El hechicero se defiende con un hechizo de protección");
	}

}
