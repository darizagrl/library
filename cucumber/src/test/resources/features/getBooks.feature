Feature: find all books

  Background: The app is up and running
    Given the docker container is running

  Scenario: User gets the books
    Given the following books
      | title                    |
      | The Clown                |
      | A Streetcar Named Desire |
    When a user requests get all the books
    Then all the books are returned
    And a status code is "200 OK" for a book