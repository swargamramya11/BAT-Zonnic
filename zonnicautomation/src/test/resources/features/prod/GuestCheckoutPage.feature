Feature: Zonnic Canada Guest Checkout page

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCALive
  Scenario Outline: Live - Guest User - Apply invalid coupon code at cart, signup and checkout page
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then add 'invalid' coupon code 'invalidCoupon' in 'cart'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'nocoupon'
    And user click on proceed to checkout button in cart page as 'guestUser'
    Then click on create account button for 'guestUser'
    Then add 'invalid' coupon code 'invalidCoupon' in 'signup'
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'guestUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    Then add 'invalid' coupon code 'invalidCoupon' in 'checkout'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'nocoupon'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |