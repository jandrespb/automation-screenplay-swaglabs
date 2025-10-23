package com.swaglab.jandcode.userinterfaces.inventory;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryLocators {

    public static final Target BUTTON_BURGER = Target.the("Button burger option on saucedemo/inventory")
            .located(By.cssSelector(".bm-burger-button > button#react-burger-menu-btn"));

    public static final Target BUTTON_BURGER_OPTIONS = Target.the("Button burger options that user choose")
            .locatedBy("//nav[@class='bm-item-list']//a[text()='{0}']");

    public static final Target LABEL_TITLE_PRINCIPAL = Target.the("Label principal text on home page")
            .located(By.xpath("//span[text()='Products']"));

    public static final Target LABEL_NAME_PRODUCT = Target.the("Label name product text each element")
            .locatedBy("//div[@class='inventory_item_name ' and contains(text(),'{0}')]");

    public static final Target LABEL_PRICE_PRODUCT = Target.the("Label price text each element")
            .locatedBy("//div[@class='inventory_item_price' and text()='{0}']");

    public static final Target BUTTON_SHOPPING_CART_PRINCIPAL = Target.the("Button shopping cart")
            .locatedBy(".shopping_cart_link");
}
