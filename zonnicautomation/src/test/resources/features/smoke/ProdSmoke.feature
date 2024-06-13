Feature: Live - Zonnic Canada Smoke flow

  @zonnicCALiveSmoke
  Scenario Outline: Live - Smoke flow - Registration and checkout with shippingMethod : <shippingMethod> and cardType : <cardType> with province: <province>
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    And validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And click on 'returnToBasket' button in 'checkout' Page
    And click on 'zonnicIcon' button in 'basket' Page
    Then click on 'myAccountLink' in header
    Then user clicks on the 'signOut' text and verify link 'signOutLink.key' with header 'signOutHeader' and title 'HomePageTitle'
    Examples:
      | province                  | shippingMethod     | cardType          |
      | Alberta                   | canadaPostExpress  | masterCardDetails |
      | British Columbia          | canadaPostStandard | visaCardDetails   |
      | Manitoba                  | fedexStandard      | amexCardDetails   |
      | New Brunswick             | fedexStandard      | masterCardDetails |
      | Newfoundland and Labrador | canadaPostStandard | visaCardDetails   |
      | Nova Scotia               | canadaPostExpress  | amexCardDetails   |
      | Ontario                   | canadaPostStandard | masterCardDetails |
      | Prince Edward Island      | canadaPostExpress  | visaCardDetails   |
      | Saskatchewan              | fedexStandard      | amexCardDetails   |

  @zonnicCALiveSmoke
  Scenario Outline: Live - Smoke flow - Validate all links in header and footer with province : <province>
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'
    And click on language selector
    And click on close in language selector
    And user goes to PDP page and click add to cart basket
    And user goes to PLP page and validate elements
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
      | whatIsZonnic    | whatIsZonnicUrl.key    | whatIsZonnicTitle    |
      | whyZonnic       | whyZonnicUrl.key       | whyIsZonnicTitle     |
      | canDoBlog       | canDoBlogUrl.key       | canDoBlogTitle       |
      | help            | helpUrl.key            | helpTitle            |
      | storeLocator    | storeLocatorUrl.key    | storeLocatorTitle    |
      | healthCare      | healthCareURL.key      | healthCareTitle      |
    And user clicks on 'footerLinks' and verify url and title contains 'UrlToContain' 'titleToContain'
      | footerLinks        | UrlToContain            | titleToContain        |
      | contactUs          | contactUsUrl.key        | helpTitle             |
      | faq                | faqUrl.key              | HomePageTitle         |
      | footerStoreLocator | storeLocatorUrl.key     | storeLocatorTitle     |
#      | terms&Conditions   | terms&ConditionsUrl.key | terms&ConditionsTitle |
      | privacyPolicy      | privacyPolicyUrl.key    | privacyPolicyTitle    |
    Examples:
      | province                  |
      | Alberta                   |
      | British Columbia          |
      | Manitoba                  |
      | New Brunswick             |
      | Newfoundland and Labrador |
      | Nova Scotia               |
      | Ontario                   |
      | Prince Edward Island      |
      | Saskatchewan              |