module rds.foodhub {
    requires javafx.controls;
    requires javafx.fxml;

    opens rds.foodhub to javafx.fxml;
    exports rds.foodhub.server;
    opens rds.foodhub.server to javafx.fxml;
    exports rds.foodhub.client;
    opens rds.foodhub.client to javafx.fxml;
    exports rds.foodhub.dto;
    opens rds.foodhub.dto to javafx.fxml;
    exports rds.foodhub.helper to javafx.fxml;
    opens rds.foodhub.helper;
    exports rds.foodhub.restaurant;
    opens  rds.foodhub.restaurant;
}