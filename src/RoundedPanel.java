import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * A JPanel with rounded corners.
 */
public class RoundedPanel extends JPanel {

    /** The radius of the rounded corners. */
    private int cornerRadius;

    /**
     * Creates a new RoundedPanel with the specified corner radius.
     *
     * @param radius the radius of the rounded corners
     */
    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        
        // This is crucial! It makes the panel transparent,
        // so the rounded corners won't have a solid background.
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // We don't call super.paintComponent(g) because we are
        // painting our own custom background.
        
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Turn on anti-aliasing for smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                              RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set the color to the panel's background
        g2.setColor(getBackground());
        
        // Draw the rounded rectangle background
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        
        g2.dispose();
        
        // The Swing framework will automatically call paintChildren()
        // after this method, so any components added to this panel
        // will be drawn on top of our rounded background.
    }
}