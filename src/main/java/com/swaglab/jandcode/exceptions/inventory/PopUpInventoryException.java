package com.swaglab.jandcode.exceptions.inventory;

import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class PopUpInventoryException implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        WebUtils.sendCommandKeyboard("enter");
    }

    public static PopUpInventoryException acceptPopUp(){
        return Tasks.instrumented(PopUpInventoryException.class);
    }
}
