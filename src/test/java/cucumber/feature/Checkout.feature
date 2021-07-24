Feature: Checkout

  Scenario: To Checkout page
    Given User already book a room
    When User click button Checkout on Chart Menu
    Then User should directing to checkout page

    When User click Proceed button
    And User choose the Payment method
    Then Confirm page open

    When User click button confirm
    Then Room has been ordered

