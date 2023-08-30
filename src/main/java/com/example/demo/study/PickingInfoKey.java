package com.example.demo.study;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PickingInfoKey {
    String itemCode; // アイテムコード
    String itemName; // 商品名
    String jgbCode; // 事業部
    String supplier; // 仕入先;

    /**
     * 指定されたフィールド名に対応する値を返却する。
     * 
     * @param fieldName フィールド名
     * @return フィールドの値。フィールドが存在しない場合はnull。
     */
    public String getFieldValueByName(String fieldName) {
        try {
            Field field = PickingInfoKey.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
