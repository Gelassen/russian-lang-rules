package ru.home.russianlang.model;

import com.google.gson.annotations.SerializedName;

public class Rule {
    private String title;
    @SerializedName("rule")
    private Node root;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
