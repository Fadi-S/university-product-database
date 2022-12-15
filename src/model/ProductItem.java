package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void setPriceInCents(int price) {
        this.priceInCents = price;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return priceInCents / 100.;
    }

    public double getPriceInCents() {
        return priceInCents;
    }

    public File getPicture() {
        try {
            return new File(new File(".").getCanonicalPath() + picturePath);
        } catch (IOException e) {
            return null;
        }
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public boolean create() {
        if (isSaved) {
            return false;
        }
        isSaved = true;

        try {
            Connection connection = Database.open();
            if (connection == null) return false;
            Statement statement = connection.createStatement();
            String str = "'" + getName() + "'," + this.getPriceInCents() + ",'" + getPicturePath() + "'";
            statement.executeUpdate("insert into Products(name, price, picturePath) values (" + str + ")");

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            return false;
        }
    }

    public static ProductItem[] getAll() {
        try {
            Connection connection = Database.open();
            if (connection == null) return new ProductItem[0];

            Statement statement = connection.createStatement();
            ResultSet productsSet = statement.executeQuery("select * from Products");
            ArrayList<ProductItem> products = new ArrayList<>();
            while (productsSet.next()) {
                ProductItem productItem = new ProductItem();
                productItem.isSaved = true;
                productItem.setId(productsSet.getInt("id"));
                productItem.setName(productsSet.getString("name"));
                productItem.setPriceInCents(productsSet.getInt("price"));
                productItem.setPicturePath(productsSet.getString("picturePath"));
                products.add(productItem);
            }

            return products.toArray(ProductItem[]::new);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return new ProductItem[0];
    }


    public static void createTableIfNotExist() {
        try {
            Connection connection = Database.open();
            if (connection == null) return;

            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists Products (" +
                            "id INTEGER primary key autoincrement," +
                            " name varchar(255)," +
                            "price int," +
                            "picturePath varchar(255)" +
                            ")"
            );
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

