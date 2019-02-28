public class StringPerformance {

	public static String appendTestString(String s, int n)
	// Append Test with String 
	{
		long startTime = System.currentTimeMillis();
		String tmp = "";
		for (int i= 0; i < n; i++)
		{	tmp = tmp + s;}
		long endTime = System.currentTimeMillis();
		System.out.printf("mit 'String'        = %1$5d ms.\n",  
					endTime - startTime);
		return tmp;
	}
	public static String appendTestStringBuffer(String s, int n)
	// Append Test with StringBuffer:
	{
		long startTime = System.currentTimeMillis();
		StringBuffer tmp = new StringBuffer (s.length() * n);
		for (int i= 0; i < n; i++)
		{	tmp.append(s);}
		String res = tmp.toString();
		long endTime = System.currentTimeMillis();
		System.out.printf("mit 'StringBuffer'  = %1$5d ms.\n",  
				endTime - startTime);
		return res;
	}
	public static String appendTestStringBuilder(String s, int n)
	// Append Test with StringBuilder
	{
		long startTime = System.currentTimeMillis();
		StringBuilder tmp = new StringBuilder (s.length() * n);
		for (int i= 0; i < n; i++)
		{	tmp.append(s);}
		String res = tmp.toString();
		long endTime = System.currentTimeMillis();
		System.out.printf("mit 'StringBuilder' = %1$5d ms.\n",  
				endTime - startTime);
		return res;
	}
	public static void main(String[] args) 
	{
		appendTestString        ("HalloHallo",   10000);
		appendTestString        ("HalloHallo",   10000);
		appendTestStringBuffer  ("HalloHallo",  100000);
		appendTestStringBuffer  ("HalloHallo",  100000);
		appendTestStringBuilder ("HalloHallo", 1000000);
		appendTestStringBuilder ("HalloHallo", 1000000);
		appendTestStringBuffer  ("HalloHallo",  100000);
		appendTestStringBuffer  ("HalloHallo",  100000);
		appendTestStringBuilder ("HalloHallo", 1000000);
		appendTestStringBuilder ("HalloHallo", 1000000);
		appendTestString        ("HalloHallo",   10000);
		appendTestString        ("HalloHallo",   10000);
	}}
