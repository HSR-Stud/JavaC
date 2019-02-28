import java.io.*;

public class TextEinAusgabe
{
   public static void main(String[] args) throws Exception   
   {
      BufferedReader cin;  // Console Input
      cin = new BufferedReader (new InputStreamReader (System.in));
      System.out.print("Bitte Text eingeben: ");
      // Einlesen in String:
      String textZeile = cin.readLine();
      // In Grossbuchstaben wandeln:
      textZeile = textZeile.toUpperCase();
      // Ausgeben:
      System.out.println("Zeile gewandelt= " + textZeile);
      System.out.println("Anzahl Zeichen = " + textZeile.length());
   }
}
