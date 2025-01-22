package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.Coin;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Watchlist;
import com.hanlin.TradingPlatform.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public Watchlist findUserWatchlist(Long userId) throws Exception {
        Watchlist watchlist = watchlistRepository.findByUserId(userId);
        if (watchlist==null){
            throw new Exception("Watchlist not found for user");
        }
        return watchlist;
    }

    @Override
    public Watchlist createWatchlist(User user) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        return watchlistRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(long id) throws Exception {
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(id);
        if (watchlistOptional.isEmpty()){
            throw new Exception("Watchlist not found for id");
        }
        return watchlistOptional.get();
    }

    @Override
    public Coin addItemToWatchlist(Coin coin, User user) throws Exception {
        Watchlist watchlist = findUserWatchlist(user.getId());
        if (watchlist.getCoins().contains(coin)) {
            watchlist.getCoins().remove(coin);
        }
        watchlist.getCoins().add(coin);
        watchlistRepository.save(watchlist);
        return coin;
    }
}
