package ru.home.russianlang.evaluator;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private String title;
    private String value;
    private List<String> exceptions = new ArrayList<>();
    private Node positive;
    private Node negative;
    private Node parent;

    private Callbacks listener;

    public Node(Callbacks listener) {
        this.listener = listener;
    }

    public Node(Callbacks listener, String value) {
        this.value = value;
        this.listener = listener;
    }

    public void setListener(Callbacks listener) {
        this.listener = listener;
        if (positive != null) {
            positive.setListener(listener);
        }
        if (negative != null) {
            negative.setListener(listener);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exception) {
        this.exceptions.add(exception);
    }

    public Node getPositive() {
        return positive;
    }

    public void setPositive(Node positive) {
        this.positive = positive;
        this.positive.parent = this;
    }

    public Node getNegative() {
        return negative;
    }

    public void setNegative(Node negative) {
        this.negative = negative;
        this.negative.parent = this;
    }

    public Node getParent() {
        return parent;
    }

    public void notifyListeners(@Nullable Node node) {
        listener.onSelectingNode(node);
    }

    public interface Callbacks {
        void onSelectingNode(Node node);
    }
}
