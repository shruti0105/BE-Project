package com.project.app.controller;

import java.security.*;
import java.security.spec.*;

public class Key_format {

    public static void main(String[] args) {

        String rsaPublicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vnM95ilcsFycJcaNpA8WpnY2632c/28K72nwJO9sm0jnOS2GTqDELk+rLk5o/cKkgyqtQyt8hJ99lMre34d9hsTRv8dY5Kv6w7GiYIRtHcmIEDSDOAlAUlV5V0A3hni9Yv+f2VdNZvp8hACn3CyTw/wlfdkDHIyYY68G5QZa9gXUPZJM9xJnr9Tx85zMBLCNnWLtpFHhoxrySJ6YlgtSP3yyySVxd0K4K38BRLsluC5+Sv9THNQRCY51wjyoaVx0QrXuJoLjbYvJYbsNRy+59Oxq4P27eRyGWb66RrcthQLGDKtXaTkpguFROp1sBJ220W8iLxA7AzfFY979y7t8wIDAQAB";
        try {
            byte[] publicKeyBytes = java.util.Base64.getDecoder().decode(rsaPublicKeyString);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            System.out.println("Generated Public Key: " + publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}