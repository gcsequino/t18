package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.tco.misc.Places;


public class TestDistancesRequest {

    private DistancesRequest distance;
    private DistancesRequest distanceWithPlaces;

    @BeforeEach
    public void createConfigurationForTestCases() {
        distance = new DistancesRequest();
        distance.buildResponse();
    }

    @BeforeEach
    public void createConfigurationForPlaces() {
        Places places = new Places();
        distanceWithPlaces = new DistancesRequest(3959.0, places);
        distanceWithPlaces.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"distances\"")
    public void testType() {
        String type = distance.getRequestType();
        assertEquals("distances", type);
    }

    @Test
    @DisplayName("Check distances array")
    public void testDistancesArray() {
        Long[] distances = distance.getDistances();
        Long[] test = {};
        assertArrayEquals(distances, test);
    }

    @Test
    @DisplayName("Check earth radius")
    public void testEarthRadius() {
        Double earthRadius = distance.getEarthRadius();
        assertEquals(earthRadius, 3959.0);
    }

    @Test
    @DisplayName("Check places Array")
    public void testPlacesArray() {
        Places testPlaces = new Places();
        assertEquals(testPlaces, distanceWithPlaces.getPlaces());

    }
    
}
