package org.top.baseconverterwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.baseconverterwebapp.converter.BaseConverter;
import org.top.baseconverterwebapp.converter.SimpleBaseConverter;

@Configuration
public class AppServicesConfig {
    @Bean
    public BaseConverter baseConverter() {
        return new SimpleBaseConverter();
    }
}
