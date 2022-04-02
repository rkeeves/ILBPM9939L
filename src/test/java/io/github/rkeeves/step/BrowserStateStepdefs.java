package io.github.rkeeves.step;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.LocalStorageConditions.itemWithValue;
import static com.codeborne.selenide.Selenide.*;
import static io.github.rkeeves.selenide.localstorage.condition.ItemValueArrayHavingValue.itemValueArrayHavingValue;
import static org.junit.jupiter.api.Assertions.*;

public class BrowserStateStepdefs {

    public static final String CART_CONTENTS = "cart-contents";
    public static final String SESSION_USERNAME = "session-username";

    @Given("Amy clears her history")
    public void amyClearsHerHistory() {
        open("https://www.saucedemo.com/robots.txt");
        clearBrowserCookies();
        localStorage().clear();
    }

    @And("Amy has no user cookie")
    public void amyDoesNotHaveAUserCookie() {
        final Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(SESSION_USERNAME);
        assertNull(cookie, "Amy had a user cookie despite the specification!");
    }

    @And("Amy has user cookie: {string}")
    public void amyHasUserCookieUname(final String expectedCookieValue) {
        Objects.requireNonNull(expectedCookieValue, "You passed a null as expected cookie value! " +
                "Are you sure about this is not some coding error?");
        final Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(SESSION_USERNAME);
        assertNotNull(cookie, "Amy had no user cookie despite the specification!");
        final String actualCookieValue = cookie.getValue();
        assertEquals(expectedCookieValue, actualCookieValue,
                String.format("Amy was expected to have '%s' as user cookie value, " +
                                "but it was '%s' instead.",
                        expectedCookieValue, actualCookieValue));
    }

    @And("Amy sets user cookie {string}")
    public void amySetUserCookie(final String newCookieValue) {
        Objects.requireNonNull(newCookieValue, "You passed a null as new cookie value! " +
                "Are you sure about this is not some coding error?");
        WebDriverRunner.getWebDriver().manage().deleteCookieNamed(SESSION_USERNAME);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(SESSION_USERNAME, newCookieValue));
    }

    @And("Amy has item {int} in storage")
    public void amyHasItemIdInStorage(final int id) {
        localStorage().shouldHave(itemValueArrayHavingValue(CART_CONTENTS, Integer.toString(id)));
    }

    @And("Amy doesn't have item {int} in storage")
    public void amyDoesnTHaveItemIdInStorage(final int id) {
        localStorage().shouldNotHave(itemValueArrayHavingValue(CART_CONTENTS, Integer.toString(id)));
    }

    @Given("Amy adds item {int} to the storage")
    public void amyAddsItemIdToTheStorage(final int id) {
        final String oldValue = localStorage().getItem(CART_CONTENTS);
        List<Integer> nums = parseArrayStringLiteral(oldValue);
        nums.add(id);
        final String newValue = toArrayStringLiteral(nums);
        localStorage().setItem(CART_CONTENTS, newValue);
        localStorage().shouldHave(itemWithValue(CART_CONTENTS, newValue));
    }

    private List<Integer> parseArrayStringLiteral(final String value) {
        List<Integer> result = new ArrayList<>();
        if (value == null) {
            return result;
        }
        boolean withinParentheses = value.startsWith("[") && value.endsWith("]");
        if (!withinParentheses) {
            throw new IllegalArgumentException(
                    String.format("The actual value was '%s', but an array literal was expected", value));
        }
        return Arrays.stream(value.substring(1, value.length() - 1)
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private String toArrayStringLiteral(final List<Integer> nums) {
        final String numsWithCommas = nums.stream()
                .map(x -> Integer.toString(x)).collect(Collectors.joining(","));
        return "[" + numsWithCommas + "]";
    }

    @And("Amy fills the storage with {int} items")
    public void amyFillsTheStorageWithCountItems(int count) {
        final String numsWithCommas = IntStream.range(0, count)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
        localStorage().setItem(CART_CONTENTS, "[" + numsWithCommas + "]");
    }
}
