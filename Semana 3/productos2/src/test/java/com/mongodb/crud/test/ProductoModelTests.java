package com.mongodb.crud.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mongodb.crud.productos.model.Producto;

public class ProductoModelTests {
	
	Producto producto; 
	@BeforeEach
	void setupBeforeEach() {
		producto = new Producto("Plancha", "Plancha para alaciado y chinos", 300.00,9);
		System.out.println("Esto se ejectua antes de la ejecución de cada método de prueba");
	}

	@AfterEach
	void tearDownAfterEach() {
		System.out.println("Se finaliza esta prueba @AfterEach\n");
	}
	
	
	@Test
	@DisplayName("Valor nulo y no nulo")
	void TestNoNulo() {
		Producto producto1 = null;
		
		assertNull(producto1, "objeto debería ser null");
		assertNotNull(producto, "este objeto no debería ser null");
	   
		
		
	}
	
	@Test
	@DisplayName("Se crea producto con información verídica")
	void ConstructorCorrecto() {
		 assertNotNull(producto.getNombreProducto(), "nombre no debería ser null");
		 assertEquals("Plancha", producto.getNombreProducto());
		 assertEquals("Plancha para alaciado y chinos",producto.getDescripcion());
		 assertEquals(300.00, producto.getPrecio());
		 assertEquals(9, producto.getCantidad());  
		
	}
	
	
	@Test
	@DisplayName("Se prueba el atributo de la FechaRegistro")
    void FechaRegistro() throws InterruptedException {
	LocalDateTime tiempoAntes = LocalDateTime.now();
    
    Producto nuevoProducto = new Producto("Mouse", "Mouse inalámbrico", 25.50, 50);
    Thread.sleep(100);
    
    LocalDateTime tiempoDespues = LocalDateTime.now();
   
    
    assertTrue(nuevoProducto.getFechaRegistro().isAfter(tiempoAntes.minusSeconds(1)) &
            nuevoProducto.getFechaRegistro().isBefore(tiempoDespues.plusSeconds(1)),
            "La fecha debería ser aproximadamente la actual");
    
    

}
    @Test
    @DisplayName("Se prueban setters y getters, y en algunos caso se prueba con valores límite como cero y nulos")
    void testValoresLimite() {
        
    	producto.setId("1234");
    	assertEquals("1234",producto.getId(),"Debería ser 1234");
    	
    	producto.setFechaRegistro(null);
    	assertNull(producto.getFechaRegistro());
    	
        producto.setPrecio(0.0);
        assertEquals(0.0, producto.getPrecio(), "Debería aceptar precio cero");
        
  
        producto.setCantidad(0);
        assertEquals(0, producto.getCantidad(), "Debería aceptar cantidad cero");
        
        
        producto.setNombreProducto("");
        assertEquals("", producto.getNombreProducto(), "Debería aceptar string vacío");
        
        producto.setDescripcion("");
        assertEquals("", producto.getDescripcion(), "Debería aceptar descripción vacía");
    }
    
    @Test
    @DisplayName("Se prueba el toString definido en el Modelo del Producto")
    void testToString() {
        Producto producto2 = new Producto("Laptop", "Laptop gaming", 1500.00, 10);
        producto2.setId("PROD-123");
        
        String esperado = "Producto [id=PROD-123, nombreProducto=Laptop, descripcion=Laptop gaming, precio=1500.0, cantidad=10, fechaRegistro=" + producto2.getFechaRegistro() + "]";
        assertEquals(esperado, producto2.toString(), "El formato toString no es correcto");
    }
}


