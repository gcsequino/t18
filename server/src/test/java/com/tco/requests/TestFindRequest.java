package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFindRequest {

    // TODO testing not complete, pls add more
    
    private FindRequest find;
    private FindRequest findEmpty;
    private FindRequest findLimitZero;
    private FindRequest findEmptyString;

    @BeforeEach
    public void createConfigurationForTestCases() {
        find = new FindRequest("Dulles", 1);
        find.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"find\"")
    public void testType() {
        String type = find.getRequestType();
        assertEquals("find", type);
    }

    @Test
    @DisplayName("Check match string")
    public void testMatch() {
        String match = find.getMatch();
        assertEquals(match, "Dulles");
    }

    @Test
    @DisplayName("Check limit")
    public void testLimit() {
        int limit = find.getLimit();
        assertEquals(limit, 1);
    }

    @Test
    @DisplayName("Check found")
    public void testFound() {
        int found = find.getFound();
        assertEquals(found, 1);
    }

    @Test
    @DisplayName("Empty Find Request")
    public void testEmpty() {
        findEmpty = new FindRequest();
        findEmpty.buildResponse();
        assertEquals("find", findEmpty.getRequestType());
    }

    @Test
    @DisplayName("Find Limit Zero")
    public void testLimitZero() {
        findLimitZero = new FindRequest("Half Moon Bay", 0);
        findLimitZero.buildResponse();
        int limit = findLimitZero.getLimit();
        assertEquals(limit, 0);
    }

    @Test
    @DisplayName("Find With Empty String")
    public void testEmptyString() {
        findEmptyString = new FindRequest("", 100);
        findEmptyString.buildResponse();
        assertTrue(findEmptyString.getFound() > 50000);
    }
}
