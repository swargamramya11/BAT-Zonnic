Feature: Zonnic Canada Home page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Feedback in homepage
    Then click 'feedback' button
    Then click on NEXT button to continue survey
    Then validate 'questionPart1' in feedback page
    Then validate 'questionPart2' in feedback page
    Then select an option of 'giveWebSiteFeedback'
    Then click 'nextButton' button
    Then validate 'giveWebSiteFeedbackQuestion' in feedback page
    Then click 'backButton' button
    Then click on NEXT button to continue survey
    Then validate 'questionPart1' in feedback page
    Then click 'nextButton' button
    Then select an option of 'satisfied'
    Then click 'nextButton' button
    Then validate 'satisfiedAndVerySatisfiedQuestion' in feedback page
    Then enter comments in text box
    Then click 'nextButton' button
    Then validate 'bestDescribes' in feedback page
    Then select an option of 'underStandHowToUseZonnic'
    Then click 'nextButton' button
    Then validate 'wereUAbleToSuccessfullyComplete' in feedback page
    Then click 'nextButton' button
    Then select an option of 'yes'
    Then validate 'whatElseYouShare' in feedback page
    Then click 'nextButton' button
    Then validate 'surveyEndMessage' in feedback page
    Then refresh browser
    Then click 'feedback' button
    Then click on NEXT button to continue survey
    Then validate 'questionPart1' in feedback page
    Then validate 'questionPart2' in feedback page
    Then select an option of 'reportAnIssue'
    Then click 'nextButton' button
    Then validate 'reportIssueQuestion' in feedback page
    Then click 'nextButton' button
    Then validate 'contactUsPageText' in feedback page
    Then click 'nextButton' button
    Then validate 'surveyEndMessage' in feedback page
    Then refresh browser
    Then click 'feedback' button
    Then click on NEXT button to continue survey
    Then validate 'questionPart1' in feedback page
    Then validate 'questionPart2' in feedback page
    Then select an option of 'contactCustomerService'
    Then click 'nextButton' button
    Then validate 'contactUsPageText2' in feedback page
    Then click 'nextButton' button
    Then validate 'surveyEndMessage' in feedback page
    Then refresh browser
    Then click 'feedback' button
    Then click on NEXT button to continue survey
    Then validate 'questionPart1' in feedback page
    Then validate 'questionPart2' in feedback page
    Then select an option of 'others'
    Then click 'nextButton' button
    Then validate 'otherQuestion' in feedback page
    Then click 'nextButton' button
    Then validate 'surveyEndMessage' in feedback page

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Language selector in Home Page
    And click on language selector
    Then validate 'languageSelectorHeading' in Language Selector
    Then validate 'languageSelectorText' in Language Selector
    Then validate 'selectYourLanguageHeader' in Language Selector
    Then select other language
    Then click on confirm button
    And click on language selector
    Then validate 'otherLanguageSelectorHeading' in Language Selector
    Then validate 'otherLanguageSelectorText' in Language Selector
    Then validate 'otherSelectYourLanguageHeader' in Language Selector
    And click on close in language selector