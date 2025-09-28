package com.almacen.inventory.suppliers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	  Optional<Supplier> findBySupplierName(String supplierName);

}
