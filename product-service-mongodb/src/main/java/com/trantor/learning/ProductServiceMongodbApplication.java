package com.trantor.learning;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantor.learning.entities.Product;
import com.trantor.learning.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProductServiceMongodbApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataInit();

	}

	private void dataInit() throws IOException {
		Path path = Paths.get("d:/dummyproducts.json");
		Reader reader = Files.newBufferedReader(path);
		Gson gson = new Gson();
		ArrayList<Product> products = gson.fromJson(reader, new TypeToken<ArrayList<Product>>(){}.getType());


		productRepository.deleteAll();
		List<Product> productList = productRepository.saveAll(products);
		//productList.forEach(System.out::println);


		productRepository.findAll().forEach(System.out::println);
	}
}
