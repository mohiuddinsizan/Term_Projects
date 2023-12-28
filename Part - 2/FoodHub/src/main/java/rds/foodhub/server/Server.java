package rds.foodhub.server;

import rds.foodhub.dto.RestaurantLoginDTO;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public Server getServer()
    {
        return this;
    }
    private ServerSocket serverSocket;
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public HashMap<String, String> userMap;
    public HashMap<String,String> restaurantMap;

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Food> foods;

    public ArrayList<Restaurant> getRestaurants()
    {
        return restaurants;
    }

    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    Server() {
        userMap = new HashMap<>();
        userMap.put("sizan", "sizan");
        userMap.put("salman", "salman");
        userMap.put("shahrukh", "shahrukh");
        userMap.put("d", "d");
        userMap.put("e", "e");
        userMap.put("r","r");

        restaurantMap = new HashMap<>();

        restaurants = new ArrayList<>();
        foods = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\FoodHub\\src\\main\\resources\\rds\\foodhub\\restaurant.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double score = Double.parseDouble(tokens[2]);
                String price = tokens[3];
                String zipCode = tokens[4];

                ArrayList<String> categories = new ArrayList<>();
                for (int i = 5; i < tokens.length; i++) {
                    if (!tokens[i].isEmpty()) {
                        categories.add(tokens[i]);
                    }
                }

                Restaurant restaurant = new Restaurant(id, name, score, price, zipCode, categories);
                restaurants.add(restaurant);
                restaurantMap.put(name,String.valueOf(id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\FoodHub\\src\\main\\resources\\rds\\foodhub\\menu.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int restaurantId = Integer.parseInt(tokens[0]);
                String category = tokens[1];
                String name = tokens[2];
                double price = price = Double.parseDouble(tokens[3]);

                Food foodItem = new Food(restaurantId, category, name, price);
                foods.add(foodItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Files loaded.");

        try
        {
            serverSocket = new ServerSocket(33333);
            while (true) {
                System.out.println("Server is waiting for client...");
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
                System.out.println("Server accepted a client.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        ReadThreadServer read = new ReadThreadServer(userMap, socketWrapper, this,restaurantMap);
        Thread readerThread = new Thread(read);
        readerThread.start();
    }

    public static void main(String[] args) {
        new Server();
    }
}
