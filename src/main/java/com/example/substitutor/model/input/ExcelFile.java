package com.example.substitutor.model.input;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.BLANK;

public class ExcelFile implements Input {

    public static final String DEFAULT_BLANK_CELL_VALUE = "";
    public static final int INDEX_OF_EXCEL_SHEET = 0;
    public static final int INDEX_OF_HEADER_ROW = 0;
    public static final int CELL_INDEX_FIRST_COLUMN = 0;
    public static final int INDEX_HEADER_DATA = 0;

    private List<HashMap<String, String>> dataToSubstitute;

    private XSSFWorkbook excelFile;

    public ExcelFile(){
        //Intentionally Empty
    }

    public ExcelFile(InputStream fileName) throws IOException {
       this.excelFile = openExistingExcelFile(fileName);
       this.dataToSubstitute = extractDataFromExcelFile();
    }

    private XSSFWorkbook openExistingExcelFile(InputStream fileName) throws IOException {
        return new XSSFWorkbook(fileName);
    }

    private List<HashMap<String,String>> extractDataFromExcelFile() {
        Sheet sheet = excelFile.getSheetAt(INDEX_OF_EXCEL_SHEET);
        Row header = sheet.getRow(INDEX_OF_HEADER_ROW);

        List<HashMap<String,String>> dataFromExcel = new ArrayList<>();
        HashMap<String,String> dataRow;

        for (Row row : sheet) {
            int cellIndex = CELL_INDEX_FIRST_COLUMN;
            if(!isRowEmpty(row)) {
                dataRow = new HashMap();
                for (Cell cell : row) {
                    String cellValue = getCellValue(cell);
                    dataRow.put(getCellValueFromRow(header, cellIndex), cellValue);
                    cellIndex++;
                }
                dataFromExcel.add(dataRow);
            }
        }

        dataFromExcel.remove(INDEX_HEADER_DATA); //Removing the headers
        return dataFromExcel;
    }

    private static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != BLANK)
                return false;
        }
        return true;
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

    private String getCellValueFromRow(Row row, int cellIndex) {
        return row.getCell(cellIndex).getStringCellValue();
    }

    @Override
    public void setDataToSubstitute(List<HashMap<String, String>> dataToSubstitute ) {
       this.dataToSubstitute =  dataToSubstitute;
    }

    @Override
    public List<HashMap<String, String>> getDataToSubstitute() {
        return dataToSubstitute;
    }

}
