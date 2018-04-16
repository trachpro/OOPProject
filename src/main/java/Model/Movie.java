package Model;

import java.util.List;

public class Movie extends Product {
    private List<String> listActors;
    private String director;

    private String language;
    private String genre;

    public Movie(String _productID, String _title, int _remaining, double _salesPrice, double _receivedPrice) {
        super(_productID, _title, _remaining, _salesPrice, _receivedPrice);
    }
}
