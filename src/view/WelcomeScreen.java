package view;

import javax.swing.*;

public class WelcomeScreen extends JFrame implements Page {

    private JPanel panel;
    private JButton addProductButton;
    private JButton viewProductButton;

    public WelcomeScreen() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addProductButton.addActionListener(e -> Navigator.goTo(new AddProductScreen()));

        viewProductButton.addActionListener(e -> Navigator.goTo(new ViewProductScreen()));
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
