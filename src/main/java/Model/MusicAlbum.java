package Model;

import java.util.List;

public class MusicAlbum extends Product {
    private List<String> listSingers;

    public MusicAlbum(String _productID, String _title, int _remaining, double _salesPrice, double _receivedPrice) {
        super(_productID, _title, _remaining, _salesPrice, _receivedPrice);
    }
}
