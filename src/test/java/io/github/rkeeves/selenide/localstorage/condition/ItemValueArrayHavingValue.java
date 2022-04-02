package io.github.rkeeves.selenide.localstorage.condition;

import com.codeborne.selenide.LocalStorage;
import com.codeborne.selenide.ObjectCondition;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@ParametersAreNonnullByDefault
public class ItemValueArrayHavingValue implements ObjectCondition<LocalStorage> {

    private final String item;
    private final String value;

    public ItemValueArrayHavingValue(String item, String value) {
        this.item = item;
        this.value = value;
    }

    public static ItemValueArrayHavingValue itemValueArrayHavingValue(String item, String value) {
        return new ItemValueArrayHavingValue(item, value);
    }

    @Nonnull
    @CheckReturnValue
    public String description() {
        return String.format("should have array item '%s' with value '%s'", this.item, this.value);
    }

    @Nonnull
    @CheckReturnValue
    public String negativeDescription() {
        return String.format("should not have array item '%s' with value '%s'", this.item, this.value);
    }

    @Nullable
    @CheckReturnValue
    public String actualValue(LocalStorage localStorage) {
        return localStorage.getItems().toString();
    }

    @Nullable
    @CheckReturnValue
    public String expectedValue() {
        return String.format("%s=%s", this.item, this.value);
    }

    @CheckReturnValue
    public boolean test(LocalStorage localStorage) {
        final String actual = localStorage.getItem(this.item);
        if (actual == null) {
            return false;
        }
        boolean withinParentheses = actual.startsWith("[") && actual.endsWith("]");
        if (!withinParentheses) {
            return false;
        }
        return Arrays.asList(actual.substring(1, actual.length() - 1)
                .split(",")).contains(this.value);
    }

    @Nonnull
    @CheckReturnValue
    public String describe(LocalStorage localStorage) {
        return "localStorage";
    }
}
