package com.almacen.inventory.inventory;


import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.almacen.inventory.shared.OrderCanceled;
import com.almacen.inventory.shared.OrderCompleted;

import jakarta.transaction.Transactional;






@Component
public class ManejadorEventoOrders {
    
    private final InventarioService inventarioService;
    
    public ManejadorEventoOrders(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }
    
    @ApplicationModuleListener
    @Async
    @Transactional
    public void cuandoPedidoCompletado(OrderCompleted evento) {
        System.out.println("📦 Procesando pedido completado: " + evento.orderId());
        System.out.println("📋 Total de líneas en el pedido: " + evento.totalLines());
        
        // Usar las líneas incluidas en el evento (respetando boundaries de módulos)
        var linesOrder = evento.lines();
        System.out.println("🔍 Procesando " + linesOrder.size() + " líneas del evento");
        
        // Procesar cada línea del pedido
        for (var line: linesOrder) {
            try {
                System.out.println("🔄 Reduciendo stock: " + line.getNombre() + 
                                 " (ID: " + line.getProductoId() + ") cantidad: " + line.getCantidad());
                
                inventarioService.reducirStock(line.getProductoId(), line.getCantidad());
                System.out.println("✅ Stock reducido exitosamente para " + line.getNombre());
                
            } catch (Exception e) {
                System.err.println("❌ Error reduciendo stock para " + line.getNombre() + 
                                 ": " + e.getMessage());
            }
        }
        
        System.out.println("✅ Procesamiento de pedido completado: " + evento.orderId());
    }
    
    @ApplicationModuleListener
    @Async
    @Transactional
    public void cuandoPedidoCancelado(OrderCanceled evento) {
    	System.out.println("***********LISTENER INVENTARIOS************");
        System.out.println("🔄 Procesando cancelación de pedido: " + evento.orderId());
        System.out.println("📋 Total de líneas canceladas: " + evento.totalLines());
        
        // En este caso, como es una cancelación, no necesitamos hacer nada con el stock
        // ya que el stock no se redujo previamente
        System.out.println("ℹ️ No se requiere acción de inventario para pedido cancelado");
    }
}