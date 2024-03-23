@tag
Feature: Validating Google Map's API

  @AddPlace
  Scenario Outline: Verify if a new place is being successfully added using Google addPlaceAPI
    Given Add Place API Payload <latitude> <longitude> <accuracy> <name> <phone_number> <address> <website> <language>
    When user calls "addPlaceAPI" with HTTP "POST" request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to <name> using "getPlaceAPI" with HTTTP "GET" request

    Examples: 
      | latitude | longitude | accuracy | name                     | phone_number         | address                     | website                  | language     |
      | 44.12478 |  55.47811 |       30 | "Time Variant Authority" | "(+91) 546 213 8790" | "Marvel Cinematic Universe" | "https://www.marvel.com" | "English-US" |

  @DeletePlace
  Scenario: Verify if a new place is being successfully deleted using Google deletePlaceAPI
    Given Delete Place API Payload
    When user calls "deletePlaceAPI" with HTTP "POST" request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    