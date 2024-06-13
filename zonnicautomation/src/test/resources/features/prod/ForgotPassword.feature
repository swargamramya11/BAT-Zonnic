Feature: Zonnic Canada Guest Checkout page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCALive @bcLive
  Scenario: Forgot Password
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'signOutLink' in header
    Then click on 'personIcon' in header
    Then user click on 'forgotPasswordLink'
    Then verify 'resetPasswordHeader'
    Then verify 'resetPasswordText'
    And validate error message of 'resetPasswordError'
    Then enter 'enterEmailForResetPassword'
    Then user click on 'resetButton'
    Then verify 'resetPasswordsuccessfullHeader'
    Then verify 'resetPasswordsuccessfullTextPart1'
    Then verify 'resetPasswordsuccessfullTextPart2'