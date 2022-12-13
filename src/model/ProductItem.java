package model;

import java.io.File;
import java.util.ArrayList;

public class ProductItem {
    private int id;
    private String name;
    private int priceInCents;
    private String picturePath;

    private boolean isSaved = false;

    public void setName(String name) {
        this.name = name;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setPrice(double price) {
        this.priceInCents = (int) Math.round(price * 100);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return priceInCents / 100.;
    }

    public File getPicture() {
        return new File(picturePath);
    }

    public boolean create() {
        if(isSaved) {
            return false;
        }
        isSaved = true;

        // TODO implements save to database
        return true;
    }

    public static ProductItem[] getAll() {
        // TODO implement get products from database
        ArrayList<Object> list = new ArrayList<>();
        ProductItem[] products = new ProductItem[list.size()];

        for (int i=0; i < list.size(); i++) {
            products[i] = new ProductItem();
            products[i].isSaved = true;

            // TODO set fields
        }

        return products;
    }

    public static void createTableIfNotExist() {
        // Create table in database
    }
}
