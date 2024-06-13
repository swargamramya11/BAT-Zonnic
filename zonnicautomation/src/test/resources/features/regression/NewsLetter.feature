Feature: Zonnic Canada newsletter subscription

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: Guest User - Newsletter subscription
    And user click on 'signUpNow'
    Then validate header 'logo' in newsletter
    Then validate error message of 'firstNameIsBlank' in newsletter
    Then validate error message of 'lastNameIsBlank' in newsletter
    Then validate error message of 'dobIsBlank' in newsletter
    Then validate error message of 'emailIsBlank' in newsletter
    Then validate error message of 'enterInvalidEmailFormat' in newsletter
    Then validate header 'header1' in newsletter
    Then validate header 'header2' in newsletter
    Then validate header 'disclaimer' in newsletter
    Then click on link 'contactUs' in newsletter
    And user click on 'signUpNow'
    Then click on link 'policy' in newsletter
    And user click on 'signUpNow'
    Then validate if field 'telephone' is displayed in newsletter
    Then validate if field 'province' is displayed in newsletter
    Then validate if field 'postalCode' is displayed in newsletter
    Then validate if field 'whereToRedeem' is displayed in newsletter
    Then validate if field header 'telephoneHeader' is displayed in newsletter
    Then validate if field header 'provinceHeader' is displayed in newsletter
    Then validate if field header 'postalCodeHeader' is displayed in newsletter
    Then validate if field header 'whereToRedeemHeader' is displayed in newsletter
    Then validate if field header 'firstNameHeader' is displayed in newsletter
    Then validate if field header 'lastNameHeader' is displayed in newsletter
    Then validate if field header 'dobHeader' is displayed in newsletter
    Then validate if field header 'emailHeader' is displayed in newsletter
    Then enter data 'firstName'
    Then enter data 'lastName'
    Then enter data 'dob'
    Then enter data 'email'
    Then select which applies to you dropdown
    Then click on link 'signUp' in newsletter
    Then validate header 'submissionHeader' in newsletter
    Then validate header 'submissionLogo' in newsletter
    Then click on link 'close' in newsletter

  @zonnicCAReg
  Scenario: LoggedIn User - Newsletter subscription
    Then user signs in with customer properties 'loginValidEmail' 'loginValidPassword.key' as 'normalUser'
    And user click on 'signUpNowLoggedIn'

  @zonnicCALive @bcLive
  Scenario: Live - LoggedIn User - Newsletter subscription
    Then user signs in with customer properties 'loginValidEmailProd' 'loginValidPassword.key' as 'normalUser'
    And user click on 'signUpNowLoggedIn'
