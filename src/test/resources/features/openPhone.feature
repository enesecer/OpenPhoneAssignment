@login
Feature: Update email

  Background:
    Given user logged in application via numeric code

  Scenario: Validate email update functionality
    When user navigates settings page
    And  user change the first name to "TestAuto"
    Then user validate the profile updated notification

