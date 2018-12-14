package com.example.substitutor.model.input;

import java.util.HashMap;
import java.util.List;


public interface Input {
    void setFormatToSubstitute(String formatToClone);

    void setDataToSubstitute(List<HashMap<String, String>> dataToClone);

    String substitute();
}
