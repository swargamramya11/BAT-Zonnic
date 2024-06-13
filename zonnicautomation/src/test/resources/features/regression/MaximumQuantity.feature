Feature: Zonnic Canada Maximum quantity functionalities

  Background: Open website and select over 18 age in age gate popup
    Given user invokes browser
    Then user navigates to Zonnic site
    And select over 18 age confirmation option with province '<province>'

  @zonnicCAReg @zonnicCALive
  Scenario: Guest User - Validate if error message is displayed or not if maximum quantity is added to cart in PLP
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '21' from 'PLP' of index '2'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'plp'
    Then refresh browser
    Then add product quantity of '10' from 'PLP' of index '1'
    Then confirm quantity '10' added to minibasket
    Then refresh browser
    Then add product quantity of '11' from 'PLP' of index '2'
    Then confirm quantity '21' added to minibasket
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'plp'

  @zonnicCAReg
  Scenario: Guest User - Validate if error message is displayed or not if maximum quantity is added to cart in PDP
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '21' from 'PDP' of index '2'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'plp'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '10' from 'PLP' of index '1'
    Then confirm quantity '10' added to minibasket
    Then refresh browser
    Then add product quantity of '11' from 'PDP' of index '2'
    Then confirm quantity '21' added to minibasket
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'plp'

  @zonnicCAReg
  Scenario: Guest User - Validate if error message is displayed or not if maximum quantity is added to cart in Basket Page
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '20' from 'PLP' of index '2'
    Then confirm quantity '20' added to minibasket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then 'increase' quantity in 'basket' of index '1'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'basket'
    And navigate to home page from basket
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '3' from 'PLP' of index '1'
    Then confirm quantity '23' added to minibasket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'basket'

  @zonnicCAReg
  Scenario: Guest User - Validate if error message is displayed or not if maximum quantity is added to cart in Mini Basket
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '20' from 'PLP' of index '2'
    Then confirm quantity '20' added to minibasket
    Then user click on basket icon and open mini Basket
    Then 'increase' quantity in 'miniBasket' of index '1'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'miniBasket'
    Then refresh browser
    Then add product quantity of '3' from 'PLP' of index '1'
    Then confirm quantity '23' added to minibasket
    Then refresh browser
    Then user click on basket icon and open mini Basket
    Then 'increase' quantity in 'miniBasket' of index '2'
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'miniBasket'

  @zonnicCAReg
  Scenario: LoggedIn User - Validate if error message is displayed or not if maximum quantity is added to cart in PLP
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '21' from 'PLP' of index '2'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'plp'
    Then refresh browser
    Then add product quantity of '10' from 'PLP' of index '1'
    Then confirm quantity '10' added to minibasket
    Then refresh browser
    Then add product quantity of '11' from 'PLP' of index '2'
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'plp'

  @zonnicCAReg
  Scenario: LoggedIn User - Validate if error message is displayed or not if maximum quantity is added to cart in PDP
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '21' from 'PDP' of index '2'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'plp'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '10' from 'PLP' of index '1'
    Then confirm quantity '10' added to minibasket
    Then refresh browser
    Then add product quantity of '11' from 'PDP' of index '2'
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'plp'

  @zonnicCAReg
  Scenario: LoggedIn User - Validate if error message is displayed or not if maximum quantity is added to cart in Basket Page
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '20' from 'PLP' of index '2'
    Then confirm quantity '20' added to minibasket
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then 'increase' quantity in 'basket' of index '1'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'basket'
    And navigate to home page from basket
    Then user click on basket icon and open mini Basket
    Then 'decrease' quantity in 'miniBasket' of index '1'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '1' from 'PLP' of index '1'
    Then user click on basket icon and open mini Basket
    And user click on checkout button in mini basket
    Then 'increase' quantity in 'basket' of index '1'
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'basket'

  @zonnicCAReg
  Scenario: LoggedIn User - Validate if error message is displayed or not if maximum quantity is added to cart in Mini Basket
    Then user create account through API and login with created credentials as 'normalUser'
    And user clicks on 'headermenu' and verify url and title contains 'UrlToContain' 'titleToContain'
      | headermenu      | UrlToContain           | titleToContain       |
      | nicotinePouches | nicotinePouchesUrl.key | nicotinePouchesTitle |
    Then add product quantity of '20' from 'PLP' of index '2'
    Then confirm quantity '20' added to minibasket
    Then user click on basket icon and open mini Basket
    Then 'increase' quantity in 'miniBasket' of index '1'
    Then validate is 'maxQuantityForSingleProduct' error message is displayed in 'miniBasket'
    Then refresh browser
    Then user click on basket icon and open mini Basket
    Then 'decrease' quantity in 'miniBasket' of index '1'
    Then add product quantity of '1' from 'PLP' of index '1'
    Then confirm quantity '20' added to minibasket
    Then refresh browser
    Then user click on basket icon and open mini Basket
    Then 'increase' quantity in 'miniBasket' of index '2'
    Then validate is 'maxQuantityForCombinationProduct' error message is displayed in 'miniBasket'