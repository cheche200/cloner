package com.example.substitutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FixtureData {

    public static List<HashMap<String, String>> getDataToSubstitute() {
        List<HashMap<String,String>> dataToSubstitute = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> dataRow = new HashMap<String, String>();
        dataRow.put("column1", "value1");
        dataRow.put("column2", "value2");
        dataRow.put("column3", "value3");

        dataToSubstitute.add(dataRow);
        return dataToSubstitute;
    }

}
