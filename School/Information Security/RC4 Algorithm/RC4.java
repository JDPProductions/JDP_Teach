package rc4;

/**
 *RC4 Encryption
 * @author Justin
 */
public class RC4 {
    private final byte[] S = new byte[256];
    private final byte[] keyArray = new byte[256];
    private final int length;

    public RC4(final byte[] key) {
        if (key.length > 1 || key.length < 256) {
            length = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                keyArray[i] = key[i % length];
            }
            int j = 0;
            for (int i = 0; i <= 255; i++) {
                j = (j + S[i] + keyArray[i]) & 0xFF;
                byte temp = S[i];
                S[i] = S[j];
                S[j] = temp;
                
                if(i == 255){
                    System.out.println("\n\nAfter initialization:\n");
                    printIndices(i,j);
                }
            }
        } 
        else {
            throw new IllegalArgumentException(
                    "The key given was of invalid length. (1-256).");
        }
    }

    public byte[] encrypt(final byte[] plaintext) {
        final byte[] cipher = new byte[plaintext.length];
        int i = 0, j = 0, keystreamByte, t;
        for (int index = 0; index < plaintext.length; index++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            t = (S[i] + S[j]) & 0xFF;
            keystreamByte = S[t];
            cipher[index] = (byte) (plaintext[index] ^ keystreamByte);
            if(index == 99){
                System.out.println("\n\nAfter 100 permutations after initialization:\n");
                printIndices(i,j);
                printS();
            }
            if(index == 999){
                System.out.println("\n\nAfter 1000 permutations after initialization:\n");
                printIndices(i,j);
                printS();
            }
        }
        return cipher;
    }

    public byte[] decrypt(final byte[] ciphertext) {
        return encrypt(ciphertext);
    }
    
    public void printS(){
        System.out.println("\nPermutation S:");
        for (int i = 0; i < S.length; i++){
            int j = 0;
            j = S[i] & 0xFF;
            String hex = Integer.toHexString(j);
            if(i % 16 == 0){
                if(i==0){
                    System.out.print(hex+"  ");
                }
                else{
                    System.out.print(hex+"\n");
                }
            }
            else{
                System.out.print(hex+"  ");
            }
        }
    }
    
    public void printIndices(int i, int j){
        int k = 0;
        k = S[i] & 0xFF;
        String hex1 = Integer.toHexString(k);
        int l = 0;
        l = S[j] & 0xFF;
        String hex2 = Integer.toHexString(l);
        System.out.println("I index: "+hex1+" J index: "+hex2);
    }
}