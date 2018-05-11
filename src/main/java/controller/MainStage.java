package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStage implements Initializable {

    @FXML private JFXButton dashboardButton;
    @FXML private JFXButton productsButton;
    @FXML private JFXButton salesButton;
    @FXML private JFXButton inventoryButton;
    @FXML private JFXButton revenueButton;
    @FXML private JFXButton profitButton;
    @FXML private JFXButton cashflowButton;

    @FXML private JFXButton customersButton;
    @FXML private JFXButton stockorderButton;
    @FXML private JFXButton settingsButton;


    @FXML private Pane mainBody;

    private JFXButton currentButton;


    public void initialize(URL location, ResourceBundle resources) {
        //App.sceneManager.setMainBodyPane(mainBody);
        setActions();

        currentButton = dashboardButton;
    }

    public void setActions(){
        dashboardButton.setOnAction(e -> {
            System.out.println(dashboardButton.getText());
            App.sceneManager.setPaneContent(dashboardButton.getText());

            handleStylesheet(dashboardButton);
        });

        productsButton.setOnAction(e -> {
            System.out.println(productsButton.getText());
            App.sceneManager.setPaneContent(productsButton.getText());

            handleStylesheet(productsButton);
        });

        salesButton.setOnAction(e -> {
            System.out.println(salesButton.getText());
            App.sceneManager.setPaneContent(salesButton.getText());

            handleStylesheet(salesButton);
        });

        inventoryButton.setOnAction(e -> {
            System.out.println(inventoryButton.getText());
            App.sceneManager.setPaneContent(inventoryButton.getText());

            handleStylesheet(inventoryButton);
        });

        revenueButton.setOnAction(e -> {
            System.out.println(revenueButton.getText());
            App.sceneManager.setPaneContent(revenueButton.getText());

            handleStylesheet(revenueButton);
        });

        profitButton.setOnAction(e -> {
            System.out.println(profitButton.getText());
            App.sceneManager.setPaneContent(profitButton.getText());

            handleStylesheet(profitButton);
        });

        cashflowButton.setOnAction(e -> {
            System.out.println(cashflowButton.getText());
            App.sceneManager.setPaneContent(cashflowButton.getText());

            handleStylesheet(cashflowButton);
        });





        customersButton.setOnAction(e -> {
            System.out.println(customersButton.getText());
            App.sceneManager.setPaneContent(customersButton.getText());

            handleStylesheet(customersButton);
        });

        stockorderButton.setOnAction(e -> {
            System.out.println(stockorderButton.getText());
            App.sceneManager.setPaneContent(stockorderButton.getText());

            handleStylesheet(stockorderButton);
        });

        settingsButton.setOnAction(e -> {
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
