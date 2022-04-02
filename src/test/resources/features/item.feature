@item
Feature: Users can view items

  Scenario Outline: User is GUARANTEED to see the correct data on Item Page
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    When Amy visits the Item Page by id <id>
    Then Amy sees that the item has name <name>, price <price>, image prefix <img_prefix>
    Examples:
      | id | name                                | price  | img_prefix     |
      |  0 | "Sauce Labs Bike Light"             | $9.99  | bike-light     |
      |  1 | "Sauce Labs Bolt T-Shirt"           | $15.99 | bolt-shirt    |
      |  2 | "Sauce Labs Onesie"                 | $7.99  | red-onesie     |
      |  3 | "Test.allTheThings() T-Shirt (Red)" | $15.99 | red-tatt       |
      |  4 | "Sauce Labs Backpack"               | $29.99 | sauce-backpack |
      |  5 | "Sauce Labs Fleece Jacket"          | $49.99 | sauce-pullover |