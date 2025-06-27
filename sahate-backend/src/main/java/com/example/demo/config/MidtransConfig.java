package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MidtransConfig {
    
    @Value("${midtrans.server.key}")
    private String serverKey;
    
    @Value("${midtrans.client.key}")
    private String clientKey;
    
    @Value("${midtrans.is.production}")
    private boolean isProduction;

    public String getServerKey() {
        return serverKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public boolean isProduction() {
        return isProduction;
    }
}