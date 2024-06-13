Feature: Zonnic Canada guest login flows

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario: Guest Sign In with valid email and password
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then user signs in with customer properties 'loginValidEmail' 'loginValidPassword.key' as 'guestUser'

  @zonnicCAReg
  Scenario: Guest Sign In with invalid email and password
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then user signs in with customer properties 'loginInvalidEmail.key' 'loginValidPassword.key' as 'guestUser'
    And validate error message of 'invalidLoginErrorMessageAsGuest'
    And validate error message of 'emailInvalidFormatSignIn'
    And validate error message of 'emailEmptyInSignIn'
    And validate error message of 'passwordEmptyInSignIn'