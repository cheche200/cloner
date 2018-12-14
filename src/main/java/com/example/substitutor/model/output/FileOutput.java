package com.example.substitutor.model.output;

public class FileOutput implements Output {



    private String substitutedData;

    @Override
    public String getSubstitutedData() {
        return substitutedData;
    }

    @Override
    public void setSubstitutedData(String substitutedData) {
        this.substitutedData = substitutedData;
    }

}
