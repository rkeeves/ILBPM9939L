package io.github.rkeeves.converter;

import io.cucumber.java.ParameterType;
import io.github.rkeeves.page.InventorySortOrder;

public class InventorySortOrderConverter {

    @ParameterType(value = "A_TO_Z|Z_TO_A|HI_TO_LO|LO_TO_HI")
    public InventorySortOrder inventorySortOrder(String sortOrderTag) {
        return InventorySortOrder.valueOf(sortOrderTag);
    }
}
