package view;

import model.ProductItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ViewProductScreen extends JFrame implements Page {

    private JPanel panel;
    private JButton addProductButton;
    private JScrollPane productsPanel;

    public ViewProductScreen() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);

        addProductButton.addActionListener(e -> Navigator.goTo(new AddProductScreen()));

        ProductItem[] products = ProductItem.getAll();

        if(products.length == 0) {
            productsPanel.add(new JLabel("No products available"));
        }

        for (ProductItem product : products) {
            Panel productPanel = new Panel();

            productPanel.setBackground(Color.white);
            productPanel.add(new JLabel(String.valueOf(product.getId())));
            productPanel.add(new JLabel(product.getName()));

            BufferedImage myPicture;
            try {
                myPicture = ImageIO.read(product.getPicture());
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                productPanel.add(picLabel);
            } catch (IOException ignored) {}

            productsPanel.add(productPanel);
        }
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
