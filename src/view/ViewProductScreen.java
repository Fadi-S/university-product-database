package view;

import model.ProductItem;
import viewmodel.ViewProductViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        this.setMinimumSize(new Dimension(550, 500));

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
        productsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (ProductItem product : products) {
            JPanel productPanel = new JPanel();

            BoxLayout productLayout = new BoxLayout(productPanel, BoxLayout.Y_AXIS);
            productPanel.setLayout(productLayout);
            productPanel.setBackground(Color.white);
            productPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel title = new JLabel("#" + product.getId() + " - " + product.getName());
            title.setFont(new Font("Ariel", Font.PLAIN, 16));
            productPanel.add(title);
            productPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            try {
                BufferedImage bufferedImage = ImageIO.read(product.getPicture());
                Image image = bufferedImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                productPanel.add(new JLabel(new ImageIcon(image)));

                productPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            } catch (IOException ignored) {}

            JLabel price = new JLabel("$" + product.getPrice());
            price.setFont(new Font("Ariel", Font.BOLD, 20));
            productPanel.add(price);

            productsPanel.add(productPanel);
        }
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
