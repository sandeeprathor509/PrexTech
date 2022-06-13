@Regression @Bulklist
Feature: Upload a file in bulk list

  Background:
    Given Launch the browser
    Then Enter the username and password

  Scenario: Verify if the user has valid permission to upload the bulk list
    Then Validate that bulk list option is visible to the user

  Scenario: Upload a file and validate the visibility in the uploaded files
    And Click on the bulk list option
    Then Upload a file through upload option

