package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin,String> {
}
