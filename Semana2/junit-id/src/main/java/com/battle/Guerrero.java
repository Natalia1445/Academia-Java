package com.battle;

public class Guerrero implements Personaje{

		private Arma arma;//se inyecta la dependencia
		
		public Guerrero(Arma arma) {
			this.arma = arma;
		}
		
		@Override
		public void atacar() {
			System.out.print("El guerrero ataca con ");
				arma.usar();
			;
		}//atacar
		
		@Override
		public void defender() {
			System.out.println("El guerrero se defiende con un escudo");
		}
		
	
}
