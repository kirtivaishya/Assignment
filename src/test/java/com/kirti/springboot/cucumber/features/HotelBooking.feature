Feature:Hotel booking

  @Booking
  Scenario: Hotel booking
    Given User is at home page of MMT
    When User selects below parameters for the search
    |Destination|checkin|checkout|Hotel|Adults|Children|
    |GOI        |06232023|06292023|TajExoticaResort|2|1|
    And User clicks on 'Search' button
    Then User proceed further for booking by clicking on 'BOOK THIS NOW'


