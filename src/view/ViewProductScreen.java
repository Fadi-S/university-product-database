package view;

import interfaces.Model;
import interfaces.ModelChangedListener;
import model.ProductItem;
import viewmodel.ViewProductViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ViewProductScreen extends JFrame implements Page {

    private JPanel panel;
    private JButton addProductButton;
    private JPanel productsPanel;

    private ArrayList<ProductItem> products ;

    public ViewProductScreen() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setMinimumSize(new Dimension(700, 500));

        addProductButton.addActionListener(e -> Navigator.goTo(new AddProductScreen()));

        this.render(null);

        ProductItem.addListener(this::render);
    }

    private void render(Model model) {
        if(model == null) {
            products = new ViewProductViewModel().get();
            renderProducts();
            return;
        }

        products.add((ProductItem) model);
        renderProducts();
    }

    private void renderProducts() {
        productsPanel.removeAll();
        if(products.size() == 0) {
            productsPanel.add(new JLabel("No products available"));

            return;
        }

        GridLayout layout = new GridLayout();
        layout.setVgap(20);
        layout.setHgap(20);
        layout.setColumns(3);
        layout.setRows((int) Math.ceil(products.size() / 3.));
        productsPanel.setLayout(layout);
        productsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        products.forEach(this::product);
    }

    private void product(ProductItem product) {
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
            productPanel.add(new JLabel(new ImageIcon(bufferedImage)));

            productPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        } catch (IOException ignored) {}

        JLabel price = new JLabel("$" + product.getPrice());
        price.setFont(new Font("Ariel", Font.BOLD, 20));
        productPanel.add(price);

        productsPanel.add(productPanel);
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
