package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class ProductsManager {

    private ObservableList<Product> products;

    public ProductsManager()
    {
        products = FXCollections.observableArrayList();
//        getProducts().add(new Book("1", "ABC", "Book", "Vietnam", 342,12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
//        getProducts().add(new Book("2", "ABCD", "Book", "China", 12, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
//        getProducts().add(new Book("3", "ABCE", "Book", "Germany", 23, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));

        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product());
        getProducts().add(new Product("PR1000", "Hihi", Category.BOOK, Status.ACTIVE, 100, 1000000, 1200000, Nation.UNITED_KINGDOM, null, 0));

    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }

//    public ObservableList<Product> search(String _idOrName, String _category, String _nation, boolean _isDeleted)
//    {
//        ObservableList<Product> result;
//
//        if(_isDeleted)
//        result = products.filtered(p -> p.isActive() && p.getCategory().equals(_category) && p.getNation().equals(_nation) && p.getName().contains(_idOrName) || p.getProductID().contains(_idOrName));
//        return result;
//    }
}
