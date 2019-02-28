// Beispiel 1: Erstellen eines neuen Strings 
String t = "Hallo "; 
final int N = 10;             
StringBuffer buf= new StringBuffer( N * t.length() );
for (int i= 0; i < N; i++) 
{   buf.append(t); }                                           
String s = buf.toString();
  
// Beispiel 2: Schlechte Nutzung von String 
String s = new String(); 
String t = new String( "Hallo " );  // unsinnig: besser Sting t = "Hallo" 
for (int i= 0; i < 10; i++)      
{   s = s + t;  }   // Ineffizient, da String unveraenderbar ist, muss bei jedem Durchgang ein neues String-Objekt erzeugt werden.