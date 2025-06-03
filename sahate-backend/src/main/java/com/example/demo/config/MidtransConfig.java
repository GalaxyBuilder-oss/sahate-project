package com.example.demo.config;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransSnapApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MidtransConfig {

    @Value("${midtrans.serverKey}")
    private String serverKey;

    @Value("${midtrans.clientKey}")
    private String clientKey;

    @Bean
    public MidtransSnapApi midtransSnapApi() {
        Config snapApiConfig = Config.builder()
                .setServerKey(serverKey)
                .setClientKey(clientKey)
                .setIsProduction(false)
                .build();
        return new ConfigFactory(snapApiConfig).getSnapApi();
    }

}
