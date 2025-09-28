package com.almacen.inventory.shared;

import java.util.List;

import com.almacen.inventory.orders.LineOrder;


public class OrderCompleted extends DomainEvent {
    
    private final Long orderId;
    private final List<LineOrder> lines;
    
    public OrderCompleted() {
        super();
        this.orderId = null;
        this.lines = null;
    }
    
    public OrderCompleted(Long orderId, List<LineOrder> lines) {
        super();
        this.orderId = orderId;
        this.lines = lines != null ? List.copyOf(lines) : List.of();
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public List<LineOrder> getLines() {
        return lines;
    }
    
    // Métodos de conveniencia para compatibilidad
    public Long orderId() {
        return orderId;
    }
    
    public List<LineOrder> lines() {
        return lines;
    }
    
    public Integer totalLines() {
        return lines != null ? lines.size() : 0;
    }
}
