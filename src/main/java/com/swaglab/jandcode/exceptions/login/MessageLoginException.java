package com.swaglab.jandcode.exceptions.login;

import com.swaglab.jandcode.userinterfaces.login.LoginLocators;
import com.swaglab.jandcode.utils.ToolsUtils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageLoginException implements Task {

    private static final Logger LOGGER = Logger.getLogger(ToolsUtils.class.getName());

    @Override
    public <T extends Actor> void performAs(T actor) {
        if(LoginLocators.MESSAGE_ERROR_LOGIN.isVisibleFor(actor)){
            LOGGER.log(Level.WARNING,"Error access login, check your credentials");
            Serenity.reportThat(LoginLocators.MESSAGE_ERROR_LOGIN.resolveFor(actor).getText(), ()->{
                throw new AssertionError("Error on credentials, please verify your username or password");
                    });
        }
    }

    public static MessageLoginException errorCredentials(){
        return Tasks.instrumented(MessageLoginException.class);
    }
}
