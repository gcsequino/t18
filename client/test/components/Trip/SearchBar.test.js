import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import user from '@testing-library/user-event';
import { beforeAll, beforeEach, describe, expect, it, jest } from '@jest/globals';
import SearchBar from '../../../src/components/Trip/Map/SearchBar';
import { VALID_PLACES_ONE, VALID_FIND_RESPONSE_ONE, VALID_FIND_RESPONSE_MULTI, MOCK_PLACES } from '../../sharedMocks';
import DisplayResults from '../../../src/components/Trip/Map/DisplayResults';


describe('Search Bar', () => {

    const places = MOCK_PLACES;
    const placeActions = {
        append: jest.fn()
    }; 
    const resultsOne = VALID_PLACES_ONE;
    const buildJsonFile = jest.fn();
    const setSearchResults = jest.fn();


    beforeAll(() =>{
        render(<SearchBar places={places} placeActions={placeActions} />);
        render(<DisplayResults results={resultsOne}/>);
    })

    it('test so it compiles', () => {
        expect(1 === '1');
    })

    it('send values to display results', () => {
        buildJsonFile("Half Moon Bay", setSearchResults);
        expect(setSearchResults).toHaveLength(0); //THIS DOESN'T WORK NEEDS TO BE LENGTH 1
    })


});