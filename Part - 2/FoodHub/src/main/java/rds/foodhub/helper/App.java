package rds.foodhub.helper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args)throws Exception {
        RestaurantManager restaurantmanager = new RestaurantManager();

        // file input 
        BufferedReader reader = new BufferedReader(new FileReader("restaurant.txt"));
        String line;
        while(true)
        {
            line = reader.readLine();
            if(line == null) break;
            String [] tokens = line.split(",");
            ArrayList<String> categories = new ArrayList<>();
                for (int i = 5; i < tokens.length; i++) {
                    if (!tokens[i].isEmpty()) {
                        categories.add(tokens[i]);
                    }
                }
                for(String x :  categories){
                    restaurantmanager.addCatagories(x);
                }
            restaurantmanager.addRestuarent(new Restaurant(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]), tokens[3], tokens[4],categories));
        }
        reader.close();
        reader = new BufferedReader(new FileReader("Menu.txt"));
        while(true){
            line = reader.readLine();
            if(line == null) break;
            String [] tokens2 = line.split(",");
            restaurantmanager.addFoodItem(Integer.parseInt(tokens2[0]),tokens2[1], tokens2[2],Double.parseDouble(tokens2[3]));
        }
        reader.close();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true){
            System.out.println("Main Menu : ");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");
            System.out.print("Enter Choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                for(;;){
                    System.out.println("Restaurant Searching Option :");
                    System.out.println("1) By Name");
                    System.out.println("2) By Score");
                    System.out.println("3) By Category");
                    System.out.println("4) By Price");
                    System.out.println("5) By Zip Code");
                    System.out.println("6) Different Category Wise List of Restaurants");
                    System.out.println("7) Back to Main Menu");
                    System.out.print("Enter Option : ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    if(option == 1){
                        System.out.print("Enter a name : ");
                        String name = scanner.nextLine();
                        ArrayList<Restaurant> RestaurantsWithSameName= restaurantmanager.searchRestuarentByName(name);
                        if(RestaurantsWithSameName!=null){
                            for(Restaurant x : RestaurantsWithSameName){
                                System.out.println("ID : "+x.getID());
                                System.out.println("Name : "+x.getRestaurantName());
                                System.out.println("Score : "+x.getScore());
                                System.out.println("Price : "+x.getPrice());
                                System.out.println("Zip Code : "+x.getZipCode());
                                System.out.println("Catagories : "+x.getCatString());
                            }
                        }
                        else{
                            System.out.println("No such restaurant with this name");
                        }
                    }
                    if(option == 2){
                        System.out.print("Enter Score range:");
                        double r1 = scanner.nextDouble();
                        double r2 = scanner.nextDouble();
                        scanner.nextLine();
                        ArrayList<Restaurant> RestaurantsWithSameScoreRange = restaurantmanager.searchRestuarentByScore(r1, r2);
                        if(RestaurantsWithSameScoreRange!=null){
                            for(Restaurant x : RestaurantsWithSameScoreRange){
                                System.out.println(x.getRestaurantName());
                            }
                        }
                        else{
                            System.out.println("No such restaurant with this score range");
                        }
                    }
                    if(option == 3){
                        System.out.print("Enter Catagory:");
                        String cat = scanner.nextLine();
                        ArrayList<Restaurant> RestaurantsWithSameCatagory = restaurantmanager.searchRestuarentByCatagory(cat);
                        if(RestaurantsWithSameCatagory!=null){
                            for(Restaurant x : RestaurantsWithSameCatagory){
                                System.out.println(x.getRestaurantName());
                            }
                        }
                        else{
                            System.out.println("No such restaurant with this catagory");
                        }
                    }
                    if(option == 4){
                        System.out.print("Enter Price : ");
                        String price = scanner.nextLine();
                        ArrayList<Restaurant> RestaurantsWithSamePrice = restaurantmanager.searchRestuarentByPrice(price);
                        if(RestaurantsWithSamePrice!=null){
                            for(Restaurant x : RestaurantsWithSamePrice){
                                System.out.println(x.getRestaurantName());
                            }
                        }
                        else{
                            System.out.println("No such restaurant with this price");
                        }
                    }
                    if(option == 5){
                        System.out.print("Enter Zip Code : ");
                        String zipCode = scanner.nextLine();
                        ArrayList<Restaurant> RestaurantsWithSameZipCode = restaurantmanager.searchByZipCode(zipCode);
                        if(RestaurantsWithSameZipCode!=null){
                            for(Restaurant x : RestaurantsWithSameZipCode){
                                System.out.println(x.getRestaurantName());
                            }
                        }
                        else{
                            System.out.println("No such restaurant with this zip code");
                        }
                    }
                    if(option == 6){
                        ArrayList <Restaurant> tempRestaurants = restaurantmanager.catagoryWiseRestuarent();
                        ArrayList<String> catagoryOfRestaurent = restaurantmanager.allCatagories();
                        if(catagoryOfRestaurent != null){
                            for(String x : catagoryOfRestaurent){
                                System.out.print(x+" : ");
                                int count = 0;
                                for(Restaurant y : tempRestaurants){
                                    if(y.containCatagory(x)){
                                        if(count==0){
                                            System.out.print(y.getRestaurantName());
                                            count++;
                                        }
                                        else System.out.print(","+y.getRestaurantName());
                                    }
                                }
                                System.out.println();
                            }
                        }
                        else{
                            System.out.println("No Restaurants !");
                        }
                    }
                    if(option == 7) break;
                }
            }

            if(choice == 2){
                for(;;){
                    System.out.println("Food Searching Option : ");
                    System.out.println("1) By Name");
                    System.out.println("2) By Name in a Given Restaurant");
                    System.out.println("3) By Category");
                    System.out.println("4) By Category in a Given Restaurant");
                    System.out.println("5) By Price Range");
                    System.out.println("6) By Price Range in a Given Restaurant");
                    System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                    System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                    System.out.println("9) Back to Main Menu");
                    System.out.print("Enter Option : ");
                    int option = scanner.nextInt();
                    ArrayList<Restaurant> allRests = restaurantmanager.allRestuarents();
                    scanner.nextLine();
                    if(option == 1){
                        System.out.print("Enter Food Name : ");
                        String foodName = scanner.nextLine();
                        ArrayList<Food> foods = restaurantmanager.searchFoodByName(foodName);
                        if(foods!=null){
                            for(Food x : foods){
                                for(Restaurant y : allRests){
                                    if(y.getID() == x.getRestaurantId()){
                                        System.out.print(y.getRestaurantName()+" : ");
                                    }
                                }
                                System.out.println(x.getFoodName());
                            }
                        }
                        else System.out.println("No such food item with this name");
                    }
                    if(option == 2){
                        System.out.print("Enter Restaurent Name : ");
                        String restaurentName = scanner.nextLine();
                        if(restaurantmanager.restuarentExistWithSameName(restaurentName)){
                            System.out.print("Enter Food Name : ");
                            String foodName = scanner.nextLine();
                            if(restaurantmanager.searchFoodByNameAndResaurent(restaurentName, foodName) != null){
                                for(Food x : restaurantmanager.searchFoodByNameAndResaurent(restaurentName, foodName)){
                                    System.out.println(x.getFoodName());
                                }
                            }
                            else System.out.println("No such food item with this name on the menu of this restaurant");
                        }
                        else System.out.println("No such restaurant with this name");
                    }
                    if(option == 3){
                        System.out.print("Enter Catagory of Food : ");
                        String foodCatagory = scanner.nextLine();
                        ArrayList<Food> foods = restaurantmanager.searchFoodByCatagory(foodCatagory);
                        if(foods!=null){
                            for(Food x : foods){
                                for(Restaurant y : allRests){
                                    if(y.getID() == x.getRestaurantId()){
                                        System.out.print(y.getRestaurantName()+" : ");
                                    }
                                }
                                System.out.println(x.getFoodName());
                            }
                        }
                        else System.out.println("No such food item with this category");
                    }
                    if(option == 4){
                        System.out.print("Enter Restaurant Name : ");
                        String RestaurantName = scanner.nextLine();
                        if(restaurantmanager.restuarentExistWithSameName(RestaurantName)){
                            System.out.print("Enter Catagory of Food : ");
                            String foodCatagory = scanner.nextLine();
                            ArrayList<Food> foods = restaurantmanager.searchFoodByCatagoryAndRestaurent(RestaurantName, foodCatagory);
                            if(foods != null ) {
                                for(Food x : foods){
                                    System.out.println(x.getFoodName());
                                }
                            }
                            else System.out.println("No such food item with this category on the menu of this restaurant");
                        }
                        else System.out.println("No such restaurant with this name");
                    }
                    if(option == 5){
                        System.out.print("Enter minimum Price : ");
                        double minPrice = scanner.nextDouble();
                        System.out.print("Enter maximum Price : ");
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine();
                        ArrayList<Food> foods = restaurantmanager.searchFoodByPrice(minPrice, maxPrice);
                        if(foods != null){
                            for(Food x : foods){
                                for(Restaurant y : allRests){
                                    if(y.getID() == x.getRestaurantId()){
                                        System.out.print(y.getRestaurantName()+" : ");
                                    }
                                }
                                System.out.println(x.getFoodName());
                            }
                        }
                        else System.out.println("No such food item with this price range");
                    }
                    if(option == 6){
                        System.out.print("Enter Restaurent Name : ");
                        String restaurentName = scanner.nextLine();
                        if(restaurantmanager.restuarentExistWithSameName(restaurentName)){
                            System.out.print("Enter minimum Price : ");
                            double minPrice = scanner.nextDouble();
                            System.out.print("Enter maximum Price : ");
                            double maxPrice = scanner.nextDouble();
                            scanner.nextLine();
                            ArrayList<Food> foods = restaurantmanager.searchFoodByPriceAndRestaurent(restaurentName, minPrice, maxPrice);
                            if(foods != null){
                                for(Food x : foods){
                                    System.out.println(x.getFoodName());
                                }
                            }
                            else System.out.println("No such fooditem with this price range on the menu of this restaurant");
                        }
                        else System.out.println("No such restaurant with this name");
                    }
                    if(option == 7){
                        System.out.print("Enter Name of the Restaurent : ");
                        String restaurentName = scanner.nextLine();
                        Food costliestFood = restaurantmanager.searchCostliestFood(restaurentName);
                        if(costliestFood != null) System.out.println(costliestFood.getFoodName());
                        else System.out.println("No Food in that restaurent ! ");
                    }
                    if(option == 8){
                        ArrayList<Food> allFoods = restaurantmanager.foodList();
                        ArrayList<Restaurant> allRestaurents = restaurantmanager.catagoryWiseRestuarent();
                        if(allRestaurents.size()<=0) break;
                        for(Restaurant x : allRestaurents){
                            int count = 0;
                            for(Food y : allFoods){
                                if(y.getRestaurantId() == x.getID()){
                                    count++;
                                }
                            }
                            System.out.println(x.getRestaurantName()+" : "+count);
                        }
                    }
                    if(option == 9) break ;
                }
            }

            if(choice == 3){
                System.out.print("Enter id : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter name : ");
                String name = scanner.nextLine();
                System.out.print("Enter score : ");
                double score = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("Enter price : ");
                String price = scanner.nextLine();
                System.out.print("Enter zip code : ");
                String zip_code = scanner.nextLine();
                ArrayList<String> categories = new ArrayList<String>();
                System.out.print("Enter First catagory : ");
                String catagory = scanner.nextLine();
                categories.add(catagory);
                System.out.print("Enter Second catagory : ");
                String catagory2 = scanner.nextLine();
                categories.add(catagory2);
                System.out.print("Enter Third catagory : ");
                String catagory3 = scanner.nextLine();
                categories.add(catagory3);
                /*
                if(catagory.equals("")){

                }
                else{
                    CatagoryHolder cat = new CatagoryHolder(catagory);
                    restaurantmanager.addCatagories(cat);
                }
                System.out.print("Enter Second catagory : ");
                String catagory2 = scanner.nextLine();
                if(catagory2.equals("")){

                }
                else{
                    CatagoryHolder cat2 = new CatagoryHolder(catagory2);
                    restaurantmanager.addCatagories(cat2);
                }
                System.out.print("Enter Third catagory : ");
                String catagory3 = scanner.nextLine();
                if(catagory3.equals("")){

                }
                else{
                    CatagoryHolder cat3 = new CatagoryHolder(catagory3);
                    restaurantmanager.addCatagories(cat3);
                }
                */
                if(!categories.isEmpty()) {
                    Restaurant Restaurant = new Restaurant(id, name, score, price, zip_code, categories);
                    if ((!restaurantmanager.restuarentExistWithSameName(name)) && (!restaurantmanager.restaurentExistWithSameID(id)))
                        restaurantmanager.addRestuarent(Restaurant);
                    else System.out.println("Restaurent with this name or id already exist !");
                }
                else System.out.println("You must enter atleast one category!");

            }

            if(choice == 4){
                System.out.print("Enter Restaurant Name : ");
                String RestaurantName = scanner.nextLine();
                if(restaurantmanager.restuarentExistWithSameName(RestaurantName)){
                    System.out.print("Enter Restaurant ID : ");
                    int restaurantID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Food's Catagory : ");
                    String foodCatagory = scanner.nextLine();
                    System.out.print("Enter Food's Name : ");
                    String foodName = scanner.nextLine();
                    System.out.print("Enter Food's Price : ");
                    double foodPrice = scanner.nextDouble();
                    scanner.nextLine();
                    if(restaurantmanager.compareNameAndIdOfRestaurent(RestaurantName, restaurantID)){
                        if(restaurantmanager.addFoodItem(restaurantID, foodCatagory, foodName, foodPrice)){
                            System.out.println("Food with this name already exist is the menu of this restaurent !");
                        }
                    }
                    else System.out.println("Entered restaurent name and id didn't match !");

                }
                else System.out.println("No such restaurant with this name ");
            }
            if(choice == 5)
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("restaurant.txt"));
                ArrayList<Restaurant> Restaurants = restaurantmanager.getRestuarents();
                for(Restaurant Restaurant : Restaurants)
                {
                    writer.write(Restaurant.getID() + "," + Restaurant.getRestaurantName() + "," + Restaurant.getScore() + "," + Restaurant.getPrice() + "," + Restaurant.getZipCode() + ",");
                    ArrayList<String> cats = Restaurant.getCategories();
                    for(String cat : cats)
                    {
                        if(Objects.equals(cat, "")) break;
                        writer.write(cat + ",");
                    }
                    writer.write("\n");
                }
                writer.close();

                writer = new BufferedWriter(new FileWriter("Menu.txt"));
                ArrayList <Food> foods = restaurantmanager.foodList();
                for(Food x : foods){
                    writer.write(x.getRestaurantId()+","+x.getCategory()+","+x.getFoodName()+","+x.getFoodPrice()+"\n");
                }
                writer.close();
                break;
            }
        }
    }
}
