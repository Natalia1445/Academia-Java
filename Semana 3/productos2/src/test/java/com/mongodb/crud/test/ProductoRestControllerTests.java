package com.mongodb.crud.test;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mongodb.crud.productos.model.Producto;
import com.mongodb.crud.productos.rest.ProductoRest;
import com.mongodb.crud.productos.service.ProductoService;


@ExtendWith(MockitoExtension.class)
public class ProductoRestControllerTests {
	
	@Mock
	private ProductoService productoService;
	
	@InjectMocks
	private ProductoRest productoRest;
	
	private Producto producto;
	
    @BeforeEach
    public void beforeEach() {
        producto = new Producto();
        producto.setId("123");
        producto.setNombreProducto("Libro");
        producto.setDescripcion("Farenheit 481");
        producto.setPrecio(410.00);
        producto.setCantidad(10);
    }
    
    @Test
    @DisplayName("When and verify -Crear Producto")
    public void CrearProducto() {
    	when(productoService.save(producto)).thenReturn(producto);
    	
    	ResponseEntity<Producto> response = productoRest.createProducto(producto);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	assertEquals("123", response.getBody().getId());
    
    	verify(productoService, times(1)).save(producto);
    }
    
    @Test
    @DisplayName("When and verify -Crear Producto Error")
    public void CrearProductoError() {
    	when(productoService.save(producto)).thenThrow(new RuntimeException("Error en base de datos"));
    	
		ResponseEntity<Producto> response = productoRest.createProducto(producto);
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    	assertNull( response.getBody());
    
    	verify(productoService, times(1)).save(producto);
    }
    
    @Test
    @DisplayName("When and verify - Obtener Productos")
    public void ObtenerProductos() {
    	Producto producto2 = new Producto();
        producto2.setId("456");
        producto2.setNombreProducto("Cuaderno");
        producto2.setDescripcion("Cuadernos rojos, azules y amarillos");
        producto2.setPrecio(50.30);
        producto2.setCantidad(5);
        
        List<Producto> productos = Arrays.asList(producto, producto2);
    	
    	when(productoService.findAll()).thenReturn(productos);
    	
    	ResponseEntity<List<Producto>> response = productoRest.getAllProductos();
    	
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    	assertEquals(2, response.getBody().size());
    	assertEquals("Cuaderno", response.getBody().get(1).getNombreProducto());
    	assertEquals(5, response.getBody().get(1).getCantidad());
    	
    	verify(productoService,times(1)).findAll();
    }
    
    @Test
    @DisplayName("When and Verify- ObtenerProductos Error")
    public void ObtenerProductosError() {
    	when(productoService.findAll()).thenThrow(new RuntimeException("Error al cargar los productos"));
    	
    	ResponseEntity<List<Producto>> response = productoRest.getAllProductos();
    	
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    	assertNull(response.getBody());
    	
    	verify(productoService,times(1)).findAll();
    }
    
    @DisplayName("When & Verify - Encontrar Producto por id")
    @Test
    public void VerProductoPorId() {
        when(productoService.findById("123")).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> response = productoRest.getProductoById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("123", response.getBody().getId());
        assertEquals("Libro", response.getBody().getNombreProducto());
        assertEquals(410.00, response.getBody().getPrecio());

        verify(productoService, times(1)).findById("123");
    }

    @DisplayName("When & Verify Encontrar Producto por id Error")
    @Test
    public void VerProductoError() {
        String idInexistente = "999";
        when(productoService.findById(idInexistente)).thenReturn(Optional.empty());

        ResponseEntity<Producto> response = productoRest.getProductoById(idInexistente);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(productoService, times(1)).findById(idInexistente);
    }
    
    @DisplayName("When & Verify Actualizar Producto")
    @Test
    public void assertEqualsTestActualizarProducto() {
        String id = "123";
        Producto productoActualizado = new Producto("Libro", "Farenheit 451", 400.00, 10);
        productoActualizado.setId(id);

        when(productoService.update(id, productoActualizado)).thenReturn(productoActualizado);

        ResponseEntity<Producto> response = productoRest.updateProducto(id, productoActualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        assertEquals("Farenheit 451", response.getBody().getDescripcion());
        assertEquals(400.00, response.getBody().getPrecio());

        verify(productoService, times(1)).update(id, productoActualizado);
    }


    
    @DisplayName("When & Verify Eliminar Producto Error no encontradoo")
    @Test
    public void EliminarProductoNoEncontrado() {
        String idInexistente = "999";
        when(productoService.findById(idInexistente)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = productoRest.deleteProducto(idInexistente);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(productoService, times(1)).findById(idInexistente);
       
    }

	
}