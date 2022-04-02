package io.github.rkeeves.page;

import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.widget.CartItem;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class CartPage {

    private final SelenideElement container = $("#cart_contents_container");

    private final SelenideElement cartList = container.$("div.cart_list");

    public CartPage visit() {
        open("https://www.saucedemo.com/cart.html");
        return shouldBeVisible();
    }

    private CartPage shouldBeVisible() {
        container.shouldBe(visible);
        return this;
    }

    public CartItem cartItemById(final int id) {
        return new CartItem(cartList.$x(".//div[div[a[@id='item_" + id + "_title_link']]]"));
    }

    public void hasItemCount(int itemCount) {
        cartList.$$("div.cart_item").shouldHave(size(itemCount));
    }
}
