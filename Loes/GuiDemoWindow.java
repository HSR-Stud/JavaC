// Das Layout der AWT-Bibliothek muss importiert werden
import java.awt.GridLayout;
import javax.swing.*;
@SuppressWarnings("serial")
public class GuiDemoWindow extends JFrame 
{
    public GuiDemoWindow()
    // Konstruktor: "View" (Ansicht) aufbauen und initialisieren.
    {
    	super("Demo Swing GUI");
		// Operation, um das GUI zu schliessen
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Layout
        getContentPane().setLayout(new GridLayout(1, 4)); // 1 Reihe, 4 Spalten
        // 1. Spalte: Temperaturanzeige
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 1)); // 3 Reihen, 1 Spalte
        p1.add(new JLabel("Temperatur", JLabel.CENTER));
        p1.add(new JLabel("27", JLabel.CENTER));
        JPanel p13 = new JPanel();
        p13.setLayout( new GridLayout(1, 2) ); // 1 Z. , 3 2 Sp. 
        JRadioButton rb1 = new JRadioButton("Cels.");
        p13.add(rb1);
        JRadioButton rb2 = new JRadioButton("Fahr.");
        p13.add(rb2);
        ButtonGroup bg = new ButtonGroup();
        rb1.setSelected(true);
        p1.add(p13);
        bg.add(rb1);
        bg.add(rb2);
        getContentPane().add(p1);
        
		// 2. Spalte: Zutatenwahl
        JPanel p2 = new JPanel();
        p2.setLayout( new GridLayout(4, 1) ); // 4 Reihen, 1 Spalte
        p2.add(new JLabel("Zutaten:", JLabel.LEFT));
        p2.add(new JCheckBox("Kapern"));
        p2.add(new JCheckBox("Zwiebeln"));
        p2.add(new JCheckBox("Knoblauch"));
        getContentPane().add(p2);
		
        // 3. Spalte: Kommentarfeld
        JTextArea jta = new JTextArea("Kommentar:", 4, 10);
        JScrollPane jsp = new JScrollPane(jta,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        getContentPane().add(jsp);
        
		// 4. Spalte: Buttons
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(2, 1)); // 2 Reihen, 1 Spalte
        p4.add(new JButton("Abschicken"));
        p4.add(new JButton("Reset"));
        getContentPane().add(p4);

        pack();
        setVisible(true); 
    }
	public static void main(String[] args) 
    {
        GuiDemoWindow app = new GuiDemoWindow();
        app.setLocation(30, 30);
    }
}