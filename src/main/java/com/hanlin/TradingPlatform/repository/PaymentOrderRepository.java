package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

    PaymentOrder findByUserId(Long userId);

    PaymentOrder getPaymentOrderById(Long id);
}
