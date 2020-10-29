package com.starwars.quasar.model;

import java.util.HashMap;
import java.util.Map;

public enum SatelliteType {
	KENOBI("kenobi"), 
    SKYWALKER("skywalker"), 
    SATO("sato");
 
    private String name;

    private static final Map<String, SatelliteType> lookup = new HashMap<>();
 
	SatelliteType(String name) {
        this.name = name;
    }
 
    public String getValue() {
        return name;
    }
  
    static {
        for(SatelliteType s : SatelliteType.values())
        {
            lookup.put(s.getValue(), s);
        }
    }
  
    public static SatelliteType get(String name) {
        return lookup.get(name);
    }
}
