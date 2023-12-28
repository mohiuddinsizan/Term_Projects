package rds.foodhub.server;

import rds.foodhub.dto.DatabaseDTO;
import rds.foodhub.dto.LoginDTO;
import rds.foodhub.dto.RestaurantLoginDTO;
import rds.foodhub.helper.Restaurant;
import rds.foodhub.helper.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadThreadServer extends Thread {
    private final SocketWrapper socketWrapper;
    public HashMap<String, String> userMap;
    public HashMap<String, String> restaurantMap;
    Server server;


    public ReadThreadServer(HashMap<String, String> map, SocketWrapper socketWrapper, Server server,HashMap<String, String> restaurantMap) {
        this.server = server;
        this.userMap = map;
        this.socketWrapper = socketWrapper;
        this.restaurantMap = restaurantMap;
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();  // blocked
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        System.out.println("hello");
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());

                        System.out.println("Received client login request. Name : " + loginDTO.getUserName() + " pass : " + password);
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO); // not blocking

                        if(loginDTO.isStatus())
                        {
                            ArrayList<Restaurant> restaurants = server.getRestaurants();
                            ArrayList<Food> foods = server.getFoods();

                            DatabaseDTO databaseDTO = new DatabaseDTO(restaurants, foods);
                            socketWrapper.write(databaseDTO);
                        }
                    }
                    else if(o instanceof RestaurantLoginDTO)
                    {
                        RestaurantLoginDTO restaurantLoginDTO = (RestaurantLoginDTO) o;
                        //Integer.parseInt(restaurantLoginDTO.getPassword()) == Integer.parseInt(restaurantLoginDTO.getUserName()));
                        String password = restaurantMap.get(restaurantLoginDTO.getUserName());
                        restaurantLoginDTO.setStatus(password.equals(restaurantLoginDTO.getPassword()));
                        System.out.println("Received restaurant login request. Name : " + restaurantLoginDTO.getUserName() + " pass : " + restaurantLoginDTO.getPassword());

                        socketWrapper.write(restaurantLoginDTO);// not blocking

                        if(restaurantLoginDTO.isStatus())
                        {
                            System.out.println("Access given");
                            ArrayList<Food> foods = server.getFoods();

                            ArrayList<Food> selectedFoods = new ArrayList<Food>();
                            for (Food food : foods)
                            {
                                if(food.getRestaurantId() == restaurantLoginDTO.getID())
                                {
                                    selectedFoods.add(food);
                                }
                            }
                            DatabaseDTO databaseDTO = new DatabaseDTO(new ArrayList<>(), selectedFoods);
                            socketWrapper.write(databaseDTO);
                        }
                        else {
                            System.out.println("Restaurant access denied!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



