Feature: ASOS Website Shopping Features

  Scenario: Reachability of product categories
    Given I am a user of the website
    When I visit the ASOS website
    And I click on the "men" category
    Then I should be taken to "men" category
    And the category should show at least 8 products
#    When I click on the first product in the results
#    Then I should be taken to the details page for that product


  Scenario: Search functionality
    Given I am a user of the website
    When I search for a product using the term "book"
    Then I should see the search results
    And there should be at least 5 products in the search results
    When I click on the first product in the results
    Then I should be taken to the details page for that product
