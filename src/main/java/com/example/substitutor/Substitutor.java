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

    public Output substitute(Input input) {
        validateInputData(input);
        List<String> substitutedData = applySubstitution(input);
        output.setSubstitutedData(substitutedData);
        return output;
    }

    private void validateInputData(Input input) {
        if(input.getDataToSubstitute() == null){
            throw new SubstitutorException("There is no input data to substitute.");
        }
        if(input.getTemplateToSubstitute() == null){
            throw new SubstitutorException("There is no template to apply substitution");
        }
    }

    private List<String> applySubstitution(Input input) {
        List<String> substitutedData = new ArrayList<>();
        String templateToSubstitute = input.getTemplateToSubstitute();

        for(Map<String,String> valuesMap : input.getDataToSubstitute()){
            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            String resolvedString = sub.replace(templateToSubstitute);
            substitutedData.add(resolvedString);
        }
        return substitutedData;
    }

    public class SubstitutorException extends RuntimeException {
        public SubstitutorException(String message) {
            super(message);
        }
    }
}
