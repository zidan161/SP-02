Feature: Search

  Scenario: Search hotel
    Given User open the website
    When User input searching data
    And User click button Search
    Then Searched hotels will displayed
