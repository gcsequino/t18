export const VALID_CONFIG_RESPONSE = JSON.stringify({
    requestType: 'config',
    serverName: 't99',
    features: ['config']
});

export const INVALID_REQUEST = JSON.stringify({
    invalid: 'this is an invalid response to fail the schema'
});

export const MOCK_PLACES = [
    { name: 'Place A', lat: 40.0, lng: 50.0 },
    { name: 'Place B', lat: 45.0, lng: 55.0 }
];

export const REVERSE_GEOCODE_RESPONSE = JSON.stringify({
    "place_id": 259127396,
    "licence": "Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright",
    "osm_type": "relation",
    "osm_id": 8539568,
    "lat": "40.57066025",
    "lon": "-105.08539645568865",
    "place_rank": 30,
    "category": "amenity",
    "type": "university",
    "importance": 0.4948531325947546,
    "addresstype": "amenity",
    "name": "Colorado State University",
    "display_name": "Colorado State University, South College Avenue, Fort Collins, Larimer County, Colorado, 80525-1725, United States",
    "address": {
        "amenity": "Colorado State University",
        "road": "South College Avenue",
        "city": "Fort Collins",
        "county": "Larimer County",
        "state": "Colorado",
        "postcode": "80525-1725",
        "country": "United States",
        "country_code": "us"
    },
    "boundingbox": [
        "40.5527786",
        "40.5789122",
        "-105.0972937",
        "-105.0721817"
    ]
});

export const VALID_PLACES_ONE = 
[
    {
        "name": "Half Moon Bay Airport",
        "latitude": "37.513401031499995",
        "longitude": "-122.500999451",
        "altitude": "66",
        "type": "small_airport"
    }
];

export const VALID_FIND_RESPONSE_ONE = JSON.stringify({

        "match": "Half Moon Bay",
        "limit": 0,
        "found": 1,
        "places": [
            {
                "name": "Half Moon Bay Airport",
                "latitude": "37.513401031499995",
                "longitude": "-122.500999451",
                "altitude": "66",
                "type": "small_airport"
            }
        ],
        "requestType": "find"
    
});

export const VALID_FIND_RESPONSE_MULTI = JSON.stringify({

        "match": "panama",
        "limit": 0,
        "places": [
            {
                "latitude": "-13.76",
                "name": "Fazenda Panamá Airport",
                "longitude": "-58.74722"
            },
            {
                "latitude": "30.201273",
                "name": "Panama City Beach Airstrip",
                "longitude": "-85.817422"
            },
            {
                "latitude": "30.212099",
                "name": "Panama City-Bay Co International Airport",
                "longitude": "-85.6828"
            },
            {
                "latitude": "8.91479",
                "name": "Panama Pacific International Airport",
                "longitude": "-79.599602"
            }
        ],
        "found": 4,
        "requestType": "find"

})

export const VALID_TRIP_OPTIMIZATION = 
[
    {
      "latitude": "37.513401031499995",
      "name": "Half Moon Bay Airport, Yale Avenue, Princeton-by-the-Sea, San Mateo County, California, 94019, United States",
      "longitude": "-122.500999451"
    },
    {
      "latitude": "33.45836989641371",
      "name": "973, North 10th Street, Garfield, Central City, Phoenix, Maricopa County, Arizona, 85006, United States",
      "longitude": "-112.060546875"
    },
    {
      "latitude": "39.861698150635",
      "name": "Concourse C, East 91st Avenue, Denver, Denver County, Colorado, United States",
      "longitude": "-104.672996521"
    },
    {
      "latitude": "40.568089",
      "name": "821, West Lake Street, Fort Collins, Larimer County, Colorado, 80521, United States",
      "longitude": "-105.091554"
    },
  ]