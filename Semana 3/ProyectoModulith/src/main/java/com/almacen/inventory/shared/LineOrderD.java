package com.almacen.inventory.shared;

public class LineOrderD {

	    private final Long productoId;
	    private final String nombre;
	    private final Integer cantidad;
	    private final Double precio;
	    
	    public LineOrderD(Long productoId, String nombre, Integer cantidad, Double precio) {
	        this.productoId = productoId;
	        this.nombre = nombre;
	        this.cantidad = cantidad;
	        this.precio = precio;
	    }
	    
	    public Long getProductoId() { return productoId; }
	    public String getNombre() { return nombre; }
	    public Integer getCantidad() { return cantidad; }
	    public Double getPrecio() { return precio; }
	    
	    public Double getSubtotal() {
	        return (precio*cantidad);
	    }
	}
