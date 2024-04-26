package com.swaglab.jandcode.userinterfaces.cart;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartLocators {
    public static final Target BUTTON_REMOVE = Target.the("Button to remove product cart")
            .located(By.xpath("//button[text()='Remove']"));

    public static final Target BUTTON_CONTINUE_SHOPPING = Target.the("Button to remove product cart")
            .located(By.cssSelector("#continue-shopping"));
}
