package com.dkothandan.prismatic.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "azure")
public class PrismaticConfig {

    private String sendGridAPIKey;

    public String getSendGridAPIKey() {
        return sendGridAPIKey;
    }

    public void setSendGridAPIKey(String sendGridAPIKey) {
        this.sendGridAPIKey = sendGridAPIKey;
    }
}
