package Model;

import java.util.List;

public class Movie extends Product {
    private List<String> listActors;
    private String director;

    private String language;
    private String genre;
    private float imdbPoint;

    public Movie(String _productID, String _name, String _category, String _nation, int _remaining, double _salesPrice, double _receivedPrice) {
        super(_productID, _name, _category, _nation, _remaining, _salesPrice, _receivedPrice);
    }
}
