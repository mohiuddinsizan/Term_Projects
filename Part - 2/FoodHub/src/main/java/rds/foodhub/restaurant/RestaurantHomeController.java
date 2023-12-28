package rds.foodhub.restaurant;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantHomeController {
    public TableView<Food> foodTable;
    @FXML
    public ImageView kfcimage;
    @FXML
    public ImageView donaldsimage;
    @FXML
    public ImageView starimage;
    @FXML
    public ImageView ihopimage;
    @FXML
    public TextField text1;
    @FXML
    public TextField text2;
    @FXML
    public TextField text3;
    @FXML
    public Button addfood;

    public TableColumn<Food, String> foodNameColumn;
    public TableColumn<Food, String> restaurantIdColumn;
    public TableColumn<Food, String> foodCategoryColumn;
    public TableColumn<Food, String> foodPriceColumn;


    private RestaurantApplication restaurantApplication;
    void setRestaurantApplication(RestaurantApplication restaurantApplication) {
        this.restaurantApplication = restaurantApplication;
    }

    @FXML
    private Label message;

    @FXML
    private Button button;
    @FXML
    private Label ordercomplete;
    @FXML
    private Label transaction;
    @FXML
    private Label zero;
    @FXML
    private Label zero2;



    int currentSearchType = 1; // 1 - name

    public void init(String msg) {
        message.setText(msg);
        ArrayList<Food> foods = restaurantApplication.getFoods();
        fillRestaurantTable(foods);
        if(msg.equals("KFC")){
            kfcimage.setVisible(true);
        }
        if(msg.equals("McDonalds")){
            donaldsimage.setVisible(true);
        }
        if(msg.equals("Starbucks")){
            starimage.setVisible(true);
        }
        if(msg.equals("IHOP")){
            ihopimage.setVisible(true);
        }

    }

    void fillRestaurantTable(ArrayList<Food> foodArrayList) {
        ObservableList<Food> foodData = FXCollections.observableArrayList(foodArrayList);

        // Set the items in the TableView to the ObservableList.
        foodTable.setItems(foodData);

        // Set up cell value factories for each column.
        foodNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodName()));
        // For the score column, convert Double to String and handle null values
        restaurantIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurantIdString()));
        foodCategoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory())); // Assuming 'getCategory()' returns a String
        foodPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriceString())); // Assuming 'getPriceAsString()' returns a String
    }


    @FXML
    void logoutAction(ActionEvent event) {
        System.out.println("Logout button clicked.");
        try {
            restaurantApplication.showLoginWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newOrder(ActionEvent actionEvent) throws IOException {
        zero.setVisible(false);
        zero2.setVisible(false);
        transaction.setVisible(false);
        ordercomplete.setVisible(false);
        addfood.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        restaurantApplication.showorderAlert();
    }

    public void readMassage(ActionEvent actionEvent) throws IOException {
        zero.setVisible(false);
        zero2.setVisible(false);
        transaction.setVisible(false);
        ordercomplete.setVisible(false);
        addfood.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        restaurantApplication.showMassageAlert();
    }

    public void INVENTORY(ActionEvent actionEvent) {
        zero.setVisible(true);
        zero2.setVisible(true);
        transaction.setVisible(true);
        ordercomplete.setVisible(true);
        addfood.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
    }

    public void addNewFoodItem(ActionEvent actionEvent) {
        addfood.setVisible(true);
        text1.setVisible(true);
        text2.setVisible(true);
        text3.setVisible(true);
    }

    public void AddFood(ActionEvent actionEvent) throws IOException {
        addfood.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        restaurantApplication.showSuccessfulAdd();
    }
}
