package com.metusala.wisercatpets.com.metusala.wisercatpets.enums;

import java.util.HashMap;
import java.util.Map;

public enum PetType {
    CAT("Cat"),
    DOG("Dog"),
    HORSE("Horse"),
    RABBIT("Rabbit"),
    PARROT("Parrot"),
;
    PetType(String name) {
        this.name = name;
    }

    public static Map<String, String> getOptions() {
        var options = new HashMap<String, String>();
        for (var type : PetType.values()) {
            options.put(type.toString(), type.name);
        }
        return options;
    }
    public final String name;
}
