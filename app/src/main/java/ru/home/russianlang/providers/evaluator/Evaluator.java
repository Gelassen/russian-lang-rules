package ru.home.russianlang.providers.evaluator;

public class Evaluator implements Node.Callbacks {

    private Node root;
    private Node selected = null;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
        this.root.setListener(this);
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
