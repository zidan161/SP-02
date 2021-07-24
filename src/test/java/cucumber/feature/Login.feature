Feature: Login

  Scenario: Login using valid credential
    Given Login page is open
    When User input Email "zidan@email.com" and Password "zidan123"
    And User click button Login
    Then User successfully log in

  Scenario: Login using unregistered credential
    Given Login page is open
    When User input Email "zidan@5mail.com" and Password "zidan123"
    And User click button Login
    Then System show error

  Scenario: User log out
    Given User already sign in
    When User click button Account
    Then Dropdown menu is showing
    When User click button Logout
    Then User logout