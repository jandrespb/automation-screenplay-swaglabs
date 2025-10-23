package com.swaglab.jandcode.userinterfaces.login;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginLocators {
    public static final Target INPUT_USER = Target.the("Field username of login")
            .located(By.cssSelector("input#user-name"));

    public static final Target INPUT_PASSWORD = Target.the("Field username of password")
            .located(By.cssSelector("input[id='password']"));

    public static final Target BUTTON_LOGIN = Target.the("Button to authenticate credentials of login")
            .located(By.cssSelector("input.submit-button"));
}
