Feature: Zonnic Canada header links

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Header - Why is Zonnic
    Then user clicks on the 'whyZonnic' text and verify link 'whyZonnicUrl.key' with title 'whyIsZonnicTitle'
    And user validate all whyzonnic page
    And assert faq text is displayed

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Header - Can do Blog
    Then user clicks on the 'canDoBlog' text and verify link 'canDoBlogUrl.key' with title 'canDoBlogTitle'
    And navigate to each and every page in blog page

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Header - Store Locator
    Then user clicks on the 'storeLocator' text and verify link 'storeLocatorUrl.key' with title 'storeLocatorTitle'
    Then validate text 'storeLocatorHeader'
    Then validate text 'belowHeader'
    Then validate text 'useCurrentLocationText'
    Then click on 'currentLocation' button
    Then enter data
    Then click on 'getDirections' button
#    Then click on locations in mapbox

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Header, empty Mini basket
    Then user clicks on the 'whyZonnic' text and verify link 'whyZonnicUrl.key' with title 'whyIsZonnicTitle'
    Then user clicks on zonnic logo
    Then user click on basket icon and open mini Basket
    And user validate empty 'miniBasket' elements

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Header - HELP
    Then user clicks on the 'Help' text and verify link 'helpHeaderUrl.key' with title 'helpTitle'
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

  @zonnicCAReg @qbReg @zonnicCALive @qbLive @bcLive
  Scenario: Header - NICOTINE POUCHES
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    And user goes to PLP page and validate elements
    Then 'increase' quantity in 'plp' of index '1'
    Then 'increase' quantity in 'plp' of index '1'
    Then add product to basket from 'PLP' of index '1'
    Then confirm quantity '3' added to minibasket
    Then 'increase' quantity in 'plp' of index '1'
    Then 'increase' quantity in 'plp' of index '1'
    Then 'decrease' quantity in 'plp' of index '1'
    Then add product to basket from 'plp' of index '1'

  @zonnicCAReg @qbReg @qbLive @bcLive
  Scenario: Header - What is Zonnic
    Then user clicks on the 'whatIsZonnic' text and verify link 'whatiszonnicHeaderUrl.key' with title 'whatIszonnicTitle'
    And validate message of 'whatIsZonnicHeaderMssg'
    Then user click on the 'exploreFlavours' text
    And  assert url contains 'nicotinePouchesUrl.key'
    And user goes to PLP page and validate elements
    Then user naviagte back to what is zonnic page
    Then user click on second explore flavours link
    And  assert url contains 'nicotinePouchesUrl.key'
    And user goes to PLP page and validate elements
    Then user naviagte back to what is zonnic page
    And user click on learnmore button
    Then user naviagte back to what is zonnic page
    And user click on learnmore button
    Then user naviagte back to what is zonnic page
    And user clicks on learnmore button
    Then user naviagte back to what is zonnic page
    And assert faqs text is displayed