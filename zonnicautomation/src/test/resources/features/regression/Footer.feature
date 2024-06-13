Feature: Zonnic Canada Footer links

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Footer - Zonnic logo, Warning message, Instagram
    And user clicks on the 'zonnicLogo' text
    And verify link 'zonnicLogoURL.key'
    Then verify below warningmessage displayed
    And user click on instagram link
    And assert url contains 'InstagramURL.key'

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Footer - GET IN TOUCH
    And user validate 'getInTouchText' in footer
    And validate opening hours
    And user validate 'hoursText1' in footer
    And user validate 'hoursText2' in footer
    Then user click on submit a ticket
    Then browser back
    Then user click on telephone number and cancel alert

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Footer - Social media in home page
    And user click on instagram icon in home page
    And assert url contains 'InstagramURL.key'

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Footer - LEGAL section links
    And user validate 'legalText' in footer
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks      | UrlToContain            | titleToContain        |
      | terms&Conditions | terms&ConditionsUrl.key | terms&ConditionsTitle |
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks   | UrlToContain         | titleToContain     |
      | privacyPolicy | privacyPolicyUrl.key | privacyPolicyTitle |

  @qbLive @bcLive
  Scenario: Live - Footer - LEGAL section links
    And user validate 'legalText' in footer
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks | UrlToContain      | titleToContain |
      | termsOfUse  | termsOfUseUrl.key | cookieTitle    |
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks            | UrlToContain            | titleToContain            |
      | terms&ConditionsOfSale | terms&ConditionsUrl.key | terms&ConditionsSaleTitle |
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks   | UrlToContain         | titleToContain     |
      | privacyPolicy | privacyPolicyUrl.key | privacyPolicyTitle |

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Footer - Contact Us
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks | UrlToContain     | titleToContain |
      | contactUs   | contactUsUrl.key | helpTitle      |
    And validate message of 'contactUsHeaderMssg'
    And user click on start chat icon
    Then user click on talk to human button
    And enter issue description in chat box
    Then click on toggle to off sound
    Then close the chat icon
    And user click on log a ticket button
    And assert url contain 'submitTicketURL'
    Then user click on see all faq button
    And assert url contain 'SeeAllFaq'
    And user click on call us button
    And  assert url contains 'CallUsUrl.key'