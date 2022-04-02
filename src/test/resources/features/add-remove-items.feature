@add-remove-items
Feature: Users can add or remove items from the cart

  Background:
    Given Amy clears her history
    And Amy sets user cookie "standard_user"

  Scenario Outline: User CAN add an item to cart via Item Page
    Given Amy visits the Item Page by id <id>
    When Amy adds the item to her cart on the Item Page
    Then Amy doesn't have the add item action on the Item Page
    And Amy has the remove item action on the Item Page
    And Amy has item <id> in storage
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User CAN remove an item from cart via Item Page
    Given Amy adds item <id> to the storage
    And Amy visits the Item Page by id <id>
    When Amy removes the item from her cart on the Item Page
    Then Amy doesn't have the remove item action on the Item Page
    And Amy has the add item action on the Item Page
    And Amy doesn't have item <id> in storage
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User CAN add an item to cart via Inventory Page
    Given Amy visits the Inventory Page
    When Amy adds the item by id <id> to her cart on the Inventory Page
    Then Amy doesn't see the add to cart action for item by id <id> on the Inventory Page
    And Amy sees the remove from cart action for item by id <id> on the Inventory Page
    And Amy has item <id> in storage
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User CAN remove an item from cart via Inventory Page
    Given Amy adds item <id> to the storage
    And Amy visits the Inventory Page
    When Amy removes item by id <id> from her cart on the Inventory Page
    Then Amy doesn't see the remove from cart action for item by id <id> on the Inventory Page
    And Amy sees the add to cart action for item by id <id> on the Inventory Page
    And Amy doesn't have item <id> in storage
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User CAN remove an item from cart via Cart Page
    Given Amy adds item <id> to the storage
    And Amy visits the Cart Page
    When Amy removes item by id <id> from her cart on the Cart Page
    Then Amy doesn't see item by id <id> on the Cart Page
    And Amy doesn't have item <id> in storage
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |