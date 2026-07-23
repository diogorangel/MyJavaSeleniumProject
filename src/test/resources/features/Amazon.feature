Feature: SauceDemo Login and Products Validation

  # LOGIN SCENARIOS (1–8)

  Scenario: Successful login
    Given I open SauceDemo page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the products page

  Scenario: Invalid password
    Given I open SauceDemo page
    When I login with username "standard_user" and password "wrong"
    Then I should see login error "Username and password do not match"

  Scenario: Empty username
    Given I open SauceDemo page
    When I login with username "" and password "secret_sauce"
    Then I should see login error "Username is required"

  Scenario: Empty password
    Given I open SauceDemo page
    When I login with username "standard_user" and password ""
    Then I should see login error "Password is required"

  Scenario: Locked user
    Given I open SauceDemo page
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see login error "Sorry, this user has been locked out."

  Scenario: Login session persistence
    Given I am logged in
    When I refresh the page
    Then I should remain on products page

  Scenario: Logout success
    Given I am logged in
    When I logout
    Then I should be redirected to login page

  Scenario: Login performance validation
    Given I open SauceDemo page
    When I login within 3 seconds
    Then login should be successful


  # PRODUCT SCENARIOS (9–15)

  Scenario: View product list
    Given I am logged in
    Then I should see at least 6 products

  Scenario: Add product to cart
    Given I am logged in
    When I add product "Sauce Labs Backpack" to cart
    Then cart badge should show "1"

  Scenario: Remove product from cart
    Given I added a product
    When I remove the product
    Then cart badge should be empty

  Scenario: Product sorting A-Z
    Given I am logged in
    When I sort products by "Name (A to Z)"
    Then products should be sorted alphabetically

  Scenario: Product sorting price low to high
    Given I am logged in
    When I sort products by "Price (low to high)"
    Then products should be sorted by price ascending

  Scenario: Open product detail
    Given I am logged in
    When I open product details for "Sauce Labs Backpack"
    Then product title should be "Sauce Labs Backpack"

  Scenario: Validate product price consistency
    Given I am logged in
    When I open product details for "Sauce Labs Backpack"
    Then price should match listing price


  # CART & CHECKOUT SCENARIOS (16–22)

  Scenario: Add multiple products
    Given I am logged in
    When I add 3 products to cart
    Then cart badge should show "3"

  Scenario: Checkout flow success
    Given I have items in cart
    When I complete checkout with valid data
    Then I should see order confirmation

  Scenario: Checkout empty cart
    Given I have empty cart
    When I attempt checkout
    Then checkout should not proceed

  Scenario: Missing first name in checkout
    Given I have items in cart
    When I checkout without first name
    Then I should see error "First Name is required"

  Scenario: Missing postal code
    Given I have items in cart
    When I checkout without postal code
    Then I should see error "Postal Code is required"

  Scenario: Cancel checkout
    Given I am in checkout page
    When I cancel checkout
    Then I should return to products page

  Scenario: Validate total price calculation
    Given I have 2 products in cart
    When I proceed to checkout overview
    Then total price should be correct