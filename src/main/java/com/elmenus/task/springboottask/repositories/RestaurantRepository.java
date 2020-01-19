package com.elmenus.task.springboottask.repositories;

import com.elmenus.task.springboottask.models.Restaurant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantRepository {

    private final String DATA_FILE = "sample-restaurant-data.json";

    public ArrayList<Restaurant> getRestaurants() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Restaurant> restaurants = null;
        try {
            restaurants = mapper.readValue(new File(DATA_FILE), new TypeReference<List<Restaurant>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public ArrayList<Restaurant> getOpenRestaurants(boolean closed) {
        ArrayList<Restaurant> restaurants = getRestaurants();
        if (!closed) {
            restaurants.removeIf(r -> r.getData().isClosed());
        } else {
            restaurants.removeIf(r -> !r.getData().isClosed());
        }
        return restaurants;
    }

    public boolean addNewRestaurant(Restaurant restaurant) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Restaurant> restaurants = getRestaurants();
        restaurants.add(restaurant);
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(DATA_FILE), restaurants);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateRestaurants(ArrayList<Restaurant> restaurants) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(DATA_FILE), restaurants);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateRestaurant(String uuid, Restaurant restaurant) {
        ArrayList<Restaurant> restaurants = getRestaurants();
        Restaurant r = restaurants.stream().filter(restaurant1 -> restaurant1.getUuid().equals(uuid)).findFirst().orElse(null);
        if (r == null)
            return false;

        int index = restaurants.indexOf(r);
        r = restaurant;
        restaurants.remove(index);
        restaurants.add(index, r);
        return updateRestaurants(restaurants);
    }
}
