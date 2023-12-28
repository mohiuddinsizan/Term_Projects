//package rds.foodhub.helper;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//public class RestaurantDatabaseSystem {
//
//    public static void main(String[] args) {
//        RestaurantManager restaurantManager = new RestaurantManager();
//        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\FoodHub\\src\\main\\resources\\restaurant.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] tokens = line.split(",");
//                int id = Integer.parseInt(tokens[0]);
//                String name = tokens[1];
//                double score = Double.parseDouble(tokens[2]);
//                String price = tokens[3];
//                String zipCode = tokens[4];
//
//                ArrayList<String> categories = new ArrayList<>();
//                for (int i = 5; i < tokens.length; i++) {
//                    if (!tokens[i].isEmpty()) {
//                        categories.add(tokens[i]);
//                    }
//                }
//
//                Restaurant restaurant = new Restaurant(id, name, score, price, zipCode, categories);
//                restaurantManager.addRestaurant(restaurant);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\FoodHub\\src\\main\\resources\\menu.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] tokens = line.split(",");
//                int restaurantId = Integer.parseInt(tokens[0]);
//                String category = tokens[1];
//                String name = tokens[2];
//                double price = Double.parseDouble(tokens[3]);
//
//                Food foodItem = new Food(restaurantId, category, name, price);
//                restaurantManager.addFoodItem(foodItem);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Scanner sc = new Scanner(System.in);
//        int choice;
//        do {
//            System.out.println("Main Menu:");
//            System.out.println("1) Search Restaurants");
//            System.out.println("2) Search Food Items");
//            System.out.println("3) Add Restaurant");
//            System.out.println("4) Add Food Item to the Menu");
//            System.out.println("5) Exit System");
//            choice = sc.nextInt();
//            sc.nextLine();
//            switch (choice) {
//                case 1 -> {
//                    System.out.println("Restaurant Searching Options:");
//                    System.out.println("1) By Name");
//                    System.out.println("2) By Score");
//                    System.out.println("3) By Category");
//                    System.out.println("4) By Price");
//                    System.out.println("5) By Zip Code");
//                    System.out.println("6) Different Category Wise List of Restaurants");
//                    System.out.println("7) Back to Main Menu");
//
//                    int x = sc.nextInt();
//                    sc.nextLine();
//                    while (x != 7) {
//                        switch (x) {
//                            case 1 -> {
//                                System.out.print("Enter restaurant name: ");
//                                String name = sc.nextLine();
//                                ArrayList<Restaurant> restaurantList = restaurantManager.searchRestaurantsByName(name);
//                                if (restaurantList.size() == 0) {
//                                    System.out.println("No restaurants found.");
//                                    continue;
//                                } else {
//                                    for (Restaurant res : restaurantList) {
//                                        res.displayInfo();
//                                    }
//                                }
//                            }
//                            case 2 -> {
//                                System.out.print("Enter score range (min max): ");
//                                double minScore = sc.nextDouble();
//                                sc.nextLine();
//                                double maxScore = sc.nextDouble();
//                                sc.nextLine();
//
//                                List<Restaurant> restaurants = restaurantManager.searchRestaurantsByScoreRange(minScore, maxScore);
//                                if (restaurants == null) {
//                                    System.out.println("No restaurants found.");
//                                    continue;
//                                } else {
//                                    for (Restaurant res : restaurants) {
//                                        res.displayInfo();
//                                    }
//                                }
//                            }
//                            case 3 -> {
//                                System.out.print("Enter category: ");
//                                String category = sc.nextLine();
//                                List<Restaurant> matchingRestaurants = restaurantManager.searchRestaurantsByCategory(category);
//                                if (matchingRestaurants == null) {
//                                    System.out.println("No restaurants found.");
//                                } else {
//                                    for (Restaurant res : matchingRestaurants) {
//                                        res.displayInfo();
//                                    }
//                                }
//                            }
//                            case 4 -> {
//                                System.out.print("Enter price: ");
//                                String price = sc.nextLine();
//
//                                List<Restaurant> restaurants = restaurantManager.searchRestaurantsByPriceRange(price);
//                                if (restaurants == null) {
//                                    System.out.println("No restaurants found.");
//                                } else {
//                                    for (Restaurant res : restaurants) {
//                                        res.displayInfo();
//                                    }
//                                }
//                            }
//                            case 5 -> {
//                                System.out.print("Enter zip code: ");
//                                String zipCode = sc.nextLine();
//
//                                List<Restaurant> restaurants = restaurantManager.searchRestaurantsByZipCode(zipCode);
//                                if (restaurants == null) {
//                                    System.out.println("No restaurants found.");
//                                } else {
//                                    for (Restaurant res : restaurants) {
//                                        res.displayInfo();
//                                    }
//                                }
//                            }
//                            case 6 -> {
//                                // print category wise restaurants list using getCategoryWiseRestaurantList which returns a map
//                                Map<String, List<String>> categoryWiseRestaurantList = restaurantManager.getCategoryWiseRestaurantList();
//                                for (Map.Entry<String, List<String>> entry : categoryWiseRestaurantList.entrySet()) {
//                                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                                }
//                            }
//                            default -> {
//                                System.out.print("Invalid option. Please select again: ");
//                                x = sc.nextInt();
//                                sc.nextLine();
//                            }
//                        }
//                        break;
//                    }
//                }
//                case 2 -> {
//                    System.out.println("Food Item Searching Options:");
//                    System.out.println("1) By Name");
//                    System.out.println("2) By Name in a Given Restaurant");
//                    System.out.println("3) By Category");
//                    System.out.println("4) By Category in a Given Restaurant");
//                    System.out.println("5) By Price Range");
//                    System.out.println("6) By Price Range in a Given Restaurant");
//                    System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
//                    System.out.println("8) List of Restaurants and Total Food Items on the Menu");
//                    System.out.println("9) Back to Main Menu");
//                    int option = sc.nextInt();
//                    sc.nextLine(); // Consume the newline character
//                    switch (option) {
//                        case 1 -> {
//                            System.out.print("Enter food name: ");
//                            String foodName = sc.nextLine();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemsByName(foodName);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 2 -> {
//                            System.out.print("Enter food name: ");
//                            String foodName = sc.nextLine();
//                            System.out.print("Enter restaurant name: ");
//                            String restaurantName = sc.nextLine();
//                            int restaurantId = restaurantManager.getRestaurantByName(restaurantName).getId();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemsByNameInRestaurant(foodName, restaurantId);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 3 -> {
//                            System.out.print("Enter category: ");
//                            String category = sc.nextLine();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemsByCategory(category);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 4 -> {
//                            System.out.print("Enter category: ");
//                            String category = sc.nextLine();
//                            System.out.print("Enter restaurant name: ");
//                            String restaurantName = sc.nextLine();
//                            int restaurantId = restaurantManager.getRestaurantByName(restaurantName).getId();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemByCategoryInARestaurantId(category, restaurantId);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 5 -> {
//                            System.out.print("Enter price range (min max): ");
//                            double minPrice = sc.nextDouble();
//                            sc.nextLine();
//                            double maxPrice = sc.nextDouble();
//                            sc.nextLine();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemsByPriceRange(minPrice, maxPrice);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 6 -> {
//                            System.out.print("Enter price range (min max): ");
//                            double minPrice = sc.nextDouble();
//                            sc.nextLine();
//                            double maxPrice = sc.nextDouble();
//                            sc.nextLine();
//                            System.out.print("Enter restaurant name: ");
//                            String restaurantName = sc.nextLine();
//                            int restaurantId = restaurantManager.getRestaurantByName(restaurantName).getId();
//
//                            List<Food> matchingFoodItems = restaurantManager.searchFoodItemsByPriceRangeInRestaurant(minPrice, maxPrice, restaurantId);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 7 -> {
//                            System.out.print("Enter restaurant name: ");
//                            String restaurantName = sc.nextLine();
//                            int restaurantId = restaurantManager.getRestaurantByName(restaurantName).getId();
//
//                            List<Food> matchingFoodItems = restaurantManager.getCostliestFoodItemsInRestaurant(restaurantId);
//                            if (matchingFoodItems == null) {
//                                System.out.println("No food items found.");
//                            } else {
//                                for (Food food : matchingFoodItems) {
//                                    food.displayInfo();
//                                }
//                            }
//                        }
//                        case 8 -> {
//                            for (Restaurant restaurant : restaurantManager.getRestaurants()) {
//                                System.out.println(restaurant.getName() + ": " + restaurantManager.getRestaurantIdWiseFoodCount(restaurant.getId()));
//                            }
//                        }
//                        default -> System.out.println("Invalid option. Please select again.");
//                    }
//                }
//                case 3 -> {
//                    System.out.println("Adding a New Restaurant");
//                    System.out.print("Enter the id of the restuarant: ");
//                    int p = sc.nextInt();
//                    sc.nextLine(); // Consume newline character
//                    System.out.print("Enter restaurant name: ");
//                    String name = sc.nextLine();
//                    System.out.print("Enter score: ");
//                    double score = sc.nextDouble();
//                    sc.nextLine();
//                    System.out.print("Enter price ($, $$, or $$$): ");
//                    String price = sc.nextLine();
//                    System.out.print("Enter zip code: ");
//                    String zipCode = sc.nextLine();
//                    System.out.print("Enter categories (up to three, separated by commas): ");
//                    String categoriesInput = sc.nextLine();
//                    String[] categories = categoriesInput.split(",");
//
//                    List<String> categoryList = new ArrayList<>(Arrays.asList(categories));
//                    if (categoryList.isEmpty() || categoryList.size() > 3) {
//                        System.out.println("Invalid number of categories. A restaurant must have 1 to 3 categories.");
//                    }
//                    Restaurant r1 = new Restaurant(p, name, score, price, zipCode, categoryList);
//                    restaurantManager.addRestaurant(r1);
//                    //System.out.println("Restaurant added successfully!");
//                }
//                case 4 -> {
//                    System.out.print("Enter the name of the food item:");
//                    String foodName = sc.nextLine();
//                    System.out.print("Enter the category of the food item:");
//                    String foodCategory = sc.nextLine();
//                    System.out.print("Enter the price of the food item:");
//                    double foodPrice = sc.nextDouble();
//                    sc.nextLine(); // Consume newline character
//                    System.out.print("Enter the restaurant name where you want to add the food item:");
//                    String restaurantName = sc.nextLine();
//
//                    // Check if the restaurant exists
//                    Restaurant restaurant = restaurantManager.getRestaurantByName(restaurantName);
//                    if (restaurant == null) {
//                        System.out.println("Restaurant not found.");
//                        continue;
//                    }
//                    Food newFoodItem = new Food(restaurant.getId(), foodCategory, foodName, foodPrice);
//                    restaurantManager.addFoodItem(newFoodItem);
//                    System.out.println("Food item added to the menu successfully.");
//                }
//                case 5 -> {
//                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Resturant\\Resturant\\restaurant.txt"))) {
//                        List<Restaurant> restuarents = restaurantManager.getRestaurants();
//                        for (Restaurant restuarent : restuarents) {
//                            writer.write(restuarent.getId() + "," + restuarent.getName() + "," + restuarent.getScore() + "," + restuarent.getPrice() + "," + restuarent.getZip_code() + ",");
//                            List<String> cats = restuarent.getCategories();
//                            for (String cat : cats) {
//                                if (cat == "") break;
//                                writer.write(cat + ",");
//                            }
//                            writer.write("\n");
//                        }
//                        System.out.println("Restaurant data saved.");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("Error saving restaurant data.");
//                    }
//
//                    // Save food data to "Menu.txt"
//                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Resturant\\Resturant\\menu.txt"))) {
//                        List<Food> foods = restaurantManager.getFoodItems(); // Change this line if needed
//                        for (Food x : foods) {
//                            writer.write(x.getRestaurantId() + "," + String.join(",", x.getCategory()) + "," + x.getName() + "," + x.getPrice() + "\n");
//                        }
//                        System.out.println("Food data saved.");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("Error saving food data.");
//                    }
//
//                    System.out.println("Data saved. Goodbye!");
//                    System.exit(0);
//                }
//            }
//        } while (choice != 5);
//        sc.close();
//    }
//}
//
