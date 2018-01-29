//package decoratortester;

/**
 * Specific class for Caesar Cipher
 */
public class CaesarCipher implements Cipher
{
    static final int CAESAR_OFFSET = 3;
    static final int ALPHABET_SIZE = 26;
    
    public void encrypt(char[] chars, int i, int i1)
    {
        for(int j = i; j < i + i1; j++)
        {
            char c = chars[j];
            if (Character.isLetter(c))
            {
                c = (char) ((int) c + CAESAR_OFFSET);
                if (!Character.isLetter(c))
                    c = (char) ((int) c - ALPHABET_SIZE);
                    // C s no longer in alphabet.  Move it back in.
                chars[j] = c; 
            }
        }  
    }

    public void decrypt(char[] chars, int i, int i2)
    {
        for(int j = i; j < i + i2; j++)
        {
            char c = chars[j];
            if (Character.isLetter(c))
            {
                c = (char) ((int) c - CAESAR_OFFSET);
                if (!Character.isLetter(c))
                    c = (char) ((int) c + ALPHABET_SIZE);
                    // C is no longer in the alphabet.  Move it back in.
                chars[j] = c;  
            }
        }  
    }
}