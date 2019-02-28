import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//===========================================================================
@SuppressWarnings("serial")
public class IncDecWindow extends JFrame
{
	//-----------------------------------------------------------------------
	// Attribute:
	private int anzahl_ = 0;  
	private JLabel lblTop_;
	private JLabel lblBottom_;
	private JButton incButton_;
	private JButton decButton_;
	//-----------------------------------------------------------------------
	public IncDecWindow() 
	// Konstruktor
	{
		// **** Prolog:
		super ("Inc - Dec mit grafischen Buttons"); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//-------------------------------------------------------------------
		// **** Ansicht (Darstellung) aufbauen:
		lblTop_ = new JLabel("Anzahl = " + anzahl_, JLabel.CENTER);
		lblTop_.setBackground(Color.GREEN); lblTop_.setOpaque(true);
		lblBottom_ = new JLabel("Number  = " + anzahl_, JLabel.CENTER);
		lblBottom_.setBackground(Color.GREEN); lblBottom_.setOpaque(true);
		incButton_ = new GrafikButton("Inc", "Inkrement");
		decButton_ = new GrafikButton("Dec", "Dekrement");
		
		setLayout( new BorderLayout() );
		
		add(lblTop_, BorderLayout.NORTH);
		add(lblBottom_, BorderLayout.SOUTH);
		JPanel p = new JPanel();
		p.setLayout( new GridLayout(1, 2) );
		add(p, BorderLayout.CENTER);
		p.add(incButton_);
		p.add(decButton_);
		
		//-------------------------------------------------------------------
		// **** Event-Handling initialisieren:
		incButton_.addActionListener( new ActionListener()
		{	// Action-Listener fuer Inc-Button (anonyme Klasse)
			public void actionPerformed(ActionEvent e) 
			{
				anzahl_++;
				lblTop_.setText("Anzahl = " + anzahl_);
				lblBottom_.setText("Number = " + anzahl_);
			}
		});
		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		decButton_.addActionListener( new ActionListener()
		{	// Action-Listener fuer Dec-Button (anonyme Klassen)
			public void actionPerformed(ActionEvent e) 
			{
				anzahl_--;
				lblTop_.setText("Anzahl = " + anzahl_);
				lblBottom_.setText("Number = " + anzahl_);
			}
		});
		//-------------------------------------------------------------------
		
		// **** Epilog:
		pack(); // Minimalgroesse einnehmen
		setVisible(true);
	}
	//-----------------------------------------------------------------------
	public static void main(String[] args)
	{
		IncDecWindow mw1 = new IncDecWindow();
		mw1.setLocation(10,  10);
//		IncDecWindow mw2 = new IncDecWindow();
//		mw2.setLocation(100, 150);
	}
	//-----------------------------------------------------------------------
}
//===========================================================================
