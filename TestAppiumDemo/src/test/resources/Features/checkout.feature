#Author: somu

@payment
Feature: Cart

  @payment
  Scenario Outline: Verify Review order
    Given Open app
    And Navigate to the first product on the list
    And Add quantity 2
    And Add to cart and go to cart
    And Complete account creation with email <email> and password <password>
    And Complete checkout with Fullname <Fullname> AddressLine1 <AddressLine1> City <City> Zipcode <Zipcode> Country <Country>
    And Click on "Go to payment"
    Then Verify Text "Enter a payment method" is shown
    Then Verify "Review order" button is shown

    Examples:
      | email             | password   | Fullname  | AddressLine1 | City       | Zipcode | Country         |
      | "bob@example.com" | "10203040" | "My Name" | "Street 123" | "New York" | "10001" | "United States" |