package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;


@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}

@RestController
class ServerController {
    @RequestMapping("/hash")
    public String myHash() {
        String data = "Simin Mottahedi";

        // Step 1: Create MessageDigest object
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        // Step 2: Initialize the object with an appropriate algorithm cipher

        // Step 3: Generate hash value
        byte[] hash = md.digest(data.getBytes());

        // Step 4: Convert hash value to hex
        String hashHex = bytesToHex(hash);

        // Step 5: Create response
        String response = "<p>Data: " + data + "<br>";
        response += "Hash Value: " + hashHex;

        return response;
    }

    // Helper method to convert bytes to hexadecimal string
    private String bytesToHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }
}
