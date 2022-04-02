@inventory
Feature: Users can view items in the cart

  Scenario Outline: User is GUARANTEED to see the correct data on Cart Page
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    And Amy adds item <id> to the storage
    When Amy visits the Cart Page
    Then Amy sees 1 item on the Cart Page
    And Amy sees that the item by <id> has name <name>, price <price> on the Cart Page
    Examples:
      | id | name                                | price  |
      |  0 | "Sauce Labs Bike Light"             | $9.99  |
      |  1 | "Sauce Labs Bolt T-Shirt"           | $15.99 |
      |  2 | "Sauce Labs Onesie"                 | $7.99  |
      |  3 | "Test.allTheThings() T-Shirt (Red)" | $15.99 |
      |  4 | "Sauce Labs Backpack"               | $29.99 |
      |  5 | "Sauce Labs Fleece Jacket"          | $49.99 |
