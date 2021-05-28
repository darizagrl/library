Feature: Retrieve the author

  Background: The app is up and running
    Given the docker container is running

  Scenario: retrieve the author with author id
    Given the author is saved with first name "Tennessee", last name "Williams"
    When the client calls GET author
    Then the response contains the author with first name "Tennessee", last name "Williams"
    And a status code of response is "200 OK"