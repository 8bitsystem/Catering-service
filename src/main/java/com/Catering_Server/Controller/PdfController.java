package com.Catering_Server.Controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Catering_Server.Service.PdfGenerationService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

//    @PostMapping("/generateAndSend")
//    public ResponseEntity<String> generateAndSendPdf(@RequestBody Map<String, Object> request) throws MessagingException {
//        Long customerId = ((Number) request.get("customerId")).longValue();
//        String email = (String) request.get("email");
//        @SuppressWarnings("unchecked")
//        Map<String, Object> venueDetails = (Map<String, Object>) request.get("venueDetails");
//
//        try {
//            pdfGenerationService.generatePdfFromCustomerId(customerId, email, venueDetails);
//            return ResponseEntity.ok().body("PDF generated and sent successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Failed to generate and send PDF.");
//        }
//    }
    
    
    @PostMapping("/generateAndSend/{email}")
    public ResponseEntity<String> generateAndSendPdf(@RequestBody String htmlContent,@PathVariable String email) throws MessagingException {

    	System.out.println(htmlContent);
      try {
          pdfGenerationService.generatePdfFromCustomerId(htmlContent,email);
          return ResponseEntity.ok().body("PDF generated and sent successfully.");
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(500).body("Failed to generate and send PDF.");
      }
        
    }
    
}
