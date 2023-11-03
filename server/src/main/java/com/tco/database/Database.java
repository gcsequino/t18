package com.tco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.tco.misc.Places;
import com.tco.misc.Place;
import java.util.NoSuchElementException;

public class Database {

    public static Places query(String sql, Credential db) throws Exception {
      try (
          // connect to the database and query
          Connection conn = DriverManager.getConnection(db.url(), db.USER, db.PASSWORD);
          Statement query = conn.createStatement();
          ResultSet results = query.executeQuery(sql)
      ) {
        return convertQueryResultsToPlaces(results);
      } catch (Exception e) {
        throw e;
      }
    }
  
    public static Places convertQueryResultsToPlaces(ResultSet results) throws Exception {
      String[] columns = {"world.name", "world.latitude", "world.longitude", "world.id", "world.altitude",
                          "world.municipality", "world.type", "region.name", "country.name", "world.wikipedia_link"};         
      String[] lables = {"name", "latitude", "longitude", "id", "altitude", "municipality", "type", "region", "country", "wikipedia_link"};

      Places places = new Places();
      while(results.next()){
        Place place = new Place();
        for(int i = 0; i < columns.length; i++){
          place.put(lables[i], results.getString(columns[i]));
        }
        places.add(place);
      }

      return places;
    }

    public static int queryCount(String sql, Credential db) throws Exception {
      try (
          // connect to the database and query
          Connection conn = DriverManager.getConnection(db.url(), db.USER, db.PASSWORD);
          Statement query = conn.createStatement();
          ResultSet results = query.executeQuery(sql)
      ) {
        return convertQueryResultsToInt(results);
      } catch (Exception e) {
        throw e;
      }
    }

    public static Integer convertQueryResultsToInt(ResultSet results) throws Exception {
      if(results.next()) {
        return results.getInt(1);
      }
      else {
        throw new NoSuchElementException();
      }
    }
}
