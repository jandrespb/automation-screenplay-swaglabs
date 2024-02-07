package com.swaglab.jandcode.tasks.inventory;

import com.swaglab.jandcode.models.inventory.Product;
import com.swaglab.jandcode.userinterfaces.inventory.InventoryLocators;
import com.swaglab.jandcode.userinterfaces.login.LoginLocators;
import com.swaglab.jandcode.utils.ToolsUtils;
import com.swaglab.jandcode.utils.WebUtils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectProduct implements Task {

    private final Product PRODUCT;
    private static final Logger LOGGER = Logger.getLogger(ToolsUtils.class.getName());

    public SelectProduct(Product products) {
        PRODUCT = products;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try{
            actor.attemptsTo(Click.on(InventoryLocators.LINK_NAME_PRODUCT.of(PRODUCT.getNameProduct())));
            WebUtils.waitExplicitElement(2);
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "Error! the option that you choose is not exist: " + PRODUCT.getNameProduct());
            Serenity.reportThat("", () -> {
                throw new AssertionError("Error! the option that you choose is not exist '"
                        + PRODUCT.getNameProduct() + "'");
            });
            e.printStackTrace();
        }
    }

    public static SelectProduct userChoose(Product product){
        return Tasks.instrumented(SelectProduct.class, product);
    }
}
