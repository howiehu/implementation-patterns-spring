@ignore
Feature: As a tester, I would like to restore database, so that I can isolate the test scenarios easily.

  Background:
    * url localUrl

  Scenario: call db restoration api
    Given path '/karate-tests/db-restoration'
    When method post
    Then status 200
