package ru.home.russianlang.providers.evaluator;

import ru.home.russianlang.model.IViewNode;

public class Evaluator implements IViewNode.Callbacks {

    private IViewNode root;
    private IViewNode selected = null;

    public IViewNode getRoot() {
        return root;
    }

    public void setRoot(IViewNode root) {
        this.root = root;
        this.root.setListener(this);
        this.selected = root;
    }

    public IViewNode getSelected() {
        return selected;
    }

    public void setSelected(IViewNode selected) {
        this.selected = selected;
    }

    @Override
    public void onSelectingNode(IViewNode node) {
        selected = node;
    }
}
