package com.mongodb.crud.productos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.crud.productos.model.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

}
