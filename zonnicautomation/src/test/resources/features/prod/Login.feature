Feature: Zonnic Canada PDP

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCALive @bcLive
  Scenario: Sign In with invalid email and password | Valid email and password
    Then user signs in with customer properties 'loginInvalidEmailProd.key' 'loginValidPassword.key' as 'guestUser'
    And validate error message of 'invalidLoginErrorMessage'
    And validate error message of 'emailInvalidFormatSignIn'
    And validate error message of 'emailEmptyInSignIn'
    And validate error message of 'passwordEmptyInSignIn'
    Then click on 'personIcon' in header
    Then user signs in with customer properties 'loginValidEmailProd' 'loginValidPassword.key' as 'normalUser'
    Then validate if account is created or not
    Then user clicks on the 'signOut' text and verify link 'signOutLink.key' with header 'signOutHeader' and title 'HomePageTitle'