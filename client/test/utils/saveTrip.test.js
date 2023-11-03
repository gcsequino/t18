import { describe, expect, it } from "@jest/globals";
import { buildTripCSV } from '../../src/utils/saveTrip'


describe('saveTrip', () => {

    const places = {
        "places": [
            {
                "latitude": "40.4319",
                "name": "Great Wall of China",
                "longitude": "115.5704"
            },
            {
                "latitude": "35.6586",
                "name": "Tokyo Tower",
                "longitude": "139.7454"
            },
            {
                "latitude": "48.8584",
                "name": "Eiffel Tower",
                "longitude": "2.2945"
            }
        ],
        "earthRadius": 6371,
        "distances": [
            2175,
            9719,
            8130
        ]
    };
    const csv_file = buildTripCSV(places);

    it('testing build trip CSV function', () => {
        expect(csv_file).toBeDefined();
    });
})