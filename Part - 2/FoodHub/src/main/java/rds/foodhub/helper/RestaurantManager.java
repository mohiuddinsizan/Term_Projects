package rds.foodhub.helper;
import java.util.ArrayList;
import java.util.Scanner;
public class RestaurantManager {
    private  ArrayList<Restaurant> restuarents = new ArrayList<>();
    private  ArrayList<String> catagories = new ArrayList<>();
    private  ArrayList<Food> foods = new ArrayList<>();
    public  ArrayList<Restaurant> getRestuarents() {
        return restuarents;
    }

    Scanner sc = new Scanner(System.in);

    public void addCatagories(String cat){
        for(String x : catagories){
            if(x.equals(cat)){
                return;
            }
        }
        catagories.add(cat);
    }

    public ArrayList<String> allCatagories(){
        if(catagories.size()<=0) return null;
        return catagories;
    }
    public ArrayList<Restaurant> allRestuarents(){
        return restuarents;
    }
    public ArrayList<Restaurant>  searchRestuarentByName(String name){
        ArrayList<Restaurant> returnRest = new ArrayList<>();
        for (Restaurant x : restuarents) {
            if (x.getRestaurantName().toLowerCase().contains(name.toLowerCase()) ) {
                returnRest.add(x);
            }
        }
        if(returnRest.isEmpty()) return null;
        else return returnRest;
    }
    public ArrayList<Restaurant> searchRestuarentByScore(double p1,double p2){
        ArrayList<Restaurant> returnRest = new ArrayList<>();
        for (Restaurant x : restuarents) {
            if (x.getScore() >= p1 && x.getScore() <= p2) {
                returnRest.add(x);
            }
        }
        if(returnRest.isEmpty())return null;
        else return returnRest;

    }
    public ArrayList<Restaurant> searchRestuarentByCatagory(String catagory){
        ArrayList<Restaurant> returnRest = new ArrayList<>();
        for (Restaurant x : restuarents) {
            if (x.containCatagory(catagory)) {
                returnRest.add(x);
            }
        }
        if(returnRest.isEmpty())return null;
        else return returnRest;
    }
    public ArrayList<Restaurant> searchRestuarentByPrice(String price) {
        ArrayList<Restaurant> returnRest = new ArrayList<>();
        for (Restaurant x : restuarents) {
            if (x.getPrice().length()<=price.length() && price.contains("$")) {
                returnRest.add(x);
            }
        }
        if(returnRest.isEmpty())return null;
        else return returnRest;
    }
    public ArrayList<Restaurant> searchByZipCode(String zipCode) {
        ArrayList<Restaurant> returnRest = new ArrayList<>();
        for (Restaurant x : restuarents) {
            if (x.getZipCode().equals(zipCode)) {
                returnRest.add(x);
            }
        }
        if(returnRest.isEmpty())return null;
        else return returnRest;
    }

    public ArrayList<Restaurant> catagoryWiseRestuarent(){
        if(restuarents.size()<= 0) return null;
        return restuarents;
    }

    // search food


    public ArrayList<Food> searchFoodByName(String name){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Food x: foods){
            if(x.getFoodName().toLowerCase().contains(name.toLowerCase())){
                returnFoods.add(x);
            }
        }
        if(returnFoods.isEmpty()) return null;
        else return returnFoods;
    }

    public ArrayList<Food> searchFoodByNameAndResaurent(String restaurentName,String foodName){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase())){
                for(Food y : foods){
                    if(y.getFoodName().toLowerCase().contains(foodName.toLowerCase())&& x.getID() == y.getRestaurantId()){
                        returnFoods.add(y);
                    }
                }
            }
        }
        if(returnFoods.isEmpty()) return null;

        else return returnFoods;
    }

    public ArrayList<Food> searchFoodByCatagory(String foodCatagory){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Food x : foods){
            if(x.getCategory().toLowerCase().contains(foodCatagory.toLowerCase())){
                returnFoods.add(x);
            }
        }
        if(returnFoods.isEmpty()) return null;

        else return returnFoods;
    }

    public ArrayList<Food> searchFoodByCatagoryAndRestaurent(String restaurentName,String foodCatagory){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase())){
                for(Food y : foods){
                    if(y.getCategory().toLowerCase().contains(foodCatagory.toLowerCase())){
                        returnFoods.add(y);
                    }
                }
            }
        }
        if(returnFoods.isEmpty()) return null;

        else return returnFoods;
    }

    public ArrayList<Food> searchFoodByPrice(double minPrice,double maxPrice){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Food x : foods){
            if(x.getFoodPrice()>=minPrice && x.getFoodPrice()<=maxPrice){
                returnFoods.add(x);
            }
        }
        if(returnFoods.size()<=0) return null;
        else return returnFoods;
    }

    public ArrayList<Food> searchFoodByPriceAndRestaurent(String restaurentName,double minPrice,double maxPrice){
        ArrayList<Food> returnFoods = new ArrayList<>();
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase())){
                for(Food y : foods){
                    if(y.getRestaurantId() == x.getID() && y.getFoodPrice()>=minPrice && y.getFoodPrice()<=maxPrice){
                        returnFoods.add(y);
                    }
                }
            }
        }
        if(returnFoods.isEmpty()) return null;

        else return returnFoods;
    }

    public Food searchCostliestFood(String restaurentName){
        Food costliestFood = null;
        double foodPrice = 0.0;
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase())){
                for(Food y : foods){
                    if(y.getFoodPrice()>=foodPrice && y.getRestaurantId()==x.getID()){
                        foodPrice = y.getFoodPrice();
                        costliestFood = y;
                    }
                }
            }
        }
        return costliestFood;
    }
    public void addRestuarent(Restaurant restuarent){
        restuarents.add(restuarent);
    }
    public boolean restuarentExistWithSameName(String name){
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public boolean restaurentExistWithSameID(int id){
        for(Restaurant x : restuarents){
            if(x.getID()==id){
                return true;
            }
        }
        return false;
    }
    // public void addFoodItem(String restaurentName,Food newFood){
    //     for(Restuarent x : restuarents){
    //         if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase()) ){
    //             if(x.getID() != newFood.getRestaurantID()){
    //                 System.out.println("The id with the restaurent name doesn't match");
    //                 return;
    //             }
    //             for(Food y : foods){
    //                 if( y.getRestaurantID() == x.getID() && y.getFoodName().toLowerCase().equals(newFood.getFoodName().toLowerCase()) && y.getCatagory().toLowerCase().equals(newFood.getCatagory().toLowerCase())){
    //                     System.out.println("This Food already exist in the menu of the restuarent");
    //                     return;
    //                 }
    //             }
    //         }
    //     }
    //     foods.add(newFood);
    // }
    public boolean addFoodItem(int restaurantID,String foodCatagory,String foodName,double foodPrice){
        for(Restaurant x : restuarents){
            if(x.getID() == restaurantID ){
                for(Food y : foods){
                    if(y.getRestaurantId() == restaurantID && y.getFoodName().toLowerCase().equals(foodName.toLowerCase()) && y.getCategory().toLowerCase().equals(foodCatagory.toLowerCase())){

                        return true;
                    }
                }
                Food food = new Food(restaurantID, foodCatagory, foodName, foodPrice);
                foods.add(food);
                break;
            }
        }
        return false;
    }
    public ArrayList<Food> foodList(){
        return foods;
    }
    public boolean compareNameAndIdOfRestaurent(String restaurentName,int id){
        for(Restaurant x : restuarents){
            if(x.getRestaurantName().toLowerCase().equals(restaurentName.toLowerCase()) && x.getID()==id){
                return true;
            }
        }
        return false;
    }
}
