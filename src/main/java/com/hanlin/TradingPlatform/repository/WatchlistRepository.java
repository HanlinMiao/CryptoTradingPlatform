package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Watchlist findByUserId(Long userId);
}
