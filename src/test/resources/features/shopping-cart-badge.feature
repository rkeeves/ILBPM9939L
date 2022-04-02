@shopping-cart-badge
Feature: The shopping cart badge shows the number of items in the cart

  Scenario: User is GUARANTEED to NOT see the shopping cart badge if cart is empty
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    When Amy visits the Inventory Page
    Then Amy doesn't see the shopping cart badge counter

  Scenario Outline: User is GUARANTEED to see the correct shopping cart badge if cart is non empty
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    And Amy fills the storage with <count> items
    When Amy visits the Inventory Page
    Then Amy sees that the shopping cart badge shows <count> items
    Examples:
      | count |
      |  1    |
      |  2    |
      |  3    |
      |  4    |
      |  5    |
      |  6    |