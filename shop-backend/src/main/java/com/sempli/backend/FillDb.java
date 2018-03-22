package com.sempli.backend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.sempli.logic.Product;

@Component
public class FillDb implements CommandLineRunner {
	
	private ProductRepository productRepository;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	public FillDb(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
//		List<String> lines = Files.readAllLines(Paths.get(res.getURI()), StandardCharsets.UTF_8);
		Resource resource = resourceLoader.getResource("classpath:booklist.csv"); 
		InputStream dbStream = resource.getInputStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(dbStream));
		String line;
		List<Product> products = new ArrayList<>();
		//dismiss titles
		line = buffer.readLine();
		do {
			line = buffer.readLine();
			if(line != null) {
				line = line.replaceAll("\"","");
				String[] productValues = line.split(",");
				ProductValidator productValidator = new ProductValidator(productValues);
				if(productValidator.isComplianing() && productValues.length == 4) {
					Product book = new Product(productValues[0], productValues[1], Integer.valueOf(productValues[2]), Boolean.parseBoolean(productValues[3]));
					products.add(book);
				}
			}	
		}while(line != null); 
		this.productRepository.saveAll(products);
	}
}
