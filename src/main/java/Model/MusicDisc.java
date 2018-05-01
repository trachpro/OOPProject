package Model;

import java.util.Date;
import java.util.List;

public class MusicAlbum extends Product {
    private List<String> listSingers;
    private Date publicationDate;
    private String language;
    private String genre;

    public MusicAlbum(String _productID, String _name, String _category, String _nation, int _remaining, double _salesPrice, double _receivedPrice) {
        super(_productID, _name, _category, _nation, _remaining, _salesPrice, _receivedPrice);
    }
}
