package com.massita.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class TelegramBotConfiguration {

    @Value("${telegram.proxy.enabled}")
    private boolean isProxyEnabled;
    @Value("${telegram.proxy.host}")
    private String proxyHost;
    @Value("${telegram.proxy.port}")
    private int proxyPort;
    @Value("${telegram.proxy.type}")
    private DefaultBotOptions.ProxyType proxyType;

    @Bean
    public DefaultBotOptions botOptions() {
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        if (isProxyEnabled) {
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(proxyPort);
            botOptions.setProxyType(proxyType);
        }
        return botOptions;
    }
}
