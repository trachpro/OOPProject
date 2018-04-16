package Model;

public abstract class Product {
    protected String productID;
    protected String title;
    protected int remaining;

    protected double salesPrice;
    protected double receivedPrice;

    public Product(String _productID, String _title, int _remaining, double _salesPrice, double _receivedPrice)
    {
        this.productID = _productID;
        this.title = _title;
        this.remaining = _remaining;
        this.salesPrice = _salesPrice;
        this.receivedPrice = _receivedPrice;
    }
}
