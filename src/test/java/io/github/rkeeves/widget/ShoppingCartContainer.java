package io.github.rkeeves.widget;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartContainer {

    private final SelenideElement root = $("#shopping_cart_container");

    private final SelenideElement counter = root.$("span.shopping_cart_badge");

    public void hasCount(int count) {
        counter.shouldBe(visible).shouldHave(exactText(Integer.toString(count)));
    }

    public void hasNoCount() {
        counter.shouldNotBe(visible);
    }
}
