package com.tco.misc;

import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.CalculateDistance;

public class Tour {

    final float timeBuffer = 500000000;

    public Places places;
    public double timeLimit;
    public double startTime;
    public int[] visited, unvisited, shortest;
    public double[][] distances;
    public double earthRadius;

    // Constructor
    public Tour(Places places, double time, double earthRadius) {
        this.places = places;
        this.timeLimit = calcTimeLimit(time);
        this.earthRadius = earthRadius;
        this.distances = new double[places.size()][places.size()];
        this.startTime = System.nanoTime();
        this.visited = new int[places.size()];
        this.unvisited = new int[places.size()];
        this.shortest = new int[places.size()];
        populateDistances();
        populateArrays();
    }

    // Calculate the timeLimit based on the input size
    public double calcTimeLimit(double time) {
        if(places.size() < 500) {
            return (time * 1E9) - timeBuffer;
        }
        else {
            return (time * 1E9) - (timeBuffer * (places.size()/250));
        }
    }

    // Populate unvisted, visited, and shortest with default values
    public void populateArrays() {
        for(int i = 0; i < places.size(); i++) {
            unvisited[i] = i;
            visited[i] = -1;
            shortest[i] = -1;
        }
    }

    // Calculate the distance between two indeces in the places data member
    public double calcDistance(int i, int j) {
        Places tempPlaces = new Places();
        tempPlaces.add(places.get(i));
        tempPlaces.add(places.get(j));
        Long[] dist = CalculateDistance.CalculateDistances(tempPlaces, this.earthRadius);
        return dist[0];
    }

    // Update the distances matrix with the distance between two indeces
    public void updateDistancesMatrix(int i, int j) {
        distances[i][j] = calcDistance(i,j);
        distances[j][i] = distances[i][j];
    }

    // Populate the distances matrix with distances between all places in the places list
    public void populateDistances() {
        int length = places.size();
        for(int i = 0; i < length; i++) {
            for(int j = i; j < length; j++) {
                if(!checkTime()) return;
                updateDistancesMatrix(i, j);
            }
        }
    }

    // Check if the execution time is under the timeLimit
    // return false if out of time
    // otherwise, return true
    public boolean checkTime() {
        double timeElapsed = System.nanoTime() - startTime;
        if(timeElapsed > timeLimit) {
            return false;
        }
        return true;
    }

    // Check that an array is valid for sorting
    public boolean isArrayValid(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == -1) return false;
        }
        return true;
    }

    // Index of an element in an array
    public int indexOf(int[] arr, int n) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == n) return i;
        }
        return -1;
    }

    // Check if an array still has valid cities
    public boolean hasCities(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != -1) return true;
        }
        return false;
    }

    // Find the first non -1 element of an array and return it
    public double firstValidDistance(int[] arr, int city) {
        double distance = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != -1) {
                distance = distances[city][i];
                break;
            }
        }
        return distance;
    }

    // Find the frist non -1 element of an array and return its index
    public int firstValidIndex(int[] arr) {
        int index = -1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != -1) {
                index = i;
                break;
            }
        }
        return index;
    }

    // find the closest unvisited city to the city provided
    public int findClosest(int city, int[] unvisited) {
        double distance = firstValidDistance(unvisited, city);
        int closest = firstValidIndex(unvisited);
        for(int i = 0; i < unvisited.length; i++) {
            if(indexOf(visited, i) == -1 && i != city && distances[city][i] < distance) {
                distance = distances[city][i];
                closest = i;
            }
        }
        return closest;
    }

    // Calculate the total distance of the given array
    public double totalDist(int[] arr) {
        double distance = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] == -1) return -1;
            distance += distances[arr[i]][arr[i+1]];
        }
        return distance;
    }

    // Update the shortest data member
    public void updateShortest() {
        if(totalDist(shortest) == -1) {
            shortest = visited.clone();
        }
        else if(totalDist(visited) < totalDist(shortest)) {
            shortest = visited.clone();
        }
    }

    // reset visisted and unvisited to default values
    public void cleanArrays() {
        for(int i = 0; i < visited.length; i++) {
            visited[i] = -1;
            unvisited[i] = i;
        }
    }

    // Sort the places array accorting to the given arr
    public void sort(int[] arr) {
        if(!isArrayValid(arr)) {
            return;
        }
        Places sortedPlaces = new Places();
        int start_index = indexOf(arr, 0);
        for(int i = start_index; i < arr.length + start_index; i++) {
            sortedPlaces.add(places.get(arr[i % arr.length]));
        }
        places = sortedPlaces;
    }

    // nearestNeighbor trip optimization
    public Places nearestNeighbor() {
        for(int i = 0; i < places.size(); i++) { // For each place in places
            if(!checkTime()) return places; // Check and see if we still have time to optimize
            cleanArrays(); // Return unvisited and visited to their default state
            int tourSize = 0; // How many cities are in the current tour
            visited[tourSize++] = i; // Add the ith city to the current tour
            unvisited[indexOf(unvisited, i)] = -1; // Remove the ith city from unvisited
            while(hasCities(unvisited)) { // Add the clostest city to the first city in the current tour
                if(!checkTime()) return places; // Check and see if we still have time to optimize
                int closest = findClosest(visited[tourSize-1], unvisited); // Find the closest city to the last city in our current tour
                visited[tourSize++] = closest; // Add it to the current tour
                unvisited[indexOf(unvisited, closest)] = -1; // Remove it from unvisited
            }
            updateShortest(); // Set the shortest array to the current tour if current is smaller
        }
        sort(shortest); // Sort places according to the shortest array
        return places;
    }
}