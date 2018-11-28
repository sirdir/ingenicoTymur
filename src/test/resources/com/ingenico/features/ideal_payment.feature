Feature: ideal payment
  Scenario: do ideal payment
    Given merchant authorized in ingenico center
    And merchant has api keys
    When api call to hostedcheckouts done
    And redirect url opened in browser
    And user precede with payment
    Then payment is successful