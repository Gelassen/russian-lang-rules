package ru.home.russianlang.model;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Serializable, IViewNode {
    private String title;
    private List<String> exceptions = new ArrayList<String>();
    private Node positive;
    private Node negative;

    private IViewNode.Callbacks listener;
    private IViewNode parent;

    @Override
    public void setListener(Callbacks listener) {
        this.listener = listener;
        if (positive != null) {
            positive.setListener(listener);
        }
        if (negative != null) {
            negative.setListener(listener);
        }
    }

    @Override
    public String getValue() {
        return title;
    }

    @Override
    public void setValue(String value) {
        this.title = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<String> getExceptions() {
        return exceptions;
    }

    @Override
    public void addException(String exception) {
        this.exceptions.add(exception);
    }

    public Node getPositive() {
        return positive;
    }

    @Override
    public void setPositive(Node positive) {
        this.positive = positive;
        this.positive.parent = this;
    }

    public Node getNegative() {
        return negative;
    }

    @Override
    public void setNegative(Node negative) {
        this.negative = negative;
        this.negative.parent = this;
    }

    @Override
    public void setParent(IViewNode parent) {
        this.parent = parent;
    }

    @Override
    public IViewNode getParent() {
        return parent;
    }

    @Override
    public void notifyListeners(@Nullable IViewNode nodeParent, Node nodeSelected) {
        if (listener == null) throw new IllegalStateException("Did you forget to add listener?");

        nodeSelected.setParent(nodeParent);
        listener.onSelectingNode(nodeSelected, nodeParent);
    }

    @Override
    public List<IViewNode> getAncestors() {
        List<IViewNode> result = new ArrayList<>();
        IViewNode tempParent = parent;
        while (tempParent != null) {
            result.add(tempParent);
            tempParent = tempParent.getParent();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getTitle(), node.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
