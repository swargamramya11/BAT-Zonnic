Feature: Zonnic Canada Blog pages

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Open Chat dialog
    And user click on chat icon
    Then user click on talk to human button
    And enter issue description in chat box
    Then click on toggle to off sound
    Then close the chat icon