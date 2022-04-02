package io.github.rkeeves.step;

import io.cucumber.java.en.When;
import io.github.rkeeves.page.BurgerMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BurgerMenuStepdefs {

    private final BurgerMenu burgerMenu;

    @When("Amy logs out")
    public void amyLogsOut() {
        burgerMenu.pressLogout();
    }
}
