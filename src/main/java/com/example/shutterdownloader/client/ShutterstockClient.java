package com.example.shutterdownloader.client;

import com.example.shutterdownloader.model.ShutterstockPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ShutterstockClient {
    private static final Logger logger = LoggerFactory.getLogger(ShutterstockClient.class);

    @Value("${shutterstock.username}")
    private String shutterstockUsername;

    @Value("${shutterstock.password}")
    private String shutterstockPassword;

    public ShutterstockClient() {
        WebDriverManager.chromedriver().setup();
    }

    public void downloadImages(List<String> imageUrls) {
        WebDriver driver = createDriver();
        ShutterstockPage shutterstockPage = new ShutterstockPage(driver);
        shutterstockPage.logIn(shutterstockUsername, shutterstockPassword);
        for (String imageUrl : imageUrls) {
            try {
                driver.get(imageUrl);
                shutterstockPage.download();
                logger.info("Started downloading {}", imageUrl);
            } catch (Exception ex) {
                logger.error("Downloading failed for {}", imageUrl);
                ex.printStackTrace();
            }
        }
        driver.close();
        driver.quit();
    }


    private WebDriver createDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        return driver;
    }
}
