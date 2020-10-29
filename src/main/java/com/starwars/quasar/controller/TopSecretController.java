package com.starwars.quasar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.exception.CustomError;
import com.starwars.quasar.model.Payload;
import com.starwars.quasar.model.Satellite;
import com.starwars.quasar.model.SatelliteType;
import com.starwars.quasar.model.Secret;
import com.starwars.quasar.util.MyOrder;
import com.starwars.quasar.util.Triangulation;

@RestController
public class TopSecretController {
	public static final Logger logger = LoggerFactory.getLogger(TopSecretController.class);

	@GetMapping("/starwars")
    public String sayHello() {
		return "<h1>Star Wars Rules!!!</h1>";
    }
	
	@RequestMapping(path = "/topsecret", method = RequestMethod.POST)
	public ResponseEntity<Object> getSecretMessage(@RequestBody Payload payload) {
		try {
			logger.info("> Starting post top secret message");
			for (Satellite s : payload.getSatellites()) {
				if (SatelliteType.get(s.getName()) == null) {
				   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			Secret secret = new Secret();
			MyOrder myorder = new MyOrder();
			Triangulation triangulate = new Triangulation();
			List<Satellite> localData = new ArrayList<>();
			List<String[]> messages = new ArrayList<>();
			List<Float> distances = new ArrayList<>();
	
			localData = myorder.orderSatellite(payload.getSatellites());
			localData.forEach(s -> {
				distances.add(s.getDistance());
				messages.add(s.getMessage());
			});
			secret.setPosition(triangulate.getLocation(distances));
			secret.setMessage(triangulate.getMessage(messages));
			
			logger.info("> response post secret message {}" , secret);
			return new ResponseEntity<Object>(secret, HttpStatus.OK);
		} catch(Exception ex) {
			CustomError error = new CustomError(HttpStatus.NOT_FOUND, "No hay suficiente informacion");
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
	}
}
