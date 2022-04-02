package io.github.rkeeves.step;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.rkeeves.example.InventoryItemExample;
import io.github.rkeeves.page.InventoryPage;
import io.github.rkeeves.page.InventorySortOrder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InventoryStepdefs {

    private final InventoryPage inventoryPage;

    @When("Amy sees the Inventory Page")
    public void amySeesTheInventoryList() {
        inventoryPage.shouldBeVisible();
    }

    @When("Amy visits the Inventory Page")
    public void amyVisitsTheInventoryPage() {
        inventoryPage.visit();
    }

    @When("Amy sees {int} items in the inventory")
    public void amySeesItemsInTheInventory(int expectedInventoryItemCount) {
        inventoryPage.shouldHaveItemCount(expectedInventoryItemCount);
    }

    @When("Amy sees the following items in the inventory in any order:")
    public void amySeesTheFollowingItemsInTheInventoryInAnyOrder(final DataTable dataTable) {
        inventoryPage.shouldHaveInventoryItemsInAnyOrder(makeExamplesFromDataTable(dataTable));
    }

    @When("Amy sees the following items in the inventory in this order:")
    public void amySeesTheFollowingItemsInTheInventoryInThisOrder(final DataTable dataTable) {
        inventoryPage.shouldHaveInventoryItemsInThisOrder(makeExamplesFromDataTable(dataTable));
    }

    private static List<InventoryItemExample> makeExamplesFromDataTable(final DataTable dataTable) {
        return  dataTable.asMaps(String.class, String.class).stream()
                .map(InventoryStepdefs::makeExampleFromMap)
                .collect(Collectors.toList());
    }

    private static InventoryItemExample makeExampleFromMap(final Map<String, String> valueMap) {
        return InventoryItemExample.builder()
                .name(valueMap.get("name"))
                .price(valueMap.get("price"))
                .build();
    }

    @When("Amy sorts the items by {inventorySortOrder}")
    public void amySortsTheItemsByA_TO_Z(final InventorySortOrder inventorySortOrder) {
        inventoryPage.selectSortOrder(inventorySortOrder);
    }

    @When("Amy navigates via the name of preview item {int}")
    public void amyNavigatesViaTheNameOfPreviewItem(final int id) {
        inventoryPage.navigateViaNameOfPreviewItemById(id);
    }

    @When("Amy navigates via the image of preview item {int}")
    public void amyNavigatesViaTheImageOfPreviewItemId(final int id) {
        inventoryPage.navigateViaImageOfPreviewItemById(id);
    }

    @Given("Amy sees that preview by id {int} has name {string}, price {word}, image prefix {word}")
    public void amySeesPreviewByIdHasAttributes(final int id,
                                                final String name,
                                                final String price,
                                                final String imgPrefix) {
        inventoryPage.previewByIdShouldHaveNameAndPriceAndImagePrefix(id, name, price, imgPrefix);
    }

    @Then("Amy sees that the shopping cart badge shows {int} items")
    public void amySeesThatTheShoppingCartBadgeShowsCountItems(final int count) {
        inventoryPage.shoppingCartBadgeHasCount(count);
    }

    @Then("Amy doesn't see the shopping cart badge counter")
    public void amyDoesnTSeeTheShoppingCartBadgeCounter() {
        inventoryPage.shoppingCartBadgeHasNoCount();
    }

    @When("Amy adds the item by id {int} to her cart on the Inventory Page")
    public void amyAddsTheItemByIdIdToHerCartOnTheInventoryPage(final int id) {
        inventoryPage.pressAddItemToCart(id);
    }

    @And("Amy sees the add to cart action for item by id {int} on the Inventory Page")
    public void amySeesTheAddToCartActionForItemByIdIdOnTheInventoryPage(final int id) {
        inventoryPage.shouldSeeAddToCart(id);
    }

    @Then("Amy doesn't see the add to cart action for item by id {int} on the Inventory Page")
    public void amyDoesnTSeeTheAddToCartActionForItemByIdIdOnTheInventoryPage(final int id) {
        inventoryPage.shouldNotSeeAddToCart(id);
    }

    @And("Amy sees the remove from cart action for item by id {int} on the Inventory Page")
    public void amySeesTheRemoveFromCartActionForItemByIdIdOnTheInventoryPage(final int id) {
        inventoryPage.shouldSeeRemoveFromCart(id);
    }

    @Then("Amy doesn't see the remove from cart action for item by id {int} on the Inventory Page")
    public void amyDoesnTSeeTheRemoveFromCartActionForItemByIdIdOnTheInventoryPage(final int id) {
        inventoryPage.shouldNotSeeRemoveFromCart(id);
    }

    @When("Amy removes item by id {int} from her cart on the Inventory Page")
    public void amyRemovesItemByIdIdFromHerCartOnTheInventoryPage(final int id) {
        inventoryPage.pressRemoveItemToCart(id);
    }
}
