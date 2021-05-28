Feature: find all authors

  Background: The app is up and running
    Given the docker container is running

  Scenario: User gets the authors
    Given the following authors
      | firstname | lastname |
      | Heinrich  | Böll     |
      | Tennessee | Williams |
    When a user requests get all the authors
    Then all the authors are returned
    And a status code is "200 OK"