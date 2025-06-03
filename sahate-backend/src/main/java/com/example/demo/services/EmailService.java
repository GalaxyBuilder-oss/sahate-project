package com.example.demo.services;

public interface EmailService {
    void sendActivationEmail(String to, String token);
    void sendResetPasswordEmail(String to, String otp);
    void sendSuccessEmail(String to, String message);
    void sendFailedEmail(String to, String reason);
}
