package hr.fer.tel.rasus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BeerCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogServiceApplication.class, args);
	}
}
