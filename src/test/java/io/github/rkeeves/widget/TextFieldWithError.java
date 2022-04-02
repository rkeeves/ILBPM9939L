package io.github.rkeeves.widget;

import com.codeborne.selenide.SelenideElement;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Condition.*;

@RequiredArgsConstructor
public class TextFieldWithError {

    private final SelenideElement input;

    public void type(final String val) {
        input.val(val);
    }

    public void shouldShowError() {
        input.parent().$x("./*[name()='svg']").shouldBe(visible);
    }
}
