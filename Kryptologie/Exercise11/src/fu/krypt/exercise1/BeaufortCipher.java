package fu.krypt.exercise1;

public class BeaufortCipher {

	
	public static String crypt(String plainText, String key)
	{
		char[] KEY = key.toLowerCase().toCharArray();
		char[] PLANTEXT = plainText.toLowerCase().toCharArray();
		int i = 0;
		String cipher = new String();
		for(char c : PLANTEXT)
		{
			//System.out.print((int)KEY[i % key.length()] +":"+ KEY[i% key.length()]+ " " + ((int)c)+":"+ c);
			
			int n = (((int)KEY[i % key.length()] - (int)c) + 26) % 26;
			//System.out.println(" " + n + (char)(n+97));
			
			cipher += String.valueOf((char)(n+97));  
			i++;
		}
		return cipher;
	}
	
	public static String decrypt(String cipherText, String key)
	{
		char[] KEY = key.toLowerCase().toCharArray();
		char[] CIPHERTEXT = cipherText.toLowerCase().toCharArray();
		int i = 0;
		String cipher = new String();
		for(char c : CIPHERTEXT)
		{
			int n = (((int)KEY[i % key.length()] - (int)c) + 26) % 26;
			cipher += String.valueOf((char)(n+97));  
			i++;
		}
		return cipher;
	}
}
