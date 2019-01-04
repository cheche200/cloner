package com.example.substitutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FixtureData {

    public static List<HashMap<String, String>> getDataToSubstitute() {
        List<HashMap<String,String>> dataToSubstitute = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> firstDataRow = new HashMap<String, String>();
        firstDataRow.put("columnA", "valueA2");
        firstDataRow.put("columnB", "valueB2");
        firstDataRow.put("columnC", "valueC2");
        dataToSubstitute.add(firstDataRow);

        HashMap<String,String> secondDataRow = new HashMap<String, String>();
        secondDataRow.put("columnA", "valueA3");
        secondDataRow.put("columnB", "valueB3");
        secondDataRow.put("columnC", "valueC3");
        dataToSubstitute.add(secondDataRow);

        HashMap<String,String> thirdDataRow = new HashMap<String, String>();
        thirdDataRow.put("columnA", "valueA4");
        thirdDataRow.put("columnB", "valueB4");
        thirdDataRow.put("columnC", "valueC4");

        dataToSubstitute.add(thirdDataRow);
        return dataToSubstitute;
    }

}
