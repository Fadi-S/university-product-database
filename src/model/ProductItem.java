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
        }catch (IOException e) {
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

        // TODO implements save to database
        Connection connection ;
        String str="";
        try {
            connection = DataBase.open();
            if (connection != null) {
                Statement statement = connection.createStatement();
                str+="'"+getName()+"',"+this.getPriceInCents()+",'"+getPicturePath()+"'";
                statement.executeUpdate("insert into Products(name, price, picturePath)values ("+str+")");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        return true;
    }

    public static ProductItem[] getAll() {

        // TODO implement get products from database
        Connection connection ;
        ResultSet list ;
        try {
            connection = DataBase.open();
            if (connection != null) {
                Statement statement = connection.createStatement();
                list = statement.executeQuery("select * from Products");
                ArrayList<ProductItem> products=new ArrayList<>() ;
                while (list.next()) {
                    ProductItem productItem=new ProductItem();
                    productItem.isSaved = true;
                    productItem.setId(list.getInt("id"));
                    productItem.setName(list.getString("name"));
                    productItem.setPriceInCents(list.getInt("price"));
                    productItem.setPicturePath(list.getString("picturePath"));
                 products.add(productItem) ;

                }
                return products.toArray(ProductItem[]::new);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
            // TODO set fields
        return null;
        }



    public static void createTableIfNotExist() {
        Connection connection = null;
        try {
            connection = DataBase.open();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("create table if not exists Products (id INTEGER primary key autoincrement ,name varchar(255),price int,picturePath varchar(255))");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
}

