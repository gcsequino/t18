package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.tco.misc.Place;
import com.tco.misc.Places;

public class TestCalculateDistances {

    private DistancesRequest testCalculateEmpty;
    private DistancesRequest testCalculateOne;
    private DistancesRequest testCalculateFull;
    private FindRequest find;

    @BeforeEach
    public void createConfigurationForEmptyTestCases() {
        Places emptyPlaces = new Places();
        testCalculateEmpty = new DistancesRequest(3959.0, emptyPlaces);
        testCalculateEmpty.buildResponse();
    }

    @BeforeEach
    public void createConfigurationForFullTestCases() {
        find = new FindRequest("Half Moon Bay", 1);
        find.buildResponse();
        Places fullPlaces = find.getPlaces();
        testCalculateOne = new DistancesRequest(3959.0, fullPlaces);
        testCalculateOne.buildResponse();
        find = new FindRequest("Denver International", 1);
        find.buildResponse();
        Places newFullPlaces = find.getPlaces();
        fullPlaces.addAll(newFullPlaces);
        testCalculateFull = new DistancesRequest(3959.0, fullPlaces);
        testCalculateFull.buildResponse();
    }

    @Test
    public void testEmpty(){
        Long [] test = {};
        Long [] distances = testCalculateEmpty.getDistances();
        assertArrayEquals(test, distances);
    }

    @Test 
    public void testOnePlace(){
        Long[] test = {0L};
        Long[] distances = {};

        distances = testCalculateOne.getDistances();
        assertEquals(test[0], distances[0]);
    }
    @Test
    public void testTwoPlaces(){
        //Test Array is what google earth says for the distance between the coordinates I found using a findrequest
        //Obviously google uses a different function, so there is a margin for correctness (.5%) that I think is acceptable
        Long[] test = {975L, 975L};
        Long[] distances = {};

        distances = testCalculateFull.getDistances();
        assertEquals(test[0], distances[0], test[0] * 0.005);
    }
}