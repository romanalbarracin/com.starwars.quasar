package com.starwars.quasar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Satellite {

	private String name;
	private Float distance;
	@Builder.Default
	private String[] message = new String[5];
}
