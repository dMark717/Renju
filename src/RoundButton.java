import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Kerek gomb
 * Ez az osztály tartalmazza a kerek gombot
 * A JButton osztályból származik le
 */

class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);

        setBackground(Color.WHITE);
        setFocusable(false);

        // Enlarge the button to make it a circle
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        // Set content area filled to false for custom painting
        setContentAreaFilled(false);
    }

    
    /** 
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }

        // Fill the oval shape
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        // Call the superclass method to paint the label and other components
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);

        // Draw the border of the oval shape
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hit detection for the round shape
    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        
        // If the button has changed size, make a new shape object
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
