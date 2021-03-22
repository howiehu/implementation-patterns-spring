Feature: get article api

  Background:
    * url localUrl
    * def articleBase = '/articles'

  Scenario: article not found
    Given path `${articleBase}/fake-slug`
    When method get
    Then status 404
    And header Content-Type = 'application/json'
    And match $.message == 'cannot find the article with slug fake-slug'

  Scenario: get a exist article
    * def result = call read('articles-post.feature@create')

    Given path `${articleBase}/${result.response.slug}`
    When method get
    Then status 200
    And header Content-Type = 'application/json'
    And match $.slug == 'fake-title'
