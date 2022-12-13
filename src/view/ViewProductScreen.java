package view;

import javax.swing.*;

public class ViewProductScreen extends JFrame implements Page {

    public ViewProductScreen() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
