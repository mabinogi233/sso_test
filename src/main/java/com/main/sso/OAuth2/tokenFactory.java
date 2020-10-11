package com.main.sso.OAuth2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class tokenFactory {
    //存储于
    //存储token-token实例，
    static Map<String,Token> ttMap = new HashMap<String, Token>();
    //储存ref-token
    static Map<String,Token> rtMap = new HashMap<String, Token>();

    /**
     * 通过code获取新token
     * @param code
     * @return
     */
    public static Token createTokenByCode(String code) {
        //创建token
        String token = createToken(code);
        String refresh_token = createToken(code);
        Token token1 = new Token();
        token1.setCode(code);
        token1.setToken(token);
        token1.setReflesh_token(refresh_token);
        //token为36000000毫秒（10小时）
        token1.setEnd_time(System.currentTimeMillis() + 36000000);
        //加入管理map
        ttMap.put(token, token1);
        rtMap.put(refresh_token, token1);
        return token1;

    }

    /**
     * 通过reflesh_token刷新token
     * @param reflesh_token
     * @return
     */
    public static Token reflesh_token(String reflesh_token){
        //刷新token
        Token token = rtMap.get(reflesh_token);
        if(token!=null) {
            ttMap.remove(token.getToken());
            //token已经失效，需要重新生成
            token.setToken(createToken(token.getCode()));
            //token为36000000毫秒（10小时）
            token.setEnd_time(System.currentTimeMillis() + 36000000);
            //刷新
            ttMap.put(token.getToken(), token);
            rtMap.replace(reflesh_token, token);
            return token;
        }else{
            return null;
        }
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean checkToken(String token){
        Token token1 = ttMap.get(token);
        if(token1!=null){
            if(token1.getEnd_time()>=System.currentTimeMillis()){
                return true;
            }
        }
        return false;
    }


    //MD5和随机数生成token
    private static String createToken(String code){
        return md5(code+getRandomString(15));
    }

    //MD5和随机数生成reflesh_token
    private static String createRefleshToken(String code){
        return md5(md5(code+getRandomString(10))+getRandomString(10));
    }

    private static String md5(String str){
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

    /**
     * 随机生成String
     * @param n
     * @return
     */
    private static String getRandomString(int n){
        //length用户要求产生字符串的长度
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<n;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void printTTmap(){
        for(Map.Entry<String,Token> entry:ttMap.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }

    public static void printRTmap(){
        for(Map.Entry<String,Token> entry:rtMap.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }

}
