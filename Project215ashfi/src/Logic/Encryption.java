
package Logic;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Encryption {

   
    int iterationCount = 19;
    String key = "771B229C7331CDDFF98C6DF4DF595E88";
    
    public String encrypt(String text) {
        try {
            SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.out.println("Ecrypted Succesfully!");
            return byteArrayToHexString(encrypted);
        } catch (Exception e) {
            return e.toString();
        }

    }

    public String decrypt(String text) {
        try {
            SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(hexStringToByteArray(text));            
            return new String(decrypted);
        } catch (Exception e) {
            return e.toString();
        }

    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    private String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}
