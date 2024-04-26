Feature: Execute gremlins on actual page
  As an actor run gremlins and see the behavior on swaglab page

  @homepage_gremlins
  Scenario: Remove element through shopping cart
    Given an actor is on swaglab landing page
    And an actor write credentials on login
    Then an actor calls gremlins and look the behavior from actual swaglab page