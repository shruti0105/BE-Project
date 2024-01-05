package com.project.upi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @PostMapping(value = "/convertJsonToXML", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String convertJsonToXml(@RequestBody String jsonInput) {
        JSONObject jsonObject = new JSONObject(jsonInput);
        return XML.toString(jsonObject);
    }
    
    @PostMapping(value = "/convertXMLToJson", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xmlInput) {
        try {
            // Create XML Mapper
            XmlMapper xmlMapper = new XmlMapper();

            // Convert XML to JSON
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode xmlNode = xmlMapper.readTree(xmlInput);
            String jsonOutput = jsonMapper.writeValueAsString(xmlNode);
            return ResponseEntity.ok(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting XML to JSON");
        }
    }
    @Autowired
    private RestTemplate restTemplate; // Autowire RestTemplate to make HTTP requests


//    @PostMapping("/initiate-transaction")
//    public ResponseEntity<String> initiateTransaction(@RequestBody Transaction data) {
//        String npciUrl = "http://localhost:8081/npci/process-transaction";
//
//        ResponseEntity<String> response = restTemplate.postForEntity(npciUrl, data, String.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            return ResponseEntity.ok("Transaction initiated and sent to NPCI successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to initiate transaction");
//        }
//    }
    
    @PostMapping("/initiate-transaction")
    public ResponseEntity<String> processTransaction(@RequestBody String encryptedCredentials) {
        // Print or log the received encrypted credentials
        System.out.println("Received encrypted credentials: " + encryptedCredentials);

        // Forward the encrypted credentials to the NPCI server's process-transaction endpoint
        String npcIProcessTransactionUrl = "http://localhost:8081/process-transaction"; // Change the URL accordingly
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(encryptedCredentials, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(npcIProcessTransactionUrl, request, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Received encrypted credentials and forwarded to NPCI for processing");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to forward encrypted credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception while forwarding data to NPCI");
        }
    }
}