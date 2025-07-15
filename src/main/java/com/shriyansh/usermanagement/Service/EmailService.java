package com.shriyansh.usermanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async("mailExecutor")
    public void sendOtp(String toEmail,String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Passwrod Reset OTP");
        message.setText("Your OTP for password reset is: "+otp);
        mailSender.send(message);
    }
}
