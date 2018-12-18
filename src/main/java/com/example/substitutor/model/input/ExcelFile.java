package com.example.substitutor.model.input;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelFile implements Input {

    public static final String DEFAULT_BLANK_CELL_VALUE = "";
    private String formatToSubstitute;

    private List<HashMap<String, String>> dataToSubstitute;

    private XSSFWorkbook excelFile;

    public ExcelFile(){
        //Intentionally Empty
    }

    public ExcelFile(InputStream fileName) throws IOException {
       this.excelFile = openExistingExcelFile(fileName);
       this.dataToSubstitute = extractDataFromExcelFile();
    }

    private List<HashMap<String,String>> extractDataFromExcelFile() {

        List<HashMap<String,String>> dataFromExcel = new ArrayList<>();
        HashMap<String,String> dataRow;

        Sheet sheet = excelFile.getSheetAt(0);
        Row header = sheet.getRow(0);

        for (Row row : sheet) {
            int cellIndex = 0;
            dataRow = new HashMap();
            for (Cell cell : row) {
                String cellValue = getCellValue(cell);
                dataRow.put(getCellValueFromRow(header, cellIndex),cellValue);
                cellIndex++;
            }

            dataFromExcel.add(dataRow);
        }

        dataFromExcel.remove(0); //Removing the headers
        return dataFromExcel;
    }

    private String getCellValueFromRow(Row header, int cellIndex) {
        return header.getCell(cellIndex).getStringCellValue();
    }

    private String getCellValue(Cell cell) {
        String cellValue = DEFAULT_BLANK_CELL_VALUE;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula().toString();
                break;
            case BLANK:
                System.out.println();
                break;
            default:
                System.out.println();
        }
        System.out.println(cellValue);
        return cellValue;
    }

    private XSSFWorkbook openExistingExcelFile(InputStream fileName) throws IOException {
            return new XSSFWorkbook(fileName);
    }

    @Override
    public void setTemplateToSubstitute(String templateToSubstitute) {
        this.formatToSubstitute = templateToSubstitute;
    }

    @Override
    public void setDataToSubstitute(List<HashMap<String, String>> dataToSubstitute ) {
       this.dataToSubstitute =  dataToSubstitute;
    }

    @Override
    public List<HashMap<String, String>> getDataToSubstitute() {
        return dataToSubstitute;
    }

    @Override
    public String getTemplateToSubstitute() {
        return formatToSubstitute;
    }

}
