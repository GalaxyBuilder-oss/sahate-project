package com.example.demo.services.orders;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.config.MidtransConfig;
import com.example.demo.dto.orders.OrderReqDto;
import com.example.demo.dto.orders.OrderResDto;
import com.example.demo.entities.orders.Order;
import com.example.demo.repositories.customer.CustomerRepository;
import com.example.demo.repositories.orders.OrderRepository;
import com.example.demo.repositories.store.StoreRepository;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentStatus;

// Midtrans dependencies
import com.midtrans.api.client.CoreApi;
import com.midtrans.api.model.request.ChargeReq;
import com.midtrans.api.model.request.TransactionDetails;
import com.midtrans.api.model.response.ChargeResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MidtransConfig midtransConfig; // Injeksi konfigurasi Midtrans

    // Inisialisasi CoreApi Midtrans
    private CoreApi coreApi() {
        return new CoreApi(
            midtransConfig.getServerKey(),
            midtransConfig.getClientKey(),
            midtransConfig.isProduction()
        );
    }

    /**
     * Membuat transaksi pembayaran melalui Midtrans untuk sebuah Order.
     * @param order Objek Order yang baru dibuat.
     * @return URL redirect untuk pembayaran (Snap URL atau sejenisnya).
     */
    private String createMidtransTransaction(Order order) {
        try {
            TransactionDetails transactionDetails = new TransactionDetails(
                String.valueOf(order.getId()), // Gunakan ID Order sebagai referensi unik
                order.getTotalPrice()
            );

            ChargeReq chargeReq = new ChargeReq();
            // Anda bisa membuat payment_type dinamis berdasarkan request dari user
            chargeReq.setPaymentType("bank_transfer"); 
            chargeReq.setTransactionDetails(transactionDetails);
            
            ChargeResponse chargeResponse = coreApi().charge(chargeReq);
            if (chargeResponse == null) {
                throw new RuntimeException("Gagal mendapatkan response dari Midtrans.");
            }
            
            // Dapatkan URL pembayaran dari response
            if (chargeResponse.getRedirectUrl() != null && !chargeResponse.getRedirectUrl().isEmpty()) {
                return chargeResponse.getRedirectUrl();
            } else if (chargeResponse.getActions() != null && !chargeResponse.getActions().isEmpty()) {
                return chargeResponse.getActions().get(0).getUrl();
            } else {
                 throw new RuntimeException("Tidak ada redirect URL atau actions yang ditemukan di response Midtrans.");
            }

        } catch (Exception e) {
            log.error("Gagal menciptakan transaksi Midtrans: {}", e.getMessage());
            throw new RuntimeException("Gagal menciptakan transaksi Midtrans: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public OrderResDto create(OrderReqDto dto) {
        try {
            log.info("Membuat pesanan baru: {}", dto);
            
            // 1. Konversi DTO ke entitas Order
            Order order = fromDto(dto);
            // Set status awal
            order.setPaymentStatus(PaymentStatus.PENDING);
            order.setOrderStatus(OrderStatus.PENDING);
            
            // 2. Simpan order ke database untuk mendapatkan ID
            Order savedOrder = orderRepository.save(order);

            // 3. Buat transaksi Midtrans untuk order yang sudah disimpan
            String redirectUrl = createMidtransTransaction(savedOrder);
            
            // 4. Simpan URL/token dari Midtrans ke order
            savedOrder.setMidtransTransactionToken(redirectUrl);
            Order updatedOrder = orderRepository.save(savedOrder);

            // 5. Konversi ke DTO untuk response, termasuk redirectUrl
            return toDto(updatedOrder);

        } catch (Exception e) {
            log.error("Terjadi kesalahan saat membuat pesanan: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gagal memproses pesanan", e);
        }
    }

    @Override
    @Transactional
    public OrderResDto update(Long id, OrderReqDto dto) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        order.setPaymentMethod(dto.getPaymentMethod());
        order.setDeliveryStatus(dto.getDeliveryStatus());
        order.setPurchaseDate(dto.getPurchaseDate());
        order.setTotalPrice(dto.getTotalPrice());
        order.setBuyer(customerRepository.findById(dto.getBuyerId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
        order.setStore(storeRepository.findById(dto.getStoreId())
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found")));
        
        return toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResDto findById(Long id) {
        return orderRepository.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @Override
    public List<OrderResDto> findAll() {
        return orderRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private Order fromDto(OrderReqDto dto) {
        Order order = new Order();
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setDeliveryStatus(dto.getDeliveryStatus());
        order.setPurchaseDate(dto.getPurchaseDate());
        order.setTotalPrice(dto.getTotalPrice());
        order.setBuyer(customerRepository.findById(dto.getBuyerId())
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer dengan ID " + dto.getBuyerId() + " tidak ditemukan")));
        order.setStore(storeRepository.findById(dto.getStoreId())
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store dengan ID " + dto.getStoreId() + " tidak ditemukan")));
        return order;
    }

    private OrderResDto toDto(Order order) {
        OrderResDto dto = new OrderResDto();
        dto.setId(order.getId());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setDeliveryStatus(order.getDeliveryStatus());
        dto.setPurchaseDate(order.getPurchaseDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setPaymentStatus(order.getPaymentStatus().name());
        dto.setOrderStatus(order.getOrderStatus().name());
        // Tambahkan redirect URL ke DTO response
        dto.setRedirectUrl(order.getMidtransTransactionToken()); 

        if (order.getBuyer() != null) {
            dto.setBuyerId(order.getBuyer().getId());
        }
        if (order.getStore() != null) {
            dto.setStoreId(order.getStore().getId());
        }
        return dto;
    }
}