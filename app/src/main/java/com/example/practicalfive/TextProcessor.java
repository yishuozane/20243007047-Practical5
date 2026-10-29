package com.example.practicalfive;

import java.util.Locale;

public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String input = municipality.trim().toLowerCase(Locale.ROOT);

        switch (input) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "chongqing":
                return "Chongqing";
            case "tianjin":
                return "Tianjin";
            default:
                return null;
        }
    }
}