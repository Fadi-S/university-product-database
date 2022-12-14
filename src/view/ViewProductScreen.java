package view;

import model.ProductItem;
import viewmodel.ViewProductViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ViewProductScreen extends JFrame implements Page {

    private JPanel panel;
    private JButton addProductButton;
    private JPanel productsPanel;

    public ViewProductScreen() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);

        addProductButton.addActionListener(e -> Navigator.goTo(new AddProductScreen()));

        ProductItem[] products = new ViewProductViewModel().get();

        if(products.length == 0) {
            productsPanel.add(new JLabel("No products available"));
        }

        GridLayout layout = new GridLayout();
        layout.setVgap(20);
        layout.setHgap(20);
        layout.setColumns(3);
        layout.setRows(products.length / 3  + 1);
        productsPanel.setLayout(layout);
        productsPanel.setAutoscrolls(true);
        for (ProductItem product : products) {
            GridLayout panelLayout = new GridLayout();
            panelLayout.setColumns(1);
            panelLayout.setRows(4);
            Panel productPanel = new Panel();
            productPanel.setLayout(panelLayout);
            productPanel.setBackground(Color.white);

            JLabel id = new JLabel("#" + product.getId());
            productPanel.add(id);

            JLabel name = new JLabel(product.getName());
            name.setForeground(Color.black);
            productPanel.add(name);

            JLabel price = new JLabel("$" + product.getPrice());
            price.setForeground(Color.black);
            productPanel.add(price);

            productPanel.setSize(80, 80);

            try {
                BufferedImage myPicture = ImageIO.read(product.getPicture());
                Image image = myPicture.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JLabel picLabel = new JLabel(new ImageIcon(image));
                productPanel.setSize(60, 60);
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
