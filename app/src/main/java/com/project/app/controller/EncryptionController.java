package com.project.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;
import org.springframework.web.client.RestTemplate;

@RestController
public class EncryptionController {

    // Replace this with your actual public key (received from KeyPair generation)
    static final String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4bLBKiJcsRsGLPb/u4oqjeg2g1myKu6GdOfqBYWdQOm5JBu/w5bIzlRSumGuVx7ucjITAqD1nD+AdkyD2PRIthsgcFF/pnSwWp2ZTNaWt4JSEImtfF4zGe6bQTmMBqagPajVYQPzrxi88IXRw7pNkqwIXmrhlsq/GVq5gorelwxxLM0xyKoT18hNA247EGbg5MuIA6GnHc82PE+R9bZ2cHyhQXKjXsOuYZmFraogTZAyisa98aHY5u/avhoCRC66ZAJkit0hvwwRebQZCdVv/G2grWKT4qXSq9M9ie1rBPCrUCju7tTo5tY0ongs6BhCqaIWuFyiMMfaP7GLc0I2cwIDAQAB";

    @Autowired
    private RestTemplate restTemplate; // Autowire RestTemplate to make HTTP requests

    @PostMapping("/encrypt-credentials")
    public ResponseEntity<String> encryptAndSendCredentials(@RequestBody String credentialsToEncrypt) {
        try {
            byte[] decodedPublicKey = Base64.getDecoder().decode(RSA_PUBLIC_KEY);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPublicKey));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(credentialsToEncrypt.getBytes());
            String encryptedCredentials = Base64.getEncoder().encodeToString(encryptedBytes);

            // Send the encrypted credentials to the UPI server
            String upiServerUrl = "http://localhost:8082/initiate-transaction";
            ResponseEntity<String> response = restTemplate.postForEntity(upiServerUrl, encryptedCredentials, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Credentials encrypted and sent to UPI server successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send encrypted credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Encryption or sending failed");
        }
    }
}