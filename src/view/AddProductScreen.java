package view;

import viewmodel.AddProductViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AddProductScreen extends JFrame implements Page {

    private JPanel panel;
    private JTextField nameField;
    private JTextField priceField;
    private JButton browseButton;
    private File picture;
    private JPanel picturePanel;
    private JButton submitButton;

    public AddProductScreen() {
        this.setContentPane(panel);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    String filename = file.getName();
                    String extension = Optional.of(filename)
                            .filter(f -> f.contains("."))
                            .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                            .orElse(null);

                    if(extension == null) return false;

                    return Arrays.asList(new String[]{
                            "png", "jpg", "jpeg", "gif"
                    }).contains(extension) || file.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "images";
                }
            });

            int result = fileChooser.showOpenDialog(this);

            if(result == JFileChooser.APPROVE_OPTION) {
                picture = fileChooser.getSelectedFile();
                try {
                    BufferedImage myPicture = ImageIO.read(picture);
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                    picLabel.setSize(300, 300);
                    picturePanel.removeAll();
                    picturePanel.setSize(300, 300);
                    picturePanel.add(picLabel);
                } catch (IOException ignored) {}
            }
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String price = priceField.getText();

            AddProductViewModel addProduct = new AddProductViewModel(name, price, picture);
            if(addProduct.validateAndAdd()) {
                Navigator.goTo(new ViewProductScreen());

                return;
            }

            JOptionPane.showMessageDialog(this, "Data you entered is invalid");
        });
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
