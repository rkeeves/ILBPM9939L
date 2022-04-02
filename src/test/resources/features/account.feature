@account
Feature: Users can login and logout

  Scenario: User CANNOT login with locked out credentials
    Given Amy clears her history
    And Amy visits the Login Page
    When Amy enters username: "locked_out_user"
    And Amy enters password: "secret_sauce"
    And Amy tries to login
    Then Amy sees login error message: "Epic sadface: Sorry, this user has been locked out."
    And Amy sees username displays error
    And Amy sees password displays error
    And Amy has user cookie: "locked_out_user"

  Scenario Outline: User CANNOT login with invalid credentials
    Given Amy clears her history
    And Amy visits the Login Page
    When Amy enters username: <uname>
    And Amy enters password: <pass>
    And Amy tries to login
    Then Amy sees login error message: <error>
    And Amy sees username displays error
    And Amy sees password displays error
    And Amy has no user cookie
    Examples:
      | uname             | pass           | error                                                                       |
      | "unknown_user"    | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "standard_user"   | "bad_password" | "Epic sadface: Username and password do not match any user in this service" |
      | ""                | ""             | "Epic sadface: Username is required"                                        |
      | ""                | "secret_sauce" | "Epic sadface: Username is required"                                        |
      | "standard_user"   | ""             | "Epic sadface: Password is required"                                        |

  Scenario Outline: User CAN login with valid credentials
    Given Amy clears her history
    And Amy visits the Login Page
    When Amy enters username: <uname>
    And Amy enters password: <pass>
    And Amy tries to login
    Then Amy sees the Inventory Page
    And Amy has user cookie: <uname>
    Examples:
      | uname                       | pass           |
      | "standard_user"             | "secret_sauce" |
      | "problem_user"              | "secret_sauce" |
      | "performance_glitch_user"   | "secret_sauce" |

  Scenario: User CAN logout
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    And Amy visits the Inventory Page
    When Amy logs out
    Then Amy visits the Login Page
    And Amy has no user cookie