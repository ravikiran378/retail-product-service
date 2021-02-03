package com.retail.product.run;

import com.retail.product.error.BadRequestException;
import com.retail.product.integration.db.entity.ProductEntity;
import com.retail.product.resource.ProductResource;
import com.retail.product.service.ProductService;
import com.retail.schema.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.retail.product")
@EnableJpaRepositories("com.retail.product.integration.db.repository")
@EntityScan("com.retail.product.integration.db.entity")
public class ProductApplication {

	private static final Logger log = LogManager.getLogger(ProductApplication.class);

	@Autowired
	ProductService productService;

	@PostConstruct
	public void keepAlive(){
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						while(true){
							Product product = null;
							try {
								Thread.sleep(30* 1000);
								product = productService.get(17L);
								log.info("Keeping Alive with {}", product);
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (BadRequestException bre){
								log.info("Keeping Alive with {}", "No Product ID, requested 1");
							}
						}
					}
				}
		).start();
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
