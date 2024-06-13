Feature: Zonnic Canada SSO flows

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Vuse site
    Then user accepts cookies and select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: SSO - Creating user in vuse canada site and try to signin in zonnic site
    Then create account in Vuse site in 'same' province
    And validate if account is created for vuse
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'
    And navigate to signin Page
    Then user signs in with new user and 'loginValidPassword.key'
    Then accept terms and conditions in zonnic site and signin
    And validate if account is created or not
    Then click on 'myAccountLink' in header
    Then user clicks on the 'signOut' text and verify link 'signOutLink.key' with header 'signOutHeader' and title 'HomePageTitle'
    And navigate to signin Page
    Then user signs in with new user and 'loginValidPassword.key'
    And validate if account is created or not

  @zonnicCAReg
  Scenario: SSO - Creating user in vuse canada site and try to Register with same email in zonnic site
    Then create account in Vuse site in 'same' province
    And validate if account is created for vuse
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'
    Then user try to register with same email which is registered in vuse with province '<province>'
    Then validate if error popup is appeared as account is already present in vuse site and close it

  @zonnicCAReg
  Scenario: SSO - Creating user in one province in Vuse canada site and try to Login in another province in zonnic site it should redirect to appropriate province
    Then create account in Vuse site in 'different' province
    And validate if account is created for vuse
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'
    And navigate to signin Page
    Then user signs in with new user and 'loginValidPassword.key'
    Then accept terms and conditions in zonnic site and signin
    Then website should be redirected to appropriate province with 'different' province