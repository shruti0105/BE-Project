package com.project.app.controller;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Keys {

    public static void main(String[] args) {
        try {
            // Generate an RSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get the public and private keys from the generated key pair
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Encode the public and private keys to Base64 strings
            String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            System.out.println("Encoded Public Key: " + encodedPublicKey);
            System.out.println("Encoded Private Key: " + encodedPrivateKey);

            // Decode the Base64 strings back to PublicKey and PrivateKey objects
            byte[] decodedPublicKey = Base64.getDecoder().decode(encodedPublicKey);
            byte[] decodedPrivateKey = Base64.getDecoder().decode(encodedPrivateKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // Decode Public Key
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
            PublicKey decodedPubKey = keyFactory.generatePublic(publicKeySpec);
            System.out.println("Decoded Public Key: " + decodedPubKey);

            // Decode Private Key
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            PrivateKey decodedPrivKey = keyFactory.generatePrivate(privateKeySpec);
            System.out.println("Decoded Private Key: " + decodedPrivKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//public: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vnM95ilcsFycJcaNpA8WpnY2632c/28K72nwJO9sm0jnOS2GTqDELk+rLk5o/cKkgyqtQyt8hJ99lMre34d9hsTRv8dY5Kv6w7GiYIRtHcmIEDSDOAlAUlV5V0A3hni9Yv+f2VdNZvp8hACn3CyTw/wlfdkDHIyYY68G5QZa9gXUPZJM9xJnr9Tx85zMBLCNnWLtpFHhoxrySJ6YlgtSP3yyySVxd0K4K38BRLsluC5+Sv9THNQRCY51wjyoaVx0QrXuJoLjbYvJYbsNRy+59Oxq4P27eRyGWb66RrcthQLGDKtXaTkpguFROp1sBJ220W8iLxA7AzfFY979y7t8wIDAQAB
// private: MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7WEi4PZQoE6LvlPgVPKPmUL1CRJqpW6Q8nV8kAPr1jTFcHa7L4nt7aTFCxT7O+Xj2PCNymG8x7bV43j9Qcrewbd/i3rJZNGwFnCBiane3oFI6V6YkuwhjMpCrKtW97rKPe4xO/TC+KCzLS1rSUUDNZRJQR5hotw/7d2TNNLFBEzvZbQPTaG9HqsWyKM1eudu1hHrlKU3vpfc7cWKWELvS0j2vRfUgC2/e7TnCEz9W1eOen3Fvxk7+3bpWLOnevd+T0fdonFB3p/IoS3bR01srr8v6IZeKabalQotVKyGho4OffrRJYKE1sJ9A3I5L/0SZlDfvWhbE01o3zSgmONn7AgMBAAECggEAAW3Hxt6dXH2N2WhC83KDc38RJR9ofeXXh6jRmsYxI8ygYD9PtSQJ5Lk61B4J/8JyVDpwMMW4hLfMka/sPrJT8/yv35yVh/hON0VnZSjCYfxPND0Kj4QnqQ4FLBuxsUxP61KPZuN9IC4ml/mqpyj8fAngd7acEyG2Sv4wSXUJ7GD0U34yqIgmXCGVhyaukoDM/THMFqsr7DhKUfQqxAkCPkfWokXUE+jAK73/Fpp+2ruVP39HSwkw/soLTSKmw1R73p+G8C1KJDPufs9k7cuEBBk8nlcZlATs/HmAobadfUiIhMPh9PgbHnhdtf34tFRtSxEpHTKuGBqXAPKFpHpV0QKBgQDqv15f2jfhh1bv/80I33bFAdKBEayfbEfLiju/jYVb6rdHpx/uYhqmGPXpaQrU/uvPyh+JrwOGMxnok3TWbIwctIO6nsOuMjYXZPuHea3zRmIUbFuELMCO6JSQgLlA50V6XdcAiTRpoIUAuBzG6IVH3H0UA0CAvrwUs5ifF4UPCwKBgQDMTklKvO/nqf3LP1qg+imnpg+6lyx67MQSeIkt0Dxbk2Jz4i6Nv3rG9cSyHwd/e0lEBLcoCkPruAayV0XUHfyQXmB5NJo9+ZsnS0PGyTGJ5YP7NMzcas3xf3silVJGh/4FLU3fiCSo/VHEw3hQ1mQ+CyJ8bfSPZBC6nR4f5En20QKBgFl53+sBLlOUVprcEbbUFl05OJdhuT5681KtueBlfPNwH7P8C7u9QRMWaIUzOKG/GPr2u9arEtjQGYwAyIQCk9VNKPi1EzkOgnegUg9tXdRge3n1Y1yaAjt2g0ohBnuqq1zc79qQcZ3hRVDk3HMkpciiTjqWkrt/+Aib/AyH1nvJAoGAUNDW5R1/uJ/hqLhS3UKXn51ss16BVc66cKQFf33TolXWqAhcae3IX1NwjxT/HkpiXOmQmeIVo/STkZA8KwABnAn5F3jbWpCV8yEumj+TvPxxDLmCH96PwGuxBakTdprsvJeDKlzsQ3yRD8hSm4dy96fn6FiGWQI4K9lixTLEn0ECgYEAgJfxNxxoiXJlu4AQCbRaMRxBExopsJxuo/flsSOwCVZWQeHKQHZaz0V8I22VSzipjOrq7wNgKiUyDNrtrymE5cIWmiE24L4EwDTw5lzrCf/3VyPOEN6HWiFBx2uqKQoyKI6wXQUVEwN5zN2jvM3ejq/x6Kwub+/ZXyiZ2L0dQyw=
