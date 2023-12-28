//package rds.foodhub.helper;
//
//import java.util.*;
//
//public class RestaurantManager {
//
//  private List<Restaurant> restaurants;
//  private List<Food> foodItems;
//
//  public RestaurantManager(List<Restaurant> restaurants, List<Food> food) {
//    this.restaurants = restaurants;
//    this.foodItems = food;
//  }
//
//  public RestaurantManager() {
//    restaurants = new ArrayList<>();
//    foodItems = new ArrayList<>();
//  }
//
//  public List<Restaurant> getRestaurants() {
//    return restaurants;
//  }
//
//  public Restaurant searchRestaurantsByExactName(String restaurantName) {
//    for (Restaurant res : restaurants) {
//      if (res.getName().equalsIgnoreCase(restaurantName)) {
//        return res;
//      }
//    }
//    return null;
//  }
//
//  public ArrayList<Restaurant> searchRestaurantsByName(String restaurantName) {
//    ArrayList<Restaurant> temp = new ArrayList<>();
//    for (Restaurant res : restaurants) {
//      if (res.getName().toLowerCase().contains(restaurantName.toLowerCase())) {
//        temp.add(res);
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Restaurant> searchRestaurantsByScoreRange(
//    double minScore,
//    double maxScore
//  ) {
//    double x = minScore;
//    double y = maxScore;
//    double maxi = Math.max(x, y);
//    double mini = Math.min(x, y);
//    List<Restaurant> temp = new ArrayList<>();
//    for (Restaurant res : restaurants) {
//      if (res.getScore() >= mini && res.getScore() <= maxi) {
//        temp.add(res);
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Restaurant> searchRestaurantsByCategory(String category) {
//    List<Restaurant> matchingRestaurants = new ArrayList<>();
//    for (Restaurant restaurant : restaurants) {
//      List<String> categories = restaurant.getCategories();
//      for (String cat : categories) {
//        if (cat.equalsIgnoreCase(category)) {
//          matchingRestaurants.add(restaurant);
//          break; ///we dont need to iterate through
//        }
//      }
//    }
//    if (!matchingRestaurants.isEmpty()) {
//      return matchingRestaurants;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Restaurant> searchRestaurantsByPriceRange(String priceRange) {
//    List<Restaurant> temp = new ArrayList<>(0);
//    for (Restaurant i : restaurants) {
//      if (i.getPrice().equalsIgnoreCase(priceRange)) {
//        temp.add(i);
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Restaurant> searchRestaurantsByZipCode(String zipCode) {
//    List<Restaurant> temp = new ArrayList<>(0);
//    for (Restaurant i : restaurants) {
//      if (i.getZip_code().equals(zipCode)) {
//        temp.add(i);
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public boolean valid(Restaurant res) {
//    for (Restaurant i : restaurants) {
//      if (
//        i.getName().equalsIgnoreCase(res.getName()) &&
//        i.getId() == res.getId() &&
//        i.getZip_code().equalsIgnoreCase(res.getZip_code())
//      ) {
//        return false;
//      }
//    }
//    return true;
//  }
//
//  public void addRestaurant(Restaurant restaurant) {
//    if (valid(restaurant)) {
//      restaurants.add(restaurant);
//      System.out.println("Restaurant added successfully!");
//    }
//  }
//
//  public Restaurant getRestaurantById(int restaurantId) {
//    for (Restaurant restaurant : restaurants) {
//      if (restaurant.getId() == restaurantId) {
//        return restaurant;
//      }
//    }
//    return null;
//  }
//
//  public Restaurant getRestaurantByName(String resName) {
//    for (Restaurant restaurant : restaurants) {
//      if (restaurant.getName().equalsIgnoreCase(resName)) {
//        return restaurant;
//      }
//    }
//    return null;
//  }
//
//  Map<String, List<String>> getCategoryWiseRestaurantList() {
//    Map<String, List<String>> temp = new HashMap<>();
//    for (Restaurant i : restaurants) {
//      for (String j : i.getCategories()) {
//        if (temp.containsKey(j)) {
//          temp.get(j).add(i.getName());
//        } else {
//          List<String> temp2 = new ArrayList<>();
//          temp2.add(i.getName());
//          temp.put(j, temp2);
//        }
//      }
//    }
//    return temp;
//  }
//
//  public List<Food> searchFoodItemsByName(String foodItemName) {
//    //option 1
//    List<Food> temp = new ArrayList<>();
//    for (Food food : foodItems) {
//      if (food.getName().toLowerCase().contains(foodItemName.toLowerCase())) {
//        temp.add(food);
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Food> searchFoodItemsByNameInRestaurant(
//    String foodItemName,
//    int restaurantId
//  ) {
//    ///option 2
//    int flag = -1;
//    List<Food> temp = new ArrayList<>(0);
//    for (Food food : foodItems) {
//      if (
//        food.getName().equalsIgnoreCase(foodItemName) &&
//        food.getRestaurantId() == restaurantId
//      ) {
//        temp.add(food);
//        flag = 1;
//      }
//    }
//    if (flag == 1) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Food> searchFoodItemsByCategory(String category) {
//    ///option 3
//    List<Food> matchingFoodItems = new ArrayList<>();
//    for (Food food : foodItems) {
//      if (food.getCategory().equalsIgnoreCase(category)) {
//        matchingFoodItems.add(food);
//      }
//    }
//    if (!matchingFoodItems.isEmpty()) {
//      return matchingFoodItems;
//    } else {
//      return null;
//    }
//  }
//
//  public ArrayList<Food> searchFoodItemByCategoryInARestaurantId(
//    String category,
//    int restaurantId
//  ) {
//    ///option 4
//    ArrayList<Food> matchingFoodItems = new ArrayList<>();
//    for (Food food : foodItems) {
//      if (
//        food.getCategory().equalsIgnoreCase(category) &&
//        food.getRestaurantId() == restaurantId
//      ) {
//        matchingFoodItems.add(food);
//      }
//    }
//    if (!matchingFoodItems.isEmpty()) {
//      return matchingFoodItems;
//    } else {
//      return null;
//    }
//  }
//
//  List<Food> searchFoodItemsByPriceRange(double minPrice, double maxPrice) {
//    ////option 5
//    double x = minPrice;
//    double y = maxPrice;
//    double mini = Math.min(x, y);
//    double maxi = Math.max(x, y);
//    List<Food> temp = new ArrayList<>(0);
//    int flag = -1;
//    for (Food food : foodItems) {
//      if (food.getPrice() >= mini && food.getPrice() <= maxi) {
//        temp.add(food);
//        flag = 1;
//      }
//    }
//    if (flag == 1) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Food> searchFoodItemsByPriceRangeInRestaurant(
//    double minPrice,
//    double maxPrice,
//    int restaurantId
//  ) {
//    ////option 6
//    ///will have to check if there is a resturant in this name and if the restuarntname has then return the list
//    List<Food> matchingFoodItems = new ArrayList<>();
//    for (Food food : foodItems) {
//      if (
//        food.getPrice() >= minPrice &&
//        food.getPrice() <= maxPrice &&
//        food.getRestaurantId() == restaurantId
//      ) {
//        matchingFoodItems.add(food);
//      }
//    }
//    if (!matchingFoodItems.isEmpty()) {
//      return matchingFoodItems;
//    } else {
//      return null;
//    }
//  }
//
//  public List<Food> getCostliestFoodItemsInRestaurant(int restaurantId) {
//    ///option 7
//    List<Food> temp = new ArrayList<>();
//    double max = 0;
//    for (Food food : foodItems) {
//      if (food.getRestaurantId() == restaurantId) {
//        if (food.getPrice() > max) {
//          max = food.getPrice();
//        }
//      }
//    }
//    for (Food food : foodItems) {
//      if (food.getRestaurantId() == restaurantId) {
//        if (food.getPrice() == max) {
//          temp.add(food);
//        }
//      }
//    }
//    if (!temp.isEmpty()) {
//      return temp;
//    } else {
//      return null;
//    }
//  }
//
//  public Integer getRestaurantIdWiseFoodCount(Integer restaurantId) {
//    int count = 0;
//    for (Food food : foodItems) {
//      if (food.getRestaurantId() == restaurantId) {
//        count++;
//      }
//    }
//    return count;
//  }
//
//  public Integer getRestaurantNameWiseFoodCount(
//    RestaurantManager restaurantManager,
//    String restaurantName
//  ) {
//    int count = 0;
//    for (Food food : foodItems) {
//      if (
//        food.getRestaurantId() ==
//        restaurantManager.getRestaurantByName(restaurantName).getId()
//      ) {
//        count++;
//      }
//    }
//    return count;
//  }
//
//  public boolean valid(Food e) {
//    for (Food i : foodItems) {
//      if (
//        (i.getName().equalsIgnoreCase(e.getName())) &&
//        (i.getCategory().equalsIgnoreCase(e.getCategory())) &&
//        (i.getPrice() == e.getPrice()) &&
//        (i.getRestaurantId() == e.getRestaurantId())
//      ) {
//        return false;
//      }
//    }
//    return true;
//  }
//
//  public void addFoodItem(Food foodItem) {
//    if (valid(foodItem)) {
//      foodItems.add(foodItem);
//    }
//  }
//
//  public List<Food> getFoodItems() {
//    return new ArrayList<>(foodItems);
//  }
//}
