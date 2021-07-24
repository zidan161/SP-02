Feature: Registration

  Scenario: Registration using valid email
    Given Login page is open
    When User input Email "zidan@8mail.com"
    And User click button create an account
    Then User directing to register page
    When User input personal data
    And User click button Register
    Then Account successfully created