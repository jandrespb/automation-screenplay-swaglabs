package com.swaglab.jandcode.tasks.inventory;

import com.swaglab.jandcode.userinterfaces.inventory.InventoryLocators;
import com.swaglab.jandcode.userinterfaces.login.LoginLocators;
import com.swaglab.jandcode.userinterfaces.saucelabs.SauceLabsLocators;
import com.swaglab.jandcode.utils.ToolsUtils;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BurgerButtonListTask implements Task {

    // Global Values
    private final StringBuilder OPTIONS_MENU_BURGER = new StringBuilder();
    private static final Logger LOGGER = Logger.getLogger(ToolsUtils.class.getName());

    // Constructor
    public BurgerButtonListTask(String optionMenuBurger) {
        this.OPTIONS_MENU_BURGER.append(optionMenuBurger);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(Click.on(InventoryLocators.BUTTON_BURGER));
        WebUtils.waitExplicitElement(2);

        switch (OPTIONS_MENU_BURGER.toString().toLowerCase()) {
            case "logout" -> actor.attemptsTo(Click.on(InventoryLocators.BUTTON_BURGER_OPTIONS
                    .of(ToolsUtils.capitalizeWord(OPTIONS_MENU_BURGER.toString()))));
            case "about" -> {
                actor.attemptsTo(Click.on(InventoryLocators.BUTTON_BURGER_OPTIONS
                        .of(ToolsUtils.capitalizeWord(OPTIONS_MENU_BURGER.toString()))));
                actor.attemptsTo();
                LOGGER.log(Level.INFO, SauceLabsLocators.PARAGRAPH_PRINCIPAL.resolveFor(actor).getText());
            }
            default -> {
                LOGGER.log(Level.WARNING, "Error! the option that you choose is not exist: " + OPTIONS_MENU_BURGER);
                Serenity.reportThat(LoginLocators.MESSAGE_ERROR_LOGIN.resolveFor(actor).getText(), () -> {
                    throw new AssertionError("Error! the option that you choose is not exist '"
                            + OPTIONS_MENU_BURGER + "'");
                });
            }
        }
    }

    public static BurgerButtonListTask chooseAnOption(String optionMenuBurger){
        return Tasks.instrumented(BurgerButtonListTask.class, optionMenuBurger);
    }
}
