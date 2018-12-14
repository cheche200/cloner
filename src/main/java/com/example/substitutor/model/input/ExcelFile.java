package com.example.substitutor.model.input;

import java.util.HashMap;
import java.util.List;

public class ExcelFile implements Input {

    private String formatToSubstitute;

    private List<HashMap<String, String>> dataToSubstitute;

    @Override
    public void setFormatToSubstitute(String formatToSubstitute) {
        this.formatToSubstitute = formatToSubstitute;
    }

    @Override
    public void setDataToSubstitute(List<HashMap<String, String>> dataToSubstitute ) {
       this.dataToSubstitute =  dataToSubstitute;
    }

    @Override
    public String substitute() {
        return "INSERT INTO table_name (column1, column2, column3) VALUES " +
                "(value1, value2, value3);";
    }
}
