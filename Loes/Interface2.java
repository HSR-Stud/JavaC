public interface MyData 
{  // Default-Interface-Methode 
	default void print(String str) 
	{ 	if (!isNull(str))  
		System.out.println("MyData Print::" + str); } 
   // Statische Interface Methode 
	static boolean isNull(String str) 
	{ 	System.out.println("Interface Null Check");  
		if ( str == null ) return true; 
		if ( str.equals("") ) return true; 
		return false;	} }  