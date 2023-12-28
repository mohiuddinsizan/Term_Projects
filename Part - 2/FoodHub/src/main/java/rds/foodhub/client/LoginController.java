package rds.foodhub.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rds.foodhub.dto.DatabaseDTO;
import rds.foodhub.dto.LoginDTO;
import rds.foodhub.helper.Food;
import rds.foodhub.helper.Restaurant;

import java.io.IOException;
import java.util.ArrayList;


public class LoginController {

    private ClientApplication clientApplication;
    void setApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
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
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);

        try {
            clientApplication.getSocketWrapper().write(loginDTO);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Object obj = clientApplication.getSocketWrapper().read();
            LoginDTO recieved = (LoginDTO) obj;

            if(recieved.isStatus())
            {

                DatabaseDTO databaseDTO = (DatabaseDTO) clientApplication.getSocketWrapper().read();

                clientApplication.setRestaurants(databaseDTO.restaurants);
                clientApplication.setFoods(databaseDTO.foods);

                clientApplication.showHomeWindow(userName);

            }
            else clientApplication.showAlert2();
        } catch (IOException | ClassNotFoundException e) {
            clientApplication.showAlert2();

            e.printStackTrace();
        }

    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }


}
