package com.almacen.inventory.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.almacen.inventory.shared.LineOrderD;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table (name = "orden")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Long cliente_id;

    
	@Column(nullable=false)
	private Double total;
	
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrder status;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LineOrder> lines = new ArrayList<>();
    
  

	public Order(Long cliente_id) {
		this.cliente_id = cliente_id;
		this.status = StatusOrder.PENDIENTE;
		this.total = 0.0;
		this.fechaCreacion = LocalDateTime.now();
	}
    
    public void agregarLinea(Long productoId, String nombreProducto, int cantidad, Double precio) {
        LineOrder line = new LineOrder(this, productoId, nombreProducto, cantidad, precio);
        this.lines.add(line);
        this.total = this.total+(precio*cantidad);
    }
    
    public void completar() {
        if (this.status != StatusOrder.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden completar pedidos pendientes");
        }
        this.status = StatusOrder.COMPLETADO;
    }
    
    public void cancelar() {
        if (this.status== StatusOrder.COMPLETADO) {
            throw new IllegalStateException("No se puede cancelar un pedido completado");
        }
        this.status = StatusOrder.CANCELADO;
    }
    
    public List<LineOrderD> getLineD() {
        return lines.stream()
            .map(line -> new LineOrderD(
                line.getProductoId(),
                line.getNombre(),
                line.getCantidad(),
                line.getPrecio()
            ))
            .toList();
    }
    
    
}
