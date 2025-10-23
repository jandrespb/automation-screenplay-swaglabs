package com.swaglab.jandcode.tasks.inventory;

import com.swaglab.jandcode.questions.saucelabs.TheCurrentUrl;
import com.swaglab.jandcode.userinterfaces.inventory.InventoryLocators;
import com.swaglab.jandcode.utils.ToolsUtils;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BurgerButtonListTask implements Task {

    private final StringBuilder OPTIONS_MENU_BURGER = new StringBuilder();
    private static final Logger LOGGER = Logger.getLogger(BurgerButtonListTask.class.getName());

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
                Serenity.reportThat("Checking url",
                        () -> assertThat(
                                actor.asksFor(TheCurrentUrl.value()),
                                equalTo("https://saucelabs.com/")
                        )
                );
            }
            default -> {
                String messageError = "Error! the option that you choose is not exist";
                LOGGER.log(Level.WARNING, messageError + ": " + OPTIONS_MENU_BURGER + " - BurgerButtonListTask");
                throw new AssertionError(messageError);
            }
        }
    }

    public static BurgerButtonListTask chooseAnOption(String optionMenuBurger) {
        return Tasks.instrumented(BurgerButtonListTask.class, optionMenuBurger);
    }
}
