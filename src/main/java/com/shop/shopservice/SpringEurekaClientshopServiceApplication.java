package com.shop.shopservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Avinash
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringEurekaClientshopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientshopServiceApplication.class, args);
	}
}