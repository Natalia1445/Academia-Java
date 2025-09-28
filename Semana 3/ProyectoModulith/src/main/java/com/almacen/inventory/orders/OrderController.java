package com.almacen.inventory.orders;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventario")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Order> crearOrder(
			@RequestParam Long cliente_id
			) {
		Order order = orderService.crearOrder(cliente_id);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> obtenerOrders(){
		List<Order> order = orderService.obtenerOrders();
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> obtenerOrder(@PathVariable Long orderId){
		Order order = orderService.obtenerOrder(orderId);
		return ResponseEntity.ok(order);
	}
	
    @PostMapping("/{orderId}/productos")
    public ResponseEntity<Void> agregarProducto(
            @PathVariable Long orderId,
            @RequestParam Long productoId,
            @RequestParam String nombreProducto,
            @RequestParam int cantidad,
            @RequestParam Double precio) {
        
        orderService.agregarProducto(orderId, productoId, nombreProducto, cantidad, precio);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{orderId}/completar")
    public ResponseEntity<Void> completarPedido(@PathVariable Long orderId) {
        orderService.completarPedido(orderId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{orderId}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long orderId) {
        orderService.cancelarPedido(orderId);
        return ResponseEntity.ok().build();
    }

}
