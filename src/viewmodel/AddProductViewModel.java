package viewmodel;

import model.ProductItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddProductViewModel{

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

        String path = this.saveImage();
        if(path != null) {
            product.setPicturePath(path);
        }

        return product.create();
    }

    private String saveImage() {
        try {
            String filename = picture.getName();
            File newPath = new File(new File(".").getCanonicalPath() + "/images/" + filename);
            newPath.getParentFile().mkdirs();

            String extension = filename.substring(filename.lastIndexOf(".") + 1);

            ImageIcon imageIcon = new ImageIcon(picture.getPath());
            int width = 120;
            int height = 120;//Math.min(500, width * imageIcon.getIconHeight() / imageIcon.getIconWidth());

            BufferedImage bi = new BufferedImage(width, height, "png".equals(extension) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);

            ImageIO.write(bi, extension, newPath);

            return "/images/" + filename;
        }catch (IOException e) {
            return null;
        }
    }

    public boolean isValid() {
        if(name.length() > 255) {
            return false;
        }

        try {
            double priceDouble = Double.parseDouble(this.price);
            if(priceDouble <= 0) {
                throw new NumberFormatException();
            }
        }catch (NumberFormatException e) {
            return false;
        }

        return picture != null && !picture.isDirectory() && picture.exists();
    }

}
