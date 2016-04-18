/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 1:29:27 PM  : Apr 17, 2016
 */
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelli
 */
public class Authentication {
        private static final int SALT_BYTE_SIZE = 32;

    /**
     * Create 32 bit random string to use a salt for the hash
     */
    public static String getSalt() {
        byte[] bytes = new byte[SALT_BYTE_SIZE]; //create an array for salt
        try {
            //provide a cryptographically secure random number generator 
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //use SHA-1 message digest algorithm
            random.nextBytes(bytes); //get random salt
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("No such Argument Exception");
        }
        return bytes.toString();
    }
    
    /* Get the hash from supplied password */
    public static String generateHashFromPassword(String password, String salt) {
        String generatedPassword = null;
        try {
            //create message digest  instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add salt bytes to digest
            md.update(salt.getBytes());
            //get the bytes of the hash after digest generates it.
            byte[] genereatedBytesFromPass = md.digest(password.getBytes());

            //convert the bytes in decimal format to hex.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < genereatedBytesFromPass.length; i++) {
                sb.append(Integer.toString((genereatedBytesFromPass[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException nre) {
            System.out.println("NoSuchArument: " + nre.getLocalizedMessage());
        }
        return generatedPassword;
    }

    public static boolean validatePasswordHash(String userPassword, String retrievedPasswordHash, String retrivedSalt) {

        boolean valid = false;

        String createdHash = generateHashFromPassword(userPassword, retrivedSalt);
        if (createdHash.equals(retrievedPasswordHash)) {
            Logger.getLogger(Authentication.class.getName()).log(Level.INFO, "The Hashes match");
            valid = true;
        } else {
            Logger.getLogger(Authentication.class.getName()).log(Level.INFO, "The Hashes dont match");
        }
        return valid;
    }
    
    
    
    public static void main(String[] args) {
//        System.out.println("Salt is: "+Authentication.getSalt());
        System.out.println("Password:lxdeA444/ ="+Authentication.generateHashFromPassword("lxdeA444/", "user2"));
        
    }
    
    
}
