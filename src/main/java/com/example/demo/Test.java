package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream(new File("サンプル.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet1 = workbook.getSheet("Sheet1");

        // Data structure to hold the data from Sheet1
        Map<String, Map<String, Map<String, Integer>>> dataMap = new LinkedHashMap<>();
        // {123456={店舗在庫={3001=1, 3002=2, 3003=3, 3004=4, 3005=5}, 店舗積送={3001=0, 3002=0, 3003=0, 3004=0, 3005=0}, 配分数={3001=0, 3002=0, 3003=0, 3004=0, 3005=0}, 基準在庫={3001=5, 3002=5, 3003=5, 3004=5, 3005=5}},

        for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
            Row row = sheet1.getRow(i);
            String itemCode = row.getCell(0).getStringCellValue();
            String delivery = row.getCell(5).getStringCellValue();

            dataMap.putIfAbsent(itemCode, new LinkedHashMap<>());
            Map<String, Map<String, Integer>> itemMap = dataMap.get(itemCode);
            for (int j = 1; j <= 4; j++) {
                String header = sheet1.getRow(0).getCell(j).getStringCellValue();
                int value = (int) row.getCell(j).getNumericCellValue();

                itemMap.putIfAbsent(header, new LinkedHashMap<>());
                itemMap.get(header).put(delivery, value);
            }
        }

        // Create a new sheet for transformed data
        Sheet sheet2 = workbook.createSheet("TransformedData");

        // Set header for the new sheet
        int rowIndex = 0;
        for (Map.Entry<String, Map<String, Map<String, Integer>>> itemEntry : dataMap.entrySet()) {
            Row headerRow = sheet2.createRow(rowIndex++);
            Cell itemCell = headerRow.createCell(0);
            itemCell.setCellValue("アイテムコード");
            Cell deliveryCell = headerRow.createCell(1);
            deliveryCell.setCellValue("配送先");

            int columnIndex = 2;
            for (String delivery : itemEntry.getValue().get("店舗在庫").keySet()) {
                Cell deliveryHeaderCell = headerRow.createCell(columnIndex++);
                deliveryHeaderCell.setCellValue(delivery);
            }
            
            boolean isFirstIteration = true;

            for (Map.Entry<String, Map<String, Integer>> headerEntry : itemEntry.getValue().entrySet()) {
                Row dataRow = sheet2.createRow(rowIndex++);
                
                if (isFirstIteration) {
                    dataRow.createCell(0).setCellValue(itemEntry.getKey());
                    isFirstIteration = false;
                }
                
                Cell headerCell = dataRow.createCell(1);
                headerCell.setCellValue(headerEntry.getKey());

                columnIndex = 2;
                for (Integer value : headerEntry.getValue().values()) {
                    Cell dataCell = dataRow.createCell(columnIndex++);
                    dataCell.setCellValue(value);
                }
            }
            rowIndex++; // For spacing between different items
        }

        // Save the updated workbook
        FileOutputStream fos = new FileOutputStream(new File("Transformed_サンプル.xlsx"));
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();
    }

}
