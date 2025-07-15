package com.shriyansh.usermanagement.Service;

import com.shriyansh.usermanagement.Repository.UserRepository;
import com.shriyansh.usermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PasswordResetService {

    @Autowired
    private OtpService otpService;

    @Autowired
    private  EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> requestOtp(String email) {
       User  user=userRepository.findByUsername(email);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        String otp=otpService.generateOtp(email);
        emailService.sendOtp(email,otp);
        return ResponseEntity.ok("OTP sent to email");
    }


    public ResponseEntity<?> resetPassword(Map<String, String> payload) {
        String email=payload.get("email");
        String otp=payload.get("otp");
        String newPassword=payload.get("newPassword");

        if(!otpService.verifyOtp(email,otp)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");

        }

        User user=userRepository.findByUsername(email);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        otpService.clearOtp(email);

        return ResponseEntity.ok("Password reset successful");
    }
}
