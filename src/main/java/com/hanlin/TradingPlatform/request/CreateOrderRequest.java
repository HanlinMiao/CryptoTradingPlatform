package com.hanlin.TradingPlatform.request;

import com.hanlin.TradingPlatform.domain.OrderType;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
