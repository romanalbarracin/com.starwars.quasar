package com.starwars.quasar.util;

import java.util.ArrayList;
import java.util.List;

import com.starwars.quasar.model.Satellite;
import com.starwars.quasar.model.SatelliteType;

public class MyOrder {

	public List<Satellite> orderSatellite(List<Satellite> satellites) {
		List<Satellite> localList = new ArrayList<>();
		for(SatelliteType t : SatelliteType.values())
		{
		    System.out.println(t.name() + " :: "+ t.getValue());
			for (Satellite s : satellites) {
				if (t.getValue().equals(s.getName())) {
					localList.add(s);
				}
			}
		}
		return localList;
	}
}
