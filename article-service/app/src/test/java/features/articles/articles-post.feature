Feature: create article api

  Background:
    * url localUrl
    * def articleBase = '/articles'
    * def randomUUID = function(){ return java.util.UUID.randomUUID() + '' }
    * def authorId = callonce randomUUID

  @create
  Scenario: create an article
    # 重置数据库
    * call read(dbRestoreFeature)

    Given path articleBase
    And request
    """
    {
      title: 'Fake Title',
      description: 'Description',
      body: 'Something',
      authorId: '#(authorId)'
    }
    """
    And header Accept = 'application/json'
    When method post
    Then status 201
    And header Content-Type = 'application/json'
    And match response ==
    """
    {
      slug: 'fake-title',
      title: 'Fake Title',
      description: 'Description',
      body: 'Something',
      authorId: '#(authorId)',
      createdAt: '#notnull',
      updatedAt: '#notnull'
    }
    """

  Scenario: slug conflict
    Given path articleBase
    And request
    """
    {
      title: 'Fake Title',
      description: 'Description',
      body: 'Something',
      authorId: '#(authorId)'
    }
    """
    And header Accept = 'application/json'
    When method post
    Then status 409
    And match header Content-Type == 'application/json'
    And match $.message == 'the article with slug fake-title already exists'

