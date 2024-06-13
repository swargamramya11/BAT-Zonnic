Feature: Zonnic Canada checkout flows

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario Outline: Multiple products checkout
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '1' from 'PLP' of index '1'
    Then add product quantity of '1' from 'PLP' of index '2'
    Then confirm quantity '2' added to minibasket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    Examples:
      | shippingMethod     | cardType        |
      | canadaPostStandard | amexCardDetails |

  @zonnicCAReg @zonnicCALive
  Scenario: Checkout Page Validations
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '1' from 'PDP' of index '2'
    Then click on shopping cart link and navigate to Cart Page
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then validate if all sections are present in checkout Page
    And validate currency in "checkout" Page
    Then validate terms and conditions Page in 'checkout'
    Then validate error messages of card details
    And click on 'returnToBasket' button in 'checkout' Page
    And user click on proceed to checkout button in cart page as 'normalUser'
    And click on 'backButton' button in 'checkout' Page
    And user click on proceed to checkout button in cart page as 'normalUser'
    And click on 'zonnicIcon' button in 'checkout' Page

  @zonnicCAReg
  Scenario Outline: Fedex Standard - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType> and order details page validations
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    And click on 'zonnicIcon' button in 'thankYouPage' Page
    Examples:
      | shippingMethod | cardType        |
      | fedexStandard  | amexCardDetails |

  @abReg @onReg @bcReg
  Scenario Outline: NextDay/Same Day delivery - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
#    And click on 'backButton' button in 'thankYouPage' Page
    Examples:
      | shippingMethod  | cardType        |
      | nextDayDelivery | amexCardDetails |

  @abReg @onReg @bcReg @skReg
  Scenario Outline: Fedex NextDay delivery - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'returnToHomeButton' in Thank you Page
    Then click on 'myAccountLink' in header
#    Then user click on 'orderDetailsButtonInOverViewPage'
    Examples:
      | shippingMethod | cardType          |
      | fedexNextDay   | masterCardDetails |

  @zonnicCAReg
  Scenario Outline: Order Confirmation email template - checkout with shipping Method: <shippingMethod> and cardType:<cardType>
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket with quantity '1'
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'noCoupon'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate products quantity in order details page
    Then assert that the email is received with subject 'orderConfimationEmail' with content 'orderConfimationEmailContent' and with header 'orderConfirmationHeader'
    Then validate price details in email with 'oldDeliveryAddress' with 'noCoupon'
    Examples:
      | shippingMethod     | cardType        |
      | canadaPostStandard | visaCardDetails |

  @zonnicCAReg
  Scenario Outline: Add address in Checkout with shipping Method: <shippingMethod> and cardType:<cardType>
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket with quantity '1'
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    Then click on add address button
    Then add address fields in checkout page
    Then validate if address is updated in checkout Page
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'noCoupon'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    Then validate if 'billingAddress' is changed in order details page
    Then validate if 'newDeliveryAddress' is changed in order details page
    Then user clicks on the 'addressBook' text and verify link 'addressBookLink.key' with header 'addressHeader1' and title 'addressBookTitle'
    And verify 'otherAddressHeader'
    Then validate other addresses
    Then assert that the email is received with subject 'orderConfimationEmail' with content 'orderConfimationEmailContent' and with header 'orderConfirmationHeader'
    Then validate price details in email with 'newDeliveryAddress' with 'noCoupon'
    Examples:
      | shippingMethod    | cardType          |
      | canadaPostExpress | masterCardDetails |

  @zonnicCAReg
  Scenario: Add address in Checkout and try to select address from list
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with 'canadaPostExpress' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then click on add address button
    Then verify 'previouslyUsedAddressesHeader'
    Then check error messages if fields are empty
    Then add address fields in checkout page
    Then select another address from list of addresses
    Then validate if selected address is displayed

  @zonnicCAReg
  Scenario Outline: When Order amount is less than $75 then shipping cost is $20 and tax percentage validations - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket with quantity '1'
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'noCoupon'
    And user should validate shipping cost if '<=75' in checkout page
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'noCoupon'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate if all price details are correct with 'noCoupon'
    Examples:
      | shippingMethod     | cardType          |
      | canadaPostStandard | masterCardDetails |

  @zonnicCAReg
  Scenario Outline: When Order amount is more than $75 then shipping cost is $20 and tax percentage validations - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket with quantity '13'
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'noCoupon'
    And user should validate shipping cost if '>=75' in checkout page
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'noCoupon'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate if all price details are correct with 'noCoupon'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | visaCardDetails |

  @zonnicCAReg
  Scenario Outline: Saved Card in checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'savingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'savedCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Examples:
      | shippingMethod    | cardType          |
      | canadaPostExpress | amexCardDetails   |
      | canadaPostExpress | visaCardDetails   |
      | canadaPostExpress | masterCardDetails |

  @zonnicCAReg
  Scenario Outline: Apply coupon code at cart page
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then add 'invalid' coupon code 'invalidCoupon' in 'cart'
    Then add 'valid' coupon code 'zon10' in 'cart'
    Then user should validate percentage of tax in cart page with 'coupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'coupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
#    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate if all price details are correct with 'coupon'
    Then assert that the email is received with subject 'orderConfimationEmail' with content 'orderConfimationEmailContent' and with header 'orderConfirmationHeader'
    Then validate price details in email with 'oldDeliveryAddress' with 'coupon'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |

  @zonnicCAReg
  Scenario Outline: Apply coupon code at checkout page
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then add 'invalid' coupon code 'invalidCoupon' in 'checkout'
    Then add 'valid' coupon code 'zon10' in 'checkout'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'coupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate if all price details are correct with 'coupon'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |

  @zonnicCAReg
  Scenario Outline: Adding cross sell product in cart and checkout
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then add cross sell product
    And validate if cross sell product added to 'cart'
    And user click on proceed to checkout button in cart page as 'normalUser'
    And validate if cross sell product added to 'checkout'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    And verify 'orderNumber'
    Then validate if cross sell product added to 'orderDetailsPage'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |