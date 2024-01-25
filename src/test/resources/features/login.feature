Feature: Access to home page login
  As an actor authenticated credentials and check homepage

  @login
  Scenario: Validate credentials login
    Given an actor is on swaglab landing page
    When an actor write credentials on login
    Then an actor verify homepage
    And an actor choose burger button option logout