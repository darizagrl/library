Feature: Save the new author

  Background: The app is up and running
    Given the docker container is running

  Scenario: save the new author
    Given the author with
      | firstname | lastname  |
      | Heinrich  | Böll      |
      | Tennessee | Williams  |
      | Terry     | Pratchett |
    When the client calls POST "/authors"
    Then the status code is "201 CREATED"
    And the authorRepo contains the author with
      | firstname | lastname  |
      | Heinrich  | Böll      |
      | Tennessee | Williams  |
      | Terry     | Pratchett |
