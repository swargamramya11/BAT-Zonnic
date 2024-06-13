Feature: Zonnic Canada Forgot Password

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario Outline: Forgot Password email and changed password email verification
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'signOut' text and verify link 'signOutLink.key' with header 'signOutHeader' and title 'HomePageTitle'
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
    Then assert that the email is received with subject 'passwordResetEmail' with content 'passwordResetEmailContent' and with header 'passwordResetHeader'
    And verify 'createNewPasswordHeader'
    And verify 'createNewPasswordTextPart1'
    And verify 'createNewPasswordTextPart2'
    And verify 'emailInCreateNewAccountHeader'
    And verify 'enterNewPasswordInCreateNewAccountHeader'
    And verify 'reEnterNewPasswordInCreateNewAccountHeader'
    And validate error message of 'emailWhenEmpty'
    And validate error message of 'enterPasswordWhenEmpty'
    And validate error message of 'reEnterPasswordWhenEmpty'
    And validate error message of 'emailWithWrongFormat'
    And validate error message of 'enterPasswordErrorWhenCharactersLessThan8'
    And validate error message of 'reEnterPasswordErrorWhenCharactersLessThan8'
    And validate error message of 'enterPasswordErrorWhenCharactersGreaterThan8WithNoNumber'
    And validate error message of 'enterPasswordErrorWhenCharactersGreaterThan8WithNoSpecialCharacter'
    And validate error message of 'enterTwoDifferentPasswords'
    Then enter 'enterInvalidEmailForCreateNewPassword'
    Then enter 'enterNewPassword'
    Then enter 'reEnterNewPassword'
    Then user click on 'updatePasswordButton'
    And validate error message of 'enterDifferentEmailAndTryToUpdatePassword'
    And close existing tab and navigate to main tab
    Then assert that the email is received with subject 'passwordResetEmail' with content 'passwordResetEmailContent' and with header 'passwordResetHeader'
    Then enter 'enterEmailForCreateNewPassword'
    Then enter 'enterNewPassword'
    Then enter 'reEnterNewPassword'
    Then user click on 'updatePasswordButton'
    Then assert that the email is received with subject 'passwordChangedEmail' with content 'passwordChangedEmailContent' and with header 'changedPasswordHeader'
    Then click on 'personIcon' in header
    Then user signs in with new user and 'loginValidPasswordNew.key'
    Then validate if account is created or not
    Examples:
      | shippingMethod    |
      | canadaPostExpress |