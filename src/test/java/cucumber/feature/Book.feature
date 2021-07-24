Feature: Book

  Scenario: Book Hotel from home page
    Given User open the website
    And Scroll to Room
    When User click button Book Now on frame
    Then User directing to checkout page
    When User click button Book Now
    Then Room has been booked

  Scenario: Book Hotel from search page
    Given User already on the search page
    When User click button Book Now on item General Room
    Then Room has been booked