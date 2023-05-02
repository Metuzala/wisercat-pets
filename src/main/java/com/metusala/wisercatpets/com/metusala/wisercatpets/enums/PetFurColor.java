package com.metusala.wisercatpets.com.metusala.wisercatpets.enums;

import java.util.HashMap;
import java.util.Map;

public enum PetFurColor {
    BLACK("Black"),
    WHITE("White"),
    BROWN("Brown"),
    YELLOW("Yellow"),
    BLUE("Blue"),
    ;

    PetFurColor(String name) {
        this.name = name;
    }

    public static Map<String, String> getOptions() {
        var options = new HashMap<String, String>();
        for (var color : PetFurColor.values()) {
            options.put(color.toString(), color.name);
        }
        return options;
    }

    public final String name;
}
