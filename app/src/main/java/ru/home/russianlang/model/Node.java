package ru.home.russianlang.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private String title;
    private List<String> exceptions = new ArrayList<String>();
    private Node positive;
    private Node negative;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public void addException(String exception) {
        this.exceptions.add(exception);
    }

    public Node getPositive() {
        return positive;
    }

    public void setPositive(Node positive) {
        this.positive = positive;
    }

    public Node getNegative() {
        return negative;
    }

    public void setNegative(Node negative) {
        this.negative = negative;
    }
}
