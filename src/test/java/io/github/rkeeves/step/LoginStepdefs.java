package io.github.rkeeves.step;

import io.cucumber.java.en.When;
import io.github.rkeeves.page.LoginPage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginStepdefs {

    private final LoginPage loginPage;

    @When("Amy visits the Login Page")
    public void amyVisitsTheLoginPage() {
        loginPage.visit().shouldBeVisible();
    }

    @When("Amy enters username: {string}")
    public void amyEntersUsername(final String uname) {
        loginPage.fillUsername(uname);
    }

    @When("Amy enters password: {string}")
    public void amyEntersPassword(final String pass) {
        loginPage.fillPassword(pass);
    }

    @When("Amy tries to login")
    public void amyTriesToLogin() {
        loginPage.pressSubmit();
    }

    @When("Amy sees login error message: {string}")
    public void amySeesLoginErrorMessageError(final String err) {
        loginPage.shouldDisplayErrorMessage(err);
    }

    @When("Amy sees username displays error")
    public void amySeesUsernameIsHighlightedAsHavingError() {
        loginPage.shouldHaveUsernameErrorHighlight();
    }

    @When("Amy sees password displays error")
    public void amySeesPasswordIsHighlightedAsHavingError() {
        loginPage.shouldHavePasswordErrorHighlight();
    }
}
