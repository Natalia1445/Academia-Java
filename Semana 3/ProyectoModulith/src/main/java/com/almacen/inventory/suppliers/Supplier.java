package com.almacen.inventory.suppliers;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "suppliers")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (nullable = false)
	private String supplierName;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable= false)
	private String phone;
	
	@Column (nullable=false)
	private String address;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<CatalogoProducto> catalogo = new ArrayList<>();

	public Supplier( String supplierName, String email, String phone, String address) {
		super();
		this.supplierName = supplierName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.catalogo = new ArrayList<>();
	}
	
	
	
}
