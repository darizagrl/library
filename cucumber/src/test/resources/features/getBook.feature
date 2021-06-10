#Feature: Retrieve the book
#
#  Background: The app is up and running
#    Given the docker container is running
#
#  Scenario: retrieve the book with book id
#    Given the book is saved with the title "The Clown"
#    When the client calls GET books/id
#    Then the response contains the book with title "The Clown"
#    And a status code of GET books/id response is "200 OK"