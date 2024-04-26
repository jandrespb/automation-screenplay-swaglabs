package com.swaglab.jandcode.tasks.inventory_item;

import com.swaglab.jandcode.userinterfaces.inventory_item.InventoryItemLocators;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class AddProduct implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(InventoryItemLocators.BUTTON_ADD_CART));
        if(InventoryItemLocators.BUTTON_REMOVE_ITEM.resolveFor(actor).getText().contains("Remove")){
            actor.attemptsTo(Click.on(InventoryItemLocators.LINK_SHOPPING_CART));
            WebUtils.waitExplicitElement(2);
        }
    }

    public static AddProduct onShoppingCart(){
        return Tasks.instrumented(AddProduct.class);
    }
}
