package substitution;
/**
 * Simple substitution decryption
 * @author Justin
 * 2-20-2017
 */

/*
 * Imported functionalities.
*/
import java.util.Scanner;

public class Substitution {

    public static void main(String[] args) {
    
        //Main Header Print Out.
        System.out.println("This is a simple substitution decryptor.");
        // Read cipher text from User.
        Scanner reader = new Scanner(System.in);  
        System.out.println("Please enter a cipher text (A-Z) in all capitols: ");
        String cipher = reader.next();
        
        //Check is cipher text contains valid characters.
        Boolean validCipher = false;
        while(validCipher == false){
            for(int i=0; i < cipher.length(); i++){
                Character c = cipher.charAt(i);
                int asciiValue = (int) c;
                if (asciiValue >= 65 && asciiValue <= 90){
                    validCipher = true;
                }
                else{
                    validCipher = false;
                    i = cipher.length();
                }
            }
            //If not valid characters ask for input again.
            if(validCipher == false){
                System.out.println("That was an invalid cipher. Please enter a cipher text (A-Z) in all capitols: ");
                cipher = reader.next();
            }
        }
        
        //Put valid cipher into character array.
        char[] charCipherArray = cipher.toCharArray();
        //Display valid cipher to user.
        System.out.println("This is your cipher: ");
        for(int i = 0; i < charCipherArray.length; i++){
            System.out.print(charCipherArray[i]);
        }
        
        
        System.out.println("\nThis is the frequency count of each character");
        for(int i = 65; i <= 90; i++){
            int count = 0;
            for(int j = 0; j < charCipherArray.length; j++){
                int asciiValueFreq = (int) charCipherArray[j];
                if(asciiValueFreq == i){
                    count++;
                }
            }
            String temp = Character.toString((char)i);
            System.out.print(temp + count + "\t");
            if (i == 70){
                System.out.print("\n");
            }
            if (i == 75){
                System.out.print("\n");
            }
            if (i == 80){
                System.out.print("\n");
            }
            if (i == 85){
                System.out.print("\n");
            }
        }
        
        String letterInput;
        System.out.println("\n Please signify your desired letter to replace.");
        letterInput = reader.next();
        
        String letterReplace;
        System.out.println("Fill out your desired replacement for each letter: ");
        letterReplace = reader.next();
        
        Character input = letterInput.charAt(0);
        int asciiValueInput = (int) input;
                
        for(int i = 0; i < charCipherArray.length; i++){
            if(charCipherArray[i] == input){
                charCipherArray[i] = letterReplace.charAt(0);
            }
        }
        
        System.out.println("This is your cipher: ");
        for(int i = 0; i < charCipherArray.length; i++){
            System.out.print(charCipherArray[i]);
        }
    }   
}
