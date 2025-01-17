package com.hanlin.TradingPlatform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanlin.TradingPlatform.domain.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    // Password will only be writable
    // Password will be hidden when we fetch users
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private UserRole role = UserRole.RoleCustomer;

    // Two-Factor Authentication
    @Embedded
    private TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
    
}
