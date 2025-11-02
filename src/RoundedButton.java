import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private int cornerRadius = 25; // You can change this for more/less rounding

    public RoundedButton() {
        super();
        setOpaque(false);
        setContentAreaFilled(false); // We paint our own fill
        setFocusPainted(false);      // No dotted line on focus
        setBorderPainted(false);     // No default border
    }
    
    // You can also add this constructor if you set the text directly in code
    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
    
    // You can add this one to set radius from the builder
    public RoundedButton(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the color
        // If button is pressed, use a darker color
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            // On mouse-over, use a brighter color
            g2.setColor(getBackground().brighter());
        } else {
            // Default color
            g2.setColor(getBackground());
        }

        // Fill the rounded rectangle
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.dispose();

        // This call is very important!
        // It paints the button's text (e.g., "Send")
        super.paintComponent(g);
    }
}