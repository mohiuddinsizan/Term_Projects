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
import java.io.IOException;


public class RestaurentFirstPage {
    private RestaurantApplication restaurantApplication;
    public void setApplication(RestaurantApplication restaurantApplication){
        this.restaurantApplication = restaurantApplication;
    }

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;


    public void loginAction(ActionEvent actionEvent) throws IOException {
        restaurantApplication.showLoginWindow();
    }

    public void registerAction(ActionEvent actionEvent) throws IOException {
        restaurantApplication.showRegistrationPage();
    }
}
