package com.example.demo.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    // @Autowired
    // private Environment env;

    @Override
    public void sendActivationEmail(String to, String token) {
        try {
            Context context = new Context();
            context.setVariable("token", token);
            String html = templateEngine.process("activation.html", context);

            sendHtmlEmail(to, "Activate Your Account", html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendResetPasswordEmail(String to, String otp) {
        try {
            Context context = new Context();
            context.setVariable("otp", otp);
            String html = templateEngine.process("reset-password.html", context);

            sendHtmlEmail(to, "Reset Your Password", html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSuccessEmail(String to, String message) {
        try {
            Context context = new Context();
            context.setVariable("message", message);
            String html = templateEngine.process("success.html", context);

            sendHtmlEmail(to, "Success Notification", html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFailedEmail(String to, String reason) {
        try {
            Context context = new Context();
            context.setVariable("reason", reason);
            String html = templateEngine.process("failed.html", context);

            sendHtmlEmail(to, "Failed Notification", html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom("noreply@sahate.com");
        mailSender.send(message);
    }
}

