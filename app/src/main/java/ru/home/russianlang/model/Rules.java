package ru.home.russianlang.model;

import java.util.List;

public class Rules {
    private String title;
    private List<Rule> rules;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
