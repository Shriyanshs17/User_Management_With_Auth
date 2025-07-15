package com.shriyansh.usermanagement.Service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String,String>otpStore=new ConcurrentHashMap<>();
    private final Map<String,Long>otpExpiry=new ConcurrentHashMap<>();

    public String generateOtp(String email){
        String otp=String.valueOf(new Random().nextInt(999999));
        otpStore.put(email,otp);
        otpExpiry.put(email,System.currentTimeMillis()+5*60*1000);
        return otp;
    }

    public boolean verifyOtp(String email,String inputOtp){
        String storedOtp=otpStore.get(email);
        Long expiry=otpExpiry.get(email);
        if(storedOtp==null || expiry==null)
            return false;
        if(System.currentTimeMillis()>expiry)
            return false;
        return storedOtp.equals(inputOtp);
    }

    public void clearOtp(String email){
        otpStore.remove(email);
        otpExpiry.remove(email);
    }
}
