import java.util.Scanner; // neu ab Java 5

public class DemoScanner {
	public static void main(String[] args) 
	{
		Scanner sin = new Scanner(System.in);
		
		System.out.print("Bitte eine ganze Zahl eingeben: ");
		while (! sin.hasNextInt() )
		{	// Das Eingegebene kann nicht als 'int' interpretiert werden.
			String s = sin.nextLine();  // eingebene Zeile einlesen
			System.out.println("Fehler: '" + s  + "' ist keine ganze Zahl!");
			System.out.print("Eine ganze Zahl bitte:  ");
		}
		// Eingegebene Ziffern einlesen und in 'int' umwandeln:
		int x = sin.nextInt();   
		// Rest der eingegebenen Zeile einlesen bzw. konsumieren:
		String s2 = sin.nextLine();

		System.out.println("x= " + x);
		System.out.println("Anzahl nachfolgende Zeichen = " + s2.length() );
}}
