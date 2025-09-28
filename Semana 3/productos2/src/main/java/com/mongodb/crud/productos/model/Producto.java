package com.mongodb.crud.productos.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "productos")
public class Producto {
    @Id
    private String id;

    private String nombreProducto;
    
    @Indexed(unique = true)
    private String descripcion;

    private Double precio;

    private Integer cantidad;
    
    private LocalDateTime fechaRegistro;

    public Producto() {
        this.fechaRegistro = LocalDateTime.now();
    }


    public Producto(String nombreProducto, String descripcion, Double precio, Integer cantidad) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaRegistro = LocalDateTime.now();
    }//constructor

	public String getId() {
		return id;
	}//getId


	public void setId(String id) {
		this.id = id;
	}//setId


	public String getNombreProducto() {
		return nombreProducto;
	}//getNombreProducto


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}//setNombreProducto


	public String getDescripcion() {
		return descripcion;
	}//getDescripcion


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}//setDescripcion


	public Double getPrecio() {
		return precio;
	}//getPrecio


	public void setPrecio(Double precio) {
		this.precio = precio;
	}//setPrecio


	public Integer getCantidad() {
		return cantidad;
	}//getCantidad


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}//setCantidad


	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}//getFechaRegistro


	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}//setFechaRegistro


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombreProducto=" + nombreProducto + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", fechaRegistro=" + fechaRegistro + "]";
	}
    
    
    
}
