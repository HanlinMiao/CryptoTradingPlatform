package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.domain.OrderType;
import com.hanlin.TradingPlatform.model.Coin;
import com.hanlin.TradingPlatform.model.Order;
import com.hanlin.TradingPlatform.model.OrderItem;
import com.hanlin.TradingPlatform.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;
}
