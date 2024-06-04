package com.Catering_Server.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Catering_Server.Service.AuthenticationService;
import com.Catering_Server.model.AuthenticationResponse;
import com.Catering_Server.model.User;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        boolean exists = authService.isEmailRegistered(email);
        if (exists) {
            return ResponseEntity.ok("Email is registered");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email is not registered");
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestParam String email) {
        if (authService.isEmailRegistered(email)) {
            String otp = authService.generateOtp();
            authService.sendOtpEmail(email, otp);
            authService.storeOtp(email, otp);
            return ResponseEntity.ok("OTP sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email is not registered");
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (authService.verifyOtp(email, otp)) {
            authService.clearOtp(email);
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }
    

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        if (authService.resetPassword(email, newPassword)) {
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not registered");
        }
    }



}
