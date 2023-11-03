package com.tco.database;

public class Find {
    public static String match(String phrase, int limit) {
        // match limit 0
        if (limit == 0) {
            return matchLimitZero(phrase);
        }
        // match empty string
        if (phrase.isEmpty()) {
            return matchPhraseEmpty(limit);
        }
        // match normal
        else {
            return matchNorm(phrase, limit);
        }
    }

    private static String matchLimitZero(String phrase) {
        phrase = "\'%" + phrase + "%\'";
        return "SELECT world.name, world.latitude, world.longitude, world.id, world.altitude,"
        + " world.municipality, world.type, region.name, country.name,"
        + " world.wikipedia_link FROM world INNER JOIN region ON world.iso_region"
        + " = region.id INNER JOIN country ON world.iso_country = country.id INNER JOIN"
        + " continent ON world.continent = continent.id WHERE (country.name LIKE " + phrase
        + " OR region.name LIKE " + phrase + " OR world.name LIKE " + phrase + " OR world.municipality"
        + " LIKE " + phrase + " OR world.id LIKE " + phrase + ") LIMIT 500;";
    }

    private static String matchPhraseEmpty(int limit) {
        return "SELECT world.name, world.latitude, world.longitude, world.id, world.altitude,"
        + " world.municipality, world.type, region.name, country.name,"
        + " world.wikipedia_link FROM world INNER JOIN region ON world.iso_region"
        + " = region.id INNER JOIN country ON world.iso_country = country.id INNER JOIN"
        + " continent ON world.continent = continent.id ORDER BY rand() LIMIT " + limit + ";";
    }

    private static String matchNorm(String phrase, int limit) {
        phrase = "\'%" + phrase + "%\'";
        return "SELECT world.name, world.latitude, world.longitude, world.id, world.altitude,"
        + " world.municipality, world.type, region.name, country.name,"
        + " world.wikipedia_link FROM world INNER JOIN region ON world.iso_region"
        + " = region.id INNER JOIN country ON world.iso_country = country.id INNER JOIN"
        + " continent ON world.continent = continent.id WHERE (country.name LIKE " + phrase
        + " OR region.name LIKE "+ phrase + " OR world.name LIKE " + phrase + " OR world.municipality"
        + " LIKE " + phrase + " OR world.id LIKE " + phrase + ") LIMIT " + limit + ";";
    }

    public static String count(String phrase) {
        phrase = "\'%" + phrase + "%\'";
        return "SELECT count(*) FROM world INNER JOIN region ON world.iso_region"
        + " = region.id INNER JOIN country ON world.iso_country = country.id INNER JOIN"
        + " continent ON world.continent = continent.id WHERE (country.name LIKE " + phrase
        + " OR region.name LIKE "+ phrase + " OR world.name LIKE " + phrase + " OR world.municipality"
        + " LIKE " + phrase + " OR world.id LIKE " + phrase + ");";
    }
}