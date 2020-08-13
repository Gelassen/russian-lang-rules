package ru.home.russianlang.evaluator;

import ru.home.russianlang.evaluator.Node;

public class Evaluator implements Node.Callbacks {

    private Node root;
    private Node selected = null;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
        this.selected = root;
    }

    public Node getSelected() {
        return selected;
    }

    public void setSelected(Node selected) {
        this.selected = selected;
    }

    @Override
    public void onSelectingNode(Node node) {
        selected = node;
    }
}
