package com.almacen.inventory;

//import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.almacen.inventory.inventory.ProductoRepository;
import com.almacen.inventory.suppliers.Supplier;
import com.almacen.inventory.suppliers.SupplierRepository;
import com.almacen.inventory.inventory.Producto;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner initSlots(ProductoRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(new Producto("Laptop HP Pavilion", "Laptop de trabajo con 16GB RAM", "LPT-HP-001", 25, 5, 899.99));
				repo.save(new Producto("Mouse Logitech MX", "Mouse inalámbrico ergonómico", "MSE-LG-002", 50, 10, 89.99));
				repo.save(new Producto("Teclado Mecánico", "Teclado mecánico RGB switches azules", "TEC-MEC-003", 30, 8, 159.99));
				repo.save(new Producto("Monitor Samsung 27", "Monitor LED 27 pulgadas Full HD", "MON-SS-004", 15, 3, 299.99));
				repo.save(new Producto("Impresora Canon", "Impresora multifuncional a color", "IMP-CN-005", 8, 2, 249.99));
				repo.save(new Producto("Auriculares Sony", "Auriculares inalámbricos con cancelación de ruido", "AUR-SY-006", 40, 12, 199.99));
				repo.save(new Producto("SSD Samsung 1TB", "Disco sólido interno SATA III", "SSD-SS-007", 60, 15, 119.99));
				repo.save(new Producto("Webcam Logitech", "Cámara web HD 1080p para videoconferencias", "WEB-LG-008", 22, 6, 79.99));
				repo.save(new Producto("Router WiFi 6", "Router inalámbrico de alta velocidad", "RTR-WF-009", 18, 4, 179.99));
				repo.save(new Producto("Cable HDMI 2.1", "Cable HDMI 4K Ultra HD 2 metros", "CBL-HD-010", 100, 20, 24.99));
			}
		};
	}
	
	@Bean
	CommandLineRunner initSuppliers(SupplierRepository repo) {
	    return args -> {
	        repo.save(new Supplier(
	                "Distribuidora Central S.A.",
	                "contacto@dcentral.com",
	                "+52 55 1234 5678",
	                "Av. Reforma 101, CDMX"
	        ));

	        repo.save(new Supplier(
	                "Proveedores del Norte",
	                "ventas@pnorte.mx",
	                "+52 81 8765 4321",
	                "Blvd. Industrias 300, Monterrey, NL"
	        ));

	        repo.save(new Supplier(
	            
	                "Suministros y Logística del Sur",
	                "info@slosur.com",
	                "+52 999 223 4455",
	                "Calle 45 #200, Mérida, Yucatán"
	
	        ));

	        repo.save(new Supplier(
	          
	                "Tecnología y Componentes",
	                "support@tyc.com.mx",
	                "+52 33 5566 7788",
	                "Av. Patria 1500, Guadalajara, Jalisco"
	       
	        ));

	        repo.save(new Supplier(
	                "Comercializadora del Bajío",
	                "comercial@cbajio.mx",
	                "+52 477 112 3344",
	                "Blvd. Campestre 2500, León, Guanajuato"
	        ));
	    };
	}
}
