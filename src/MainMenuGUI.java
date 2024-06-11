import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main menu GUI
 * Ez az osztály felelős a főmenü GUI-ért
 */
public class MainMenuGUI {
    /** 
     * Main method
     * @param args Argumentumok
     */
    public static void main(String[] args) {
        // Load the settings
        if(!Settings.settingsAreLoaded) Settings.loadSettings();
        Settings.settingsAreLoaded = true;

        // Main menu frame létrehozása
        JFrame frame = new JFrame("Renju");
        frame.setUndecorated(true);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the menu components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        //Change background to black
        panel.setBackground(Color.BLACK);
    }
    /**
     * Elhelyezi a komponenseket a panelen
     * @param panel JPanel
     */

    private static void placeComponents(JPanel panel) {
        // Set the layout to null for absolute positioning
        panel.setLayout(null);

        //Calculate the center of the window
        int centerX = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        int centerY = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

        //Important thing
        int buttonSize = Toolkit.getDefaultToolkit().getScreenSize().height / 17;

        // Create a start button
        JButton startButton = new JButton("Játék indítása");
        startButton.setBounds(centerX-buttonSize*2, centerY-buttonSize*4, buttonSize*4, buttonSize); // Set button position and size
        startButton.setBackground(CustomColors.Buttons);

        // Add action listener for the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle start button click event
                if (Settings.multiPlayer) new MultiplayerGUI();
                else new SingleplayerGUI();
                //Close window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                frame.dispose();
            }
        });

        //Create settings button
        JButton settingsButton = new JButton("Beállítások");
        settingsButton.setBounds(centerX-buttonSize*2, centerY-buttonSize*2, buttonSize*4, buttonSize); // Set button position and size
        settingsButton.setBackground(CustomColors.Buttons);

        // Add action listener for the settings button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle settings button click event
                // Add code to open settings window here
                new SettingsGUI();
                //Close window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                frame.dispose();
            }
        });

        // Create an exit button
        JButton exitButton = new JButton("Kilépés");
        exitButton.setBounds(centerX-buttonSize*2, centerY, buttonSize*4, buttonSize); // Set button position and size
        exitButton.setBackground(CustomColors.Buttons);

        // Add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle exit button click event
                // Save the settings
                Settings.saveSettings();
                // Stop the program
                System.exit(0);
            }
        });

        //Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        //Create background 
        JLabel background = new JLabel(new ImageIcon(Images.originalImage.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, java.awt.Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        //Add background to layered pane
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        //Add buttons to layered pane
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(settingsButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);

        //Add layered pane to panel
        panel.add(layeredPane);


    }
}

