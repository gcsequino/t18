package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.database.*;
import com.tco.misc.Place;
import com.tco.misc.Places;

public class FindRequest extends Request{

    private String match;
    private Integer limit;
    private Integer found;
    private Places places;
    private final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    @Override
    public void buildResponse() {
        // Database setup
        try {
            Credential db = new Credential();
            String sql = Find.match(match, limit);
            String count = Find.count(match);
            places = Database.query(sql, db);
            found = Database.queryCount(count, db);
            //System.out.println("QUERY COUNT: " + found);
        } catch(Exception e) {
            log.error("Caught exception {} during db access", e);
        }
        
        log.trace("buildResponse -> {}", this);
    }

    // The following methods exist only for testing purposes
    // and are not used during normal execution, including the constructor.

    public FindRequest() {
        this.requestType = "find";
    }

    public FindRequest(String match, Integer limit){
        this.requestType = "find";
        this.match = match;
        this.limit = limit;
    }

    public String getMatch() {
        return match;
    }

    public int getLimit() {
        return limit;
    }

    public int getFound() {
        return found;
    }

    public Places getPlaces() {
        return places;
    }
}
