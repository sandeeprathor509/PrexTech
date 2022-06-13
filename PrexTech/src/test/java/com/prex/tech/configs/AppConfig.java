package com.prex.tech.configs;

import com.codoid.products.fillo.Fillo;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.testng.Reporter;

import java.util.*;

/**
 * This class is read only do not modify anything here
 */
@Data
@Configuration
@ComponentScan(basePackages = "com.prex.tech")
@PropertySource(value = "classpath:application.properties")
public class AppConfig implements ApplicationContextAware {

    @Autowired
    private Environment environment;

    @Value("${url}")
    private String url;

    @Value("${app.selenium.timeout.seconds}")
    private long seleniumTimeout;

    @Value("${test.data.fileName}")
    private String testDataFile;

    private String appActivity;

    private String appPackage;

    private static ApplicationContext context;

    public String getPlatformName() {
        String platformName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");
        if (platformName != null && new ArrayList<>(Arrays.asList("chrome", "firefox", "safari")).contains(platformName))
            return platformName;
        else
            throw new RuntimeException("Incorrect or no platformName specified in TestNG XML");
    }

    public String getUsername() {
        return environment.getProperty("app."+getEnv()+".username");
    }

    public String getPassword() {
        return environment.getProperty("app."+getEnv()+".password");
    }

    public String getEnv() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ENV").toLowerCase(Locale.ROOT);
    }

    public String getDashboardURL() {
        return environment.getProperty("app."+getEnv()+".dashboard.url");
    }

    public String getAdminURL() {
        return environment.getProperty("app.admin.dashboard.url");
    }

    public String getRewardURL() {
        return environment.getProperty("app.reward.dashboard.url");
    }

    public String baseURI() {
        return environment.getProperty("app.prextech.api");
    }

    public String getCurrentWorkingDir() {
        return System.getProperty("user.dir");
    }

    @Bean
    public Fillo fillo() {
        return new Fillo();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppConfig.context = applicationContext;
    }

    public static <T extends Object> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
