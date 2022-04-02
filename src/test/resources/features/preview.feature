@preview
Feature: Users can preview items and navigate to the Item Page

  Background:
    Given Amy clears her history
    And Amy sets user cookie "standard_user"
    And Amy visits the Inventory Page

  Scenario Outline: User CAN navigate to item via preview's name
    When Amy navigates via the name of preview item <id>
    Then Amy sees the Item Page by id <id>
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User CAN navigate to item via preview's image
    When Amy navigates via the image of preview item <id>
    Then Amy sees the Item Page by id <id>
    Examples:
      | id |
      |  0 |
      |  1 |
      |  2 |
      |  3 |
      |  4 |
      |  5 |

  Scenario Outline: User is GUARANTEED to see the correct data on the preview
    Given Amy sees that preview by id <id> has name <name>, price <price>, image prefix <img_prefix>
    Examples:
      | id | name                                | price  | img_prefix     |
      |  0 | "Sauce Labs Bike Light"             | $9.99  | bike-light     |
      |  1 | "Sauce Labs Bolt T-Shirt"           | $15.99 | bolt-shirt    |
      |  2 | "Sauce Labs Onesie"                 | $7.99  | red-onesie     |
      |  3 | "Test.allTheThings() T-Shirt (Red)" | $15.99 | red-tatt       |
      |  4 | "Sauce Labs Backpack"               | $29.99 | sauce-backpack |
      |  5 | "Sauce Labs Fleece Jacket"          | $49.99 | sauce-pullover |