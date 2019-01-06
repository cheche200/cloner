package com.example.substitutor;

import com.example.substitutor.model.input.Input;
import com.example.substitutor.model.output.Output;
import com.example.substitutor.model.output.FileOutput;
import com.example.substitutor.model.template.Template;
import org.apache.commons.text.StringSubstitutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Substitutor {

    Output output = new FileOutput();

    public Output substitute(Input input, Template template) {
        validateData(input, template);
        List<String> substitutedData = applySubstitution(input, template);
        output.setSubstitutedData(substitutedData);
        return output;
    }

    private void validateData(Input input, Template template) {
        if(input.getDataToSubstitute() == null){
            throw new SubstitutorException("There is no input data to substitute.");
        }
        if(template.getTemplate() == null){
            throw new SubstitutorException("There is no template to apply substitution");
        }
    }

    private List<String> applySubstitution(Input input, Template template) {
        List<String> substitutedData = new ArrayList<>();
        String templateToSubstitute = template.getTemplate();

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
