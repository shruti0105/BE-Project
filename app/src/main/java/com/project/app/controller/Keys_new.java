package com.project.app.controller;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Keys_new {

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

//Encoded Public Key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4bLBKiJcsRsGLPb/u4oqjeg2g1myKu6GdOfqBYWdQOm5JBu/w5bIzlRSumGuVx7ucjITAqD1nD+AdkyD2PRIthsgcFF/pnSwWp2ZTNaWt4JSEImtfF4zGe6bQTmMBqagPajVYQPzrxi88IXRw7pNkqwIXmrhlsq/GVq5gorelwxxLM0xyKoT18hNA247EGbg5MuIA6GnHc82PE+R9bZ2cHyhQXKjXsOuYZmFraogTZAyisa98aHY5u/avhoCRC66ZAJkit0hvwwRebQZCdVv/G2grWKT4qXSq9M9ie1rBPCrUCju7tTo5tY0ongs6BhCqaIWuFyiMMfaP7GLc0I2cwIDAQAB
//Encoded Private Key: MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDhssEqIlyxGwYs9v+7iiqN6DaDWbIq7oZ05+oFhZ1A6bkkG7/DlsjOVFK6Ya5XHu5yMhMCoPWcP4B2TIPY9Ei2GyBwUX+mdLBanZlM1pa3glIQia18XjMZ7ptBOYwGpqA9qNVhA/OvGLzwhdHDuk2SrAheauGWyr8ZWrmCit6XDHEszTHIqhPXyE0DbjsQZuDky4gDoacdzzY8T5H1tnZwfKFBcqNew65hmYWtqiBNkDKKxr3xodjm79q+GgJELrpkAmSK3SG/DBF5tBkJ1W/8baCtYpPipdKr0z2J7WsE8KtQKO7u1Ojm1jSieCzoGEKpoha4XKIwx9o/sYtzQjZzAgMBAAECggEABzPotTsGgUJvBoVVcPs+U2wLPVLh/DvDf7yc+9MhSx6KYYwfKRNJUkhfoYIXicE7c9qEP8u6OuPN6jiT5psZLPKdrOf07m9/50xrRYf9pFqc2tlB95ub/uJeAw1emiG8MGuQb7msD7T25qgmoxvxeNJTrAu2KlVLFviNvgxHgUN9i3MJIAQXsgPqMOtwIwAeFIZL1GSmdFVgqiq6cztZqZqlIv8/8M2EjEtW25x20MNM15400V/0O61RR5xnB4QahGH4BSs5lhBdo1/ZRCNEXOFNiZ8xBq4MbCVfNlujDvB2vyafW20fMa4TneWoVffayb2TWxusS6cUOvjcSIP54QKBgQDvLqhQn3fPjV83UEXhHp1jchtQwekud5xmfSFU6379CYo1fBP4qFIa/uHV2NC2czP8PYTzxhJjeTTkgcq1aK8sW1/OztIYWh4mBpBNHpEMJ6m8nVxAYGOniW1LUd6AAKwexx6Ic1KGxDBnqdt92MZXkZp29pX5P1KJQvqWpQYoIQKBgQDxkWG0DNWF0qDfvdoRzA6DjPVUHrdzFP3AUaVCH9hCguC0ZelamGZFn5BwX31BT4tSxRSxb3WOz4WZxWAsnWzS0waJWvMjL3JFUsQ++hLRXHyaU2DDB/i3Qj9Yo8n0rUy6HYcvs9Djagj4Pim4ts0k2uhWyLAmbLsJnyDsb7W8EwKBgQDWv5OL38IY7huhdEdpmq1BToBL/+63M9mPA6Zbsp1v35Lh9qePyMD/T/3AnUy3o7P3M90daqez/G2LBSXVv0oYj2n0Kynjh3YuOhJxy5H70djKapcbje+ryu4AhF87ml7vu2QkqzZbnebADHfwBCQBF9ASI8//2+TlLOgFPeHLQQKBgQCTmAokIjtLuneEfNECUrUDd1plfScE72xi1v+ruR0+9gwo7KN5MOg73dQ4dH9pKhqsBbIw/zLKgMzWORwb5kuZPEogHiuz8cdVx84T1GMu+909xXe3ceVqwtVFaT15e3EHvgbdBHa6nAzC7+g57abFv+J5M8E8mO5mYI2TKGXXdQKBgQDKuNI0EiuJUW9vnQQIvoZCO2IyrHUKaC0hnsv6EnFQRDcXYaw1y/4TndZUIUHydkbqnmZZpXPVZPNP1MXEXoIdkpJ+4LndzJ8QyWQnI4DQpB40PiXV8QY4N9motV57BqXaoqsXGVPnF4n3DCIFwAHRk4b3MXaf2urMjagZU1pSrw==

