package com.prex.tech.configs;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WebDriverConfig {

    ThreadLocal<WebDriver> WebDrivers = new ThreadLocal<>();

    @Bean
    @Scope("prototype")
    public WebDriver webDriver() {
        return WebDrivers.get();
    }

    public void setDriver(WebDriver driver) {
        WebDrivers.set(driver);
    }

}
