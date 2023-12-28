package rds.foodhub.restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rds.foodhub.dto.DatabaseDTO;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;
import rds.foodhub.server.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantApplication extends Application {
    private Stage window;
    public Stage getWindow() {
        return window;
    }

    private ArrayList<Food> foods;
    private ArrayList<Restaurant> restaurants;
    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
    public void setRestaurants(ArrayList<Restaurant> rests){
        for(Restaurant x : rests) {
            System.out.println(x.getRestaurantName());
        }
        this.restaurants = rests;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return this.restaurants;
    }
    SocketWrapper socketWrapper;
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage primaryWindow) throws IOException, ClassNotFoundException {
        this.window = primaryWindow;
        connectToServer();
        showFirstPage();
    }

    private void connectToServer() throws IOException, ClassNotFoundException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);
    }

    public void showHomeWindow(String showHomeWindow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("/rds/foodhub/restaurant-home-view.fxml"));
        Parent root = fxmlLoader.load();
        RestaurantHomeController controller = fxmlLoader.getController();

        controller.setRestaurantApplication(this);
        controller.init(showHomeWindow);

        Scene scene = new Scene(root, 680, 525);
        window.setTitle("Home Screen");
        window.setScene(scene);
        window.show();
    }
    public void showLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("/rds/foodhub/restaurant-login-view.fxml"));
        Parent root = fxmlLoader.load();
        RestaurantLoginController controller = fxmlLoader.getController();
        controller.setApplication(this);
        controller.init();

        Scene scene = new Scene(root, 400, 300);
        window.setTitle("Login Screen");
        window.setScene(scene);
        window.show();
    }
    public void showFirstPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("/rds/foodhub/restaurant-first-page.fxml"));
        Parent root = fxmlLoader.load();
        RestaurentFirstPage controller = fxmlLoader.getController();
        controller.setApplication(this);
        //controller.init();
        Scene scene = new Scene(root, 600, 400);
        window.setTitle("WELCOME!");
        window.setScene(scene);
        window.show();
    }
    public void showRegistrationPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("/rds/foodhub/registration-page.fxml"));
        Parent root = fxmlLoader.load();
        RegistrationPageController controller = fxmlLoader.getController();
        controller.setApplication(this);
        //controller.init();
        Scene scene = new Scene(root, 600, 400);
        window.setTitle("Registration !");
        window.setScene(scene);
        window.show();
    }
    public void showAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Category!");
        alert.setHeaderText("Missing Category!");
        alert.setContentText("You need to provide atleast one category !!!.");
        alert.show();
        showFirstPage();
    }
    public void showMassageAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Massages");
        alert.setHeaderText("No Massages!");
        alert.setContentText("You have 0 massages to read !!!.");
        alert.showAndWait();
    }
    public void showorderAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No order");
        alert.setHeaderText("No order!");
        alert.setContentText("You have 0 order to complete !!!.");
        alert.showAndWait();
    }
    public void showAlert2() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Existential Crisis!");
        alert.setHeaderText("Existential Crisis!");
        alert.setContentText("Restaurent with this name or id already exist !!.");
        alert.show();
        showFirstPage();
    }
    public void showAlert3() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Password!");
        alert.setHeaderText("Wrong Password!");
        alert.setContentText("Password corresponding to this username didn't match!!!!.");
        alert.show();
        showFirstPage();
    }
    public void showSuccessfulRegistration() throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Registered!");
        a.setContentText("Congratulation! Your registration is Successful !");
        a.show();
        showFirstPage();
    }
    public void showSuccessfulAdd() throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Added!");
        a.setContentText("Congratulation! Your Food has been added to the menu Successfully !");
        a.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}