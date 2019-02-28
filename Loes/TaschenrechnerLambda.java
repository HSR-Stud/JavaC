import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class TaschenrechnerLambda extends JFrame implements ActionListener
{
	//------------------------------------------------------------------------
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
	public TaschenrechnerLambda() 
	{
		// *** Prolog:
		super("Einfacher Rechner");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 300);  // Breite, Hoehe
		
		//------------------------------------------------------
		// GUI - Teil wie bei Taschenrechner
		//------------------------------------------------------

		// *** Event-Handling initalisieren:

		// Event-Handler bei Ziffern-Buttons anmelden:
		for (int i= 0; i < 10; i++)
		{
			btnsNumber_[i].addActionListener(this); 
		}
        btnClear_.addActionListener
        (	// Lambda-Ausdruck:
        	event ->
        	{	// Action-Listener fuer 'CLEAR'-Button (Eingabe loeschen).
        		txfAnzeige_.setText("");
        		operator_= '0'; // Wert in 'oldValue' ist ungueltig
        		state_ = IDLE; 
        	}
        );
        btnChangeSign_.addActionListener
        (	// Lambda-Ausdruck:
        	event ->
			{	// Action-Listener fuer 'CHS' (Vorzeichen wechseln).
				String t = txfAnzeige_.getText();
				if (t.indexOf('-') < 0)
					txfAnzeige_.setText('-' + t);
				else
					txfAnzeige_.setText( t.substring(1) );
				state_ = ETXF; // Eingabe fuer Textfeld
			}
        );
        btnDecPoint_.addActionListener
        (	// Lambda-Ausdruck:
        	event ->
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
        );
        btnResult_.addActionListener
        ( 	// Lambda-Ausdruck:
        	event ->
        	{   // Action-Listener fuer '='-Button (Resultat anzeigen).
  	  			if (operator_ == '0') return; // Operator fehlt
  	  			if (state_ != ETXF) return;   // keine Eingabe in Textfeld
  	  			evaluate('=');
  	  			state_ = EEQU; // Eingabe war "= Button"
 	  		}
        );
        btnAdd_.addActionListener
        (	// Lambda-Ausdruck:
        	event ->
        	{	// Action-Listener fuer '+' (Addition).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('+');  // Ausdruck auswerten
  	  			}
  	  			else operator_='+';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}        	
   		);
        btnSub_.addActionListener
        (	// Lambda-Ausdruck:
	        event -> 
	        {	// Action-Listener fuer '-' (Subtraktion).
				if (state_ == IDLE) return;
				if (state_ == ETXF)
				{	// vorherige Eingabe war in Textfeld.
					evaluate('-');  // Ausdruck auswerten
				}
				else operator_='-';  // Operator sich merken
				state_ = EAOP; // Eingabe war arithm. Operator
			}
        );
        btnMul_.addActionListener
        (	// Lambda-Ausdruck:
   	        event -> 
  	  		{   // Action-Listener fuer '*' (Multiplikation).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('*');  // Ausdruck auswerten
  	  			}
  	  			else operator_='*';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
        );
        btnDiv_.addActionListener
        (	// Lambda-Ausdruck:
        	event ->
  	  		{   // Action-Listener fuer '/' (Division).
  	  			if (state_ == IDLE) return;
  	  			if (state_ == ETXF)
  	  			{	// vorherige Eingabe war in Textfeld.
  	  				evaluate('/');  // Ausdruck auswerten
  	  			}
  	  			else operator_='/';  // Operator sich merken
  	  			state_ = EAOP; // Eingabe war arithm. Operator
  	  		}
  		);
		this.setVisible(true);
	}
	//------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) 
	// Action-Listener fuer die Ziffern-Buttons.
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
    private void evaluate(char op) {
	//------------------------------------------------------
	// Funktion Evaluate - wie bei Taschenrechner
	//------------------------------------------------------
    }
    //========================================================================
	public static void main(String[] args) 
	{
		TaschenrechnerLambda mainWindow = new TaschenrechnerLambda();
		mainWindow.setLocation(20,  20);
	}}
