package ru.home.russianlang.evaluator;

import org.jetbrains.annotations.Nullable;

public class Node {
    private String title;
    private String value;
    private String exception;
    private Node positive;
    private Node negative;

    private Callbacks listener;

    public Node(Callbacks listener) {
        this.listener = listener;
    }

    public Node(Callbacks listener, String value) {
        this.value = value;
        this.listener = listener;
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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
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

    public void notifyListeners(@Nullable Node node) {
        listener.onSelectingNode(node);
    }

    public interface Callbacks {
        void onSelectingNode(Node node);
    }
}
