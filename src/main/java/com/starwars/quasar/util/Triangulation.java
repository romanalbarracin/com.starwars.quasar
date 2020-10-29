package com.starwars.quasar.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.starwars.quasar.model.Position;

public class Triangulation {
	
	public Position getLocation(List<Float> distance) {
		Position posKenobi = new Position();
		Position posSkywalker = new Position();
		Position posSato = new Position();
		Position posEmisor = new Position();
		Position posX = new Position();
		Position posY = new Position();
		Position auxiliar = new Position();
		Position auxiliar2 = new Position();
		
		posKenobi = Position.builder().x(-500).y(-200).build();
		posSkywalker = Position.builder().x(100).y(-100).build();
		posSato = Position.builder().x(500).y(100).build();
		
		double distanceSatoSkywalker = Math.pow(Math.pow(posSkywalker.getX() - posSato.getX(), 2)
				 					 + Math.pow(posSkywalker.getY() - posSato.getY(),2), 0.5);
		posX = Position.builder()
					   .x((posSkywalker.getX() - posKenobi.getX()) / (float)distanceSatoSkywalker)
					   .y((posSkywalker.getY() - posKenobi.getY()) / (float)distanceSatoSkywalker)
					   .build();
		
		auxiliar = Position.builder()
						   .x(posSato.getX() - posKenobi.getX())
					  	   .y(posSato.getY() - posKenobi.getY())
					  	   .build();
		
		double i = posX.getX() * auxiliar.getX() + posX.getY() * auxiliar.getY();
		
		auxiliar2 = Position.builder()
							.x(posSato.getX() - posKenobi.getX() - (float)i * posX.getX())
						    .y(posSato.getY() - posKenobi.getY() - (float)i * posX.getY())
						    .build();
		
		posY = Position.builder()
				       .x((float) (auxiliar2.getX() / (Math.pow(Math.pow(auxiliar2.getX(),2) + Math.pow(auxiliar2.getY(),2),.5))))
					   .y((float) (auxiliar2.getY() / (Math.pow(Math.pow(auxiliar2.getX(),2) + Math.pow(auxiliar2.getY(),2),.5))))
					   .build();
		
		float j = posY.getX() * auxiliar.getX() + posY.getY() * auxiliar.getY();
		float x = (float) ((Math.pow(distance.get(0), 2) - Math.pow(distance.get(1), 2) + Math.pow(distanceSatoSkywalker, 2)) / (2 * distanceSatoSkywalker));
		float y = (float) ((Math.pow(distance.get(0), 2) - Math.pow(distance.get(2), 2) + Math.pow(i, 2) + Math.pow(j, 2)) / (2 * j) - i * x / j);
		
		float positionX = posKenobi.getX() + x * posX.getX() + y * posY.getX();
		float positionY = posKenobi.getY() + x * posX.getY() + y * posY.getY();
		
		posEmisor = Position.builder().x(positionX).y(positionY).build();
		return posEmisor;
	}

	public String getMessage(List<String[]> messages) {
		String[] secret = new String[5];
		List<String> options0 = new ArrayList<String>();
		List<String> options1 = new ArrayList<String>();
		List<String> options2 = new ArrayList<String>();
		List<String> options3 = new ArrayList<String>();
		List<String> options4 = new ArrayList<String>();
		List<String> dist0 = new ArrayList<String>();
		List<String> dist1 = new ArrayList<String>();
		List<String> dist2 = new ArrayList<String>();
		List<String> dist3 = new ArrayList<String>();
		List<String> dist4 = new ArrayList<String>();
		
		messages.forEach(m -> {
			if (!m[0].isEmpty()) { options0.add(m[0]); }
			if (!m[1].isEmpty()) { options1.add(m[1]); }
			if (!m[2].isEmpty()) { options2.add(m[2]); }
			if (!m[3].isEmpty()) { options3.add(m[3]); }
			if (!m[4].isEmpty()) { options4.add(m[4]); }
		});
		dist0 = options0.stream().distinct().collect(Collectors.toList());
		dist1 = options1.stream().distinct().collect(Collectors.toList());
		dist2 = options2.stream().distinct().collect(Collectors.toList());
		dist3 = options3.stream().distinct().collect(Collectors.toList());
		dist4 = options4.stream().distinct().collect(Collectors.toList());
		
		secret[0] = dist0.size() >= 1 ? dist0.get(0) : "";
		secret[1] = dist1.size() >= 1 ? dist1.get(0) : "";
		secret[2] = dist2.size() >= 1 ? dist2.get(0) : "";
		secret[3] = dist3.size() >= 1 ? dist3.get(0) : "";
		secret[4] = dist4.size() >= 1 ? dist4.get(0) : "";
		
		return String.join(" ", secret);
	}
}
