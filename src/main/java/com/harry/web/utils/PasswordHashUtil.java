package com.harry.web.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by chenhaibo on 2017/11/28.
 */
public class PasswordHashUtil {
    private static String hashAlgorithmName = "MD5";
    private static int hashIterations = 3;

    public static String passwordHash(String password, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object credentials = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        return credentials.toString();
    }
}
