package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        removeUnAvailabeProductsFromList();
        return calculateOrderPrice();
    }

    private double calculateOrderPrice() {
        double orderPrice = 0.0;
        for (Product p : products)
            orderPrice += p.getProductPrice();
        return orderPrice;
    }

    private void removeUnAvailabeProductsFromList() {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            removeUnAvailableProduct(iterator, p);
        }
    }

    private void removeUnAvailableProduct(Iterator<Product> iterator, Product p) {
        if (isUnAvailableProduct(p))
            iterator.remove();
    }

    private boolean isUnAvailableProduct(Product p) {
        return !p.isAvailable();
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
