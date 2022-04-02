package io.github.rkeeves.widget;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;

public class Preview {

    private final SelenideElement divInventoryItem;

    private final SelenideElement addToCartButton;

    private final SelenideElement removeFromCartButton;

    public Preview(SelenideElement divInventoryItem) {
        this.divInventoryItem = divInventoryItem;
        this.addToCartButton = divInventoryItem.$("button[id^='add-to-cart']");
        this.removeFromCartButton = divInventoryItem.$("button[id^='remove']");
    }

    public Preview shouldBeVisible() {
        divInventoryItem.shouldBe(visible);
        return this;
    }

    public Preview shouldHaveName(final String expectedName) {
        divInventoryItem.$("div.inventory_item_name").shouldHave(exactText(expectedName));
        return this;
    }

    public Preview shouldHavePrice(final String expectedPrice) {
        divInventoryItem.$("div.inventory_item_price").shouldHave(exactText(expectedPrice));
        return this;
    }

    public Preview shouldHaveImagePrefix(final String expectedImgPrefix) {
        divInventoryItem.$("img.inventory_item_img").shouldHave(attributeMatching("src",
                String.format(".*/%s.*", expectedImgPrefix)));
        return this;
    }

    public void pressAddToCart() {
        addToCartButton.shouldBe(visible).scrollIntoView(false).click();
    }

    public void shouldSeeAddToCart() {
        addToCartButton.shouldBe(visible);
    }

    public void shouldNotSeeAddToCart() {
        addToCartButton.shouldNotBe(visible);
    }

    public void pressRemoveFromCart() {
        removeFromCartButton.shouldBe(visible).scrollIntoView(false).click();
    }

    public void shouldSeeRemoveFromCart() {
        removeFromCartButton.shouldBe(visible);
    }

    public void shouldNotSeeRemoveFromCart() {
        removeFromCartButton.shouldNotBe(visible);
    }
}
