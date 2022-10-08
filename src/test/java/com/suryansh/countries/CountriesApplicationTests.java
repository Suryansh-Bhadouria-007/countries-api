package com.suryansh.countries;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
class CountriesApplicationTests {

	//1.

//	@Test
//	public void givenReturnTypeIsString_whenJacksonOnClasspath_thenDefaultContentTypeIsJSON()
//		throws Exception {
//
//		// Given
//		String expectedMimeType = "application/json";
//
//		// Then
//		String actualMimeType = this.mockMvc.perform(MockMvcRequestBuilders.get("/greetings-with-response-body", 1))
//			.andReturn().getResponse().getContentType();
//
//		Assert.assertEquals(expectedMimeType, actualMimeType);
//	}
}
