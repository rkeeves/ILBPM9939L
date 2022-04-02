package io.github.rkeeves.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.example.InventoryItemExample;
import io.github.rkeeves.widget.InventoryContainer;
import io.github.rkeeves.widget.Preview;
import io.github.rkeeves.widget.ShoppingCartContainer;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class InventoryPage {

    private final SelenideElement title = $("#header_container > .header_secondary_container > span.title");

    private final SelenideElement activeSort = $("span.select_container > span.active_option");

    private final SelenideElement sortSelector = $("select.product_sort_container");

    private final InventoryContainer inventoryContainer = new InventoryContainer();

    private final ShoppingCartContainer shoppingCartContainer = new ShoppingCartContainer();

    public InventoryPage visit() {
        open("https://www.saucedemo.com/inventory.html");
        shouldBeVisible();
        return this;
    }

    public InventoryPage shouldBeVisible() {
        title.shouldBe(visible).shouldHave(Condition.exactText("Products"));
        return this;
    }

    public InventoryPage shouldHaveItemCount(int expectedInventoryItemCount) {
        inventoryContainer.shouldBeVisble().shouldHaveItemCount(expectedInventoryItemCount);
        return this;
    }

    public InventoryPage shouldHaveInventoryItemsInAnyOrder(final List<InventoryItemExample> inventoryItemExamples) {
        inventoryContainer.shouldHaveInventoryItemsInAnyOrder(inventoryItemExamples);
        return this;
    }

    public InventoryPage shouldHaveInventoryItemsInThisOrder(final List<InventoryItemExample> inventoryItemExamples) {
        inventoryContainer.shouldHaveInventoryItemsInThisOrder(inventoryItemExamples);
        return this;
    }

    public InventoryPage selectSortOrder(final InventorySortOrder inventorySortOrder) {
        sortSelector.selectOption(inventorySortOrder.getOptionLabel());
        activeSort.shouldHave(exactText(inventorySortOrder.getOptionLabel()));
        return this;
    }

    public void navigateViaNameOfPreviewItemById(int id) {
        inventoryContainer.pressNameByPreviewId(id);
    }

    public void navigateViaImageOfPreviewItemById(int id) {
        inventoryContainer.pressImageByPreviewId(id);
    }

    public InventoryPage previewByIdShouldHaveNameAndPriceAndImagePrefix(final int id,
                                                                         final String name,
                                                                         final String price,
                                                                         final String imgPrefix) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.shouldBeVisible()
                .shouldHaveName(name)
                .shouldHavePrice(price)
                .shouldHaveImagePrefix(imgPrefix);
        return this;
    }

    public void shoppingCartBadgeHasCount(int count) {
        shoppingCartContainer.hasCount(count);
    }

    public void shoppingCartBadgeHasNoCount() {
        shoppingCartContainer.hasNoCount();
    }

    public void pressAddItemToCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.pressAddToCart();
    }

    public void shouldSeeAddToCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.shouldSeeAddToCart();
    }

    public void shouldNotSeeAddToCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.shouldNotSeeAddToCart();
    }

    public void shouldSeeRemoveFromCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.shouldSeeRemoveFromCart();
    }

    public void shouldNotSeeRemoveFromCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.shouldNotSeeRemoveFromCart();
    }

    public void pressRemoveItemToCart(int id) {
        final Preview preview = inventoryContainer.getPreviewById(id);
        preview.pressRemoveFromCart();
    }
}
