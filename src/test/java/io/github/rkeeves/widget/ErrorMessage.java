package io.github.rkeeves.widget;

import com.codeborne.selenide.SelenideElement;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Condition.*;

@RequiredArgsConstructor
public class ErrorMessage {

    private final SelenideElement divErrorMessageContainer;

    public void shouldShowMessage(final String expectedMessage) {
        divErrorMessageContainer.shouldBe(visible).$("h3").shouldHave(exactText(expectedMessage));
    }
}
