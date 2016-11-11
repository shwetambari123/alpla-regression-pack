Feature: End to End

  Scenario Outline: Search multiple

    Then User can go to home page
    Then user registers as new user
    Then the ui should show message "Your registration completed"
    And he search the product with keyword "<keyword>"
    Then he should see equal <count> result
    When user select the first result and it should be added

    Examples:

      | keyword | count |
      | windows | 1     |
      | laptop  | 2     |



  Scenario Outline: Search multiple with no results

    Then User can go to home page
    Then user registers as new user
    And he search the product with keyword "<keyword>"
    Then he should see equal <count> result
    Then no results found message is shown
    Then the ui should show message "No products were found that matched your criteria"

    Examples:

      | keyword | count |
      | iphone  | 0     |








