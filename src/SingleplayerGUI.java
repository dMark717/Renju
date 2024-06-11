import javax.swing.*;
import java.awt.*;

/**
 * Singleplayer GUI
 * Ez az osztály felelős a singleplayer GUI-ért
 */

public class SingleplayerGUI {
    public SingleplayerGUI() {
        Board board = new Board();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Renju");
            frame.setUndecorated(true);
            frame.getContentPane().setBackground(Color.BLACK);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

            //Important thing
            int buttonSize = Toolkit.getDefaultToolkit().getScreenSize().height / 17;

            //Add surrender button
            JButton surrenderButton = new JButton("Feladás");
            surrenderButton.setBounds(0, 0, buttonSize*4, buttonSize); // Set button position and size
            surrenderButton.setBackground(CustomColors.Buttons);
            //frame.add(surrenderButton);

            //Add step back button
            JButton stepBackButton = new JButton("Visszalépés");
            stepBackButton.setBounds(0, buttonSize * 2 - buttonSize/2, buttonSize*4, buttonSize); // Set button position and size
            stepBackButton.setBackground(CustomColors.Buttons);
            //frame.add(stepBackButton);

            
            //Add grid panel
            JPanel gridPanel = new JPanel(new GridLayout(15, 15));
            int startPoint = (Toolkit.getDefaultToolkit().getScreenSize().width - Toolkit.getDefaultToolkit().getScreenSize().height) / 2;
            int endPoint = Toolkit.getDefaultToolkit().getScreenSize().width - startPoint*2;
            gridPanel.setBounds(startPoint + buttonSize, 0, endPoint - 2*buttonSize, Toolkit.getDefaultToolkit().getScreenSize().height -2*buttonSize);
            gridPanel.setOpaque(false);

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    final int x = i;
                    final int y = j;
                    RoundButton button = new RoundButton("");
                    button.setPreferredSize(new Dimension(buttonSize, buttonSize));
                    gridPanel.add(button);
                    button.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            if (board.getTiles()[x][y] == 0) {
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            if (board.getTiles()[x][y] == 0) {
                                button.setBackground(Color.WHITE);
                            }
                        }
                    });
                    button.addActionListener(e -> {
                        System.out.println(x + ", " + y);
                        if (!board.moveIsLegal(x, y, 1)) return;
                        //Save board state
                        button.setBackground(CustomColors.darkPlayer);
                        board.makeMove(x, y, 1);

                        // Check if the game is over
                        int winner = board.checkWin();
                        if (winner != 0) {
                            if (winner == 1) {
                                JOptionPane.showMessageDialog(frame, "A sötét nyert!");
                            } else {
                                JOptionPane.showMessageDialog(frame, "A világos nyert!");
                            }
                            frame.dispose();
                            MainMenuGUI.main(null);
                        } else {
                            // Call opponent move here
                            Coordinate opponentMove = Opponent.makeMove(board);
                            //Draw opponent move on board
                            board.makeMove(opponentMove.x, opponentMove.y, 2);
                            RoundButton NPCbutton = (RoundButton) gridPanel.getComponent(opponentMove.x * 15 + opponentMove.y);
                            NPCbutton.setBackground(CustomColors.lightPlayer);


                            // Check if the game is over
                            winner = board.checkWin();
                            if (winner != 0) {
                                if (winner == 1) {
                                    JOptionPane.showMessageDialog(frame, "A sötét nyert!");
                                } else {
                                    JOptionPane.showMessageDialog(frame, "A világos nyert!");
                                }
                                frame.dispose();
                                MainMenuGUI.main(null);
                            }
                        }
                    });
                }
            }

            //frame.add(gridPanel);
            frame.setVisible(true);
            frame.setFocusable(true);
            frame.requestFocus();

            //Add action listener for the step back button
            stepBackButton.addActionListener(e -> {
                // Handle step back button click event
                board.undoMove();
                board.undoMove();
                //Set all button colors
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        RoundButton button = (RoundButton) gridPanel.getComponent(i * 15 + j);
                        if (board.getTiles()[i][j] == 0) {
                            button.setBackground(Color.WHITE);
                        } else if (board.getTiles()[i][j] == 1) {
                            button.setBackground(CustomColors.darkPlayer);
                        } else {
                            button.setBackground(CustomColors.lightPlayer);
                        }
                    }
                }

            });

            //Add action listener for the surrender button
            surrenderButton.addActionListener(e -> {
                // Handle surrender button click event
                JOptionPane.showMessageDialog(frame, "A világos nyert!");
                frame.dispose();
                MainMenuGUI.main(null);
            });

            //Add layered pane
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            
            //Add background image
            Images.background.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            layeredPane.add(Images.background, JLayeredPane.DEFAULT_LAYER);

            //Add grid panel to the layered pane
            layeredPane.add(gridPanel, JLayeredPane.PALETTE_LAYER);

            //Add buttonst to the top left corner
            layeredPane.add(surrenderButton, JLayeredPane.POPUP_LAYER);
            layeredPane.add(stepBackButton, JLayeredPane.POPUP_LAYER);

            //Add layered pane to the frame
            frame.add(layeredPane);






        });
    }
}
