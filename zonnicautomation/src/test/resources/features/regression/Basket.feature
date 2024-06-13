Feature: Zonnic Canada Basket page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @zonnicCALive
  Scenario: Mini basket page validations
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    Then validate elements in 'miniBasket'
    Then remove items from 'miniBasket'

  @zonnicCAReg @zonnicCALive
  Scenario: Basket page validations
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then validate elements in 'basket'
    And click on 'backButton' button in 'basket' Page
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    And click on 'continueShopping' button in 'basket' Page
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    And click on 'zonnicIcon' button in 'basket' Page
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then remove items from 'basket'

  @zonnicCAReg @zonnicCALive
  Scenario: Empty Basket page
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then remove items from 'basket'
    And user validate empty 'basket' elements
    And click on start shopping button