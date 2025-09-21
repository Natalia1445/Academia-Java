package com.battle;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DemoUtilsTest {
	
	private Guerrero guerrero;
	private Espada espada;
	private Hechicero hechicero;
	private BastonMagico bastonMagico;
	private Arco arco;
	private Arquero arquero;
	private ByteArrayOutputStream outputStream;
	private PrintStream  originalOut;

	
	@BeforeEach
	void setUp()
	//con outputStream se obtiene la captura de salida de consola
	{
		outputStream = new ByteArrayOutputStream();
		//originalOut= System.out;
		System.setOut(new PrintStream(outputStream));
	}
	
	@AfterEach
	
	 void tearDown() {
        System.setOut(originalOut);
    }
	
	@DisplayName("Ataque Guerrero")
	@Test
	void testAtaqueGuerrero() {
		//set up 
		espada = new Espada();
		guerrero = new Guerrero(espada);
		
		
		// execute
		guerrero.atacar();
		String actual = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual.contains("El guerrero ataca con una espada filosa")); 
	}//testAtaqueGuerrero 
	
	@Test
	@DisplayName("Ataque Hechicero")
	void testAtaqueHechicero() {
		//set up 
		bastonMagico = new BastonMagico();
		hechicero = new Hechicero(bastonMagico);
		
		// execute
		hechicero.atacar();
		String actual1 = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual1.contains("El hechicero lanza un hechizo usando un poderoso bastón mágico")); 	
	}//testAtaqueHechicero 
	
	@Test
	@DisplayName("Ataque Arquero")
	void testAtaqueArquero() {
		//set up 
		arco = new Arco();
		arquero = new Arquero(arco);
		
		// execute
		arquero.atacar();
		String actual = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual.contains("El arquero lanza flechas con un arco")); 
	}//testAtaqueArquero 
	
	@Test
	@DisplayName("Defensa Guerrero")
	void testDefensaGuerrero() {
		//set up 
		espada = new Espada();
		guerrero = new Guerrero(espada);
		
		// execute
		guerrero.defender();
		String actual = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual.contains("El guerrero se defiende con un escudo")); 
	}//testDefensaGuerrero 
	
	
	@Test
	@DisplayName("Defensa Hechicero")
	void testDefensaHechicero() {
		//set up 
		bastonMagico = new BastonMagico();
		hechicero = new Hechicero(bastonMagico);
		
		// execute
		hechicero.defender();
		String actual = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual.contains("El hechicero se defiende con un hechizo de protección")); 	
	}//testDefensaHechicero 
	
	@Test
	@DisplayName("Defensa Arquero")
	void testDefensaArquero() {
		//set up 
		arco = new Arco();
		arquero = new Arquero(arco);
		
		// execute
		arquero.defender();
		String actual = outputStream.toString();
		//assert 
		Assertions.assertTrue(actual.contains("El arquero esquiva rápidamente")); 	
	}//testAtaqueGuerrero 
	
	@Test
	@DisplayName("Batalla Completa")
	void testBatallaCompleta() {
		Personaje guerrero = new Guerrero(new Espada());
		Personaje arquero = new Arquero(new Arco());
		
		BatallaArena arena = new BatallaArena(guerrero, arquero);
		arena.pelear();
		
		String actual= outputStream.toString();
		
	   assertAll(
			   () -> Assertions.assertNotNull(guerrero),
			   () -> Assertions.assertNotNull(arquero),
	            () -> Assertions.assertTrue(actual.contains("El arquero esquiva rápidamente")),
	            () -> Assertions.assertTrue(actual.contains("El arquero lanza flechas con un arco")),
	            () -> Assertions.assertTrue(actual.contains("El guerrero se defiende con un escudo")),
	            () -> Assertions.assertTrue(actual.contains("El guerrero ataca con una espada filosa"))
	        );
	}
	   @Test
	   @DisplayName("Probar el método main")
	    void main_imprime_resultado_de_la_pelea() {
	        Principal.main(new String[]{});

	        // entonces
	        String salida = outputStream.toString();
	        // Verifica partes clave para hacerlo menos frágil ante cambios menores
	        assertTrue(salida.contains("El guerrero ataca"), "Debe imprimir el ataque del guerrero");
	        assertTrue(salida.contains("El hechicero se defiende"), "Debe imprimir la defensa del oponente");
	}
	
	
}
