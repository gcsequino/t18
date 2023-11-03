import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.tco.misc.Place;
import com.tco.misc.Places;

import com.tco.misc.Tour;
import com.tco.misc.CalculateDistance;
import com.tco.requests.FindRequest;

public class TestTour {

    public Places places = new Places();

    public Place newPlace(String name, String lat, String lng) {
        Place place = new Place();
        place.put("name", name);
        place.put("latitude", lat);
        place.put("longitude", lng);
        return place;
    }

    @BeforeEach
    public void populatePlaces() {
        places.add(newPlace("1", "0", "0"));
        places.add(newPlace("2", "0", "80"));
        places.add(newPlace("3", "0", "70"));
        places.add(newPlace("4", "0", "60"));
        places.add(newPlace("5", "0", "50"));
        places.add(newPlace("6", "0", "40"));
        places.add(newPlace("7", "0", "30"));
        places.add(newPlace("8", "0", "20"));
        places.add(newPlace("9", "0", "10"));
    }

    @Test
    @DisplayName("Testing distance matrix")
    public void distMatrixTest() {
        Tour tour = new Tour(places, 1, 3959);
        assertEquals(9, tour.distances.length);
    }

    @Test
    @DisplayName("Testing visited")
    public void visited() {
        Tour tour = new Tour(places, 1,  3959);
        int[] arr = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        assertArrayEquals(arr, tour.visited);
    }

    @Test
    @DisplayName("Testing unvisited")
    public void unvisited() {
        Tour tour = new Tour(places, 1, 3959);
        int[] arr = {0,1,2,3,4,5,6,7,8};
        assertArrayEquals(arr, tour.unvisited);
    }

    @Test
    @DisplayName("Testing shortest")
    public void shortest() {
        Tour tour = new Tour(places, 1, 3959);
        int[] arr = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        assertArrayEquals(arr, tour.shortest);
    }

    @Test
    @DisplayName("Testing getPlaces")
    public void places() {
        Tour tour = new Tour(places, 1, 3959);
        assertEquals(places, tour.places);
    }

    @Test
    @DisplayName("Testing getTimeLimit")
    public void timeLimit() {
        Tour tour = new Tour(places, 1, 3959);
        assertEquals(500000000, tour.timeLimit);
    }

    @Test
    @DisplayName("Testing getStartTime")
    public void startTime() {
        Tour tour = new Tour(places, 1, 3959);
        assert(tour.startTime > 0);
    }

    @Test
    @DisplayName("Testing getEarthRadius")
    public void earthRadius() {
        Tour tour = new Tour(places, 1, 3959);
        assertEquals(3959, tour.earthRadius);
    }

    @Test
    @DisplayName("Testing nearestNeighbor")
    public void nearestNeighbor() {
        Tour tour = new Tour(places, 1, 3959);
        Places optPlaces = tour.nearestNeighbor();
        assertEquals(9, optPlaces.size());
    }
}