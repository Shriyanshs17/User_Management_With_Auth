package com.shriyansh.usermanagement.Controller;

import com.shriyansh.usermanagement.Repository.UserRepository;
import com.shriyansh.usermanagement.Service.PasswordResetService;
import com.shriyansh.usermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/password")
public class passwordResetController {

    @Autowired
    PasswordResetService passwordResetService;

    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String,String>payload){
        String email=payload.get("email");
       return passwordResetService.requestOtp(email);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String,String>payload){
       return passwordResetService.resetPassword(payload);
    }

}
