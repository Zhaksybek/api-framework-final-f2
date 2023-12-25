@seller @api
Feature: Test Sellers


  Scenario: get all sellers and their phone number
    Given user hits get all sellers api with "sellers" and parameters: isArchived "false", page 1, size 10
    Then user gets all sellers phone number