package com.example.demo.study;

public enum DeliveryField {
    店舗("店舗"),
    店舗在庫("店舗在庫"),
    店舗積送("店舗積送"),
    配分数("配分数"),
    基準在庫("基準在庫")
    ;

    private final String fieldName;

    DeliveryField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static DeliveryField fromString(String s) {
        for (DeliveryField fn : DeliveryField.values()) {
            if (fn.getFieldName().equals(s)) {
                return fn;
            }
        }
        return null;
    }

}
