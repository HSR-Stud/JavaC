import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

//========================================================================
@SuppressWarnings("serial")
public class TempUmrechnung extends JFrame implements ActionListener, 
	DocumentListener
{
	//---------------------------------------------------------------------
	private JTextField txfEingabe_;
	private JTextField txfAusgabe_;
	private JRadioButton rbtnC_, rbtnF_;
	private JButton btnRechne_;
	//---------------------------------------------------------------------
	public TempUmrechnung(String arg0)  
	{
		// **** Prolog:
		super(arg0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize(290, 120);  // Breite, Hoehe

		// **** GUI-Darstellung aufbauen - nur Darstellung! :

		// Trick, damit es schoener aussieht: Ein zusaetzliches JPanel 'cp' 
		// erzeugen und eine Umrandung (Border) darum hinzufuegen:
		JPanel cp = new JPanel();
		Border b = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		cp.setBorder(b);
		this.getContentPane().add( cp );
		
		cp.setLayout( new GridLayout(0, 1, 8, 2) ); // x Z., 1 Sp.

		// Zeile 1:
		cp.add( new JLabel("Temperatur Umrechnung:") );

		{	// Zeile 2:
			JPanel p = new JPanel();
			cp.add(p);
			p.setLayout(new GridLayout(1, 3, 8, 0) ); // 1 Z., 3 Sp.
			JLabel lblInput = new JLabel("Eingabe:");
			lblInput.setHorizontalAlignment(JLabel.TRAILING);
			p.add(lblInput );
			txfEingabe_ = new JTextField(8);
			p.add(txfEingabe_);
			{	// Buttongruppe:
				JPanel pbg = new JPanel();
				pbg.setLayout( new GridLayout(1, 2) ); // 1 Z., 2 Sp.
				ButtonGroup bg = new ButtonGroup();
				rbtnC_ = new JRadioButton("°C");
				rbtnF_ = new JRadioButton("°F");
				rbtnC_.setSelected(true);
				bg.add(rbtnC_); bg.add(rbtnF_);
				pbg.add(rbtnC_); pbg.add(rbtnF_);
				p.add(pbg);
			}
		}
		{	// Zeile 3:
			JPanel p = new JPanel();
			cp.add(p);
			p.setLayout(new GridLayout(1, 3, 8, 0)); // 1 Z., 3 Sp.
			JLabel label = new JLabel("  Ergebnis= ");
			label.setHorizontalAlignment(JLabel.TRAILING);
			p.add(label );
			txfAusgabe_ = new JTextField(8);
			txfAusgabe_.setEditable(false);
			p.add(txfAusgabe_);
			btnRechne_ = new JButton("Rechne");
			p.add(btnRechne_);
		}
		// **** Event-Handling initalisieren:
		btnRechne_.addActionListener( this );
		txfEingabe_.addActionListener( this );
		txfEingabe_.getDocument().addDocumentListener(this);

		// **** Epilog:
		this.pack();
		this.setVisible(true);
	}
	//---------------------------------------------------------------------
	// **** Implementierung des 'Action-Listener' Interfaces:
	public void actionPerformed(ActionEvent e) 
	// Diese Funktion wird aufgerufen falls (a) der Button "Rechne" 
	// angeklickt wird und (b) falls im Eingabetextfeld die Eingabe mit
	// der Return-Taste abggeschlossen wird.
	{
		try
		{
			double z = Double.parseDouble( txfEingabe_.getText() );
			String s;
			if ( rbtnC_.isSelected() )
			{	// Celsius --> Fahrenheit:
				z = (z * 9.0/5.0) + 32;
				s = String.format("%5.2f °F", z);
			}
			else
			{	// Fahrenheit --> Celsius:
				z = (z - 32) * 5.0 / 9.0;
				s = String.format("%5.2f °C", z);
			}
			txfAusgabe_.setText( s );
		}
		catch (NumberFormatException nfe)
		{
			txfAusgabe_.setText("Fehleingabe!");
		}
	}
	//---------------------------------------------------------------------
	// **** Implementierung des 'DocumentListener' Interfaces:
	public void insertUpdate(DocumentEvent e) 
	// Diese Funktion wird aufgerufen falls ein Zeichen ins Eingabe-Textfeld
	// eingegeben wird.
	{
		//System.out.println("'insertUpdate()' called ...");
		// Inhalt des Ausgabe-Textfeldes loeschen:
		txfAusgabe_.setEditable(true);
		txfAusgabe_.setText("");
		txfAusgabe_.setEditable(false);
	}
	public void removeUpdate(DocumentEvent e) 
	// Diese Funktion wird aufgerufen falls ein Zeichen im Eingabe-Textfeld
	// mit der Delete- oder Backspace-Taste geloescht wird.
	{
		//System.out.println("'removeUpdate()' called ...");
		// Inhalt des Ausgabe-Textfeldes loeschen:
		txfAusgabe_.setEditable(true);
		txfAusgabe_.setText("");
		txfAusgabe_.setEditable(false);
	}
	public void changedUpdate(DocumentEvent e) 
	// Diese Funktion wird aufgerufen falls ein Attribut (z.B. Farbe) 
	// im Eingabe-Textfeld aendert.
	{
		//System.out.println("'changedUpdate()' called ...");
	}
	//---------------------------------------------------------------------
	public static void main(String[] args) 
	{
		TempUmrechnung mw = new TempUmrechnung("Temperatur umrechnen");
		mw.setLocation(100, 150);
	}
	//---------------------------------------------------------------------
}
