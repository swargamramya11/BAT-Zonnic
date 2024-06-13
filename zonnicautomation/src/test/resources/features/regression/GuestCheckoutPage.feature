Feature: Zonnic Canada Guest Checkout page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario Outline: Guest checkout
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'guestUser'
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'guestUser'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'noCoupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
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
      | shippingMethod     | cardType        |
      | canadaPostStandard | amexCardDetails |

  @zonnicCAReg
  Scenario Outline: Guest User - Apply coupon code at cart page
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then add 'invalid' coupon code 'invalidCoupon' in 'cart'
    Then add 'valid' coupon code 'zon10' in 'cart'
    Then user should validate percentage of tax in cart page with 'coupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'coupon'
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then user create account through API and login with created credentials as 'guestUser'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'coupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
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
  Scenario Outline: Guest User - Apply coupon code at checkout page
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'noCoupon'
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then user create account through API and login with created credentials as 'guestUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    Then add 'invalid' coupon code 'invalidCoupon' in 'checkout'
    Then add 'valid' coupon code 'zon10' in 'checkout'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'coupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
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
  Scenario Outline: Guest User - Apply coupon code at signup page
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then user should validate percentage of tax in cart page with 'nocoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'nocoupon'
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then click on create account button for 'guestUser'
    Then add 'invalid' coupon code 'invalidCoupon' in 'signup'
    Then add 'valid' coupon code 'zon10' in 'signup'
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'guestUser'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'coupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate percentage of tax in cart page with 'coupon' in 'checkoutAfterSelectingShipping'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'coupon'
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
  Scenario Outline: Guest User - Add address in Checkout with shipping Method: <shippingMethod> and cardType:<cardType>
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    And user click on proceed to checkout button in cart page as 'guestUser'
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'guestUser'
    Then click on add address button
    Then check error messages if fields are empty
    Then add address fields in checkout page
    Then validate if address is updated in checkout Page
    Then Customer selects shipment method '<shippingMethod>'
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
    Examples:
      | shippingMethod    | cardType          |
      | canadaPostExpress | masterCardDetails |