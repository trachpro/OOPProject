package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStage implements Initializable {

    @FXML private JFXButton dashboardButton;
    @FXML private JFXButton salesButton;
    @FXML private JFXButton productsButton;
    @FXML private JFXButton customersButton;
    @FXML private JFXButton stockorderButton;
    @FXML private JFXButton inventoryButton;
    @FXML private JFXButton revenueButton;
    @FXML private JFXButton cashflowButton;
    @FXML private JFXButton profitButton;
    @FXML private JFXButton settingsButton;

    @FXML private Label menuLabel;


    @FXML private Pane mainBody;


    public void initialize(URL location, ResourceBundle resources) {
        //App.sceneManager.setMainBodyPane(mainBody);
        setActions();

    }

    public void setActions(){
        dashboardButton.setOnAction(e -> {
            System.out.println(dashboardButton.getText());
            menuLabel.setText("Dashboard");
            App.sceneManager.setPaneContent(dashboardButton.getText());
        });

        productsButton.setOnAction(e -> {
            System.out.println(productsButton.getText());
            menuLabel.setText("Products");
            App.sceneManager.setPaneContent(productsButton.getText());
        });

        salesButton.setOnAction(e -> {
            System.out.println(salesButton.getText());
            menuLabel.setText("Sales");
            App.sceneManager.setPaneContent(salesButton.getText());
        });

        revenueButton.setOnAction(e -> {
            System.out.println(revenueButton.getText());
            menuLabel.setText("Revenue");
            App.sceneManager.setPaneContent(revenueButton.getText());
        });

        customersButton.setOnAction(e -> {
            System.out.println(customersButton.getText());
            menuLabel.setText("Customers");
            App.sceneManager.setPaneContent(customersButton.getText());
        });

        stockorderButton.setOnAction(e -> {
            System.out.println(stockorderButton.getText());
            menuLabel.setText("Stock Order");
            App.sceneManager.setPaneContent(stockorderButton.getText());
        });

        inventoryButton.setOnAction(e -> {
            System.out.println(inventoryButton.getText());
            menuLabel.setText("Inventory");
            App.sceneManager.setPaneContent(inventoryButton.getText());
        });

        cashflowButton.setOnAction(e -> {
            menuLabel.setText("Cash Flow");
            System.out.println(cashflowButton.getText());
            App.sceneManager.setPaneContent(cashflowButton.getText());
        });

        profitButton.setOnAction(e -> {
            menuLabel.setText("Profit");
            System.out.println(profitButton.getText());
            App.sceneManager.setPaneContent(profitButton.getText());
        });

        settingsButton.setOnAction(e -> {
            menuLabel.setText("Profit");
            System.out.println(settingsButton.getText());
            App.sceneManager.setPaneContent(settingsButton.getText());
        });

    }


}
