package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    private FXMLLoader dashboardLoader;
    private FXMLLoader productsLoader;
    private FXMLLoader salesLoader;
    private FXMLLoader employeesLoader;
    private FXMLLoader stockorderLoader;
    private FXMLLoader inventoryLoader;
    private FXMLLoader revenueLoader;
    private FXMLLoader cashflowLoader;
    private FXMLLoader expensesLoader;
    private FXMLLoader settingsLoader;


    private Node dashboard;
    private Node products;
    private Node sales;
    private Node customers;
    private Node stockorder;
    private Node inventory;
    private Node revenue;
    private Node cashflow;
    private Node expenses;
    private Node settings;

    @FXML private JFXButton dashboardButton;
    @FXML private JFXButton productsButton;
    @FXML private JFXButton salesButton;
    @FXML private JFXButton inventoryButton;
    @FXML private JFXButton revenueButton;
    @FXML private JFXButton expensesButton;
    @FXML private JFXButton cashflowButton;
    @FXML private JFXButton employeesButton;
    @FXML private JFXButton stockorderButton;
    @FXML private JFXButton settingsButton;

    @FXML private Label menuLabel;


    @FXML private Pane mainBody;

    private JFXButton currentButton;
    private String currentMenu;

    public void initialize(URL location, ResourceBundle resources) {
        currentMenu = "xxx";

        initializeLoaders();
        setActions();

        currentButton = dashboardButton;
    }

    public void setActions() {
        dashboardButton.setOnAction(e -> {
            setPaneContent(dashboardButton.getText());;
        });

        productsButton.setOnAction(e -> {
            setPaneContent(productsButton.getText());
        });

        salesButton.setOnAction(e -> {
            setPaneContent(salesButton.getText());
        });

        revenueButton.setOnAction(e -> {
            setPaneContent(revenueButton.getText());
        });

        employeesButton.setOnAction(e -> {
            setPaneContent(employeesButton.getText());
        });

 
        inventoryButton.setOnAction(e -> {
            setPaneContent(inventoryButton.getText());
        });

        expensesButton.setOnAction(e -> {
            setPaneContent(expensesButton.getText());
        });

        cashflowButton.setOnAction(e -> {
            setPaneContent(cashflowButton.getText());
        });


        stockorderButton.setOnAction(e -> {
            setPaneContent(stockorderButton.getText());
        });

        settingsButton.setOnAction(e -> {
            setPaneContent(settingsButton.getText());
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

    public FXMLLoader getLoader(String name)
    {
        FXMLLoader result;
        switch (name)
        {
            case "Dashboard":
            {
                result = dashboardLoader;
                break;
            }

            case "Sales":
            {
                result = salesLoader;
                break;
            }

            case "Products":
            {
                result =  productsLoader;
                break;
            }

            case "Employees":
            {
                result = employeesLoader;
                break;
            }

            case "Stock order":
            {
                result = stockorderLoader;
                break;
            }

            case "Inventory":
            {
                result = inventoryLoader;
                break;
            }

            case "Revenue":
            {
                result = revenueLoader;
                break;
            }

            case "Cashflow":
            {
                result = cashflowLoader;
                break;
            }

            case "Expenses":
            {
                result = expensesLoader;
                break;
            }

            case "Settings":
            {
                result = settingsLoader;
                break;
            }

            default:
            {
                result = null;
                break;
            }
        }
        return result;
    }

    private void initializeLoaders()
    {
        dashboardLoader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
        System.out.println("Load dashboard");
        try {
            dashboard = dashboardLoader.load();
            System.out.println("Load dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }

        salesLoader = new FXMLLoader(getClass().getResource("/view/sales/Sales.fxml"));
        System.out.println("Load sale");
        try {
            sales = salesLoader.load();
            System.out.println("Load sale");
        } catch (IOException e) {
            e.printStackTrace();
        }

        productsLoader = new FXMLLoader(System.class.getResource("/view/products/Products.fxml"));
        try {
            products = productsLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        employeesLoader = new FXMLLoader(getClass().getResource("/view/employees/Employees.fxml"));
        try {
            customers = employeesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stockorderLoader = new FXMLLoader(getClass().getResource("/view/Stockorder.fxml"));
        try {
            stockorder = stockorderLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inventoryLoader = new FXMLLoader(getClass().getResource("/view/inventory/Inventory.fxml"));
        try {
            inventory = inventoryLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        revenueLoader = new FXMLLoader(getClass().getResource("/view/Revenue.fxml"));
        try {
            revenue = revenueLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cashflowLoader = new FXMLLoader(getClass().getResource("/view/Cashflow.fxml"));
        try {
            cashflow = cashflowLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        expensesLoader = new FXMLLoader(getClass().getResource("/view/expenses/Expenses.fxml"));
        try {
            expenses = expensesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        settingsLoader = new FXMLLoader(getClass().getResource("/view/Settings.fxml"));
        try {
            settings = settingsLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPaneContent(String menuTitle)
    {
        String trimmedMenuTitle = menuTitle.trim();
        System.out.println("Trimmed menu : "+trimmedMenuTitle);
        if(!trimmedMenuTitle.equals(currentMenu))
        {
            Node node;
            JFXButton button;
            switch (trimmedMenuTitle)
            {
                case "Dashboard":
                {
                    node = dashboard;
                    button = dashboardButton;
                    break;
                }

                case "Sales":
                {
                    node = sales;
                    button = salesButton;

                    break;
                }

                case "Products":
                {
                    node =  products;
                    button = productsButton;
                    break;
                }

                case "Employees":
                {
                    node = customers;
                    button = employeesButton;
                    break;
                }

                case "Stock order":
                {
                    node = stockorder;
                    button = stockorderButton;
                    break;
                }

                case "Inventory":
                {
                    node = inventory;
                    button = inventoryButton;
                    break;
                }

                case "Revenue":
                {
                    node = revenue;
                    button = revenueButton;
                    break;
                }

                case "Cashflow":
                {
                    node = cashflow;
                    button = cashflowButton;
                    break;
                }

                case "Expenses":
                {
                    node = expenses;
                    button = expensesButton;
                    break;
                }

                case "Settings":
                {
                    node = settings;
                    button = settingsButton;
                    break;
                }

                default:
                {
                    node = null;
                    button = null;
                    break;
                }
            }

            menuLabel.setText(trimmedMenuTitle);
            mainBody.getChildren().removeAll(mainBody.getChildren());
            mainBody.getChildren().add(node);
            currentMenu = trimmedMenuTitle;

            handleStylesheet(button);
        }
    }
}
