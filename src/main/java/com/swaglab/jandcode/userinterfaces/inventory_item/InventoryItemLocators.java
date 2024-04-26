package com.swaglab.jandcode.userinterfaces.inventory_item;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryItemLocators {

    public static final Target BUTTON_ADD_CART = Target.the("Button to add a cart product item")
            .located(By.xpath("//button[text()='Add to cart']"));

    public static final Target BUTTON_REMOVE_ITEM = Target.the("Button to remove a cart product item")
            .located(By.xpath("//button[text()='Remove']"));

    public static final Target LINK_SHOPPING_CART = Target.the("Link that our product is save on cart")
            .located(By.cssSelector(".shopping_cart_link"));
}
