package ru.home.russianlang.providers.evaluator;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import ru.home.russianlang.model.IViewNode;

public class Evaluator implements IViewNode.Callbacks {

    private IViewNode root;
    private IViewNode selected = null;

    private List<Pair<IViewNode, Integer>> answers = new ArrayList<>();

    @Override
    public void onSelectingNode(IViewNode node, IViewNode nodeParent) {
        selected = node;
        answers.add(
                new Pair<>(
                        nodeParent,
                        node.equals(nodeParent.getPositive()) ? Answers.ANSWER_POSITIVE : Answers.ANSWER_NEGATIVE
                ));
    }

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

    public List<Pair<IViewNode, Integer>> getAnswers() {
        return answers;
    }

    public Boolean onUpOnLevelHigher() {
        boolean succeed = false;
        if (selected.getParent() != null) {
            selected = selected.getParent();
            answers.remove(answers.size() - 1);
            succeed = true;
        }
        return succeed;
    }

    public static class Answers {

        public static final int ANSWER_POSITIVE = 1;

        public static final int ANSWER_NEGATIVE = 0;

    }
}
