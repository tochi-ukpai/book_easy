/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.util;

/**
 *
 * @author tochukwu
 */
public class Encryption {
    public static byte[] computeHash(String x) throws Exception
  {
     java.security.MessageDigest d;
     d = null;
     d = java.security.MessageDigest.getInstance("SHA-1");
     d.reset();
     d.update(x.getBytes());
     return  d.digest();
  }

  public static String byteArrayToHexString(byte[] b){
     StringBuffer sb;
     sb = new StringBuffer(b.length * 2);
     for (int i = 0; i < b.length; i++){
       int v = b[i] & 0xff;
       if (v < 16) {
         sb.append('0');
       }
       sb.append(Integer.toHexString(v));
     }
     return sb.toString().toUpperCase();
  }
  
  public static boolean verifyUserPassword(String providedPassword,
            String securedPassword) throws Exception
    {
        boolean returnValue = false;
        
        // Generate New secure password with the same salt
        String newSecurePassword = byteArrayToHexString(computeHash(providedPassword));
        
        // Check if two passwords are equal
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        
        return returnValue;
    }
  
  public static String generateHashedPassword(String pass) throws Exception{
      return byteArrayToHexString(computeHash(pass));
  }
}
