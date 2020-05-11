import java.util.* ;	

	interface Cipher {
		public String encrypt( String alphabet, String plainText, String key);
		public String decrypt( String alphabet, String cipherText, String key);
	}
	
	class Caesar implements Cipher {

		public String encrypt( String alphabet, String plainText, String key){
			String result = new String();				
				for(int i = 0; i < plainText.length(); i++){
					int k = Integer.parseInt(key);
					char ch = plainText.charAt(i);
					int index = alphabet.indexOf(ch);
						
					index = (index + k) % alphabet.length();
					result = result + alphabet.charAt(index);
				}
			return result;					
		 }				
	 
		public String decrypt( String alphabet, String cipherText, String key){
			String result = new String();				
				for(int i = 0; i < cipherText.length(); i++){
					int k = Integer.parseInt(key);
					char ch = cipherText.charAt(i);
					int index = alphabet.indexOf(ch);
					
					index = (index - k) % alphabet.length();
					if(index < 0) { 
						index = index + alphabet.length();					
				}
					result = result + alphabet.charAt(index);
				}
			return result;					
		 }
		
	}

	class Vigenere implements Cipher {

		public String createNewKey( String key, int length ){
			int j = 0;
			String newKey = new String();
			for(int i = 0; i < length; i++){
				if(j == key.length()){
					j = 0;
				}
				newKey += key.charAt(j);
				j++;		
		 	}
			return newKey;
		}

	  	public String encrypt( String alphabet, String plainText, String key){

			String newkey = createNewKey(key, plainText.length());
			String result = new String();
			for(int i = 0; i < plainText.length(); i++){
				char ch1 = plainText.charAt(i);
				char ch2 = newkey.charAt(i);
				int indexOfPlain = alphabet.indexOf(ch1);
				int indexOfKey = alphabet.indexOf(ch2);
				int index = (indexOfPlain + indexOfKey) % alphabet.length();
				
				result = result + alphabet.charAt(index);
			}
			return result;
		}
		
		public String decrypt( String alphabet, String cipherText, String key){

			String newkey = createNewKey(key, cipherText.length());
			String result = new String();
			for(int i = 0; i < cipherText.length(); i++){
				char ch1 = cipherText.charAt(i);
				char ch2 = newkey.charAt(i);
				int indexOfCipher = alphabet.indexOf(ch1);
				int indexOfKey = alphabet.indexOf(ch2);
				int index = (indexOfCipher - indexOfKey) % alphabet.length();
				if(index < 0){
					index += alphabet.length();		
				}

				result = result + alphabet.charAt(index);
			}
			return result;
  		}
	
	}		
	
public class CSCI {
	
	public static void menu(){
		System.out.println("CSCI Cipher Operations:");
		System.out.printf("\n");
		System.out.println("1. Decrypt and verify Caesar cipher");
		System.out.println("2. Decrypt and verify Vigenere cipher");
		System.out.println("3. Display this menu again");
		System.out.println("4. Quit");
	}
	
	public static String equal( String s1, String s2){
		String yes = "true";
		String no = "false";
		if(s1.equals(s2)){
			return yes;
		}
		return no;
  	}
	
	public static void printOutput(String decr, String encr, String eq){
		System.out.println("Plain text: " + decr);
		System.out.println("Plain text re-encrypted for verification: " + encr);
		System.out.println("Are cipher text and encrypted text equal: " +  eq);	
	}
	
	public static void main(String args[]){

		int choice; 			
		menu();

		do{
			System.out.print("Choice : ");
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();

			if(choice != 1 && choice != 2 && choice != 3 && choice != 4){
				System.out.println("Invalid choice.Try again!");
			}
			
			if(choice == 4){
				System.out.println("Have a crime-free day!");
				choice = 4;
			}
		
			if(choice == 1){
				System.out.print("Enter alphabet : ");
					Scanner s1 = new Scanner(System.in);
					String alphabet = s1.nextLine();
				
				System.out.print("Enter cipher text : ");
					Scanner s2 = new Scanner(System.in);
					String cipher = s2.nextLine();
				
				System.out.print("Enter key : ");
					Scanner s3 = new Scanner(System.in);
					String key = s3.nextLine();

				Cipher x = new Caesar(); 
				String decr = x.decrypt( alphabet, cipher, key);
				String encr = x.encrypt(alphabet, decr, key);
				String eq = equal(cipher, encr);
				
				printOutput(decr, encr, eq);
			}
			
			if(choice == 2){
				System.out.print("Enter alphabet : ");
					Scanner s1 = new Scanner(System.in);
					String alphabet = s1.nextLine();
				
				System.out.print("Enter cipher text : ");
					Scanner s2 = new Scanner(System.in);
					String cipher = s2.nextLine();
				
				System.out.print("Enter key : ");
					Scanner s3 = new Scanner(System.in);
					String key = s3.nextLine();

				Cipher x = new Vigenere();
				String decr = x.decrypt( alphabet, cipher, key);
				String encr = x.encrypt(alphabet, decr, key);
				String eq = equal(cipher, encr);
				
				printOutput(decr, encr, eq);
			}
			
			if(choice == 3){
				menu();
			}

		}while(choice != 4);
		
	}
}