package steps;

import driver.DriverManager;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;

public class SauceSteps {

    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());
    CartPage cartPage = new CartPage(DriverManager.getDriver());
    CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());

    // ---------- LOGIN ----------

    @Given("I open SauceDemo page")
    public void open_site() {
        DriverManager.getDriver().get("https://www.saucedemo.com/");
    }

    @When("I login with username {string} and password {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("I should see the products page")
    public void verify_products_page() {
        Assert.assertTrue(productsPage.isLoaded());
    }

    @Then("I should see login error {string}")
    public void verify_error(String error) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(error));
    }

    @When("I logout")
    public void logout() {
        productsPage.logout();
    }

    @Then("I should be redirected to login page")
    public void verify_login_page() {
        Assert.assertTrue(loginPage.isLoaded());
    }

    // ---------- SESSION ----------

    @Given("I am logged in")
    public void user_logged_in() {
        open_site();
        login("standard_user", "secret_sauce");
        verify_products_page();
    }

    @When("I refresh the page")
    public void refresh() {
        DriverManager.getDriver().navigate().refresh();
    }

    @Then("I should remain on products page")
    public void verify_session() {
        Assert.assertTrue(productsPage.isLoaded());
    }

    // ---------- PRODUCTS ----------

    @Then("I should see at least {int} products")
    public void validate_products(int count) {
        Assert.assertTrue(productsPage.getProductCount() >= count);
    }

    @When("I add product {string} to cart")
    public void add_product(String name) {
        productsPage.addProductToCart(name);
    }

    @Then("cart badge should show {string}")
    public void validate_cart(String expected) {
        Assert.assertEquals(expected, productsPage.getCartCount());
    }

    @When("I remove the product")
    public void remove_product() {
        productsPage.removeFirstProduct();
    }

    @When("I sort products by {string}")
    public void sort_products(String filter) {
        productsPage.sortBy(filter);
    }

    @Then("products should be sorted alphabetically")
    public void validate_sort_alpha() {
        Assert.assertTrue(productsPage.isSortedAlphabetically());
    }

    @Then("products should be sorted by price ascending")
    public void validate_sort_price() {
        Assert.assertTrue(productsPage.isSortedByPrice());
    }

    @When("I open product details for {string}")
    public void open_product(String name) {
        productsPage.openProduct(name);
    }

    @Then("product title should be {string}")
    public void validate_product_title(String expected) {
        Assert.assertEquals(expected, productsPage.getProductTitle());
    }

    // ---------- CART ----------

    @Given("I added a product")
    public void added_product() {
        user_logged_in();
        add_product("Sauce Labs Backpack");
    }

    @When("I add {int} products to cart")
    public void add_multiple(int qty) {
        productsPage.addMultipleProducts(qty);
    }

    @Given("I have items in cart")
    public void items_in_cart() {
        user_logged_in();
        add_multiple(2);
    }

    @Given("I have empty cart")
    public void empty_cart() {
        user_logged_in();
    }

    // ---------- CHECKOUT ----------

    @When("I complete checkout with valid data")
    public void checkout_success() {
        cartPage.goToCart();
        checkoutPage.completeCheckout("Diogo", "Rangel", "12345");
    }

    @Then("I should see order confirmation")
    public void order_confirm() {
        Assert.assertTrue(checkoutPage.isOrderComplete());
    }

    @When("I attempt checkout")
    public void attempt_checkout() {
        cartPage.goToCart();
        checkoutPage.startCheckout();
    }

    @Then("checkout should not proceed")
    public void checkout_blocked() {
        Assert.assertTrue(checkoutPage.isCheckoutBlocked());
    }

    @When("I checkout without first name")
    public void missing_firstname() {
        cartPage.goToCart();
        checkoutPage.checkout("", "Rangel", "12345");
    }

    @Then("I should see error {string}")
    public void checkout_error(String msg) {
        Assert.assertTrue(checkoutPage.getError().contains(msg));
    }

    @When("I cancel checkout")
    public void cancel_checkout() {
        checkoutPage.cancel();
    }

    @Then("I should return to products page")
    public void back_products() {
        Assert.assertTrue(productsPage.isLoaded());
    }

    @Then("total price should be correct")
    public void validate_total() {
        Assert.assertTrue(checkoutPage.isTotalCorrect());
    }
}