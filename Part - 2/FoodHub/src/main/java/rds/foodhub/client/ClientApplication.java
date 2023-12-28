package rds.foodhub.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;
import rds.foodhub.server.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;

public class ClientApplication extends Application {
    private Stage window;

    public Stage getWindow() {
        return window;
    }

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
    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }


    SocketWrapper socketWrapper;
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage primaryWindow) throws IOException {
        this.window = primaryWindow;
        connectToServer();
        showLoginWindow();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);
    }

    public void showLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/rds/foodhub/login-view.fxml"));
        Parent root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        controller.init();
        controller.setApplication(this);

        Scene scene = new Scene(root, 400, 300);
        window.setTitle("Login Screen");
        window.setScene(scene);
        window.show();
    }
    public void showAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not Found");
        alert.setHeaderText("Not Found!");
        alert.setContentText("nothing matched with the searched item !!!.");
        alert.showAndWait();
    }
    public void showAlert2() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No User");
        alert.setHeaderText("No User Found!");
        alert.setContentText("Password didn't match with the Username !!!.");
        alert.showAndWait();
    }

    public void showHomeWindow(String showHomeWindow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/rds/foodhub/client-home-view.fxml"));
        Parent root = fxmlLoader.load();
        HomeController controller = fxmlLoader.getController();

        controller.setClientApplication(this);
        controller.init(showHomeWindow);

        Scene scene = new Scene(root, 680, 550);
        window.setTitle("Home Screen");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}