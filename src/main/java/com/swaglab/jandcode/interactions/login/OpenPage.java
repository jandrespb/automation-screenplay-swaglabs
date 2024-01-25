package com.swaglab.jandcode.interactions.login;

import com.swaglab.jandcode.userinterfaces.login.UrlPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenPage implements Interaction {

    private UrlPage urlPage;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.wasAbleTo(Open.browserOn(urlPage));
    }

    public static OpenPage onBrowser(){
        return Tasks.instrumented(OpenPage.class);
    }
}
