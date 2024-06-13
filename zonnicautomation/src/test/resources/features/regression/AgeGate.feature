Feature: Zonnic Canada Age Gate

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Age Gate components validation
    Then select 'language'
    Then verify 'zonnicLogo' in age gate
    Then verify 'welcomeZonnicText' in age gate
    Then verify 'selectProvinceText' in age gate
    Then verify 'textAtTop' in age gate
    Then verify 'textUnderDropDown' in age gate
#    Then verify 'textAtLast' in age gate
    Then verify 'alphabeticalOrderOfDropdownElements' in age gate
    Then verify 'countOfDropDownElements' in age gate

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Age Gate - Select "YES, I AM 18+" button
    Then select 'language'
    Then select 'province'
    Then click on 'YesIam18+' in age gate

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Age Gate - Select "NO, I AM NOT 18+" button
    Then select 'language'
    Then select 'province'
    Then click on 'NoIamNot18+' in age gate
    Then verify 'oops' in age gate
    Then verify 'textInAgeLessPage' in age gate
    Then verify 'zonnicLogo' in age gate

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Language tabs validation in age gate
    Then select 'language'
    Then verify 'welcomeZonnicText' in age gate
    Then select 'otherLanguage'
    Then verify 'otherWelcomeZonnicText' in age gate