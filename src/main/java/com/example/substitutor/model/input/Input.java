package com.example.substitutor.model.input;

import java.util.HashMap;
import java.util.List;


public interface Input {
    void setDataToSubstitute(List<HashMap<String, String>> dataToClone);

    List<HashMap<String, String>> getDataToSubstitute();
}
