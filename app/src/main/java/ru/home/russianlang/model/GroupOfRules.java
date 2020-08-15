package ru.home.russianlang.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupOfRules {

    @SerializedName("GroupOfRules")
    private List<Rules> rules;

    public List<Rules> getRules() {
        return rules;
    }

    public void setRules(List<Rules> rules) {
        this.rules = rules;
    }
}
