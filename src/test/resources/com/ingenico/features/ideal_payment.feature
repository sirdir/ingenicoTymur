Feature: ideal payment
  Scenario: do ideal payment
    Given sad children
    When api call
    And open url in browser and precede with payment
    Then happy children