package com.mongodb.crud.productos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mongodb.crud.productos.model.Producto;
import com.mongodb.crud.productos.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public Producto save(Producto producto) {
        if (producto.getFechaRegistro() == null) {
            producto.setFechaRegistro(LocalDateTime.now());
        }
        return productoRepository.save(producto);
	}//Producto agregar
	
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }//Productos
    
    @Override
    public Optional<Producto> findById(String id) {
        return productoRepository.findById(id);
    }//producto get Id
    
    @Override
    public Producto update(String id, Producto producto) {
        Optional<Producto> existingProducto = productoRepository.findById(id);
        if (existingProducto.isPresent()) {
            Producto productoToUpdate = existingProducto.get();
            productoToUpdate.setNombreProducto(producto.getNombreProducto());
            productoToUpdate.setDescripcion(producto.getDescripcion());
            productoToUpdate.setPrecio(producto.getPrecio());
            productoToUpdate.setCantidad(producto.getCantidad());
            return productoRepository.save(productoToUpdate);
        }
        throw new RuntimeException("Producto no encontrado con id: " + id);
    }//Producto update
    
    @Override
    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }//producto delete
}
