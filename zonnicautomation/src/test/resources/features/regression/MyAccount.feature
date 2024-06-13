Feature: Zonnic Canada My Account scenarios

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg
  Scenario Outline: My Account - Order History and Buy All Again
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with '<shippingMethod>' as 'normalUser'
    Then validate if account is created or not
    Then click on 'myAccountLink' in header
#    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
#    Then validate empty order history page
    And user navigates to PLP page and adds a product to basket
    And user navigate to basket and then to checkout page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Then click on 'MyAccountButton' in Thank you Page
    Then user clicks on the 'orderHistory' text and verify link 'orderHistoryLink.key' with header 'orderHistoryHeader' and title 'orderHistoryTitle'
    Then verify order if present or not
    Then user click on 'orderDetailsButtonInOrderHistoryPage'
    Then validate if 'deliveryAddress' is changed in order details page
    And verify 'orderNumber'
    And verify 'statusHeader'
    And verify 'orderHeader'
    And verify 'placedHeader'
    And verify 'orderNumber'
    And verify 'date'
    And verify 'status'
    And verify 'shippingMethod'
    And verify 'shippingMethodHeader'
    And verify 'paymentMethod'
    And verify 'paymentMethodHeader'
    Then user click on 'buyAllAgainButton'
    Then validate if products added to basket
    And user click on proceed to checkout button in cart page as 'normalUser'
    Then Customer selects shipment method '<shippingMethod>'
    And Customer makes a home delivery purchase with card type '<cardType>' and with 'newCard' by 'notSavingCard'
    And assert that the 'ThankForPurchase' thank you message is displayed in the page header
    Examples:
      | shippingMethod    | cardType        |
      | canadaPostExpress | amexCardDetails |

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: My Account - Marketing Preference
    And create a new account with province '<province>' and address should be entered by 'quickAddressFinder' with 'canadaPostExpress' as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'marketingPreference' text and verify link 'marketingPreferenceLink.key' with header 'marketPreferenceHeader' and title 'marketingPreferenceTitle'
    Then 'unsubscribe' to newsletter
    Then click on 'myAccountLink' in header
    Then user clicks on the 'marketingPreference' text and verify link 'marketingPreferenceLink.key' with header 'marketPreferenceHeader' and title 'marketingPreferenceTitle'
    Then 'subscribe' to newsletter

  @zonnicCAReg
  Scenario: My Account - Overview Page
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'myAccount' text and verify link 'myAccountLink.key' with header 'myAccountHeader' and title 'myAccountTitle'
    And verify 'welcome'
    And verify 'firstAndLastName'
    And verify 'email'
    And verify 'recentOrders'
    And verify 'addressHeader'
    And verify 'myAccountDetails'
    And verify 'defaultShippingAddressHeader'
    And verify 'defaultBillingAddressHeader'
    Then user click on 'editDetails'
    Then user clicks on the 'myAccount' text and verify link 'myAccountLink.key' with header 'myAccountHeader' and title 'myAccountTitle'
    Then user click on 'editAddress'
    Then user clicks on the 'myAccount' text and verify link 'myAccountLink.key' with header 'myAccountHeader' and title 'myAccountTitle'
    Then user click on 'changePassword'
    Then user clicks on the 'myAccount' text and verify link 'myAccountLink.key' with header 'myAccountHeader' and title 'myAccountTitle'
#    Then user click on 'viewOrderHistory'
#    Then user clicks on the 'myAccount' text and verify link 'myAccountLink.key' with header 'myAccountHeader' and title 'myAccountTitle'

  @zonnicCAReg @bcLive
  Scenario: My Account - Signout
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'signOutLink' in header

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: My Details - Change Password
    Then user create account through API and login with created credentials as 'normalUser'
    Then user clicks on the 'myDetails' text and verify link 'myDetailsLink.key' with header 'myDetailsHeader' and title 'myDetailsTitle'
    Then verify 'firstNameHeader'
    Then verify 'lastNameHeader'
    Then verify 'emailHeader'
    Then verify 'currentPasswordHeader'
    Then verify 'firstNameInMyDetails'
    Then verify 'lastNameInMyDetails'
    Then verify 'emailInMyDetails'
    Then verify 'currentPasswordInMyDetails'
    And click on edit for change password
    And validate error message of 'currentPassword'
    And validate error message of 'newPassword'
    And validate error message of 'confirmPassword'
    And validate error message of 'newPasswordErrorWhenCharactersLessThan8'
    And validate error message of 'confirmPasswordErrorWhenCharactersLessThan8'
    And validate error message of 'newPasswordErrorWhenCharactersGreaterThan8WithNoSpecialCharacter'
    And validate error message of 'confirmPasswordErrorWhenCharactersGreaterThan8WithNoNumber'
    Then enter 'wrongCurrentPassword'
    Then enter 'newPassword'
    And validate error message of 'confirmPassword'
    Then enter 'confirmPassword'
    Then user click on 'saveChangesButtonInPasswordChangeWithoutSuccessMessageValidation'
    And validate error message of 'byEnteringWrongCurrentPassword'
    Then enter 'currentPassword'
    Then enter 'enterNewPasswordSameAsCurrent'
    And validate error message of 'newPassword'
    Then enter 'newPassword'
    Then enter 'confirmPassword'
    Then user click on 'saveChangesButtonInPasswordChange'
    Then validate success message of 'successMessageForChangePassword'
    Then user clicks on the 'signOut' text and verify link 'signOutLink.key' with header 'signOutHeader' and title 'HomePageTitle'
    And navigate to signin Page
    Then user signs in with new user and 'loginValidPassword.key'
    And validate error message of 'wrongPassword'
    Then user signs in with new user and 'loginValidPasswordNew.key'
    Then validate if account is created or not

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: My Account - Add Address and remove in other addresses
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'addressBook' text and verify link 'addressBookLink.key' with header 'addressHeader1' and title 'addressBookTitle'
    And verify 'defaultShippingAddressHeaderInAddressBook'
    And verify 'defaultBillingAddressHeaderInAddressBook'
    And verify 'defaultBillingAddress'
    And verify 'defaultShippingAddress'
    Then click on 'addNewAddress'
    Then 'addAddressWithDefaultShippingAddressChecked' in address book
    Then click on 'addNewAddress'
    Then 'addAddressWithDefaultShippingAddressNotChecked' in address book
    And verify 'otherAddressHeader'
    And click on make default shipping address in 'otherAddresses'
    And click on make default shipping address in 'defaultBillingAddresses'
    Then 'removeAddress' in address book

  @zonnicCAReg @zonnicCALive @bcLive
  Scenario: My Account - Edit Address in default shipping and default billing address section
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'addressBook' text and verify link 'addressBookLink.key' with header 'addressHeader1' and title 'addressBookTitle'
    Then click on 'addNewAddress'
    Then 'addAddressWithDefaultShippingAddressChecked' in address book
    Then click on 'editAddress'
    Then 'editAddressWithDefaultShippingAddressChecked' in address book
    And click on make default shipping address in 'defaultBillingAddresses'

  @zonnicCAReg
  Scenario: My Account - Edit Address in other address section
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'addressBook' text and verify link 'addressBookLink.key' with header 'addressHeader1' and title 'addressBookTitle'
    Then click on 'addNewAddress'
    Then 'addAddressWithDefaultShippingAddressNotChecked' in address book
    Then click on 'editAddress'
    Then 'editAddressInOthersWithDefaultShippingAddressNotChecked' in address book
    Then click on 'editAddress'
    Then 'editAddressInOthersWithDefaultShippingAddressChecked' in address book

  @zonnicCAReg
  Scenario: My Account - Add Address and Edit address field validations
    Then user create account through API and login with created credentials as 'normalUser'
    Then click on 'myAccountLink' in header
    Then user clicks on the 'addressBook' text and verify link 'addressBookLink.key' with header 'addressHeader1' and title 'addressBookTitle'
    Then click on 'addNewAddress'
    And validate error message of 'firstName'
    And validate error message of 'lastName'
    And validate error message of 'phoneNumber'
    Then 'addAddressWithDefaultShippingAddressChecked' in address book
    Then click on 'editAddress'
    And validate error message of 'firstName'
    And validate error message of 'lastName'
    And validate error message of 'phoneNumber'
    Then 'editAddressWithDefaultShippingAddressChecked' in address book