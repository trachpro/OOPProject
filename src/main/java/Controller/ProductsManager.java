package Controller;

import Model.Book;
import Model.Product;
import javafx.collections.ObservableList;

import java.util.Date;

public class ProductsManager {

    private ObservableList<Product> products;

    public ProductsManager()
    {
        getProducts().add(new Book("1", "ABC", 123, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
        getProducts().add(new Book("2", "ABCD", 123, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
        getProducts().add(new Book("3", "ABCE", 123, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }
}
