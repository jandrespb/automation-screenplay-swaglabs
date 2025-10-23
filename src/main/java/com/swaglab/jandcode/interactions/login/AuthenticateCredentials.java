package com.swaglab.jandcode.interactions.login;

import com.swaglab.jandcode.exceptions.login.MessageLoginException;
import com.swaglab.jandcode.userinterfaces.login.LoginLocators;
import com.swaglab.jandcode.utils.Constants;
import com.swaglab.jandcode.utils.PropertiesUtils;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;


public class AuthenticateCredentials implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.wasAbleTo(Enter.theValue(PropertiesUtils.getValueProperties(Constants.PATH_DATA_PROPERTIES, "username")
                .trim()).into(LoginLocators.INPUT_USER));
        actor.wasAbleTo(Enter.theValue(PropertiesUtils.getValueProperties(Constants.PATH_DATA_PROPERTIES, "password")
                .trim()).into(LoginLocators.INPUT_PASSWORD));
        WebUtils.waitExplicitElement(2);
        actor.wasAbleTo(Click.on(LoginLocators.BUTTON_LOGIN));
        WebUtils.waitExplicitElement(2);
        actor.attemptsTo(MessageLoginException.errorCredentials());

    }

    public static AuthenticateCredentials addCredentials() {
        return Tasks.instrumented(AuthenticateCredentials.class);
    }
}
