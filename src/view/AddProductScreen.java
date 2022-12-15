package view;

import viewmodel.AddProductViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
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

        JRootPane rootPane = SwingUtilities.getRootPane(panel);
        rootPane.setDefaultButton(submitButton);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if(file.isDirectory()) return true;

                    String filename = file.getName();
                    String extension = Optional.of(filename)
                            .filter(f -> f.contains("."))
                            .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                            .orElse(null);

                    if(extension == null) return false;

                    return Arrays.asList(new String[]{
                            "png", "jpg", "jpeg", "gif"
                    }).contains(extension);
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
                    Image image = myPicture.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    JLabel picLabel = new JLabel(new ImageIcon(image));
                    picturePanel.add(picLabel);
                    picturePanel.updateUI();
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
