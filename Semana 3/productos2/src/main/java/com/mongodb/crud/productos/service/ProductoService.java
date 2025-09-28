package com.mongodb.crud.productos.service;

import java.util.List;
import java.util.Optional;

import com.mongodb.crud.productos.model.Producto;

public interface ProductoService {
	
	Producto save(Producto producto);
	
	List<Producto> findAll();

	Optional<Producto> findById(String id);
	
	Producto update(String id, Producto producto);
	
	void deleteById(String id);
}
 