package com.starwars.quasar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.quasar.exception.CustomError;
import com.starwars.quasar.model.Satellite;
import com.starwars.quasar.model.SatelliteType;
import com.starwars.quasar.model.Secret;
import com.starwars.quasar.util.MyOrder;
import com.starwars.quasar.util.Triangulation;

@RestController
public class TopSecretSplitController {
	public static final Logger logger = LoggerFactory.getLogger(TopSecretSplitController.class);

	@RequestMapping(path = "/topsecret_split/{satellite_name}", method = RequestMethod.POST)
	public ResponseEntity<Object> getSplitSecretMessage(@PathVariable("satellite_name") String satellite_name,
														@RequestBody Satellite payload) {
		try {
			logger.info("> Starting post top secret split message");
			
			if (satellite_name == null || SatelliteType.get(satellite_name) == null || payload == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Secret secret = new Secret();
			MyOrder myorder = new MyOrder();
			Triangulation triangulate = new Triangulation();
			List<Satellite> localData = new ArrayList<>();
			List<String[]> messages = new ArrayList<>();
			List<Float> distances = new ArrayList<>();
			List<Satellite> satellites = new ArrayList<>();
			
			payload.setName(satellite_name);
			satellites.add(payload);
			localData = myorder.orderSatellite(satellites);
			localData.forEach(s -> {
				distances.add(s.getDistance());
				messages.add(s.getMessage());
			});
			secret.setPosition(triangulate.getLocation(distances));
			secret.setMessage(triangulate.getMessage(messages));
			
			logger.info("> response post top secret split message {}" , secret);
			return new ResponseEntity<Object>(secret, HttpStatus.OK);
		} catch(Exception ex) {
			CustomError error = new CustomError(HttpStatus.NOT_FOUND, "No hay suficiente informacion");
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
	}

	@RequestMapping(path = "/topsecret_split/{satellite_name}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSecretMessage(@PathVariable("satellite_name") String satellite_name) {
		try {
			logger.info("> Starting get top secret message split");
			
			if (satellite_name == null || SatelliteType.get(satellite_name) == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Triangulation triangulate = new Triangulation();
			Secret secret = new Secret();
			secret.setPosition(triangulate.getLocation(null));
			secret.setMessage(triangulate.getMessage(null));
			
			logger.info("> response get top secret message split {}" , secret);
			return new ResponseEntity<Object>(secret, HttpStatus.OK);
		} catch(Exception ex) {
			CustomError error = new CustomError(HttpStatus.NOT_FOUND, "No hay suficiente informacion");
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
	}
}
