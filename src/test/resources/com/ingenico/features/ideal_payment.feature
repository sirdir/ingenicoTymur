Feature: ideal payment
  Scenario: Do IDeal payment
    Given merchant with email kubay.timur@gmail.com and password B%5rkrgb authorized on sandbox
    And merchant has api keys
    When api call done
      | currency | amount | countryCode | variant | locale |
      | EUR      | 100    | NL          | 100     | en_GB  |
    And user open in browser redirect url
    And user precede with payment via bank Issuer Simulation V3 - ING
    Then payment is successful