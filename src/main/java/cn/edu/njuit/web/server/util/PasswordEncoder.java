package cn.edu.njuit.web.server.util;

import org.yc.orm.iot.DataPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// use md5 to encode password
public class PasswordEncoder {
    static {
        register();
    }

    public static void register() {
        DataPool.getInstance().register(new PasswordEncoder());
    }

    public static PasswordEncoder getInstance() {
        return DataPool.getInstance().get(PasswordEncoder.class);
    }

    private String bytes2Hex(byte[] bytes) {
        StringBuilder str = new StringBuilder();
        for (byte aByte : bytes) {
            str.append(String.format("%02x", aByte));
        }
        return str.toString();
    }

    public String encode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] digest = md5.digest();
            return bytes2Hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
