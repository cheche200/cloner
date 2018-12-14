package com.example.substitutor.model.output;

import java.util.List;

public class FileOutput implements Output {

    private List<String> substitutedData;


    @Override
    public List<String> getSubstitutedData() {
        return substitutedData;
    }

    @Override
    public void setSubstitutedData(List<String> substitutedData) {
        this.substitutedData = substitutedData;
    }
}
