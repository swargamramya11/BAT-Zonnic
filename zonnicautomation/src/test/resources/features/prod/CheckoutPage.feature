Feature: Zonnic Canada Checkout flows in prod

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCALive
  Scenario Outline: Live - When Order amount is less than $75 then shipping cost is $20 and tax percentage validations - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
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
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    Examples:
      | shippingMethod     | cardType          |
      | canadaPostStandard | masterCardDetails |

  @zonnicCALive
  Scenario Outline: Live - When Order amount is more than $75 then shipping cost is $20 and tax percentage validations - Checkout with shippingMethod: <shippingMethod> and cardType:<cardType>
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
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | visaCardDetails |

  @zonnicCALive
  Scenario Outline: Card fields validations | Add address in checkout
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then validate error messages of card details
    Then click on add address button
    Then check error messages if fields are empty
    Then add address fields in checkout page
    Then validate if address is updated in checkout Page
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |

  @zonnicCALive
  Scenario Outline: Live - Apply invalid coupon code at cart and checkout page
    Then user create account through API and login with created credentials as 'normalUser'
    And user navigates to PLP page and adds a product to basket
    Then user click on basket icon and open mini Basket
    And user get subtotal in mini basket
    And user click on checkout button in mini basket
    Then add 'invalid' coupon code 'invalidCoupon' in 'cart'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'cart'
    Then user should validate subtotal and total 'cart' page with 'nocoupon'
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then user should validate percentage of tax in cart page with 'noCoupon' in 'checkoutAfterSelectingShipping'
    Then add 'invalid' coupon code 'invalidCoupon' in 'checkout'
#    Then user should validate subtotal and total 'checkoutBeforeSelectingShipping' page with 'nocoupon'
    Then Customer selects shipment method '<shippingMethod>'
    Then user should validate subtotal and total 'checkoutAfterSelectingShipping' page with 'nocoupon'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |