package com.swaglab.jandcode.stepdefinitions;

import com.swaglab.jandcode.interactions.gremlins.RunGremlins;
import io.cucumber.java.en.Then;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class GremlinsSteps {

    @Then("an actor calls gremlins and look the behavior from actual swaglab page")
    public void anActorCallsGremlinsAndLookTheBehaviorFromActualSwaglabPage() {
        theActorInTheSpotlight().wasAbleTo(RunGremlins.onSwagLabPage());
    }
}
