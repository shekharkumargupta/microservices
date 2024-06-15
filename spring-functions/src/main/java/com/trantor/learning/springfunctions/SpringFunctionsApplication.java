package com.trantor.learning.springfunctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringFunctionsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringFunctionsApplication.class,
				"--spring.cloud.function.location=..../target/spring-functions-0.0.1-SNAPSHOT.jar",
				"--spring.cloud.function.definition=greet");

		FunctionCatalog functionCatalog = applicationContext.getBean(FunctionCatalog.class);
		//Function<String, String> upperCase = functionCatalog.lookup("/upperCase");
		Supplier<String> greet = functionCatalog.lookup("/greet");
		//System.out.println(upperCase.apply("shekhar"));
		System.out.println(greet.get());
	}


	//@Bean
	public Function<String, String> upperCase(){
		return value -> new String(value).toUpperCase();
	}

	@Bean
	public Supplier<String> greet(){
		return () -> new String("Hello World!!");
	}
}
