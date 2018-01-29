//package decoratortester;

import java.io.Reader;
import java.io.IOException;

/**
 * Decorator class to decrypt writing
 */
public class DecryptingReader extends Reader
{
    private final Reader reader;
    
    public DecryptingReader(Reader reader)
    {
        this.reader = reader;
    }

    @Override
    public int read(char[] chars, int i, int i1) throws IOException
    {
        System.out.println("Characters before a decryption has been made: ");
        System.out.println(chars);
        
        int result = reader.read(chars, i, i1);
        CaesarCipher cipher = new CaesarCipher();
        cipher.decrypt(chars, i, i1);
        
        for(int j = 0; j < chars.length; j++)
        {
            System.out.println("Characters after a decryption has been made: ");
            System.out.println(chars[j]);
        }
            
        return result;
    }

    @Override
    public void close() throws IOException
    {
        reader.close();
    }
}