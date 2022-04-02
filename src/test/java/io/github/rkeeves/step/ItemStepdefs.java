package io.github.rkeeves.step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.rkeeves.page.ItemPage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemStepdefs {

    private final ItemPage itemPage;

    @When("Amy sees the Item Page by id {int}")
    public void amySeesTheItemPageById(final int id) {
        itemPage.shouldBeVisible().hasId(id);
    }

    @When("Amy visits the Item Page by id {int}")
    public void amyVisitsTheItemPageById(final int id) {
        itemPage.visitById(id);
    }

    @When("Amy sees that the item has name {string}, price {word}, image prefix {word}")
    public void amySeesThatTheItemHas(final String name, final String price, final String imagePrefix) {
        itemPage.shouldBeVisible()
                .shouldHaveName(name)
                .shouldHavePrice(price)
                .shouldHaveImagePrefix(imagePrefix);
    }

    @When("Amy adds the item to her cart on the Item Page")
    public void amyAddsTheItemToHerCartOnTheItemPage() {
        itemPage.shouldBeVisible()
                .pressAddToCart();
    }

    @When("Amy removes the item from her cart on the Item Page")
    public void amyRemovesTheItemFromHerCartOnTheItemPage() {
        itemPage.shouldBeVisible()
                .pressRemoveFromCart();
    }

    @Then("Amy doesn't have the remove item action on the Item Page")
    public void amyDoesnTHaveTheRemoveItemActionOnTheItemPage() {
        itemPage.shouldNotHaveRemoveFromItemAction();
    }

    @Then("Amy doesn't have the add item action on the Item Page")
    public void amyDoesnTHaveTheAddItemActionOnTheItemPage() {
        itemPage.shouldNotHaveAddItemAction();
    }

    @And("Amy has the remove item action on the Item Page")
    public void amyHasTheRemoveItemActionOnTheItemPage() {
        itemPage.shouldHaveRemoveFromItemAction();

    }

    @And("Amy has the add item action on the Item Page")
    public void amyHasTheAddItemActionOnTheItemPage() {
        itemPage.shouldHaveAddItemAction();
    }
}
