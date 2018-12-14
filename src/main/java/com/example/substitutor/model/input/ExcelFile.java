package com.example.substitutor.model.input;

import java.util.HashMap;
import java.util.List;

public class ExcelFile implements Input {

    private String formatToSubstitute;

    private List<HashMap<String, String>> dataToSubstitute;

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
