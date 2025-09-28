package com.almacen.inventory.shared;

import java.util.List;

import com.almacen.inventory.orders.LineOrder;


public class OrderCanceled extends DomainEvent{
    private final Long orderId;
    private final Integer totalLines; // Simplificado para evitar problemas de serialización
    
    public OrderCanceled() {
        super();
        this.orderId = null;
        this.totalLines = null;
    }
    
    public OrderCanceled(Long orderId, List<LineOrder> lines) {
        super();
        this.orderId = orderId;
        this.totalLines = lines != null ? lines.size() : 0;
    }
    
    public Long getOrderoId() {
        return orderId;
    }
    
    public Integer getTotalLines() {
        return totalLines;
    }
    
    // Métodos de conveniencia para compatibilidad
    public Long orderId() {
        return orderId;
    }
    
    public Integer totalLines() {
        return totalLines;
    }

}
