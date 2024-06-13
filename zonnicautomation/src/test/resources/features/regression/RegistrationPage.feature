Feature: Zonnic Canada Registration page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario: Registration email template
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with 'canadaPostStandard' as 'normalUser'
    Then assert that the email is received with subject 'registrationConfirmationEmail' with content 'registrationConfirmationEmailEmailContent' and with header 'registrationConfirmationHeader'

  @zonicCAReg @zonnicCALive @bcLive
  Scenario: Account Sign-Up
    And click on Create account button '<province>' and Without filling mandatory fields 'quickAddressFinder' with 'canadaPostStandard'
    Then give incorrect format input for the E-mail field
    Then assert Error message for invalid format of email
    And validate password rule suggestion
    And validate different password combination
    And validate password strength indicator
    And enter confirm password
    Then Without click user accepts T&C
    And User click on checkbox Link
    Then click on create an account
    Then validate if account is created or not

  @zonnicCAReg @bcLive
  Scenario: Validate Terms and Conditions, contact us and privacy policy in SignUp Page
    Then click on 'personIcon' in header
    Then click on 'createAccount' in header
    And click on 'link' and verify url and title in signUp Page
      | link             |
      | contactUs        |
      | privacyPolicy    |
      | terms&Conditions |

  @zonnicCAReg
  Scenario: Prevent prospective customers from submitting more than 2 verification requests in 72h
    Then click on 'personIcon' in header
    Then click on 'createAccount' in header
    And user try to create a new account with 'nonwhitelisted' email format province '<province>' and 'quickAddressFinder' with 'canadaPostStandard' as 'normalUser'
    Then validate 'errorMessage1' in account creation
    Then click on create an account
    Then validate 'errorMessage1' in account creation
    Then click on create an account
    Then validate 'errorMessage2' in account creation