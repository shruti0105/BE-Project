package com.project.npci.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class NpciController {

    @PostMapping("/process-transaction")
    public ResponseEntity<String> processTransaction(@RequestBody String data) {
        try {
            // Print the received data from UPI server
            System.out.println("Received Transaction Data from UPI:"+data);


            return ResponseEntity.ok("Data received and printed by NPCI server");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process data");
        }
    }
}