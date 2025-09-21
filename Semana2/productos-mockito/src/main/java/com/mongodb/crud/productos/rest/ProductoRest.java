package com.mongodb.crud.productos.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.crud.productos.model.Producto;
import com.mongodb.crud.productos.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRest {
	
	@Autowired
	private ProductoService productoService;
	
	
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto savedProducto = productoService.save(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProducto);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}//PostMapping
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos() {
		try {
			List<Producto> productos = productoService.findAll();
			return ResponseEntity.ok(productos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}//GetProductos
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
		try {
			Optional<Producto> producto = productoService.findById(id); 
				return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}//getProducto
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
		try {
			Producto updatedProducto = productoService.update(id, producto);
			return ResponseEntity.ok(updatedProducto);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}//updateProducto
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
		try {
			if (productoService.findById(id).isPresent()) {
				productoService.deleteById(id);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}//deleteProduct
	
}
