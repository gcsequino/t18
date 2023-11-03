import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, it } from '@jest/globals';


import { ItineraryActionsDropdown } from '../../../src/components/Trip/Itinerary/actions';

describe('rendering some modals', () => {

    const placeActions = {
        append: jest.fn()
    };

    beforeEach(() => {
        render(<ItineraryActionsDropdown placeActions={placeActions}/>)
    });

    it('test so it compiles', () => {
        expect(1 == '1');
    });
});
