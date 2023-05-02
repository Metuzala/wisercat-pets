package com.metusala.wisercatpets.com.metusala.wisercatpets.enums;

import java.util.HashMap;
import java.util.Map;

public enum CountryOfOrigin {
    ESTONIA("Estonia"),
    LATVIA("Latvia"),
    LITHUANIA("Lithuania"),
    FINLAND("Finland"),
    SWEDEN("Sweden"),
    NORWAY("Norway"),
    ;
    CountryOfOrigin(String name) {
        this.name = name;
    }

    public static Map<String, String> getOptions() {
        var options = new HashMap<String, String>();
        for (var country : CountryOfOrigin.values()) {
            options.put(country.toString(), country.name);
        }
        return options;
    }

    public final String name;
}
