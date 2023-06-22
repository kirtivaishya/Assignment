Feature:Hotel booking

  Scenario: Hotel booking
    Given User is on home page
    When  User selects destination as 'GOA'
    And User selects check-in date as '1-july'
    And User selects check-out date as '3-july'
    And User selects number of guests as '2'
    Then User clicks on 'Search'
    And The search api returned success
    And Users selects 'Ginger Goa, Panjim' from search result
    And User selects click on 'Select Combo' for booking

