import javax.swing.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Settings GUI
 * Ez az osztály felelős a beállítások GUI-ért
 */

public class SettingsGUI {
    public SettingsGUI() {
        // Create the main menu frame
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
     * @param panel
     */
    private static void placeComponents(JPanel panel) {
        // Set the layout to null for absolute positioning
        panel.setLayout(null);

        //Calculate the center of the window
        int centerX = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        int centerY = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

        
        //Important thing
        int buttonSize = Toolkit.getDefaultToolkit().getScreenSize().height / 17;

        //Convert boolean to string
        String stepBacksStr;
        if (Settings.stepBacksEnabled) stepBacksStr = "Be";
        else stepBacksStr = "Ki";

        //Create step backs is allowed button
        JButton stepBacksButton = new JButton("Visszalépések: " + stepBacksStr);
        stepBacksButton.setBounds(centerX-buttonSize*2, centerY-buttonSize*6, buttonSize*4, buttonSize); // Set button position and size
        stepBacksButton.setBackground(CustomColors.Buttons);

        // Add action listener for the step backs button
        stepBacksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle step backs button click event
                // Add code to change step backs here
                Settings.changeStepBacks();
                if (Settings.stepBacksEnabled) stepBacksButton.setText("Visszalépések: Be");
                else stepBacksButton.setText("Visszalépések: Ki");
            }
        });

        // Create difficulty button
        JButton difficultyButton = new JButton("Nehézség: " + Settings.difficultyToString());
        difficultyButton.setBounds(centerX-buttonSize*2, centerY-buttonSize*4, buttonSize*4, buttonSize); // Set button position and size
        difficultyButton.setBackground(CustomColors.Buttons);

        // Add action listener for the difficulty button
        difficultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle difficulty button click event
                // Add code to change difficulty here
                Settings.changeDifficulty();
                difficultyButton.setText("Nehézség: " + Settings.difficultyToString());
            }
        });


        // Create Game mode button
        JButton gameModeButton = new JButton("Játék: " + Settings.gameModeToString());
        gameModeButton.setBounds(centerX-buttonSize*2, centerY-buttonSize*2, buttonSize*4, buttonSize); // Set button position and size
        gameModeButton.setBackground(CustomColors.Buttons);

        // Add action listener for the game mode button
        gameModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle game mode button click event
                // Add code to change game mode here
                Settings.changeGameMode();
                gameModeButton.setText("Játék: " + Settings.gameModeToString());
            }
        });

        // Create Game type button
        JButton gameTypeButton = new JButton("Renju szabályok: " + Settings.gameTypeToString());
        gameTypeButton.setBounds(centerX-buttonSize*2, centerY, buttonSize*4, buttonSize); // Set button position and size
        gameTypeButton.setBackground(CustomColors.Buttons);

        // Add action listener for the game type button
        gameTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle game type button click event
                // Add code to change game type here
                Settings.changeGameType();
                gameTypeButton.setText("Renju szabályok: " + Settings.gameTypeToString());
            }
        });



        // Create a back button
        JButton backButton = new JButton("Vissza");
        backButton.setBounds(centerX-buttonSize*2, centerY+buttonSize*2, buttonSize*4, buttonSize); // Set button position and size
        backButton.setBackground(CustomColors.Buttons);

        // Add action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button click event
                // Add code to go back to the main menu here
                MainMenuGUI.main(null);
                //Close window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                frame.dispose();
            }
        });

        // Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        //Create background
        JLabel background = new JLabel(new ImageIcon(Images.originalImage.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, java.awt.Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        //Add background to layered pane
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        // Add buttons to the layered pane
        layeredPane.add(stepBacksButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(difficultyButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(gameModeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(gameTypeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

        // Add layered pane to panel
        panel.add(layeredPane);
        
    }
}

