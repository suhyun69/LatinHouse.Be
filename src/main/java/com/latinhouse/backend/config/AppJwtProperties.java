package com.latinhouse.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.jwt")
public class AppJwtProperties {
    private String secret;
    private String issuer;
    private int accessExpMinutes;

    // getters/setters
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    public int getAccessExpMinutes() { return accessExpMinutes; }
    public void setAccessExpMinutes(int accessExpMinutes) { this.accessExpMinutes = accessExpMinutes; }
}