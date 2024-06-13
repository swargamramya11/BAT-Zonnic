Feature: Zonnic Canada PDP

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: PDP Validations
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    And user validate all pdp elements