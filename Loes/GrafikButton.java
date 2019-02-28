import java.awt.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GrafikButton extends JButton {

    private String textKlein_;
    private String textMitte_;

    public GrafikButton(String textKlein, String textMitte)
    {
        setPreferredSize(new Dimension(200, 150)); // Breite, Hoehe
        this.setBackground(Color.YELLOW);
        this.textKlein_ = textKlein;
        this.textMitte_ = textMitte;
    }

    public void paintComponent (Graphics g) 
    {
        super.paintComponent(g);
        int w= this.getWidth(); int h= this.getHeight();
        g.setColor(Color.blue);
        g.fillOval(10, 10, w-20, h-20 ); // linke, obere Ecke, Breite, Hoehe
        g.setColor(Color.red);
        g.drawString(textKlein_, 20, 20); // linke, untere Ecke von 'textKlein'
        Font font = new Font ("Serif", Font.ITALIC, h/5);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int tw= fm.stringWidth(textMitte_);
        g.drawString( textMitte_, (w-tw)/2, h/2);
    }
}
