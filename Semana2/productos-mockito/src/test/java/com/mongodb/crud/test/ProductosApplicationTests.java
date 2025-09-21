package com.mongodb.crud.test;



import com.mongodb.crud.productos.ProductosApplication;
import com.mongodb.crud.productos.model.Producto;
import com.mongodb.crud.productos.repository.ProductoRepository;
import com.mongodb.crud.productos.service.ProductoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductosApplication.class)
public class ProductosApplicationTests {

   //Mock para la dependencia que quiero copiar
    @Mock
    private ProductoRepository productoRepository;

    // @InjectMocks para la clase que deseo probar
    @InjectMocks
    private ProductoServiceImpl productoService;
    
    // Se hace la instancia de producto
    private Producto producto;

    @BeforeEach
    public void beforeEach() {
        producto = new Producto();
        producto.setNombreProducto("Libro");
        producto.setDescripcion("Farenheit 591");
        producto.setPrecio(410.00);
        producto.setCantidad(10);
    }

    @DisplayName("When & Verify - Guardar Producto")
    @Test
    public void assertEqualsTestGuardarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto productoGuardado = productoService.save(producto);

        assertEquals("Libro", productoGuardado.getNombreProducto());
        assertEquals("Farenheit 591", productoGuardado.getDescripcion());
        assertEquals(410.00, productoGuardado.getPrecio());
        assertEquals(10, productoGuardado.getCantidad());


        verify(productoRepository).save(producto);
        verify(productoRepository, times(1)).save(producto);
    }
    @DisplayName("When & Verify Guardar Producto - Fecha Registro Null")
    @Test
    public void SiFechaEsNullAsignaFechaActual() {
        // Producto sin fecha
        Producto productoSinFecha = new Producto("Libro", "Descripción", 100.0, 5);
        productoSinFecha.setFechaRegistro(null); // Nos aseguramos que la fecha de registro sea nula
        
        // Fecha antes de guardar
        LocalDateTime antesDeGuardar = LocalDateTime.now();
        
        // Mock del repository
        when(productoRepository.save(any(Producto.class)))
            .thenReturn(productoSinFecha);
        
 
        Producto resultado = productoService.save(productoSinFecha);
        
        LocalDateTime despuesDeCorrer = LocalDateTime.now();
        
    //Asserts
        assertNotNull(resultado.getFechaRegistro(), "La fecha de registro no debería ser null");
        assertTrue(resultado.getFechaRegistro().isAfter(antesDeGuardar.minusSeconds(1)), 
                   "La fecha debería ser posterior al momento antes de la ejecución");
        assertTrue(resultado.getFechaRegistro().isBefore(despuesDeCorrer.plusSeconds(1)),
                   "La fecha debería ser anterior al momento después de la ejecución");
        
        //verificamos que se llamo una vez al repositorio
        verify(productoRepository, times(1)).save(productoSinFecha);
    }
    
    @DisplayName("When & Verify un Producto")
    @Test
    public void assertEqualsTestVerProducto() {
        String id = "123";
        producto.setId(id); 

        // Se usa el mismo objeto del Mock
        when(productoRepository.findById("123"))
            .thenReturn(Optional.of(producto));  

        assertEquals(
            Optional.of(producto),      // Comparamos con el mismo producto
            productoService.findById("123"));

        verify(productoRepository, times(1)).findById("123");
    }
    

    @DisplayName("When & Verify Ver Productos")
    @Test
    public void assertEqualsTestVerProductos() {
    	Producto producto2 = new Producto();
    	producto2.setNombreProducto("Cuardeno");
    	producto2.setDescripcion("Cuadernos rojos, azules y amarillos");
    	producto2.setPrecio(50.30);
    	producto2.setCantidad(5);
    	
    	List<Producto> productos = Arrays.asList(producto, producto2);
    	when(productoRepository.findAll())
    	.thenReturn(productos);
    	
    	List<Producto> foundedProductos = productoService.findAll();
    	
    	assertEquals(2, foundedProductos.size());
    	assertEquals("Libro", foundedProductos.get(0).getNombreProducto());
    	assertEquals(50.30, foundedProductos.get(1).getPrecio());
    	
    	verify(productoRepository, times(1)).findAll();
    }
    
    @DisplayName("When & Verify Actualizar Producto")
    @Test
    public void assertEqualsActualizarProducto() {
    	 String id = "123";
         producto.setId(id);  


		Producto productoActualizado = new Producto("Libro", "Farenheit 451", 400.00,10);
		productoActualizado.setId(id);

		Producto productoEsperado = new Producto("Libro", "Farenheit 451", 400.00, 10);
		productoEsperado.setId(id);

		when(productoRepository.findById(id))
				.thenReturn(Optional.of(producto));

		when(productoRepository.save(producto))
				.thenReturn(productoEsperado);

		Producto resultado = productoService.update(id, productoActualizado);

		assertEquals(productoEsperado, resultado);

		verify(productoRepository, times(1)).findById(id);
		verify(productoRepository, times(1)).save(producto);
    }
    @DisplayName("When & Verify Caso excepción de actualizar producto")
    @Test 
    public void updateProductoNoExistente() {
    	String idInexistente= "00";
    	Producto productoActualizado = new Producto("Libro Clásico", "1984", 400.00,10);
    	when(productoRepository.findById(idInexistente))
    	.thenReturn(Optional.empty());
    	
    	RuntimeException exception = assertThrows(RuntimeException.class, 
    			()-> {
    		        productoService.update(idInexistente, productoActualizado);});
    	assertEquals("Producto no encontrado con id: "+ idInexistente,exception.getMessage() );
    	verify(productoRepository, times(1)).findById(idInexistente);
    	verifyNoMoreInteractions(productoRepository);
    }
     

    @DisplayName("When & Verify Eliminar Producto")
    @Test
    public void assertNotThrowEliminarProducto() {
        String id = "123";
        producto.setId(id);  
		assertDoesNotThrow(() -> productoService.deleteById(id));

		verify(productoRepository, times(1)).deleteById(id);
    }
}