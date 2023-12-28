package rds.foodhub.client;

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

public class HomeController {
    public TableView<Restaurant> restaurantTable;
    public TableView<Food> foodtable;
    public TableColumn<Food,String>foodname;
    public TableColumn<Food,String>foodid;
    public TableColumn<Food,String>foodprice;
    public TableColumn<Food,String>foodcategory;

    public TableColumn<Restaurant, String> restaurantNameColumn;
    public TableColumn<Restaurant, String> restaurantScoreColumn;
    public TableColumn<Restaurant, String> restaurantPriceColumn;
    public TableColumn<Restaurant, String> restaurantZipcodeColumn;
    public TableColumn<Restaurant, String> restaurantCategoriesColumn;
    public TextField searchBox;


    private ClientApplication clientApplication;
    void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @FXML
    private Label message;
    @FXML
    private Label enterRange;
    @FXML
    private Label foodnamelabel;
    @FXML
    private Label restNamelabel;
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label maxprice;
    @FXML
    private Label minprice;
    @FXML
    private Label minscore;
    @FXML
    private Label maxscore;
    @FXML
    private Label zipcode;
    @FXML
    private ImageView image;
    @FXML
    private ImageView salman;
    @FXML
    private ImageView shahrukh;
    @FXML
    private ImageView sizan;
    @FXML
    private ImageView enjoyimage;
    @FXML
    private TextField textfield1;
    @FXML
    private TextField textfield2;

    @FXML
    private Button button;
    @FXML
    private Button seacrh;

    @FXML
    private Button orderplacebutton;
    @FXML
    private Button fooditemselection;
    @FXML
    private Button SearchRestaurant;
    @FXML
    private Button FoodSearch;
    @FXML
    private Button searchfoodbycat;
    @FXML
    private Button searchfoodbycatandrest;
    @FXML
    private Button searchfoodbyprice;
    @FXML
    private Button allfoods;
    @FXML
    private Button costlyFood;
    @FXML
    private Button searchfoodbypriceandrest;
    @FXML
    private Button byfoodName;
    @FXML
    private Button searchByrest;
    @FXML
    private Button allrests;
    @FXML
    private Button searchRestaurentByzip;
    @FXML
    private Button searchRestaurentByprc;
    @FXML
    private Button searchRestaurentBycats;
    @FXML
    private Button searchRestaurentByscore;
    @FXML
    private Button foodsearchByrest;
    @FXML
    private Button byName;

    int currentSearchType = 1; // 1 - name

    public void init(String msg) {

        message.setText(msg);
        if(msg.equals("shahrukh")) shahrukh.setVisible(true);
        else if(msg.equals("sizan")) sizan.setVisible(true);
        else image.setVisible(true);
        System.out.println("hello");
        ArrayList<Restaurant> restaurants = clientApplication.getRestaurants();
        ArrayList<Food> foods = clientApplication.getFoods();

    }

    void fillRestaurantTable(ArrayList<Restaurant> restaurantrestaurantArrayList)
    {
        ObservableList<Restaurant> restaurantData = FXCollections.observableArrayList(restaurantrestaurantArrayList);

        // Set the items in the TableView to the ObservableList.
        restaurantTable.setItems(restaurantData);

        // Set up cell value factories for each column.
        restaurantNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurantName()));

        // For the score column, convert Double to String and handle null values
        restaurantScoreColumn.setCellValueFactory(cellData -> {
            Double score = cellData.getValue().getScore();
            return new SimpleStringProperty(score != null ? score.toString() : "");
        });

        restaurantPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice()));
        restaurantZipcodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZipCode()));
        restaurantCategoriesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCatString()));
    }

    void fillFoodTable(ArrayList<Food> restaurantrestaurantArrayList)
    {
        ObservableList<Food> FoodData = FXCollections.observableArrayList(restaurantrestaurantArrayList);
        // Set the items in the TableView to the ObservableList.
        foodtable.setItems(FoodData);
        // Set up cell value factories for each column.
        foodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodName()));
        // For the score column, convert Double to String and handle null values
        foodprice.setCellValueFactory(cellData -> {
            Double score = cellData.getValue().getFoodPrice();
            return new SimpleStringProperty(score != null ? score.toString() : "");
        });
        foodid.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRestaurantId())));
        foodcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
    }

    @FXML
    void logoutAction(ActionEvent event) {
        System.out.println("Logout button clicked.");
        try {
            clientApplication.showLoginWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startSearch(ActionEvent actionEvent) throws IOException {
        if(currentSearchType == 9) {

            String searchText = textfield2.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getRestaurantName().toLowerCase().contains(searchText.toLowerCase()))
                {
                    searchRestaurant.add(restaurant);
                    flag = true;
                }
            }

            if(flag){
                fillRestaurantTable(searchRestaurant);
                restaurantTable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                restaurantTable.setVisible(false);
                clientApplication.showAlert();
            }
        }
        if(currentSearchType == 1) {
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            for(Food x : clientApplication.getFoods())
            {
                if(x.getFoodName().toLowerCase().contains(searchText.toLowerCase()))
                {
                    flag = true;
                    searchFoods.add(x);
                }
            }

            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }
        }
        if(currentSearchType == 2) {
            orderplacebutton.setVisible(true);
            fooditemselection.setVisible(true);
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            String searchText2 = textfield2.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getRestaurantName().toLowerCase().contains(searchText2.toLowerCase()))
                {
                    for(Food x : clientApplication.getFoods())
                    {
                        if(x.getFoodName().toLowerCase().contains(searchText.toLowerCase()) && x.getRestaurantId() == restaurant.getID())
                        {
                            searchFoods.add(x);
                            flag = true;
                        }
                    }
                }
            }
            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }

        }
        if(currentSearchType == 3) {
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;


            for(Food x : clientApplication.getFoods())
            {
                if(x.getCategory().toLowerCase().contains(searchText.toLowerCase()))
                {
                    searchFoods.add(x);
                    flag = true;
                }
            }

            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }

        }

        if(currentSearchType == 4) {
            orderplacebutton.setVisible(true);
            fooditemselection.setVisible(true);
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            String searchText2 = textfield2.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getRestaurantName().toLowerCase().contains(searchText2.toLowerCase()))
                {
                    for(Food x : clientApplication.getFoods())
                    {
                        if(x.getCategory().toLowerCase().contains(searchText.toLowerCase()) && x.getRestaurantId() == restaurant.getID())
                        {
                            searchFoods.add(x);
                            flag = true;
                        }
                    }
                }
            }
            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }
            foodtable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ObservableList<Food> selectedFoods = foodtable.getSelectionModel().getSelectedItems();;
            for (Food food : selectedFoods) {
                System.out.println("Selected Food: " + food.getFoodName());
            }
        }

        if(currentSearchType == 5) {
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            String searchText2 = textfield2.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            double max = Double.parseDouble(searchText);
            double min =  Double.parseDouble(searchText2);
            for(Food x : clientApplication.getFoods())
            {
                if(x.getFoodPrice()>=min && x.getFoodPrice()<=max)
                {
                    searchFoods.add(x);
                    flag = true;
                }
            }
            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }
        }

        if(currentSearchType == 6) {
            orderplacebutton.setVisible(true);
            fooditemselection.setVisible(true);
            restaurantTable.setVisible(false);
            String searchText = textfield1.getText();
            String searchText2 = textfield2.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            double max = Double.parseDouble(searchText);
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getRestaurantName().toLowerCase().contains(searchText2.toLowerCase()))
                {
                    for(Food x : clientApplication.getFoods())
                    {
                        if(x.getFoodPrice()<=max && x.getRestaurantId() == restaurant.getID())
                        {
                            searchFoods.add(x);
                            flag = true;
                        }
                    }
                }
            }
            if(flag){
                fillFoodTable(searchFoods);
                foodtable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                foodtable.setVisible(false);
                clientApplication.showAlert();
            }

        }
        if(currentSearchType == 10) {
            foodtable.setVisible(false);
            String searchText = textfield1.getText();
            String searchText2 = textfield2.getText();
            double max = Double.parseDouble(searchText);
            double min =  Double.parseDouble(searchText2);
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            ArrayList<Food> searchFoods = new ArrayList<>();
            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getScore()>=min && restaurant.getScore()<=max)
                {
                    searchRestaurant.add(restaurant);
                    flag = true;
                }
            }
            if(flag){
                fillRestaurantTable(searchRestaurant);
                restaurantTable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                restaurantTable.setVisible(false);
                clientApplication.showAlert();
            }


        }
        if(currentSearchType == 11) {
            foodtable.setVisible(false);
            String searchText = textfield1.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.containCatagory(searchText.toLowerCase()))
                {
                    searchRestaurant.add(restaurant);
                    flag = true;
                }
            }
            if(flag){
                fillRestaurantTable(searchRestaurant);
                restaurantTable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                restaurantTable.setVisible(false);
                clientApplication.showAlert();
            }
        }
        if(currentSearchType == 12) {
            foodtable.setVisible(false);
            String searchText = textfield1.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();

            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getPrice().contains(searchText))
                {
                    searchRestaurant.add(restaurant);
                    flag = true;
                }
            }
            if(flag){
                fillRestaurantTable(searchRestaurant);
                restaurantTable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                restaurantTable.setVisible(false);
                clientApplication.showAlert();
            }
        }
        if(currentSearchType == 13) {
            foodtable.setVisible(false);
            String searchText = textfield1.getText();
            ArrayList<Restaurant> searchRestaurant = new ArrayList<>();

            boolean flag = false;
            for(Restaurant restaurant : clientApplication.getRestaurants())
            {
                if(restaurant.getZipCode().equals(searchText))
                {
                    searchRestaurant.add(restaurant);
                    flag = true;
                }
            }
            if(flag){
                fillRestaurantTable(searchRestaurant);
                restaurantTable.setVisible(true);
                enjoyimage.setVisible(false);
            }
            else{
                restaurantTable.setVisible(false);
                clientApplication.showAlert();
            }
        }

    }


    public void SearchFood(ActionEvent actionEvent) {
        searchfoodbycat.setVisible(true);
        searchfoodbycatandrest.setVisible(true);
        searchfoodbyprice.setVisible(true);
        allfoods.setVisible(true);
        costlyFood.setVisible(true);
        byfoodName.setVisible(true);
        searchfoodbypriceandrest.setVisible(true);
        foodsearchByrest.setVisible(true);
        enjoyimage.setVisible(true);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);

        allrests.setVisible(false);
        searchRestaurentByzip.setVisible(false);
        searchRestaurentByprc.setVisible(false);
        searchRestaurentBycats.setVisible(false);
        searchRestaurentByscore.setVisible(false);
        byName.setVisible(false);
        seacrh.setVisible(false);

        textfield1.setVisible(false);
        CategoryLabel.setVisible(false);
        textfield2.setVisible(false);
        restNamelabel.setVisible(false);
        foodnamelabel.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        seacrh.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
        restaurantTable.setVisible(false);
        foodtable.setVisible(false);
    }

    public void searchRestaurant(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        allrests.setVisible(true);
        searchRestaurentByzip.setVisible(true);
        searchRestaurentByprc.setVisible(true);
        searchRestaurentBycats.setVisible(true);
        searchRestaurentByscore.setVisible(true);
        byName.setVisible(true);
        restaurantTable.setVisible(false);
        foodtable.setVisible(false);
        enjoyimage.setVisible(true);

        searchfoodbycat.setVisible(false);
        searchfoodbycatandrest.setVisible(false);
        searchfoodbyprice.setVisible(false);
        allfoods.setVisible(false);
        costlyFood.setVisible(false);
        byfoodName.setVisible(false);
        searchfoodbypriceandrest.setVisible(false);
        foodsearchByrest.setVisible(false);
        seacrh.setVisible(false);
        zipcode.setVisible(false);

        textfield1.setVisible(false);
        CategoryLabel.setVisible(false);
        textfield2.setVisible(false);
        restNamelabel.setVisible(false);
        foodnamelabel.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        seacrh.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
    }


    public void searchFoodByCategory(ActionEvent actionEvent) {
        textfield1.setVisible(true);
        currentSearchType = 3;
        CategoryLabel.setVisible(true);
        textfield2.setVisible(false);
        restaurantTable.setVisible(false);
        restNamelabel.setVisible(false);
        foodnamelabel.setVisible(false);
        minprice.setVisible(false);
        foodtable.setVisible(false);
        maxprice.setVisible(false);
        seacrh.setVisible(true);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
        enjoyimage.setVisible(true);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
    }

    public void searchFoodByCategoryAndRestaurant(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        textfield1.setVisible(true);
        textfield2.setVisible(true);
        currentSearchType = 4;
        CategoryLabel.setVisible(true);
        restNamelabel.setVisible(true);
        foodnamelabel.setVisible(false);
        enjoyimage.setVisible(true);
        restaurantTable.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        seacrh.setVisible(true);
        enterRange.setVisible(false);
        foodtable.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
    }

    public void searchFoodByPrice(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        textfield2.setVisible(true);
        textfield1.setVisible(true);
        currentSearchType = 5;
        seacrh.setVisible(true);
        enjoyimage.setVisible(true);
        maxprice.setVisible(true);
        minprice.setVisible(true);
        foodnamelabel.setVisible(false);
        foodtable.setVisible(false);
        restNamelabel.setVisible(false);
        restaurantTable.setVisible(false);
        CategoryLabel.setVisible(false);
        enterRange.setVisible(true);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
    }

    public void searchAllFoods(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        currentSearchType = 8;
        seacrh.setVisible(false);
        maxprice.setVisible(false);
        foodtable.setVisible(false);
        minprice.setVisible(false);
        foodnamelabel.setVisible(false);
        restNamelabel.setVisible(false);
        CategoryLabel.setVisible(false);
        restaurantTable.setVisible(false);
        textfield2.setVisible(false);
        textfield1.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
        restaurantTable.setVisible(false);
        ArrayList<Food> searchFoods = new ArrayList<>();
        searchFoods.addAll(clientApplication.getFoods());
        fillFoodTable(searchFoods);
        foodtable.setVisible(true);
        enjoyimage.setVisible(false);
    }

    public void costliestFood(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        currentSearchType = 7;
        seacrh.setVisible(false);
        maxprice.setVisible(false);
        minprice.setVisible(false);
        foodnamelabel.setVisible(false);
        foodtable.setVisible(false);
        restNamelabel.setVisible(false);
        CategoryLabel.setVisible(false);
        restaurantTable.setVisible(false);
        textfield2.setVisible(false);
        textfield1.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
        zipcode.setVisible(false);
        double max = 0;
        restaurantTable.setVisible(false);
        ArrayList<Food> searchFoods = new ArrayList<>();

        for(Food x : clientApplication.getFoods())
        {
            if(x.getFoodPrice()>=max)
            {
                max = x.getFoodPrice();
            }
        }
        for(Food x : clientApplication.getFoods())
        {
            if(x.getFoodPrice()==max)
            {
                searchFoods.add(x);
            }
        }
        fillFoodTable(searchFoods);
        foodtable.setVisible(true);
        enjoyimage.setVisible(false);

    }

    public void searchFoodByPriceandRestaurant(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        textfield2.setVisible(true);
        textfield1.setVisible(true);
        currentSearchType = 6;
        seacrh.setVisible(true);
        maxprice.setVisible(true);
        zipcode.setVisible(false);
        minprice.setVisible(false);
        foodtable.setVisible(false);
        restaurantTable.setVisible(false);
        foodnamelabel.setVisible(false);
        restNamelabel.setVisible(true);
        enjoyimage.setVisible(true);
        CategoryLabel.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
    }

    public void searchFoodbyName(ActionEvent actionEvent) {
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        currentSearchType = 1;
        textfield1.setVisible(true);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(true);
        restNamelabel.setVisible(false);
        CategoryLabel.setVisible(false);
        foodtable.setVisible(false);
        zipcode.setVisible(false);
        restaurantTable.setVisible(false);
        enjoyimage.setVisible(true);
        textfield2.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
    }

    public void searchFoodByRestaurant(ActionEvent actionEvent) {
        currentSearchType = 2;
        textfield1.setVisible(true);
        textfield2.setVisible(true);
        zipcode.setVisible(false);
        foodtable.setVisible(false);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(true);
        enjoyimage.setVisible(true);
        restNamelabel.setVisible(true);
        CategoryLabel.setVisible(false);
        restaurantTable.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
    }

    public void allRestaurant(ActionEvent actionEvent) {
        currentSearchType = 14;
        textfield1.setVisible(false);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        textfield2.setVisible(false);
        seacrh.setVisible(false);
        foodnamelabel.setVisible(false);
        restNamelabel.setVisible(false);
        restaurantTable.setVisible(false);
        foodtable.setVisible(false);
        zipcode.setVisible(false);
        CategoryLabel.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);

        ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
        for(Restaurant restaurant : clientApplication.getRestaurants())
        {
            searchRestaurant.add(restaurant);
        }
        fillRestaurantTable(searchRestaurant);
        restaurantTable.setVisible(true);
        enjoyimage.setVisible(false);
    }

    public void searchRestaurentByZipCode(ActionEvent actionEvent) {
        currentSearchType = 13;
        textfield1.setVisible(true);
        textfield2.setVisible(false);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(false);
        zipcode.setVisible(true);
        foodtable.setVisible(false);
        enjoyimage.setVisible(true);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        restaurantTable.setVisible(false);
        restNamelabel.setVisible(false);
        CategoryLabel.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);

    }

    public void searchRestaurentByPrice(ActionEvent actionEvent) {
        currentSearchType = 12;
        textfield1.setVisible(true);
        textfield2.setVisible(false);
        zipcode.setVisible(false);
        enjoyimage.setVisible(true);
        foodtable.setVisible(false);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(false);
        restNamelabel.setVisible(false);
        CategoryLabel.setVisible(false);
        minprice.setVisible(false);
        restaurantTable.setVisible(false);
        maxprice.setVisible(true);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
    }
    public void searchRestaurentByCategory(ActionEvent actionEvent){
        currentSearchType = 11;
        textfield1.setVisible(true);
        textfield2.setVisible(false);
        seacrh.setVisible(true);
        foodtable.setVisible(false);
        foodnamelabel.setVisible(false);
        zipcode.setVisible(false);
        restNamelabel.setVisible(false);
        restaurantTable.setVisible(false);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        enjoyimage.setVisible(true);
        CategoryLabel.setVisible(true);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);

    }

    public void searchRestaurentByScore(ActionEvent actionEvent) {
        currentSearchType = 10;
        textfield1.setVisible(true);
        foodtable.setVisible(false);
        textfield2.setVisible(true);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(false);
        enjoyimage.setVisible(true);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        restaurantTable.setVisible(false);
        restNamelabel.setVisible(false);
        zipcode.setVisible(false);
        CategoryLabel.setVisible(false);
        minprice.setVisible(false);
        maxprice.setVisible(false);
        enterRange.setVisible(true);
        minscore.setVisible(true);
        maxscore.setVisible(true);
    }

    public void searchRestaurentByName(ActionEvent actionEvent) {
        currentSearchType = 9;
        textfield1.setVisible(false);
        textfield2.setVisible(true);
        seacrh.setVisible(true);
        foodnamelabel.setVisible(false);
        restNamelabel.setVisible(true);
        restaurantTable.setVisible(false);
        enjoyimage.setVisible(true);
        foodtable.setVisible(false);
        CategoryLabel.setVisible(false);
        minprice.setVisible(false);
        zipcode.setVisible(false);
        orderplacebutton.setVisible(false);
        fooditemselection.setVisible(false);
        maxprice.setVisible(false);
        minscore.setVisible(false);
        maxscore.setVisible(false);
    }


    public void selectFoodItems(ActionEvent actionEvent) {
//        foodtable.setVisible(true);
//        ObservableList<Food> selectedFoods = foodtable.getSelectionModel().getSelectedItems();
//        for (Food food : selectedFoods) {
//            System.out.println("Selected Food: " + food.getFoodName());
//        }
    }

    public void placeOrder(ActionEvent actionEvent) {

    }
}
