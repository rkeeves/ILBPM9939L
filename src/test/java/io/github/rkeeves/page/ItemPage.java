package io.github.rkeeves.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class ItemPage {

    private final SelenideElement itemContainer = $("#inventory_item_container");

    private final SelenideElement name = itemContainer.$("div.inventory_details_name");

    private final SelenideElement price = itemContainer.$("div.inventory_details_price");

    private final SelenideElement image = itemContainer.$("img.inventory_details_img");

    private final SelenideElement addToCartButton = itemContainer.$("button[id^='add-to-cart']");

    private final SelenideElement removeFromCartButton = itemContainer.$("button[id^='remove']");

    public ItemPage visitById(int id) {
        open(String.format("https://www.saucedemo.com/inventory-item.html?id=%d", id));
        return this.shouldBeVisible();
    }

    public ItemPage shouldBeVisible() {
        itemContainer.shouldBe(visible);
        name.shouldBe(visible);
        price.shouldBe(visible);
        image.shouldBe(visible);
        return this;
    }

    public ItemPage shouldHaveName(final String expectedName) {
        name.shouldHave(exactText(expectedName));
        return this;
    }

    public ItemPage shouldHavePrice(final String expectedPrice) {
        price.shouldHave(exactText(expectedPrice));
        return this;
    }

    public ItemPage shouldHaveImagePrefix(final String expectedSubstring) {
        image.shouldHave(attributeMatching("src",
                String.format(".*/%s.*", expectedSubstring)));
        return this;
    }

    public ItemPage hasId(int id) {
        itemContainer.shouldBe(visible);
        final String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        final String expectedUrl = String.format("https://www.saucedemo.com/inventory-item.html?id=%d", id);
        assertEquals(expectedUrl, currentUrl);
        return this;
    }

    public void shouldHaveAddItemAction() {
        addToCartButton.shouldBe(visible);
    }

    public void shouldNotHaveAddItemAction() {
        addToCartButton.shouldNotBe(visible);
    }

    public void pressAddToCart() {
        addToCartButton.shouldBe(visible).scrollIntoView(false).click();
    }

    public void shouldHaveRemoveFromItemAction() {
        removeFromCartButton.shouldBe(visible);
    }

    public void shouldNotHaveRemoveFromItemAction() {
        removeFromCartButton.shouldNotBe(visible);
    }

    public void pressRemoveFromCart() {
        removeFromCartButton.shouldBe(visible).scrollIntoView(false).click();
    }
}
