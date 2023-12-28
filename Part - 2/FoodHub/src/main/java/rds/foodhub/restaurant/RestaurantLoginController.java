package rds.foodhub.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rds.foodhub.dto.DatabaseDTO;
import rds.foodhub.dto.LoginDTO;
import rds.foodhub.dto.RestaurantLoginDTO;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;

import java.io.IOException;
import java.util.ArrayList;


public class RestaurantLoginController {

    private RestaurantApplication restaurantApplication;
    void setApplication(RestaurantApplication restaurantApplication) {
        this.restaurantApplication = restaurantApplication;
    }

    public void init()
    {

    }

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        String userName = userText.getText();
        String password = passwordText.getText();
        RestaurantLoginDTO restaurantLoginDTO = new RestaurantLoginDTO();
        restaurantLoginDTO.setUserName(userName);
        restaurantLoginDTO.setPassword(password);
        restaurantLoginDTO.setID(Integer.parseInt(password));
        try {
            restaurantApplication.getSocketWrapper().write(restaurantLoginDTO);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Object obj = restaurantApplication.getSocketWrapper().read();
            RestaurantLoginDTO recieved = (RestaurantLoginDTO) obj;

            if (recieved.isStatus()){
                DatabaseDTO databaseDTO = (DatabaseDTO) restaurantApplication.getSocketWrapper().read();
                restaurantApplication.setFoods(databaseDTO.foods);
//                restaurantApplication.setRestaurants(databaseDTO.restaurants);
//                ArrayList<Restaurant> res = new ArrayList<>();
//                res = databaseDTO.restaurants;
//                for(Restaurant r : res){
//                    System.out.println(r.getRestaurantName());
//                }
                restaurantApplication.showHomeWindow(userName);
            }
            else restaurantApplication.showAlert3();

        }
        catch (IOException | ClassNotFoundException e) {
            restaurantApplication.showAlert3();
        }
    }
    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    public void BackButton(ActionEvent actionEvent) throws IOException {
        restaurantApplication.showFirstPage();
    }
}
