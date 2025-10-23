package com.swaglab.jandcode.utils;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.serenitybdd.core.Serenity.getDriver;

public class WebUtils {

    private static final Logger LOGGER = Logger.getLogger(ToolsUtils.class.getName());

    /**
     * This method let wait elements as explicit form, ideal of cases that we cannot identify an object
     * or that system know the object internally, this cannot have interactions for this object.
     *
     * @param seconds time on seconds.
     */
    public static void waitExplicitElement(int seconds){
        int milliseconds = seconds * 1000;
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * This method send a command on keyboard and this do an action
     * @param keyword is a character
     */
    public static void sendCommandKeyboard(String keyword){

        Actions actions = new Actions(getDriver());
        switch (keyword.toUpperCase()){
            case "ENTER":
                actions.sendKeys(Keys.ENTER).perform();
                break;
            case "F5":
                actions.sendKeys(Keys.F5).perform();
                break;
            default:
                LOGGER.log(Level.INFO,"Keyword invalid!");
                break;
        }
    }
}
