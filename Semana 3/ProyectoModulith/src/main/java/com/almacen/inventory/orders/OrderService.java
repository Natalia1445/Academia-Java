package com.almacen.inventory.orders;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.almacen.inventory.shared.OrderCanceled;
import com.almacen.inventory.shared.OrderCompleted;
import com.almacen.inventory.shared.OrderCreated;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }
    
    @Transactional
    public Order crearOrder(Long cliente_id) {
        Order order = new Order(cliente_id);
    
      order = orderRepository.save(order);
        
        eventPublisher.publishEvent(
                new OrderCreated(order.getId(), order.getCliente_id())
            );
   
        return order;
    }
 
    public List<Order> obtenerOrders(){
    	return orderRepository.findAll();
    }
    
    @Transactional
    public void agregarProducto(Long orderId, Long productoId, String nombreProducto, int cantidad, Double precio) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado: " + orderId));
        
        order.agregarLinea(productoId, nombreProducto, cantidad, precio);
        orderRepository.save(order);
    }
    
    @Transactional
    public void completarPedido(Long orderId) {
        Order order = orderRepository.findByIdWithLines(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado: " + orderId));

        order.completar();
        orderRepository.save(order);

        // Publicar evento de dominio
        eventPublisher.publishEvent(new OrderCompleted(order.getId(), order.getLines()));
    }
    
    @Transactional
    public void cancelarPedido(Long orderId) {
        Order order = orderRepository.findByIdWithLines(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado: " + orderId));

        order.cancelar();
        orderRepository.save(order);

        // Publicar evento de dominio
        eventPublisher.publishEvent(new OrderCanceled(order.getId(), order.getLines()));
    }
      
    
    @Transactional(readOnly = true)
    public Order obtenerOrder(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado: " + orderId));
    }

}
