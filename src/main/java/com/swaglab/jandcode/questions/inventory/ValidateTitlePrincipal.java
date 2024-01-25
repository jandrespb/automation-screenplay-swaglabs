package com.swaglab.jandcode.questions.inventory;

import com.swaglab.jandcode.userinterfaces.inventory.InventoryLocators;
import com.swaglab.jandcode.utils.Constants;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidateTitlePrincipal implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return InventoryLocators.LABEL_TITLE_PRINCIPAL.resolveFor(actor)
                .getText().equals(Constants.TITLE_PRINCIPAL_INVENTORY);
    }

    public static ValidateTitlePrincipal onHomePage(){
        return new ValidateTitlePrincipal();
    }
}
