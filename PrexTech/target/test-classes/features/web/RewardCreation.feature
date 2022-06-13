@Regression @Reward
Feature: Creating a reward

  Background:
    Given Launch the browser
    And Enter the username and password
    And User clicks on the create new reward button

  Scenario: After user clicks on the create new reward, validate the basic information on the page
    Then Verify the text on the create new reward screen

  Scenario: Validate the error messages if user forgot to fill out imp information
    And Click on the next button
    Then Validate the validation error message for the name

  Scenario: Validate the date validation error message
    Then Enter the name in reward details page
    And Click on the next button
    And Click on the next button
    Then Validate the date error message on the mechanics page

  @Authorization
  Scenario: Fill the reward form with the basic details
    Then Enter the name in reward details page
    And Click on the next button
    Then Fill up the start time and end time
      | StartTime | 04 |
      | EndTime   | 05 |
      | StartDay  | 12 |
      | EndDay   | 13 |
    And Click on the next button
    And Submit the reward values

  Scenario: Validate the created user is being displayed in the reward list or not
    And Search for the created merchant name in the reward list



