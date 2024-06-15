package com.trantor.learning.springfunctions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(classes = SpringFunctionsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringFunctionsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() throws Exception {
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				RequestEntity.post(new URI("/upperCase"))
						.body("shekhar"), String.class);
		System.out.println(responseEntity.getBody());
		assert true;
	}

}
