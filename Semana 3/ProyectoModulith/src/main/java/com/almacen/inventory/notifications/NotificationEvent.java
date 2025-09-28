package com.almacen.inventory.notifications;


import org.springframework.modulith.events.ApplicationModuleListener;

import org.springframework.stereotype.Component;


import com.almacen.inventory.shared.OrderCreated;
import com.almacen.inventory.shared.StockBajo;
import com.almacen.inventory.shared.SupplierCreado;



@Component
public class NotificationEvent {
	
	
	@ApplicationModuleListener
	void avisarStockBajo(StockBajo stockBajo){
		System.out.println("⚠️ ALERTA: Stock bajo detectado!");
        System.out.println("   Producto: " + stockBajo.getNombre());
        System.out.println("   Stock actual: " + stockBajo.getStockActual());
        System.out.println("   Stock mínimo: " + stockBajo.getStockMinimo());
	}
	
	@ApplicationModuleListener
	void buscarProveedores(StockBajo stockBajo){
	    System.out.println("Buscando proveedores para: " + stockBajo.getNombre());
	    
	
	    System.out.println(" Consultando base de datos de proveedores...");
	    System.out.println("Contactando proveedores disponibles...");
	}
	
    @ApplicationModuleListener
    void avisarSupplierCreado(SupplierCreado supplierCreado){
        System.out.println("✅ NUEVO PROVEEDOR: " + supplierCreado.getSupplierName());
        System.out.println("   ID: " + supplierCreado.getId());
    }
    
    @ApplicationModuleListener
    void avisarOrdenCreada(OrderCreated orderCreated) {
    	System.out.println("Nueva order creada con un ID: " + orderCreated.getId());
    }

}
	


