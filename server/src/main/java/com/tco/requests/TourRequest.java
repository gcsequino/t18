package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.Tour;

public class TourRequest extends Request{


    private Places places;
    private double response;
    private double earthRadius;

    private final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    @Override
    public void buildResponse() {
        log.trace("Building tour response");
        try {
            if(response > 0) {
                Tour tour = new Tour(places, response, earthRadius);
                places = tour.nearestNeighbor();
            }
        } catch(Exception e) {
            log.error("Caught exception {} during tour optimization", e);
        }
        log.trace("buildResponse -> {}", this);
    }  

    public TourRequest() {
        this.requestType = "tour";
    }
}
