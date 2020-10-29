package com.starwars.quasar.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.javafaker.Faker;
import com.starwars.quasar.AbstractTest;
import com.starwars.quasar.exception.CustomError;
import com.starwars.quasar.model.Satellite;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TopSecretSplitController.class)
class TopSecretSplitControllerTest extends AbstractTest {
 
	@Test
	public void testPostSecretMessage() throws Exception {
		String uri = "/topsecret_split/skywalker";
		Faker fake = new Faker();
		Satellite payload = new Satellite();	
		payload = Satellite.builder()
				 			 .distance(Float.valueOf(fake.number().digits(3)))
				 			 .message(new String[]{"","es","","mensaje",""})
				 			 .build();
		
		String inputJson = super.mapToJson(payload);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      				 .contentType(MediaType.APPLICATION_JSON_VALUE)
			      				 .content(inputJson))
								 .andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		CustomError error = super.mapFromJson(content, CustomError.class);
		assertEquals(404, status);
		assertEquals(error.getMessage(), "No hay suficiente informacion");
	}
	
	@Test
	public void testGetSecretMessage() throws Exception {
		String uri = "/topsecret_split/skywalker";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      				 .contentType(MediaType.APPLICATION_JSON_VALUE)
			      				 .content(""))
								 .andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		CustomError error = super.mapFromJson(content, CustomError.class);
		assertEquals(404, status);
		assertEquals(error.getMessage(), "No hay suficiente informacion");
	}
}
