import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Taschenrechner extends JFrame implements ActionListener
{
	// Lokale Variabeln deklarieren
	private JTextField txfAnzeige_;
	private JButton btnsNumber_[];   // Ziffern 0..9
	private JButton btnChangeSign_;  // Vorzeichen wechseln
	private JButton btnDecPoint_;    // Dezimalpunkt
   
	// Rechen-Operationen:
	private JButton btnAdd_, btnSub_, btnMul_, btnDiv_;
	private JButton btnResult_; // Resultat anzeigen
	private JButton btnClear_;  // Eingabe loeschen
	
    private double oldValue_;
    private char operator_ = '0'; 

    // Zustandsmaschine (Finite state machine - FSM) mit int-Werten:
    private int state_ = IDLE;
    private final static int IDLE = 0; // keine Eingabe
    private final static int ETXF = 1; // letzte Eingabe war im Textfeld
    private final static int EAOP = 2; // letzte Eingabe war arithm. Operator
    private final static int EEQU = 3; // letzte Eingabe war '='
	//------------------------------------------------------------------------
    
    // Elementklasse / Innere Klasse - Ein Objekt der Innere Klasse
    // gehoert zu einem Objekt der aeusseren Klasse
    // Elementklasse: Die Klassendefinition ist DIREKT, UNMITTELBAR
    // in einer anderen Klasse enthalten.
    private class ClearButtonListener implements ActionListener   // Element-Klasse
    {	
    	// Action-Listener fuer 'CLEAR'-Button (Eingabe loeschen).
    	public void actionPerformed(ActionEvent e)
    	{
    		txfAnzeige_.setText("");
    		operator_= '0'; // Wert in 'oldValue' ist ungueltig
    		state_ = IDLE; 
    	}
    }
    // Taschenrechner-Konstruktor
	public Taschenrechner() 
	{
		super("Einfacher Rechner");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 300);  // Breite, Hoehe
		
		// *** GUI-Darstellung
		setLayout( new BorderLayout() );
		{	// Im "Norden - Das Eingabefeld:	
			txfAnzeige_ = new JTextField (30);
			// Horizontal ausgerichtetes Eingabefeld
			txfAnzeige_.setHorizontalAlignment(JTextField.RIGHT);
			// Trick: Umrandung (Border) um txfAnzeige hinzufuegen:
			Border b = BorderFactory.createEmptyBorder(10, 10, 10, 10);
			txfAnzeige_.setBorder(b);
			// txfAnzeige im Norden zur ContentPane hinzufuegen:
			add(txfAnzeige_, BorderLayout.NORTH);
		}
		{	// Im Osten fuer arithmetische Operationen
			JPanel p = new JPanel();
			// GridLayout: 0 Zeilen (d.h. beliebig), 1 Spalte, mit je // 5 Px Abstand
			p.setLayout( new GridLayout(0, 1, 5, 5) );
			p.add( btnAdd_ = new JButton("+") );
			p.add( btnSub_ = new JButton("-") );
			p.add( btnMul_ = new JButton("*") );
			p.add( btnDiv_ = new JButton("/") );
			p.add( btnResult_ = new JButton("=") );
			// Trick: Umrandung (Border) um p hinzufuegen damit Abstand entsteht zu den anderen Elementen:
			Border b = BorderFactory.createEmptyBorder(20, 10, 20, 10);
			p.setBorder(b);
			// p im Osten zur ContentPane hinzufuegen:
			add(p, BorderLayout.EAST);
		}
		{	// Im "Center" Die Zahlen
			JPanel p = new JPanel();
			p.setLayout( new GridLayout(4, 3, 10, 10) ); // 4 Z., 3 Sp. je 10 Px Abstand
			// Array mit 10 Buttons mit den Zahlen
			btnsNumber_ = new JButton[10];
			// Buttons erzeugen:
			for (int i= 0; i < 10; i++)
			{
				btnsNumber_[i] = new JButton("" + i); 
			}
			btnChangeSign_ = new JButton("CHS");  // Vorzeichen wechseln
			btnDecPoint_   = new JButton(".");    // Dezimalpunkt
			// Buttons zu p hinzufuegen:
			p.add(btnsNumber_[7]); p.add(btnsNumber_[8]); p.add(btnsNumber_[9]);
			p.add(btnsNumber_[4]); p.add(btnsNumber_[5]); p.add(btnsNumber_[6]);
			p.add(btnsNumber_[1]); p.add(btnsNumber_[2]); p.add(btnsNumber_[3]);
			p.add(btnsNumber_[0]); p.add(btnDecPoint_);   p.add(btnChangeSign_);
			// Trick: Umrandung (Border) um p hinzufuegen damit Abstand entsteht:
			Border b = BorderFactory.createEmptyBorder(20, 10, 20, 10);
			p.setBorder(b);
			// p im Center zur ContentPane hinzufuegen:
			add(p, BorderLayout.CENTER);
		}
		{	// Im "Sueden":
			btnClear_ = new JButton ("CLEAR"); // Eingabe loeschen
			add (btnClear_, BorderLayout.SOUTH);
		}
		//------------------------------------------------------
		// *** Event-Handling initalisieren:
		// Event-Handler bei Ziffern-Buttons anmelden:
		for (int i= 0; i < 10; i++)
		{
			btnsNumber_[i].addActionListener(this); 
		}
        // Die Aktionen des ClearButton werden an die Innere Klasse uebergeben
        btnClear_.addActionListener(new ClearButtonListener() );
		//
       // Lokale Klasse / Innere Klasse: wird innerhalb 
       // des Konstruktors definiert
        // ChangeSign ButtonListener fuer Vorzeichenfehler 
		class ChangeSignButtonListener implements ActionListener  
		{	
			public void actionPerformed(ActionEvent e)
			{
				String t = txfAnzeige_.getText();
				if (t.indexOf('-') < 0)
					txfAnzeige_.setText('-' + t);
				else
					txfAnzeige_.setText( t.substring(1) );
				state_ = ETXF; // Eingabe fuer Textfeld
			}
		}
        btnChangeSign_.addActionListener(new ChangeSignButtonListener() );
        
        // Innere Klasse: Lokale Klasse DecPoint ButtonListener fuer Dezimalpunkt

		class DecPointButtonListener implements ActionListener
      	{	// lokale Klasse
  	  		public void actionPerformed(ActionEvent e)
  	  		{	// Action-Listener fuer Decimalpunkt.
  	  			String t = txfAnzeige_.getText();
  	  			if (state_ == ETXF)
  	  			{	// Vorherige Eingabe war in Textfeld.
  	  				// Punkt anhaengen falls noch nicht vorhanden:
  	  				if (t.indexOf('.') < 0) txfAnzeige_.setText(t + '.');
  	  			}
  	  			else
  	  			{
  	  				txfAnzeige_.setText("0.");
  	  			}
  	  			state_ = ETXF; // Eingabe war in Textfeld
  	  		}
  		}
        btnDecPoint_.addActionListener( new DecPointButtonListener() );
        

        class ResultButtonListener  implements ActionListener
  		{	// Lokale Klasse - Innere Klasse: Die Klassendefinition ist in einem CODE Block { } enthalten. 
            // Haeufig in einer Memberfunktion
  	  		public void actionPerformed(ActionEvent e)
  	  		{   // Action-Listener fuer '='-Button (Resultat anzeigen).
  	  			if (operator_ == '0') return; // Operator fehlt
  	  			if (state_ != ETXF) return;   // keine Eingabe in Textfeld
  	  			evaluate('=');
  	  			state_ = EEQU; // Eingabe war "= Button"
 	  		}
  		}
        btnResult_.addActionListener( new ResultButtonListener() );
		ActionListener alAdd = new ActionListener()
		
		// Anonyme Klasse: Die Klassendefinition ist ohne Klassenname in einem Ausdruck enthalten.
		// Dadurch wird gleichzeitig ein Objekt davon erzeugt. 
  		{	
  	  		public void actionPerformed(ActionEvent e)
  	  		{	// Action-Listener fuer '+' (Addition).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('+');  // Ausdruck auswerten
  	  			}
  	  			else operator_='+';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
  		};
        btnAdd_.addActionListener( alAdd );
        btnSub_.addActionListener( new ActionListener()
        //Anonyme Klasse: Funktion innerhalb von { } ohne Klassendefinition
  		{   public void actionPerformed(ActionEvent e)
  	  		{	// Action-Listener fuer '-' (Subtraktion).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('-');  // Ausdruck auswerten
  	  			}
  	  			else operator_='-';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
  		});
        //- - - - - - - - - - - - - - - - - - - - - - - - - - -
        btnMul_.addActionListener( new ActionListener()
        //Anonyme Klasse: Funktion innerhalb von { } ohne Klassendefinition
  		{	// anonyme Klasse
  	  		public void actionPerformed(ActionEvent e)
  	  		{   // Action-Listener fuer '*' (Multiplikation).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('*');  // Ausdruck auswerten
  	  			}
  	  			else operator_='*';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
  		});
        //- - - - - - - - - - - - - - - - - - - - - - - - - - -
        btnDiv_.addActionListener( new ActionListener()
        //Anonyme Klasse: Funktion innerhalb von { } ohne Klassendefinition
  		{	
  	  		public void actionPerformed(ActionEvent e)
  	  		{   // Action-Listener fuer '/' (Division).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('/');  // Ausdruck auswerten
  	  			}
  	  			else operator_='/';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
  		});
		// *** Epilog:
		this.setVisible(true);
	}
	// Action-Listener fuer die Ziffern-Buttons.
	public void actionPerformed(ActionEvent e) 
	{
		JButton btn = (JButton)e.getSource();
		String s = btn.getText();
		// char ch = btn.getText().charAt(0); // 1. Zeichen
//		// ch muss Ziffer '0'..'9' sein.
//		int n = ch - '0';  
        String t1 = (state_ != ETXF) ? "" : txfAnzeige_.getText();
        txfAnzeige_.setText(t1 + s);
        state_ = ETXF; // Eingabe war in Textfeld
	}
    //------------------------------------------------------------------------
    private void evaluate(char op)
    {
	//------------------------------------------------------------------------
		Nicht relevant
	//------------------------------------------------------------------------	
    }
    //========================================================================
	public static void main(String[] args) 
	{
		Taschenrechner mainWindow = new Taschenrechner();
		mainWindow.setLocation(20,  20);
	}
	//------------------------------------------------------------------------
}
