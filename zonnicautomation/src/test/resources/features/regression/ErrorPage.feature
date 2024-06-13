Feature: Zonnic Canada Basket page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: 404 Page validations
    And user navigates to invalid url
    Then validate 'text1'
    Then validate 'text2'
    And click on 'continueBrowsing' button in error Page
    And user navigates to invalid url
    And click on 'contactUs' button in error Page