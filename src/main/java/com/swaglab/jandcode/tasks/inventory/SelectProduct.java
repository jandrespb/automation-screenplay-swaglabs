package com.swaglab.jandcode.tasks.inventory;

import com.swaglab.jandcode.models.inventory.Product;
import com.swaglab.jandcode.userinterfaces.inventory.InventoryLocators;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectProduct implements Task {

    private final Product PRODUCT;
    private static final Logger LOGGER = Logger.getLogger(SelectProduct.class.getName());

    public SelectProduct(Product products) {
        PRODUCT = products;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String message = "Error! product name don't exist: " + PRODUCT.getNameProduct();
        LOGGER.log(Level.INFO, message + " - SelectProduct");

        if (!InventoryLocators.LABEL_NAME_PRODUCT.of(PRODUCT.getNameProduct()).resolveFor(actor).isVisible()) {
            throw new AssertionError(message);
        }

        actor.attemptsTo(
                Click.on(InventoryLocators.LABEL_NAME_PRODUCT.of(PRODUCT.getNameProduct()))
        );
    }

    public static SelectProduct userChoose(Product product){
        return Tasks.instrumented(SelectProduct.class, product);
    }
}
