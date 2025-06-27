package com.example.demo.controllers.store;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.store.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@Tag(name = "Payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Endpoint untuk menerima notifikasi HTTP (webhook) dari Midtrans.
     * URL ini (misal: https://your-domain.com/api/payment/notification)
     * harus didaftarkan pada dashboard Midtrans di menu:
     * Settings > Configuration > Notification URL.
     *
     * @param notification Payload JSON yang dikirim oleh Midtrans.
     * @return HTTP 200 OK untuk mengkonfirmasi penerimaan notifikasi.
     */
    @PostMapping("/notification")
    public ResponseEntity<Void> handleMidtransNotification(@RequestBody Map<String, Object> notification) {
        log.info("Menerima payload notifikasi dari Midtrans: {}", notification);
        
        // Teruskan payload ke service untuk diproses lebih lanjut.
        // Service sudah dirancang untuk menangani error secara internal tanpa melempar exception.
        paymentService.handleMidtransNotification(notification);

        // Penting: Selalu kirim response 200 OK agar Midtrans tidak mengirim ulang notifikasi.
        return ResponseEntity.ok().build();
    }
}