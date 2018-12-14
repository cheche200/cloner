package com.example.substitutor;

import com.example.substitutor.model.input.Input;
import com.example.substitutor.model.output.Output;
import com.example.substitutor.model.output.FileOutput;
import org.apache.commons.text.StringSubstitutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Substitutor {

    Output output = new FileOutput();

    public Output run(Input input) {
        List<String> substitutedData = substitute(input);
        output.setSubstitutedData(substitutedData);
        return output;
    }

    private List<String> substitute(Input input) {
        List<String> substitutedData = new ArrayList<>();
        String templateToSubstitute = input.getTemplateToSubstitute();

        for(Map<String,String> valuesMap : input.getDataToSubstitute()){
            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            String resolvedString = sub.replace(templateToSubstitute);
            substitutedData.add(resolvedString);
        }

        return substitutedData;
    }


}
