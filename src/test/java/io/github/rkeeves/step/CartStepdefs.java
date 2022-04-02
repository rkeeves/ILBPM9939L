package io.github.rkeeves.step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.rkeeves.page.CartPage;
import io.github.rkeeves.widget.CartItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartStepdefs {

    private final CartPage cartPage;

    @And("Amy visits the Cart Page")
    public void amyVisitsTheCartPage() {
        cartPage.visit();
    }

    @When("Amy removes item by id {int} from her cart on the Cart Page")
    public void amyRemovesItemByIdIdFromHerCartOnTheCartPage(final int id) {
        final CartItem cartItem = cartPage.cartItemById(id);
        cartItem.shouldBeVisible().pressRemove();
    }

    @Then("Amy doesn't see item by id {int} on the Cart Page")
    public void amyDoesnTSeeItemByIdIdOnTheCartPage(final int id) {
        final CartItem cartItem = cartPage.cartItemById(id);
        cartItem.shouldNotBeVisible();
    }

    @Then("Amy sees that the item by {int} has name {string}, price {word} on the Cart Page")
    public void amySeesThatTheItemByIdHas(final int id,
                                          final String name,
                                          final String price) {
        final CartItem cartItem = cartPage.cartItemById(id);
        cartItem.shouldBeVisible()
                .hasName(name)
                .hasPrice(price);
    }

    @Then("Amy sees {int} item on the Cart Page")
    public void amySeesItemOnTheCartPage(int itemCount) {
        cartPage.hasItemCount(itemCount);
    }
}
