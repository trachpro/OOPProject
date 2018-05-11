package model.product;

import java.util.regex.Pattern;

public class Product {
    private String productID;
    private String name;
    private Enum<Category> category;
    private Enum<Status> status;

    private int quantity;

    private double sellingPrice;
    private double buyingPrice;

    private Enum<Nation> nation;

    private String imageUrl;
    private int discount;


    public Product(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount)
    {
        this.setProductID(_productID);
        this.setName(_name);
        this.setCategory(_category);
        this.setStatus(_status);
        this.setQuantity(_quantity);

        this.setSellingPrice(_sellingPrice);
        this.setBuyingPrice(_buyingPrice);
        this.setNation(_nation);

        this.setImageUrl(_imageUrl);
        this.setDiscount(_discount);
    }

    public Product()
    {
        this.setProductID("PR0001");
        this.setName("");
        this.setCategory(Category.BOOK);
        this.setStatus(Status.ACTIVE);
        this.setQuantity(0);

        this.setSellingPrice(0);
        this.setBuyingPrice(0);
        this.setNation(Nation.UNITED_KINGDOM);

        this.setImageUrl("default.png");
        this.setDiscount(0);
    }

    public Product(Product _p)
    {
        this.setProductID(_p.getProductID());
        this.setName(_p.getName());
        this.setCategory(_p.getCategory());
        this.setStatus(_p.getStatus());
        this.setQuantity(_p.getQuantity());

        this.setSellingPrice(_p.getSellingPrice());
        this.setBuyingPrice(_p.getBuyingPrice());
        this.setNation(_p.getNation());

        this.setImageUrl(_p.getImageUrl());
        this.setDiscount(_p.getDiscount());
    }



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum<Category> getCategory() {
        return category;
    }

    public void setCategory(Enum<Category> category) {
        this.category = category;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Enum<Nation> getNation() {
        return nation;
    }

    public void setNation(Enum<Nation> nation) {
        this.nation = nation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void printDetail()
    {
        System.out.println("ID: "+getProductID());
        System.out.println("Name: "+getName());
        System.out.println("Category: "+getCategory().toString());
        System.out.println("Status: "+getStatus().toString());
        System.out.println("Quantity: "+getQuantity());

        System.out.println("BuyPrice: "+getBuyingPrice());
        System.out.println("SellPrice: "+getSellingPrice());
        System.out.println("Nation: "+getNation().toString());
        System.out.println("Img: "+getImageUrl());
        System.out.println("Discount: "+getDiscount());
    }

    public String toString()
    {
        String result = "";
        result = result.concat(getCategory().toString());
        result = result.concat("|");

        result = result.concat(getProductID());
        result = result.concat("|");

        result = result.concat(getName().toString());
        result = result.concat("|");

        result = result.concat(getStatus().toString());
        result = result.concat("|");

        result = result.concat(Integer.toString(getQuantity()));
        result = result.concat("|");

        result = result.concat(Double.toString(getBuyingPrice()));
        result = result.concat("|");

        result = result.concat(Double.toString(getSellingPrice()));
        result = result.concat("|");

        result = result.concat(getNation().toString());
        result = result.concat("|");

        result = result.concat(getImageUrl());
        result = result.concat("|");

        result = result.concat(Integer.toString(getDiscount()));


        return result;
    }

    public static Product valueOf(String text)
    {
        String[] parts = text.split(Pattern.quote("|"));

        Product p = new Product();
        p.setProductID(parts[1]);
        p.setName(parts[2]);
        p.setCategory(Category.valueOf(parts[0]));
        p.setStatus(Status.valueOf(parts[3]));
        p.setQuantity(Integer.valueOf(parts[4]));
        p.setBuyingPrice(Double.valueOf(parts[5]));
        p.setSellingPrice(Double.valueOf(parts[6]));
        p.setNation(Nation.valueOf(parts[7]));
        p.setImageUrl(parts[8]);
        p.setDiscount(Integer.valueOf(parts[9]));

        return p;
    }
}


