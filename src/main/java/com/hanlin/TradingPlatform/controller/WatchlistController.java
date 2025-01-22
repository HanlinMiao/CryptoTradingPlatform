package com.hanlin.TradingPlatform.controller;

import com.hanlin.TradingPlatform.model.Coin;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Watchlist;
import com.hanlin.TradingPlatform.service.CoinService;
import com.hanlin.TradingPlatform.service.UserService;
import com.hanlin.TradingPlatform.service.WatchlistService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    @Autowired
    WatchlistService watchlistService;
    @Autowired
    UserService userService;
    @Autowired
    CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchlist(user.getId());

        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Watchlist> createWatchlist(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Watchlist createdWatchlist = watchlistService.createWatchlist(user);

        return new ResponseEntity<>(createdWatchlist, HttpStatus.CREATED);
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(
        @PathVariable Long watchlistId
    ) throws Exception {
        Watchlist watchlist = watchlistService.findUserWatchlist(watchlistId);
        return ResponseEntity.ok(watchlist);
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(
        @RequestHeader("Authorization") String jwt,
        @PathVariable String coinId
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Coin addedCoin = watchlistService.addItemToWatchlist(coinService.findById(coinId), user);
        return ResponseEntity.ok(addedCoin);
    }
}
