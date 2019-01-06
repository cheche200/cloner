package com.example.substitutor.model.template;

public class TemplateFile implements Template {

    private String template;

    public TemplateFile() {
        //Intentionally empty
    }

    public TemplateFile(String fileName) {

    }

    @Override
    public void setTemplate(String templateToSubstitute) {
        this.template = templateToSubstitute;
    }

    @Override
    public String getTemplate() {
        return this.template;
    }
}
