package com.almacen.inventory.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderCreated {
	
	Long id;
	Long cliente_id;


}