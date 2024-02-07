package com.swaglab.jandcode.stepdefinitions;

import com.swaglab.jandcode.interactions.cart.RemoveProduct;
import com.swaglab.jandcode.tasks.inventory.SelectProduct;
import com.swaglab.jandcode.models.inventory.Product;
import com.swaglab.jandcode.tasks.inventory_item.AddProduct;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class RemoveProductsSteps {

    // We call our model and get this instance
    private final Product PRODUCT_MODEL = Product.getInstance();

    @When("an actor select one item")
    public void anActorSelectOneItem(DataTable dataTable) {
        PRODUCT_MODEL.setValueProduct(dataTable.asLists().get(0));
        theActorInTheSpotlight().attemptsTo(SelectProduct.userChoose(PRODUCT_MODEL));
    }
    @When("an actor press button shopping cart")
    public void anActorPressButtonShoppingCart() {
        theActorInTheSpotlight().attemptsTo(AddProduct.onShoppingCart());
    }
    @When("an actor remove element from shopping cart")
    public void anActorRemoveElementFromShoppingCart() {
        theActorInTheSpotlight().attemptsTo(RemoveProduct.onSectionCart());
    }

}
