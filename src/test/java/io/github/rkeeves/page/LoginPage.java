package io.github.rkeeves.page;

import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.widget.ErrorMessage;
import io.github.rkeeves.widget.TextFieldWithError;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class LoginPage {

    private final SelenideElement wrapper = $("div.login_wrapper");

    private final TextFieldWithError username = new TextFieldWithError($(byId("user-name")));

    private final TextFieldWithError password = new TextFieldWithError($(byId("password")));

    private final ErrorMessage errorMessage = new ErrorMessage($("div.error-message-container"));

    private final SelenideElement submitButton = $("#login-button");

    public LoginPage visit() {
        open("https://www.saucedemo.com/");
        shouldBeVisible();
        return this;
    }

    public LoginPage shouldBeVisible() {
        wrapper.shouldBe(visible);
        return this;
    }

    public LoginPage fillUsername(String usernameValue) {
        username.type(usernameValue);
        return this;
    }

    public LoginPage shouldHaveUsernameErrorHighlight() {
        username.shouldShowError();
        return this;
    }

    public LoginPage fillPassword(String passwordValue) {
        password.type(passwordValue);
        return this;
    }

    public LoginPage shouldHavePasswordErrorHighlight() {
        password.shouldShowError();
        return this;
    }

    public void pressSubmit() {
        submitButton.scrollIntoView(false).click();
    }

    public LoginPage shouldDisplayErrorMessage(String expectedErrorMessage) {
        errorMessage.shouldShowMessage(expectedErrorMessage);
        return this;
    }
}
