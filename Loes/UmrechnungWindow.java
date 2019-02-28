import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class UmrechnungWindow extends JFrame implements ActionListener
{
	// Achtung: Diese Variablen muessen hier stehen, damit man auch 
	// ausserhalb des Konstruktors darauf zugreifen kann!
	private JRadioButton rb2;
	private JRadioButton rb1;
	private JTextArea jta;
	private JTextArea jka;
	private JButton button;
	
	public UmrechnungWindow()
	// Konstruktor
	{
		super("Temperaturumrechnung");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3, 2, 6, 5)); //  3 Zeilen, 2 Spalten mit horizontalem Abstand von 6 Pixels und vertikalem Abstand von 5 Pixels
		// 1. Zeile
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Temperaturumrechnung", JLabel.LEFT));
		getContentPane().add(p1);
		
		// 2. Zeile
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1,3));
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1));
		p3.add(new JLabel("Eingabe", JLabel.LEFT));
		p3.add(new JLabel("Ergebnis", JLabel.LEFT));
		p2.add(p3);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(2,1));
		jta = new JTextArea("25");
		p4.add(jta);
		jka = new JTextArea("77");
		p4.add(jka);
		p2.add(p4);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout(2,1));
		JPanel p6 = new JPanel();
		p6.setLayout(new GridLayout(1,2));
		rb1 = new JRadioButton("C");
		p6.add(rb1);
		rb2 = new JRadioButton("F");
		p6.add(rb2);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		p5.add(p6);
		button = new JButton("Rechne");
		p5.add(button);
		p2.add(p5);
		getContentPane().add(p2);
		
		button.addActionListener(this);
		pack();
		setVisible(true);
		
	}
	public static void main(String[] args)
	{
		UmrechnungWindow app = new UmrechnungWindow();
	}
	// Action Listener
	public void actionPerformed(ActionEvent e)
	{
		String text = jta.getText();
		double temp = Double.parseDouble(text);
		double result;
		if(rb1.isSelected())
		{ 	result = (temp*9.0/5.0) + 32;}
		else
		{ result = (temp-32)*(5.0/9.0); }
		text = String.format("%.2f", result);
		
		jka.setText(text);
	}
	
}