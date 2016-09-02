package by.htp4.bitreight.library.service;

public enum BookSortType {
    PRICE_ASC("price", true),
    PRICE_DESC("price", false),
    YEAR("year", true),
    NONE(null, false);

    private String fieldName;
    private boolean isAscendingOrder;

    BookSortType(String fieldName, boolean isAscendingOrder) {
        this.fieldName = fieldName;
        this.isAscendingOrder = isAscendingOrder;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean isAscendingOrder() {
        return isAscendingOrder;
    }
}