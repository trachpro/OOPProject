package model.receipts;

import model.product.Product;

public class ItemOrder {
    private Product product;
    private int amount;

    public ItemOrder(Product _product, int _amount)
    {
        setProduct(_product);
        setAmount(_amount);
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
