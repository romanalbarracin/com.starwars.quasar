package com.starwars.quasar.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
import com.starwars.quasar.model.Payload;
import com.starwars.quasar.model.Satellite;
import com.starwars.quasar.model.Secret;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TopSecretController.class)
class TopSecretControllerTest extends AbstractTest {
 
	@Test
	public void testGetSecretMessageOK() throws Exception {
		String uri = "/topsecret";
		Faker fake = new Faker();
		Payload payload = new Payload();
		Satellite skywalker = new Satellite();
		Satellite sato = new Satellite();
		Satellite kenobi = new Satellite();
		skywalker = Satellite.builder()
				 			 .name("skywalker")
				 			 .distance(Float.valueOf(fake.number().digits(3)))
				 			 .message(new String[]{"","es","","mensaje",""})
				 			 .build();
		sato = Satellite.builder()
				 		.name("sato")
				 		.distance(Float.valueOf(fake.number().digits(3)))
				 		.message(new String[]{"Este","es","","","secreto"})
				 		.build();
		kenobi = Satellite.builder()
				 		  .name("kenobi")
				 		  .distance(Float.valueOf(fake.number().digits(3)))
				 		  .message(new String[]{"","es","un","mensaje",""})
				 		  .build();
		payload.setSatellites(new ArrayList<>());
		payload.getSatellites().add(skywalker);
		payload.getSatellites().add(sato);
		payload.getSatellites().add(kenobi);
		
		String inputJson = super.mapToJson(payload);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      				 .contentType(MediaType.APPLICATION_JSON_VALUE)
			      				 .content(inputJson))
								 .andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Secret secret = super.mapFromJson(content, Secret.class);
		assertEquals(200, status);
		assertNotEquals(secret.getMessage(), null);
	}

	@Test
	public void testgetSecretMessageError() throws Exception {
		String uri = "/topsecret";
		Faker fake = new Faker();
		Payload payload = new Payload();
		Satellite sato = new Satellite();
		Satellite kenobi = new Satellite();
		sato = Satellite.builder()
				 		.name("sato")
				 		.distance(Float.valueOf(fake.number().digits(3)))
				 		.message(new String[]{"Este","es","","","secreto"})
				 		.build();
		kenobi = Satellite.builder()
				 		  .name("kenobi")
				 		  .distance(Float.valueOf(fake.number().digits(3)))
				 		  .message(new String[]{"","es","un","mensaje",""})
				 		  .build();
		payload.setSatellites(new ArrayList<>());
		payload.getSatellites().add(sato);
		payload.getSatellites().add(kenobi);
		
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

}
