package viewmodel;

import model.ProductItem;

import java.io.File;

public class AddProductViewModel {

    String name;
    String price;
    File picture;

    public AddProductViewModel(String name, String price, File picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public boolean validateAndAdd() {
        if(! isValid()) return false;

        ProductItem product = new ProductItem();

        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        product.setPicturePath(picture.getPath());

        return product.create();
    }

    public boolean isValid() {
        if(name.length() > 255) {
            return false;
        }

        try {
            Double.parseDouble(price);
        }catch (NumberFormatException e) {
            return false;
        }

        if(picture == null || picture.isDirectory() || !picture.exists()) {
            return false;
        }

        return true;
    }

}
