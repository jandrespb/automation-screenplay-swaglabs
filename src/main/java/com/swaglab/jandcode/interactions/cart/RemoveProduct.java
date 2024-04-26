package com.swaglab.jandcode.interactions.cart;

import com.swaglab.jandcode.userinterfaces.cart.CartLocators;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class RemoveProduct implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CartLocators.BUTTON_REMOVE));
        WebUtils.waitExplicitElement(1);
        actor.attemptsTo(Click.on(CartLocators.BUTTON_CONTINUE_SHOPPING));
    }

    public static RemoveProduct onSectionCart(){
        return Tasks.instrumented(RemoveProduct.class);
    }
}
