package Controller;

import Model.Product;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, Integer> remainingColumn;
    @FXML private TableColumn<Product, Double> salespriceColumn;
    @FXML private TableColumn<Product, Double> importpriceColumn;
    @FXML private TableColumn<Product, String> hellColumn;
    @FXML private TableColumn<Product, String> nationColumn;

    @FXML private JFXButton addButton;

    //ProductsManager productsManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(e -> {
            System.out.println(addButton.getText());
            App.dataManager.getProductsManager().getProducts().add(new Product());
        });
    }

    public void setData()
    {
//        idColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
//            Product p = cdf.getValue();
//            return new SimpleStringProperty(p.getProductID());
//        });
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        remainingColumn.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        salespriceColumn.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
        nationColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));
        importpriceColumn.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        hellColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String isActive;
            if(p.isActive()) isActive = "Active";
            else isActive = "Inactive";

            return new SimpleStringProperty(isActive);
        });
//        hellColumn.setCellValueFactory(cellData -> cellData.getValue().);

        //productsManager = new ProductsManager();
        //System.out.println(productsManager.getProducts());
        productsTable.setItems(App.dataManager.getProductsManager().getProducts());
        System.out.println(productsTable.getColumns());
    }
}
