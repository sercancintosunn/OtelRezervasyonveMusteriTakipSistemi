package com.otel.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {

    public static String hashSifre(String sifre) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(sifre.getBytes());

            StringBuilder sb = new StringBuilder();
            for(byte b : messageDigest){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("MD5 algoritmasi bulunanmadı",e);
        }
    }

    public static boolean sifreDogrulama(String girilensifre,String haslenmisSifre){
        String girilenSifreHash = hashSifre(girilensifre);
        return girilenSifreHash.equals(haslenmisSifre);
    }

}
