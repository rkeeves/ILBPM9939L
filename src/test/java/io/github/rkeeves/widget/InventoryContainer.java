package io.github.rkeeves.widget;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.example.InventoryItemExample;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InventoryContainer {

    private final SelenideElement container = $("#inventory_container.inventory_container");
    
    public InventoryContainer shouldBeVisble() {
        container.shouldBe(visible);
        return this;
    }

    public InventoryContainer shouldHaveItemCount(int expectedInventoryItemCount) {
        listItemElements()
                .shouldHave(size(expectedInventoryItemCount));
        return this;
    }

    public InventoryContainer shouldHaveInventoryItemsInAnyOrder(
            final List<InventoryItemExample> expectedItems) {
        shouldHaveItemCount(expectedItems.size());
        final List<String> expectedNames = expectedItems.stream()
                .map(InventoryItemExample::getName)
                .collect(Collectors.toList());
        final List<String> expectedPrices = expectedItems.stream()
                .map(InventoryItemExample::getPrice)
                .collect(Collectors.toList());
        listItemNameElements()
                .shouldHave(exactTextsCaseSensitiveInAnyOrder(expectedNames));
        listItemPriceElements()
                .shouldHave(exactTextsCaseSensitiveInAnyOrder(expectedPrices));
        return this;
    }

    public InventoryContainer shouldHaveInventoryItemsInThisOrder(
            final List<InventoryItemExample> expectedItems) {
        shouldHaveItemCount(expectedItems.size());
        final List<String> expectedNames = expectedItems.stream()
                .map(InventoryItemExample::getName)
                .collect(Collectors.toList());
        final List<String> expectedPrices = expectedItems.stream()
                .map(InventoryItemExample::getPrice)
                .collect(Collectors.toList());
        listItemNameElements()
                .shouldHave(exactTexts(expectedNames));
        listItemPriceElements()
                .shouldHave(exactTexts(expectedPrices));
        return this;
    }

    private ElementsCollection listItemElements() {
        return container.$$("div.inventory_list > div.inventory_item");
    }

    private ElementsCollection listItemNameElements() {
        return container.$$("div.inventory_list > div.inventory_item div.inventory_item_name");
    }

    private ElementsCollection listItemPriceElements() {
        return container.$$("div.inventory_list > div.inventory_item div.inventory_item_price");
    }

    public void pressNameByPreviewId(int id) {
        container.$(String.format("#item_%d_title_link", id))
                .shouldBe(visible).scrollIntoView(false).click();
    }

    public void pressImageByPreviewId(int id) {
        container.$(String.format("#item_%d_img_link", id))
                .shouldBe(visible).scrollIntoView(false).click();
    }

    public Preview getPreviewById(int id) {
        final SelenideElement divInventoryItem = container.$x(".//div[div[a[@id='item_" + id + "_img_link']]]");
        return new Preview(divInventoryItem);
    }
}
