package com.main.sso.OAuth2;


//MD5加密包
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Random;

//生成唯一code
public class codeFactory {
    /**
     * 生成唯一的code
     * @param username
     * @param password
     * @return
     */
    public static String getCode(String username,String password){
        return getMD5Str(username);
    }



    /**
     * MD5散列，生成唯一ID
     * @param str
     * @return
     */
    private static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }
}
