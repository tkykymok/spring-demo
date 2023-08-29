package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTransformer {

    public static void main(String[] args) throws Exception {
        // Excelファイルを読み込み
        File sampleDataFile = new File("サンプルデータ.xlsx");
        Workbook sampleDataWb = new XSSFWorkbook(sampleDataFile);
        Sheet sourceSheet = sampleDataWb.getSheet("Sheet1");
        sampleDataWb.close();

        // シートからPickingInfoのリストを抽出
        List<PickingInfo> pickingInfoList = extractDataFromSheet(sourceSheet);
        // PickingInfoのリストを変換してデータマップを作成
        Map<PickingInfoKey, Map<DeliveryField, Map<String, Integer>>> dataMap = transformData(pickingInfoList);

        System.out.println(dataMap);

        // 一時ファイルのパス
        Path tempPath = Paths.get("temp.xlsx");
        // ファイルをコピー（既に存在する場合は上書き）
        Files.copy(Paths.get("サンプル.xlsx"), tempPath, StandardCopyOption.REPLACE_EXISTING);

        // テンプレートシートを取得
        File file = new File("temp.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet templateSheet = workbook.getSheet("template");

        // 出力用のシートを作成
        Sheet outputSheet = createSheet(workbook, "ピッキングシート");

        // 出力シートにデータを埋め込む
        fillOutputSheet(dataMap, templateSheet, outputSheet);
        // ワークブックを新しいExcelファイルとして保存
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedTime = time.format(formatter);
        saveWorkbookToFile(workbook, formattedTime + "_ピッキングシート_サンプル.xlsx");

        // 一時的に作成したファイルを削除
        Files.delete(tempPath);
    }

    /**
     * 出力シートにデータを埋め込むメソッド
     *
     * @param dataMap
     * @param templateSheet
     * @param outputSheet
     */
    private static void fillOutputSheet(Map<PickingInfoKey, Map<DeliveryField, Map<String, Integer>>> dataMap,
                                        Sheet templateSheet, Sheet outputSheet) {

        int outputRowNum = 1;
        for (Map.Entry<PickingInfoKey, Map<DeliveryField, Map<String, Integer>>> entry : dataMap.entrySet()) {
            // キーを取得
            PickingInfoKey info = entry.getKey();
            // 項目、店舗毎の値、
            Map<DeliveryField, Map<String, Integer>> perItemMap = entry.getValue();
            // 店舗コード一覧を取得
            Collection<Map<String, Integer>> collection = perItemMap.values();
            Map<String, Integer> firstMap = collection.iterator().next();
            List<String> storeCdList = new ArrayList<>(firstMap.keySet());

            // 初回ループ時はヘッダー部分を設定、それ以外はヘッダー部分の設定をスキップ
            for (int j = outputRowNum == 1 ? 1 : 3; j <= templateSheet.getLastRowNum(); j++) {
                Row templateRow = templateSheet.getRow(j);
                Row newRow = outputSheet.createRow(outputRowNum++);

                for (int k = 1; k < templateRow.getLastCellNum(); k++) {
                    Cell templateCell = templateRow.getCell(k);
                    Cell newCell = newRow.createCell(k);
                    newCell.setCellStyle(templateCell.getCellStyle());
                    newCell.setCellValue(templateCell.getStringCellValue());

                    DeliveryField fieldName = DeliveryField.fromString(templateCell.getStringCellValue());
                    if (fieldName == null) {
                        String fieldValue = info.getFieldValueByName(templateCell.getStringCellValue());
                        if (fieldValue != null) {
                            newCell.setCellValue(fieldValue);
                        }
                    } else {
                        switch (fieldName) {
                            case 店舗:
                                fillCells(newRow, k, storeCdList, templateCell, storeCd -> storeCd);
                                break;
                            case 店舗在庫:
                            case 店舗積送:
                            case 配分数:
                            case 基準在庫:
                                fillCells(newRow, k, storeCdList, templateCell, storeCd -> perItemMap.get(fieldName).get(storeCd));
                                break;
                            default:
                                break;
                        }
                    }

                    outputSheet.setColumnWidth(k, templateSheet.getColumnWidth(k));
                }
            }
        }
    }

    /**
     * 値を持つセルにデータを埋め込むメソッド
     *
     * @param newRow
     * @param colIndex
     * @param dataList
     * @param templateCell
     * @param valueProvider
     * @param <T>
     */
    private static <T> void fillCells(Row newRow, int colIndex, List<T> dataList, Cell templateCell,
                                      Function<T, Object> valueProvider) {
        // カスタムのスタイルを作成
        CellStyle rightBorderStyle = newRow.getSheet().getWorkbook().createCellStyle();
        rightBorderStyle.setBorderRight(BorderStyle.MEDIUM); // 右太枠線を追加

        int count = 1;
        for (T data : dataList) {
            Cell newCell = newRow.createCell(colIndex + count);
            // セルのスタイルと値を設定
            CellStyle currentStyle = copyCellStyle(newRow.getSheet().getWorkbook(), templateCell.getCellStyle());
            newCell.setCellStyle(currentStyle);
            Object value = valueProvider.apply(data);
            if (value instanceof String) {
                newCell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                newCell.setCellValue((Integer) value);
            }

            // 最後のセルだけ右太枠線のスタイルを適用
            if (count == dataList.size()) {
                CellStyle combinedStyle = copyCellStyle(newRow.getSheet().getWorkbook(), currentStyle);
                combinedStyle.setBorderRight(rightBorderStyle.getBorderRight());
                newCell.setCellStyle(combinedStyle);
            }
            count++;
        }
    }

    /**
     * 新しいシートを作成する
     *
     * @param workbook
     * @param sheetName
     * @return
     */
    private static Sheet createSheet(Workbook workbook, String sheetName) {
        int sheetIndex = workbook.getSheetIndex(sheetName);
        if (sheetIndex > 0) {
            workbook.removeSheetAt(sheetIndex);
        }
        return workbook.createSheet(sheetName);
    }

    /**
     * 指定のシートを削除する
     *
     * @param workbook
     * @param sheetName
     * @return
     */
    private static void removeSheet(Workbook workbook, String sheetName) {
        int sheetIndex = workbook.getSheetIndex(sheetName);
        if (sheetIndex >= 0) {
            workbook.removeSheetAt(sheetIndex);
        }
    }

    /**
     * ワークブックをファイルに保存するメソッド
     *
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void saveWorkbookToFile(Workbook workbook, String fileName) throws Exception {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            removeSheet(workbook, "template");
            workbook.write(outputStream);
        }
        workbook.close();
    }

    // 既存のCellStyleをコピーするヘルパーメソッド
    public static CellStyle copyCellStyle(Workbook workbook, CellStyle oldStyle) {
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(oldStyle);
        return newStyle;
    }

    /**
     * シートからPickingInfoのリストを抽出するメソッド
     *
     * @param sheet
     * @return
     */
    static private List<PickingInfo> extractDataFromSheet(Sheet sheet) {
        List<PickingInfo> pickingInfoList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            PickingInfo info = new PickingInfo();

            info.setItemCode(row.getCell(0).getStringCellValue());
            info.setItemName(row.getCell(1).getStringCellValue());
            info.setJgbCode(row.getCell(2).getStringCellValue());
            info.setSupplier(row.getCell(3).getStringCellValue());
            info.setStoreStock((int) row.getCell(4).getNumericCellValue());
            info.setStockInTransit((int) row.getCell(5).getNumericCellValue());
            info.setQty((int) row.getCell(6).getNumericCellValue());
            info.setSafetyStock((int) row.getCell(7).getNumericCellValue());
            info.setStoreCd(row.getCell(8).getStringCellValue());

            pickingInfoList.add(info);
        }

        return pickingInfoList;
    }

    /**
     * PickingInfoのリストを変換してデータマップを作成するメソッド
     * Map<PickingInfoKey, Map<項目名, Map<店舗コード, V>>>
     *
     * @param pickingInfoList
     * @return
     */
    private static Map<PickingInfoKey, Map<DeliveryField, Map<String, Integer>>> transformData(
            List<PickingInfo> pickingInfoList) {
        Map<PickingInfoKey, Map<DeliveryField, Map<String, Integer>>> dataMap = new LinkedHashMap<>();

        for (PickingInfo pickingInfo : pickingInfoList) {
            PickingInfoKey infoKey = new PickingInfoKey(
                    pickingInfo.getItemCode(),
                    pickingInfo.getItemName(),
                    pickingInfo.getJgbCode(),
                    pickingInfo.getSupplier());

            dataMap.putIfAbsent(infoKey, new LinkedHashMap<>());

            Map<DeliveryField, Map<String, Integer>> itemMap = dataMap.get(infoKey);
            itemMap.putIfAbsent(DeliveryField.店舗在庫, new LinkedHashMap<>());
            itemMap.putIfAbsent(DeliveryField.店舗積送, new LinkedHashMap<>());
            itemMap.putIfAbsent(DeliveryField.配分数, new LinkedHashMap<>());
            itemMap.putIfAbsent(DeliveryField.基準在庫, new LinkedHashMap<>());

            itemMap.get(DeliveryField.店舗在庫).put(pickingInfo.getStoreCd(), pickingInfo.getStoreStock());
            itemMap.get(DeliveryField.店舗積送).put(pickingInfo.getStoreCd(), pickingInfo.getStockInTransit());
            itemMap.get(DeliveryField.配分数).put(pickingInfo.getStoreCd(), pickingInfo.getQty());
            itemMap.get(DeliveryField.基準在庫).put(pickingInfo.getStoreCd(), pickingInfo.getSafetyStock());
        }

        return dataMap;
    }
}