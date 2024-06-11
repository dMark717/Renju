import java.awt.Image;
import java.awt.Toolkit;
import java.io.Console;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Képek
 * Ez az osztály tartalmazza a képeket
 * Random képet választ a háttérnek
 */

public class Images {
    static Image originalImage = Toolkit.getDefaultToolkit().getImage("wood.jpg");
    static JLabel background = new JLabel(new ImageIcon(originalImage.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH)));
}
