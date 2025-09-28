package com.almacen.inventory.suppliers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.almacen.inventory.shared.SupplierCreado;



@Service
public class SupplierService {
   private final SupplierRepository supplierRepository;
   private final ApplicationEventPublisher eventPublisher;
    
    public SupplierService(SupplierRepository supplierRepository, ApplicationEventPublisher eventPublisher) {
       this.supplierRepository = supplierRepository;
        this.eventPublisher = eventPublisher;
    }
    
    @Transactional
    public Supplier crearSupplier(String supplierName, String email, String phone, String address) {
        Supplier nuevoSupplier = new Supplier(supplierName, email, phone, address);
        
        nuevoSupplier = supplierRepository.save(nuevoSupplier);
        
 
        eventPublisher.publishEvent(
            new SupplierCreado(nuevoSupplier.getId(), nuevoSupplier.getSupplierName())
        );
        
        return nuevoSupplier;
    }//saveProveedor
    

    public List<Supplier> obtenerSuppliers() {
        return supplierRepository.findAll();
    }//Productos
    
    @Autowired
    private CatalogoProductoRepository catalogoRepository; 

    public void asociarProductoConProveedor(Long supplierId, String sku, Double precioCompra) {
        Supplier supplier = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new RuntimeException("Supplier no encontrado"));
        
        CatalogoProducto catalogo = new CatalogoProducto();
        catalogo.setSupplier(supplier);
        catalogo.setSku(sku);
        catalogo.setPrecioCompra(precioCompra);
        
        catalogoRepository.save(catalogo);
        
        System.out.println(" Producto " + sku + " asociado con " + supplier.getSupplierName());
    }

	public Supplier obtenerSupplier(Long supplierId) {
	   
	        return supplierRepository.findById(supplierId)
	            .orElseThrow(() -> new IllegalArgumentException("Supplier no encontrado: " + supplierId));
	    }
	
	
	}
    

	
	

	

