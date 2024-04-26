package com.swaglab.jandcode.interactions.gremlins;

import com.swaglab.jandcode.utils.JavascriptUtils;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Evaluate;

public class RunGremlins implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        WebUtils.waitExplicitElement(2);
        actor.wasAbleTo(Evaluate.javascript(JavascriptUtils.libraryGremlins()));
        WebUtils.waitExplicitElement(2);
        actor.wasAbleTo(Evaluate.javascript(JavascriptUtils.executeGremlins()));
        WebUtils.waitExplicitElement(8);
    }

    public static RunGremlins onSwagLabPage() {
        return Tasks.instrumented(RunGremlins.class);
    }
}
