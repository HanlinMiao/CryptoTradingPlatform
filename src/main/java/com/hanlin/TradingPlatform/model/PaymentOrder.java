package com.hanlin.TradingPlatform.model;

import com.hanlin.TradingPlatform.domain.PaymentMethod;
import com.hanlin.TradingPlatform.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus paymentOrderStatus;

    private PaymentMethod paymentMethod;

    @ManyToOne
    private User user;
}
