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
import rds.foodhub.helper.RestaurantManager;

import java.io.IOException;
import java.util.ArrayList;


public class RegistrationPageController {
    RestaurantManager restaurantmanager = new RestaurantManager();
    private ArrayList<String> catagories = new ArrayList<String>();
    private RestaurantApplication restaurantApplication;
    void setApplication(RestaurantApplication restaurantApplication) {
        this.restaurantApplication = restaurantApplication;
    }
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField zipcode;
    @FXML
    private TextField score;
    @FXML
    private TextField cat1;
    @FXML
    private TextField cat2;
    @FXML
    private TextField cat3;

    private Button create;
    public void createNewRestaurent(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String restaurantID = id.getText();
        String restaurantName = name.getText();
        String restaurantPrice = price.getText();
        String restaurantZip = zipcode.getText();
        String restaurantScore = score.getText();
        String restaurantcat1 = cat1.getText();
        String restaurantcat2 = cat2.getText();
        String restaurantcat3 = cat3.getText();
        catagories.add(restaurantcat1);
        catagories.add(restaurantcat2);
        catagories.add(restaurantcat3);
        ArrayList<Restaurant> rest = new ArrayList<>();
        RestaurantLoginDTO restaurantLoginDTO = new RestaurantLoginDTO();
        restaurantLoginDTO.setUserName(restaurantName);
        restaurantLoginDTO.setID(Integer.parseInt(restaurantID));
        try {
            restaurantApplication.getSocketWrapper().write(restaurantLoginDTO);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = restaurantApplication.getSocketWrapper().read();
        RestaurantLoginDTO recieved = (RestaurantLoginDTO) obj;

        if(restaurantcat1.isEmpty() && restaurantcat2.isEmpty() && restaurantcat2.isEmpty()) {
            restaurantApplication.showAlert();
        }
//        if(restaurantmanager.restuarentExistWithSameName(restaurantName) || restaurantmanager.restaurentExistWithSameID(Integer.parseInt(restaurantID))){
//            can = false;
//            restaurantApplication.showAlert2();
//        }

        else if(!recieved.isStatus()){
        //else{
            Restaurant Restaurant = new Restaurant(Integer.parseInt(restaurantID), restaurantName, Double.valueOf(restaurantScore), restaurantPrice, restaurantZip, catagories);
                restaurantmanager.addRestuarent(Restaurant);
                restaurantApplication.showSuccessfulRegistration();
        }
        else{
            restaurantApplication.showAlert2();
        }


    }

    public void cancelRegistration(ActionEvent actionEvent) throws IOException {
        restaurantApplication.showFirstPage();
    }
}
