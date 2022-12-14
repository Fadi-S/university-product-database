package viewmodel;

import model.ProductItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AddProductViewModel {

    String name;
    String price;
    File picture;

    public AddProductViewModel(String name, String price, File picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public boolean validateAndAdd()  {
        if(! isValid()) return false;

        ProductItem product = new ProductItem();

        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        try {
            String filename = picture.getName();
            File newPath = new File(new File(".").getCanonicalPath() + "/images/" + filename);
            product.setPicturePath("/images/" + filename);

            Files.copy(picture.toPath(), newPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return product.create();
    }

    public boolean isValid() {
        if(name.length() > 255) {
            return false;
        }

        try {
            double priceDouble = Double.parseDouble(this.price);
            if(priceDouble <= 0) {
                return false;
            }
        }catch (NumberFormatException e) {
            return false;
        }

        if(picture == null || picture.isDirectory() || !picture.exists()) {
            return false;
        }

        return true;
    }

}
