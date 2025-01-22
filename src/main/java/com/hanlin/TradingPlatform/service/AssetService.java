package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.Asset;
import com.hanlin.TradingPlatform.model.Coin;
import com.hanlin.TradingPlatform.model.User;

import java.util.List;

public interface AssetService {

    Asset createAsset(User user, Coin coin, double quantity);

    Asset getAssetById(long assetId) throws Exception;

    Asset getAssetByUserIdAndId(Long userId, Long assetId);

    List<Asset> getUserAssets(Long userId);

    Asset updateAsset(Long assetId, double quantity) throws Exception;

    Asset findAssetByUserIdAndCoinId(Long userId, String coinId);

    void deleteAsset(Long assetId);
}
