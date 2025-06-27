package com.example.demo.services.store;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.orders.Order;
import com.example.demo.repositories.orders.OrderRepository;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    // Service ini sekarang bergantung pada OrderRepository untuk update status
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Menangani notifikasi (webhook) dari Midtrans untuk mengupdate status pembayaran dan order.
     * Metode ini harus diekspos sebagai endpoint REST API (misal: POST /api/payments/notification).
     * @param notification Payload notifikasi dari Midtrans dalam bentuk Map.
     */
    @Override
    @Transactional
    public void handleMidtransNotification(Map<String, Object> notification) {
        try {
            // Ambil order_id dari notifikasi. Ini adalah ID dari entitas Order kita.
            String orderId = (String) notification.get("order_id");
            String transactionStatus = (String) notification.get("transaction_status");
            String fraudStatus = (String) notification.get("fraud_status");

            log.info("Menerima notifikasi Midtrans untuk Order ID: {}, Status: {}", orderId, transactionStatus);

            Order order = orderRepository.findById(Long.parseLong(orderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order dengan ID " + orderId + " tidak ditemukan."));

            // Jangan proses notifikasi jika order sudah selesai atau gagal
            if (order.getPaymentStatus() == PaymentStatus.SUCCESS || order.getPaymentStatus() == PaymentStatus.FAILED) {
                log.warn("Notifikasi untuk Order ID: {} sudah dalam status final ({}). Notifikasi diabaikan.", orderId, order.getPaymentStatus());
                return;
            }

            // Logika untuk update status berdasarkan notifikasi
            if ("capture".equals(transactionStatus) && "accept".equals(fraudStatus)) {
                order.setPaymentStatus(PaymentStatus.SUCCESS);
                order.setOrderStatus(OrderStatus.PROCESSING); // Pesanan siap diproses
            } else if ("settlement".equals(transactionStatus)) {
                order.setPaymentStatus(PaymentStatus.SUCCESS);
                order.setOrderStatus(OrderStatus.PROCESSING); // Pesanan siap diproses
            } else if ("deny".equals(transactionStatus) || "cancel".equals(transactionStatus) || "expire".equals(transactionStatus)) {
                order.setPaymentStatus(PaymentStatus.FAILED);
                order.setOrderStatus(OrderStatus.CANCELLED); // Pesanan dibatalkan
            } else if ("pending".equals(transactionStatus)) {
                order.setPaymentStatus(PaymentStatus.PENDING);
            }
            
            orderRepository.save(order);
            log.info("Status Order ID: {} berhasil diperbarui menjadi Payment: {} dan Order: {}", orderId, order.getPaymentStatus(), order.getOrderStatus());

        } catch (Exception e) {
            // Log error untuk debugging. Penting untuk tidak melempar exception kembali
            // ke Midtrans karena akan menyebabkan pengiriman notifikasi berulang.
            log.error("Error saat menangani notifikasi Midtrans: {}", e.getMessage(), e);
        }
    }
}