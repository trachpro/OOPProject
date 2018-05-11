package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStage implements Initializable {

    @FXML private JFXButton dashboardButton;
    @FXML private JFXButton productsButton;
    @FXML private JFXButton salesButton;
    @FXML private JFXButton inventoryButton;
    @FXML private JFXButton revenueButton;
    @FXML private JFXButton expensesButton;
    @FXML private JFXButton cashflowButton;
    @FXML private JFXButton customersButton;
    @FXML private JFXButton stockorderButton;
    @FXML private JFXButton settingsButton;

    @FXML private Label menuLabel;


    @FXML private Pane mainBody;

    private JFXButton currentButton;


    public void initialize(URL location, ResourceBundle resources) {
        //App.sceneManager.setMainBodyPane(mainBody);
        setActions();

        currentButton = dashboardButton;
    }

    public void setActions() {
        dashboardButton.setOnAction(e -> {
            System.out.println(dashboardButton.getText());
            menuLabel.setText("Dashboard");
            App.sceneManager.setPaneContent(dashboardButton.getText());

            handleStylesheet(dashboardButton);
        });

        productsButton.setOnAction(e -> {
            System.out.println(productsButton.getText());
            menuLabel.setText("Products");
            App.sceneManager.setPaneContent(productsButton.getText());

            handleStylesheet(productsButton);
        });

        salesButton.setOnAction(e -> {
            System.out.println(salesButton.getText());
            menuLabel.setText("Sales");
            App.sceneManager.setPaneContent(salesButton.getText());

            handleStylesheet(salesButton);
        });

        revenueButton.setOnAction(e -> {
            System.out.println(revenueButton.getText());
            menuLabel.setText("Revenue");
            App.sceneManager.setPaneContent(revenueButton.getText());
            handleStylesheet(revenueButton);
        });

        customersButton.setOnAction(e -> {
            System.out.println(customersButton.getText());
            menuLabel.setText("Customers");
            App.sceneManager.setPaneContent(customersButton.getText());
            handleStylesheet(customersButton);
        });

 
        inventoryButton.setOnAction(e -> {
            System.out.println(inventoryButton.getText());
            menuLabel.setText("Inventory");
            App.sceneManager.setPaneContent(inventoryButton.getText());
            handleStylesheet(inventoryButton);
        });

        expensesButton.setOnAction(e -> {
            menuLabel.setText("Expenses");
            System.out.println(expensesButton.getText());
            App.sceneManager.setPaneContent(expensesButton.getText());
            handleStylesheet(expensesButton);
        });

        cashflowButton.setOnAction(e -> {
            menuLabel.setText("Cash Flow");
            System.out.println(cashflowButton.getText());
            App.sceneManager.setPaneContent(cashflowButton.getText());

            handleStylesheet(cashflowButton);
        });


        stockorderButton.setOnAction(e -> {
            System.out.println(stockorderButton.getText());
            App.sceneManager.setPaneContent(stockorderButton.getText());
            menuLabel.setText("Stock Order");
            handleStylesheet(stockorderButton);
        });

        settingsButton.setOnAction(e -> {
            menuLabel.setText("Settings");
            System.out.println(settingsButton.getText());
            App.sceneManager.setPaneContent(settingsButton.getText());

            handleStylesheet(settingsButton);
        });

    }

    private void handleStylesheet(JFXButton nextButton)
    {
        if(currentButton != nextButton)
        {
            currentButton.getStyleClass().remove("selected");
            nextButton.getStyleClass().add("selected");
            currentButton = nextButton;
        }
    }
}
