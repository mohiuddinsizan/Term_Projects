package rds.foodhub.dto;

import javafx.scene.chart.PieChart;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;

public class DatabaseDTO implements Serializable {
    public ArrayList<Restaurant> restaurants;
    public ArrayList<Food> foods;

    public DatabaseDTO(ArrayList<Restaurant> restaurants, ArrayList<Food> foods)
    {
        this.restaurants = restaurants;
        this.foods = foods;
    }
    public void addNewRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }
}
