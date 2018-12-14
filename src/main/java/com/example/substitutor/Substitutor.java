package com.example.substitutor;

import com.example.substitutor.model.input.Input;
import com.example.substitutor.model.output.Output;
import com.example.substitutor.model.output.FileOutput;

public class Substitutor {

    Output output = new FileOutput();

    public Output run(Input input) {

        String substitutedData = input.substitute();

        output.setSubstitutedData(substitutedData);
        return output;
    }


}
