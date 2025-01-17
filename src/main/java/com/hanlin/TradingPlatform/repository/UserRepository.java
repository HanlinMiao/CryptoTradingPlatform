package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
