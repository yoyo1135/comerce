package net.cfl.comerceshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"net.cfl.comerceshop"}) // Asegúrate de que este paquete incluya todos tus componentes
@EnableJpaRepositories(basePackages = "net.cfl.comerceshop.repositorio") // Asegúrate de que este paquete incluya tus repositorios
public class ComerceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComerceShopApplication.class, args);
	}

}
