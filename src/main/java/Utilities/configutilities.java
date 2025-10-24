package Utilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Base64;

public class configutilities {
    static public Properties getProps(String filename) {
        Properties myProps = new Properties();
        try {

            InputStream propFile = configutilities.class.getClassLoader().getResourceAsStream(filename+".properties");
            if (propFile == null) {
                System.out.println(filename+"File Not Found");
            } else {
                myProps.load(propFile);
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File Not Found: " + filename);
        } catch (Exception e) {
            // Handle other IO exceptions
            throw new RuntimeException("Error reading properties file: " + filename, e);
        }
        return myProps;
    }
    // Simple Decryption (Base64 for demo)
    public static String decryptPassword(String encryptedPassword) {
        if (encryptedPassword == null || encryptedPassword.trim().isEmpty()) {
            return "";
        }

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword.trim());
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ [WARN] Password not valid Base64, returning as-is: " + encryptedPassword);
            return encryptedPassword;
        }
}
}
