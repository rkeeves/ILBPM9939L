package io.github.rkeeves.widget;

import com.codeborne.selenide.SelenideElement;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

@RequiredArgsConstructor
public class CartItem {

    private final SelenideElement divCartItem;

    private final SelenideElement quantity;

    private final SelenideElement name;

    private final SelenideElement price;

    private final SelenideElement remove;

    public CartItem(SelenideElement divCartItem) {
        this.divCartItem = divCartItem;
        this.quantity = divCartItem.$("div.cart_quantity");
        this.name = divCartItem.$("div.inventory_item_name");
        this.price = divCartItem.$("div.inventory_item_price");
        this.remove = divCartItem.$("button[id^='remove']");
    }

    public CartItem shouldBeVisible() {
        divCartItem.shouldBe(visible);
        return this;
    }

    public CartItem shouldNotBeVisible() {
        divCartItem.shouldNotBe(visible);
        return this;
    }

    public void pressRemove() {
        remove.scrollIntoView(false).click();
    }

    public CartItem hasName(String expectedName) {
        name.shouldHave(exactText(expectedName));
        return this;
    }

    public CartItem hasPrice(String expectedPrice) {
        price.shouldHave(exactText(expectedPrice));
        return this;
    }
}
