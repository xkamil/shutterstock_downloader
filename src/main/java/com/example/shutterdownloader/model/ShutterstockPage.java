package com.example.shutterdownloader.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShutterstockPage {
    public static final String URL = "https://www.shutterstock.com/pl/";
    private static final int WAIT_FOR_LOGIN_TIMEOUT = 20;

    private final WebDriver driver;
    private final By openLoginScreenBtn = By.cssSelector("button[data-automation=\"SecondaryItems_log-in_button\"]");
    private final By usernameInput = By.cssSelector("input[data-automation=\"LoginForm_email_input\"]");
    private final By passwordInput = By.cssSelector("input[data-automation=\"LoginForm_password_input\"]");
    private final By loginBtn = By.cssSelector("button[data-automation=\"LoginForm_continue_button\"]");
    private final By signedUserBtn = By.cssSelector("button[data-automation=\"SecondaryItems_user_button\"]");
    private final By downloadBtn = By.cssSelector("button[data-automation=\"ActivationButton_Pobierz_button\"]");


    public ShutterstockPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logIn(String username, String password) {
        driver.get(URL);
        driver.findElement(openLoginScreenBtn).click();
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_LOGIN_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(signedUserBtn));
    }

    public void download() {
        driver.findElement(downloadBtn).click();
    }
}
