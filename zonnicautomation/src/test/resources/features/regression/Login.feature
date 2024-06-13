Feature: Zonnic Canada Login

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario: Sign In with valid email and password
    Then user signs in with customer properties 'loginValidEmail' 'loginValidPassword.key' as 'normalUser'
    Then validate if account is created or not

  @zonnicCAReg
  Scenario: Sign In with invalid email and password
    Then user signs in with customer properties 'loginInvalidEmail.key' 'loginValidPassword.key' as 'normalUser'
    And validate error message of 'invalidLoginErrorMessage'
    And validate error message of 'emailInvalidFormatSignIn'
    And validate error message of 'emailEmptyInSignIn'
    And validate error message of 'passwordEmptyInSignIn'