//package decoratortester;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


/** 
   Tests the EncryptingWriter and DecryptingReader classes
*/
class DecoratorTester
{
    public static void main(String[] args) throws IOException
    {
        String str =  "Sally sells sea shells by the sea shore";
        StringWriter strWrite = new StringWriter();
        strWrite.write(str);
        
        
        EncryptingWriter writer = new EncryptingWriter(strWrite);
        writer.write(str);
        
        String encryptStr = "Vdoob vhoov vhd vkhoov eb wkh vhd vkruh                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ";
        StringReader strRead = new StringReader(encryptStr);
        //strRead.read();
        
        DecryptingReader reader = new DecryptingReader(strRead);
        reader.read();
    } 
}