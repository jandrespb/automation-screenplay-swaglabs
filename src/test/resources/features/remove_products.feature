Feature: Remove elements on E-commerce Swag Labs demo
  As an actor can remove elements on e-commerce

  @remove_shopping_cart
  Scenario Outline: Remove element through shopping cart
    Given an actor is on swaglab landing page
    And an actor write credentials on login
    When an actor select one item
      | <nameProduct> | <priceProduct> |
    And an actor press button shopping cart
    And an actor remove element from shopping cart
    Then an actor verify homepage
    And an actor choose burger button option logout

    Examples:
      | nameProduct         | priceProduct |
      | Saucesdsd Labs Backpack | 29.99        |