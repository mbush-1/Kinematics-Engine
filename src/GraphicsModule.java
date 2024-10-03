import javax.swing.*;
import java.awt.*;

public class GraphicsModule {
    boolean runningEngine;
    JFrame frame;
    JPanel panel;

    static void startGraphicsModule() {
        GraphicsModule graphicsModule = new GraphicsModule();
        graphicsModule.initializeViews();
    }

    void initializeViews() {
        frame = new JFrame("KinematicsEngine");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout()); // Set GridBagLayout for the frame

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.CENTER; // Center horizontally and vertically
        gbc.insets = new Insets(20, 20, 20, 20); // Add some padding
        gbc.weightx = 1; // Allow horizontal expansion
        gbc.weighty = 0.5; // Allow some vertical expansion

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("BALL DROP SIMULATOR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set larger font
        titlePanel.add(titleLabel);
        frame.add(titlePanel, gbc); // Add title panel

        gbc.gridy = 1; // Move to the next row for the button panel
        gbc.weighty = 1; // Allow the button panel to take more vertical space

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use FlowLayout for button panel

        // Create buttons with larger font
        JLabel height = new JLabel("HEIGHT:");
        height.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField heightInput = new JTextField(8);
        JButton runButton = new JButton("RUN SIMULATION");
        runButton.setFont(new Font("Arial", Font.BOLD, 20));


        // Add buttons to button panel
        buttonPanel.add(runButton);
        buttonPanel.add(height);
        buttonPanel.add(heightInput);

        frame.add(buttonPanel, gbc); // Add button panel

        // Make the frame visible
        frame.setVisible(true);
    }

}
