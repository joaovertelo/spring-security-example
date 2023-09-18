package com.devertelo.springsecurity.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")
@Getter
@Setter
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
    private Integer refreshTokenExpirationAfterDays;

    public JwtConfig() {
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
