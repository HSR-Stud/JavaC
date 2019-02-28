import java.awt.*;
@SuppressWarnings("serial")
public class MyAwtWindow extends Frame
{
    // Attribute:
	private int anzahl_ = 0;  
	private Label label_;
	private Button incButton_;
	private Button decButton_;
	//-----------------------------------------------------------------------
	public int getAnzahl()
	{
		return anzahl_;
	}
	public void setAnzahl(int n)
	{
		anzahl_ = n;
	}
	//-----------------------------------------------------------------------
	public void setLabelText (String t)
	{
		label_.setText(t);
	}
	//-----------------------------------------------------------------------
	public MyAwtWindow()
	// Konstruktor
	{
		// *** Prolog:
		super ("Ihr Name"); 
		setSize(300, 200); // Breite, Hoehe

		// *** Ansicht (Darstellung) aufbauen:
		label_ = new Label("Anzahl = " + anzahl_, Label.CENTER);
		label_.setBackground(Color.GREEN);
		incButton_ = new Button("Inkrement");
		decButton_ = new Button("Dekrement");

		setLayout(new GridLayout(1, 3)); // 1 Zeile, 3 Spalten
		add(incButton_);
		add(label_);
		add(decButton_);

		// *** Event-Handling initialisieren:
		IncButtonListener ibl = new IncButtonListener( this );
		incButton_.addActionListener( ibl );
		// Alternative: mit anonymem Objekt -> Objekt ohne Namen
		// Direkte Uebergabe der Klasse an ein Objekt (hier ans Button-Objekt)
		// incButton_.addActionListener( new IncButtonListener(this) );	
		decButton_.addActionListener( new DecButtonListener(this) );
		addWindowListener(new MyWindowListener() );
		
		// pack(); // Minimalgroesse einnehmen
		setVisible(true);
	}
	//-----------------------------------------------------------------------
	public static void main(String[] args)
	{
		MyAwtWindow mw1 = new MyAwtWindow();
		mw1.setLocation(10, 10);
		MyAwtWindow mw2 = new MyAwtWindow();
		mw2.setLocation(100, 150);
	}
}