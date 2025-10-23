package com.swaglab.jandcode.exceptions.login;

import com.swaglab.jandcode.tasks.inventory.BurgerButtonListTask;
import com.swaglab.jandcode.userinterfaces.login.LoginLocators;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageLoginException implements Task {

    private static final By MESSAGE_ERROR_LOGIN = By.cssSelector("h3[data-test='error']");
    private static final Logger LOGGER = Logger.getLogger(MessageLoginException.class.getName());

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        // Save the implicit wait that BrowseTheWeb uses (serenity)
        Duration originalImplicit = BrowseTheWeb.as(actor).getImplicitWaitTimeout();
        // Disable implicit wait locally to make findElements fail-fast
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        boolean errorVisible = !driver.findElements(MESSAGE_ERROR_LOGIN).isEmpty();

        if (errorVisible) {
            String message = "Error! " + driver.findElement(MESSAGE_ERROR_LOGIN).getText().trim();
            LOGGER.log(Level.WARNING, message + " - MessageLoginException");
            throw new AssertionError(message);
        }

        // Restore the original implicit wait ALWAYS
        driver.manage().timeouts().implicitlyWait(originalImplicit);

    }

    public static MessageLoginException errorCredentials() {
        return Tasks.instrumented(MessageLoginException.class);
    }
}
