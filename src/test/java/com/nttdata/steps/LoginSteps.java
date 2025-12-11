package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    public void ingresarUsuario(String usuario){
        driver.findElement(LoginPage.user).sendKeys(usuario);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LoginPage.password));
    }

    public void ingresarClave(String clave){
        driver.findElement(LoginPage.password).sendKeys(clave);
    }

    public void login(){
        driver.findElement(LoginPage.loginBtn).click();
    }
}
