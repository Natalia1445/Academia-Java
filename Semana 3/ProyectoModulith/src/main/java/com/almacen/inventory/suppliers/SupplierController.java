package com.almacen.inventory.suppliers;

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
public class SupplierController {
    
    private final SupplierService supplierService;
    
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    
	}
	 @PostMapping("/suppliers")
	    public ResponseEntity<Supplier> crearSupplier(
	            @RequestParam String supplierName,
	            @RequestParam String email,
	            @RequestParam String phone,
	            @RequestParam String address ){
	        
	        Supplier Nuevosupplier = supplierService.crearSupplier(supplierName,email, phone, address);
	        return ResponseEntity.ok(Nuevosupplier);
	    }
	 
	    @GetMapping("/suppliers")
	    public ResponseEntity<List<Supplier>> obtenerSuppliers() {
	        List<Supplier> suppliers = supplierService.obtenerSuppliers();
	        return ResponseEntity.ok(suppliers);
	    }
	    
	    @GetMapping("/suppliers/{supplierId}")
	    public ResponseEntity<Supplier> obtenerSupplier(@PathVariable Long supplierId) {
	        Supplier supplier = supplierService.obtenerSupplier(supplierId);
	        return ResponseEntity.ok(supplier);
	    }

}
