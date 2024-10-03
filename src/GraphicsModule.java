import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsModule {
    JFrame frame;
    KinematicsEngine data;
    JTextField heightInput;
    JTextField timePrecisionInput;

    // Fields for simulation tracking
    JLabel heightValue;
    JLabel timeValue;
    JLabel velocityValue;
    JFrame tF; // New JFrame for simulation tracker

    void startGraphicsModule() {
        data = Main.kinematicsEngine;
        initializeViews();
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

        gbc.gridy = 1; // Move to the next row for the input fields
        gbc.weighty = 0; // Reset weight for input fields

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout()); // Use FlowLayout for input panel

        // Create input fields and labels with larger font
        JLabel heightTag = new JLabel("HEIGHT:");
        heightTag.setFont(new Font("Arial", Font.BOLD, 20));
        heightInput = new JTextField(8);

        JLabel timePrecisionTag = new JLabel("PRECISION:");
        timePrecisionTag.setFont(new Font("Arial", Font.BOLD, 20));
        timePrecisionInput = new JTextField(8);

        // Add components to the input panel
        inputPanel.add(heightTag);
        inputPanel.add(heightInput);
        inputPanel.add(timePrecisionTag);
        inputPanel.add(timePrecisionInput);

        // Add input panel to the frame
        frame.add(inputPanel, gbc);

        gbc.gridy = 2; // Move to the next row for the button
        gbc.weighty = 0.5; // Allow the button to take some vertical space

        // Button to run simulation
        JButton runButton = new JButton("RUN SIMULATION");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if both input fields are not empty and that inputs are valid
                data.setStartHeight(Double.parseDouble(heightInput.getText()));
                data.setTimePrecision(Integer.parseInt(timePrecisionInput.getText()));
                System.out.println("Opening the tracker!");
                openSimulationTracker();

                new Thread(() -> {
                    try {
                        data.runGravitySimulator(KinematicsEngine.GUI);
                        data.resetSimulation();

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });

                // Open the simulation tracker window


                // If user wants to restart the simulation

        // Add the button to the frame
        frame.add(runButton, gbc);

        // Make the main frame visible
        frame.setVisible(true);
    }

    void openSimulationTracker() {
        tF = new JFrame("Simulation Tracker");
        JPanel trackerFrame = new JPanel();
        tF.setSize(300, 200);
        tF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        trackerFrame.setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns

        JLabel currentHeightLabel = new JLabel("Current Height: ");
        heightValue = new JLabel(); // Placeholder for height value
        JLabel currentTimeLabel = new JLabel("Current Time: ");
        timeValue = new JLabel(); // Placeholder for time value
        JLabel currentVelocityLabel = new JLabel("Current Velocity: ");
        velocityValue = new JLabel(); // Placeholder for velocity value

        // Add labels to the tracker frame
        trackerFrame.add(currentHeightLabel);
        trackerFrame.add(heightValue);
        trackerFrame.add(currentTimeLabel);
        trackerFrame.add(timeValue);
        trackerFrame.add(currentVelocityLabel);
        trackerFrame.add(velocityValue);


        // Make the tracker frame visible
        tF.add(trackerFrame);

        tF.setVisible(true);
    }

    void updateSimulationTracker(double height, double time, double velocity) {
        heightValue.setText(height + "m");
        timeValue.setText(time + "s");
        velocityValue.setText(velocity  + "m/s");
    }
}
