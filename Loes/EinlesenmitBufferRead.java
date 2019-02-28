import java.io.*;
public class TheOldWay
{
public static void main(String[] args) throws Exception
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.print("Bitte Name eingeben: ");
// Einlesen in String:
	String name = br.readLine();
// Ausgeben:
	System.out.println("Hallo " + name);}
} 