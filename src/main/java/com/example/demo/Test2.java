package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("サンプル.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet1 = workbook.getSheet("Sheet1");

        RecursiveExcelWriter writer = new RecursiveExcelWriter();
        List<Item> items = writer.extractDataFromSheet(sheet1);

        Sheet sheet2 = workbook.createSheet("TransformedData");
        writer.writeDataToExcel(items, sheet2);

        // Save the updated workbook
        FileOutputStream fos = new FileOutputStream(new File("Transformed_サンプル.xlsx"));
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();
    }

    static  class Item {
        private String itemCode;
        private List<Header> headers;

        public Item() {
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public List<Header> getHeaders() {
            return headers;
        }

        public void setHeaders(List<Header> headers) {
            this.headers = headers;
        }
    }

    static  class Header {
        private String headerName;
        private Map<String, Delivery> deliveries;

        public Header() {
        }

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public Map<String, Delivery> getDeliveries() {
            return deliveries;
        }

        public void setDeliveries(Map<String, Delivery> deliveries) {
            this.deliveries = deliveries;
        }

    }

    static  class Delivery {
        private String deliveryName;
        private Integer value;

        public Delivery() {
        }

        public String getDeliveryName() {
            return deliveryName;
        }

        public void setDeliveryName(String deliveryName) {
            this.deliveryName = deliveryName;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

    }

    public static class RecursiveExcelWriter {

        public List<Item> extractDataFromSheet(Sheet sheet) {
            Map<String, Item> itemMap = new LinkedHashMap<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String itemCode = row.getCell(0).getStringCellValue();
                String delivery = row.getCell(5).getStringCellValue();

                Item item = itemMap.computeIfAbsent(itemCode, k -> new Item());
                item.setItemCode(itemCode);

                for (int j = 1; j <= 4; j++) {
                    String headerName = sheet.getRow(0).getCell(j).getStringCellValue();
                    int value = (int) row.getCell(j).getNumericCellValue();

                    Header header = findOrCreateHeader(item, headerName);
                    Delivery deliveryObj = new Delivery();
                    deliveryObj.setDeliveryName(delivery);
                    deliveryObj.setValue(value);
                    header.getDeliveries().put(delivery, deliveryObj);
                }
            }

            return new ArrayList<>(itemMap.values());
        }

        private Header findOrCreateHeader(Item item, String headerName) {
            if (item.getHeaders() == null) {
                item.setHeaders(new ArrayList<>());
            }

            for (Header header : item.getHeaders()) {
                if (header.getHeaderName().equals(headerName)) {
                    return header;
                }
            }

            Header newHeader = new Header();
            newHeader.setHeaderName(headerName);
            newHeader.setDeliveries(new LinkedHashMap<>());
            item.getHeaders().add(newHeader);
            return newHeader;
        }

        public void writeDataToExcel(List<Item> items, Sheet sheet) {
            int rowIndex = 0;
            for (Item item : items) {
                rowIndex = writeItem(item, sheet, rowIndex);
            }
        }

        private int writeItem(Item item, Sheet sheet, int rowIndex) {
            Row itemRow = sheet.createRow(rowIndex++);
            itemRow.createCell(0).setCellValue(item.getItemCode());

            for (Header header : item.getHeaders()) {
                rowIndex = writeHeader(header, sheet, rowIndex);
            }

            return rowIndex;
        }

        private int writeHeader(Header header, Sheet sheet, int rowIndex) {
            Row headerRow = sheet.createRow(rowIndex++);
            headerRow.createCell(1).setCellValue(header.getHeaderName());

            for (Delivery delivery : header.getDeliveries().values()) {
                rowIndex = writeDelivery(delivery, sheet, rowIndex);
            }

            return rowIndex;
        }

        private int writeDelivery(Delivery delivery, Sheet sheet, int rowIndex) {
            Row deliveryRow = sheet.createRow(rowIndex++);
            deliveryRow.createCell(2).setCellValue(delivery.getDeliveryName());
            deliveryRow.createCell(3).setCellValue(delivery.getValue());

            return rowIndex;
        }

    }
}
