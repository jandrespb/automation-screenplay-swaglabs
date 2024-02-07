package com.swaglab.jandcode.stepdefinitions;

import com.swaglab.jandcode.exceptions.inventory.PopUpInventoryException;
import com.swaglab.jandcode.interactions.login.AuthenticateCredentials;
import com.swaglab.jandcode.interactions.login.OpenPage;
import com.swaglab.jandcode.questions.inventory.ValidateTitlePrincipal;
import com.swaglab.jandcode.tasks.inventory.BurgerButtonListTask;
import com.swaglab.jandcode.utils.Constants;
import com.swaglab.jandcode.utils.PropertiesUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginSteps {
    @Before
    public void previousActorBegin(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(PropertiesUtils.getValueProperties(Constants.PATH_DATA_PROPERTIES,"actor").trim());
    }

    @Given("an actor is on swaglab landing page")
    public void anActorIsOnSwaglabLandingPage() {
        theActorInTheSpotlight().wasAbleTo(OpenPage.onBrowser());
    }

    @When("an actor write credentials on login")
    public void anActorWriteCredentialsOnLogin() {
        theActorInTheSpotlight().wasAbleTo(AuthenticateCredentials.addCredentials());
    }

    @Then("an actor verify homepage")
    public void anActorVerifyHomepage() {
        theActorInTheSpotlight().attemptsTo(PopUpInventoryException.acceptPopUp());
        theActorInTheSpotlight().should(seeThat(ValidateTitlePrincipal.onHomePage()));
    }

    @Then("an actor choose burger button option (.*)$")
    public void anActorChooseBurgerButtonOption(String optionMenu) {
        theActorInTheSpotlight().wasAbleTo(BurgerButtonListTask.chooseAnOption(optionMenu));
    }

}
