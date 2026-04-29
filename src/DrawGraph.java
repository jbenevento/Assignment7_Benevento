import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class DrawGraph {

    public static void main(String[] args) throws Exception {

        // Input
        String s = "ANT CUN BOG AMA DC TOL SAN";

        String[] nodes;

        // Creates an empty array if string is empty
        // Else, splits the input into an array of node labels
        if (s == null || s.trim().isEmpty()) {
            nodes = new String[0];
        } else {
            nodes = s.trim().split("\\s+");
        }

        int n = nodes.length;

        // Create canvas (width x height)
        int W = 900, H = 900;
        BufferedImage img = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // Smoother lines & shapes
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, W, H);

        // Only draw graph if there are nodes
        if (n > 0) {

            // Center the layout
            int cx = W / 2;
            int cy = H / 2;
            int radius = 300;

            // Array to store x & y of each node
            int[] x = new int[n];
            int[] y = new int[n];

            // Place nodes in a circle
            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n;
                x[i] = cx + (int) (radius * Math.cos(angle));
                y[i] = cy + (int) (radius * Math.sin(angle));
            }

            // Edge color black
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2));

            // Draw directed edges between nodes
            for (int i = 0; i < n; i++) {
                int right = (i + (2 * i + 1)) % n;

                int left = (i - (2 * i + 2)) % n;
                if (left < 0) {
                    left += n;
                }

                drawArrow(g, x[i], y[i], x[right], y[right]);
                drawArrow(g, x[i], y[i], x[left], y[left]);
            }

            // Edge thickness
            g.setStroke(new BasicStroke(1));

            int r = 24; // Node radius

            // Node outline
            g.setColor(Color.BLACK);
            for (int i = 0; i < n; i++) {
                g.drawOval(x[i] - r, y[i] - r, 2 * r, 2 * r);
            }

            // Node labels
            FontMetrics fm = g.getFontMetrics();
            for (int i = 0; i < n; i++) {
                String label = nodes[i];
                int tw = fm.stringWidth(label);
                g.drawString(label, x[i] - tw / 2, y[i] + 5);
            }
        }

        g.dispose();

        // Graph image
        ImageIO.write(img, "png", new File("graph.png"));
        System.out.println("Saved graph.png");
    }

    // Draws directed edge
    // Adjusts endpoints so arrow doesn't overlap node circles
    private static void drawArrow(Graphics2D g, int x1, int y1, int x2, int y2) {

        // Compute direction angle from start to end point
        double angle = Math.atan2(y2 - y1, x2 - x1);

        double nodeRadius = 24;

        //A Adjust endpoint so line stops at edge of node
        int x2Adjusted = (int) (x2 - nodeRadius * Math.cos(angle));
        int y2Adjusted = (int) (y2 - nodeRadius * Math.sin(angle));

        //Draw main arrow line
        g.drawLine(x1, y1, x2Adjusted, y2Adjusted);

        int len = 13; // Arrowhead size

        // Calculate arrowhead points
        int xA = (int) (x2Adjusted - len * Math.cos(angle - Math.PI / 6));
        int yA = (int) (y2Adjusted - len * Math.sin(angle - Math.PI / 6));

        int xB = (int) (x2Adjusted - len * Math.cos(angle + Math.PI / 6));
        int yB = (int) (y2Adjusted - len * Math.sin(angle + Math.PI / 6));

        // Draw arrowhead
        g.drawLine(x2Adjusted, y2Adjusted, xA, yA);
        g.drawLine(x2Adjusted, y2Adjusted, xB, yB);
    }
}

/*
Source:
https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
https://docs.oracle.com/javase/tutorial/2d/index.html
https://www.youtube.com/watch?v=KcEvHq8Pqs0
https://www.youtube.com/watch?v=NVbPljlTDjs&list=PLJSII25WrAz44w9JffTBmleWutzjj4CGh&index=2
https://www.youtube.com/watch?v=kdviwg43Y70&list=PLJSII25WrAz44w9JffTBmleWutzjj4CGh&index=3
https://www.youtube.com/watch?v=rfMXBmOX8vw&list=PLJSII25WrAz44w9JffTBmleWutzjj4CGh&index=4
https://stackoverflow.com/questions/72617901/drawing-your-own-graphic-in-java-with-graphics2d
https://www.codejava.net/java-se/graphics/drawing-lines-examples-with-graphics2d
https://stackoverflow.com/questions/56338913/drawing-an-edge-line-between-two-nodes-circles

ChatGPT Graphic2D - explaination and implementation
 */