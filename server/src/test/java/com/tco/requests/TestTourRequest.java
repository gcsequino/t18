package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestTourRequest {
    private TourRequest emptyTour;
    private TourRequest fullTour;

    @BeforeEach
    public void createConfigurationForTestCases() {
        emptyTour = new TourRequest();
        emptyTour.buildResponse();
        
    }

    @Test
    @DisplayName("Testing Empty Tour Constructor") 
    public void TestEmptyTour() {
        String requestType = emptyTour.getRequestType();
        assertEquals("tour", requestType);
    }

}
