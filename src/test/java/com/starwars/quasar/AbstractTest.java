package com.starwars.quasar;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {
	
	@Autowired
	protected MockMvc mvc;
	
	protected String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.writeValueAsString(object);
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException,
																	JsonMappingException,
																	IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
