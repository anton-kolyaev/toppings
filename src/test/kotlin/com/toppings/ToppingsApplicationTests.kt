package com.toppings

import com.toppings.dto.StatisticDTO
import com.toppings.dto.ToppingsDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.client.support.BasicAuthorizationInterceptor





@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToppingsApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@LocalServerPort
	private var port = 0

	@Test
	fun smoke() {

		val baseUrl = "http://localhost:$port/toppings/"

		restTemplate.postForObject(
			baseUrl + "create",
			ToppingsDTO("pepe.frog@kekistan.com", listOf("kekchup")),
			Any::class.java
		)
		restTemplate.postForObject(
			baseUrl + "create",
			ToppingsDTO("pepe.frog@kekistan.com", listOf("ketchup", "strawberry", "mozzarella")),
			Any::class.java
		)
		restTemplate.postForObject(
			baseUrl + "create",
			ToppingsDTO("bart.simpson@sprinfield.com", listOf("mayonnaise", "mozzarella")),
			Any::class.java
		)


	}

}
