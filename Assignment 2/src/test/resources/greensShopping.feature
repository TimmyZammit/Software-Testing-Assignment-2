Feature: Greens Website Shopping Features

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    And I click on the "Fruits & Veg" category
    Then I should be taken to "Fruits & Veg" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    And I click on the "Butcher" category
    Then I should be taken to "Butcher" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    And I click on the "Chilled & Dairy" category
    Then I should be taken to "Chilled & Dairy" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    And I click on the "Delicatessen" category
    Then I should be taken to "Delicatessen" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    And I click on the "Fish" category
    Then I should be taken to "Fish" category
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product


  Scenario: Search functionality
    Given I am a user of the website
    When I visit the Greens website
    And I click on the "Malta" location
    When I search for a product using the term "vodka"
    Then I should see the search results
    And the category should show at least 5 products
    When I click on the first product in the results
    Then I should be taken to the details page for that product
