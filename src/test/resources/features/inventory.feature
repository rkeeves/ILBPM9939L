@inventory
Feature: Users can list and sort the available inventory items

  Background:
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    And Amy visits the Inventory Page

  Scenario: User CAN list the inventory items
    When Amy visits the Inventory Page
    Then Amy sees 6 items in the inventory
    And Amy sees the following items in the inventory in any order:
      | name                              | price  |
      | Sauce Labs Backpack               | $29.99 |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Sauce Labs Fleece Jacket          | $49.99 |
      | Sauce Labs Onesie                 | $7.99  |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |

  Scenario: User CAN sort the inventory items by name from A-Z
    When Amy sorts the items by A_TO_Z
    Then Amy sees 6 items in the inventory
    And Amy sees the following items in the inventory in this order:
      | name                              | price  |
      | Sauce Labs Backpack               | $29.99 |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Sauce Labs Fleece Jacket          | $49.99 |
      | Sauce Labs Onesie                 | $7.99  |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |

  Scenario: User CAN sort the inventory items by name from Z-A
    When Amy sorts the items by Z_TO_A
    Then Amy sees 6 items in the inventory
    And Amy sees the following items in the inventory in this order:
      | name                              | price  |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |
      | Sauce Labs Onesie                 | $7.99  |
      | Sauce Labs Fleece Jacket          | $49.99 |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Backpack               | $29.99 |

  Scenario: User CAN sort the inventory items by price from High to low
    When Amy sorts the items by HI_TO_LO
    Then Amy sees 6 items in the inventory
    And Amy sees the following items in the inventory in this order:
      | name                              | price  |
      | Sauce Labs Fleece Jacket          | $49.99 |
      | Sauce Labs Backpack               | $29.99 |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Onesie                 | $7.99  |

  Scenario: User CAN sort the inventory items by price from Low to High
    When Amy sorts the items by LO_TO_HI
    Then Amy sees 6 items in the inventory
    And Amy sees the following items in the inventory in this order:
      | name                              | price  |
      | Sauce Labs Onesie                 | $7.99  |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |
      | Sauce Labs Backpack               | $29.99 |
      | Sauce Labs Fleece Jacket          | $49.99 |