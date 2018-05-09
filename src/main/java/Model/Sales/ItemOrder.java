package Model.Sales;

import Model.Product.Product;

public class ItemOrder {
    private Product product;
    private int amount;

    public ItemOrder(Product _product, int _amount)
    {
        setProduct(_product);
        setAmount(_amount);
    }

    public double calculateCost()
    {
        double cost = getProduct().getSellingPrice() * getAmount() * (1 - (getProduct().getDiscount() / 100));
        return cost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
