//package decoratortester;

import java.io.Writer;
import java.io.IOException;

/**
 * Decorator class to encrypt writing
 */
public class EncryptingWriter extends Writer
{
    private final Writer writer;
    
    public EncryptingWriter(Writer writer)
    {
        this.writer = writer;
    }

    public void write(char[] chars, int i, int i1) throws IOException
    {
        System.out.println("Characters before an encryption has been made: ");
        System.out.println(chars);
        
        CaesarCipher cipher = new CaesarCipher();
        cipher.encrypt(chars, i, i1);
        
        System.out.println("Characters after an encryption has been made: ");
        System.out.println(chars);
        
        
        writer.write(chars, i, i1);
    }

    @Override
    public void close() throws IOException
    {
        writer.close();
    }

    @Override
    public void flush() throws IOException
    {
        writer.flush();
    }
}