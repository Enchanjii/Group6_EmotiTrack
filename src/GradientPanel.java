import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    // These are the colors from the "cooler" design you liked
    private Color color1 = new Color(230, 239, 255); // Light blue top
    private Color color2 = new Color(240, 230, 255); // Light lavender bottom

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Turn on anti-aliasing for a smoother look
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth();
        int h = getHeight();

        // Create a vertical gradient from top (color1) to bottom (color2)
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}