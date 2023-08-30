package com.example.demo.study;

import java.lang.reflect.Field;

import lombok.Data;

@Data
public class PickingInfo {
    String itemCode; // アイテムコード
    String itemName; // 商品名
    String jgbCode; // 事業部
    String supplier; // 仕入先
    Integer storeStock; // 店舗在庫
    Integer stockInTransit; // 店舗積送
    Integer qty; // 配分数
    Integer safetyStock; // 配送先
    String storeCd; // 配送先

    /**
     * 指定されたフィールド名に対応する値を返却する。
     * 
     * @param fieldName フィールド名
     * @return フィールドの値。フィールドが存在しない場合はnull。
     */
    public String getFieldValueByName(String fieldName) {
        try {
            Field field = PickingInfo.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
