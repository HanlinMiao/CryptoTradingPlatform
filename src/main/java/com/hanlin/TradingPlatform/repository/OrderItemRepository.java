package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
