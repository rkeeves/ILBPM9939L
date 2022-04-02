package io.github.rkeeves.page;

import lombok.Getter;

public enum InventorySortOrder {
    A_TO_Z("Name (A to Z)"),
    Z_TO_A("Name (Z to A)"),
    HI_TO_LO("Price (high to low)"),
    LO_TO_HI("Price (low to high)");

    @Getter
    private final String optionLabel;

    InventorySortOrder(String optionLabel) {
        this.optionLabel = optionLabel;
    }
}
