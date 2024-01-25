package com.swaglab.jandcode.userinterfaces.saucelabs;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SauceLabsLocators {
    public static final Target PARAGRAPH_PRINCIPAL = Target.the("Paragraph principal on page sauce labs")
            .located(By.xpath("//div[@class='MuiBox-root css-4q1zgn']//child::p"));
}
