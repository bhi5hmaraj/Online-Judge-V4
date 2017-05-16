import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

class Vis extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -7022004218033866614L;
    BufferedImage img;
    Graphics2D    g;
    boolean       stop;

    Vis() {
        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clear();
        JComponent c = new JComponent() {
            /**
             * 
             */
            private static final long serialVersionUID = -3795125497108613881L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };
        c.setPreferredSize(new Dimension(500, 500));
        add(c);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                stop = false;
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    void vis() {
        repaint();
        stop = true;
        try {
            while (stop)
                Thread.sleep(10);
        } catch (Exception e) {
        }
    }

    void clear() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.BLACK);
    }
    
    public static void main(String[] args) {
        new Vis();
    }
}