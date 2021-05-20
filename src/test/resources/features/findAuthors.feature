Feature: find all authors

  Scenario: User gets the authors
    Given the following authors
      | firstname | lastname |
      | Heinrich  | Boll     |
      | Tennessee | Williams |
    When a user requests get all the authors
    Then all the authors are returned