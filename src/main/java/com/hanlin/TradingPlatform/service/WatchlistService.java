package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.Coin;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Watchlist;

public interface WatchlistService {
    Watchlist findUserWatchlist(Long userId) throws Exception;
    Watchlist createWatchlist(User user);
    Watchlist findById(long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}
