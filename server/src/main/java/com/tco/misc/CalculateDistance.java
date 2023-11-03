package com.tco.misc;

import com.tco.misc.Place;
import com.tco.misc.Places;
import java.lang.Math;
import java.util.ArrayList;

public class CalculateDistance {
    public static Long[] CalculateDistances(Places places, Double earthRadius){  

        ArrayList<Long> storeDistances = new ArrayList<>(); //Initialize arraylist
        try{

            if(places.size() < 1){
                Long[] emptyPlaces = {}; //No places, return empty distances
                return emptyPlaces;
            }
            else{
                for(int j = 0; j < places.size() - 1; j++){
                    storeDistances.add(distanceTwoPlaces(places.get(j), places.get(j + 1), earthRadius)); //Add distance to index of arraylist
                }
                storeDistances.add(distanceTwoPlaces(places.get(0), places.get(places.size() - 1), earthRadius)); //Last distance, last place in trip to first place

            }
        }

        catch (NullPointerException e){
            System.out.println(e);
        }

        Long[] distances = new Long[storeDistances.size()];
        for(int i = 0; i < distances.length; i++){
            distances[i] = storeDistances.get(i); //convert array list to array
        }

        if(places != null && places.size() == 1) {
            Long[] emptyDistances = {0L}; //One place, return distance of 0
            return emptyDistances;
        }
        return distances; //return distances array
    }

    private static Long distanceTwoPlaces(Place place1, Place place2, Double earthRadius){
        double lambda1 = Math.toRadians(Double.parseDouble(place1.get("longitude")));;
        double lambda2 = Math.toRadians(Double.parseDouble(place2.get("longitude")));;
        double diffLambda = Math.abs(lambda2 - lambda1); //we only use difflambda, but this code is more readable

        double phi1 = Math.toRadians(Double.parseDouble(place1.get("latitude")));;
        double phi2 = Math.toRadians(Double.parseDouble(place2.get("latitude")));
        
        //building both numerators
        double firstNumerator = Math.pow(Math.cos(phi2)*Math.sin(diffLambda),2);
        double secondNumerator = Math.pow(Math.cos(phi1)*Math.sin(phi2) - Math.sin(phi1)*Math.cos(phi2)*Math.cos(diffLambda), 2);
        
        //building both denominators
        double firstDenominator = Math.sin(phi1)*Math.sin(phi2); 
        double secondDenominator = Math.cos(phi1)*Math.cos(phi2)*Math.cos(diffLambda); 

        double centralAngle = Math.atan2(Math.sqrt(firstNumerator + secondNumerator), firstDenominator + secondDenominator); //All of the calculation here
        return (Long)Math.round(earthRadius * centralAngle); //return single distance for just two places
    }
}
