package io.github.rkeeves.page;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Component
public class BurgerMenu {

    private final SelenideElement container = $("#menu_button_container");

    private final SelenideElement menuButton = $("#react-burger-menu-btn");

    private final SelenideElement menuWrapper = container.$("div.bm-menu-wrap");

    private final SelenideElement logoutLink = $("#logout_sidebar_link");

    public void pressLogout() {
        container.shouldBe(visible);
        menuWrapper.shouldNotBe(visible);
        menuButton.scrollIntoView(false).click();
        menuWrapper.shouldBe(visible);
        logoutLink.scrollIntoView(false).click();
    }
}
