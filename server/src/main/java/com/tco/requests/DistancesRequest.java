package com.tco.requests;

import com.tco.misc.CalculateDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.misc.Place;
import com.tco.misc.Places;

public class DistancesRequest extends Request{


    private Places places;
    private Double earthRadius = 3959.0;
    private Long[] distances;
    
    private final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    @Override
    public void buildResponse() {
        log.trace("building a response");
        distances = CalculateDistance.CalculateDistances(places, earthRadius);
        log.trace("buildResponse -> {}", this);
    }

    // The following methods exist only for testing purposes
    // and are not used during normal execution, including the constructor

    public DistancesRequest() {
        this.requestType = "distances";
    }

    public DistancesRequest(Double earthRadius, Places places) {
        this.requestType = "distances";
        this.earthRadius = earthRadius;
        this.places = places;
    }

    public Long[] getDistances() {
        return distances;
    }

    public Double getEarthRadius(){
        return earthRadius;
    }

    public Places getPlaces() {
        return places;
    }
}
