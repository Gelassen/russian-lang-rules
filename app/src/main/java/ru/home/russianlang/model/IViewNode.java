package ru.home.russianlang.model;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IViewNode {

    void setListener(Callbacks listener);

    String getValue();

    void setValue(String value);

    String getTitle();

    void setTitle(String title);

    List<String> getExceptions();

    void addException(String exception);

    Node getPositive();

    void setPositive(Node positive);

    Node getNegative();

    void setNegative(Node negative);

    void setParent(IViewNode parent);

    IViewNode getParent();

    /**
     * On new selected callback
     * */
    void notifyListeners(@Nullable IViewNode nodeParent, Node nodeSelected);

    List<IViewNode> getAncestors();

    interface Callbacks {
        void onSelectingNode(IViewNode node, IViewNode nodeParent);
    }
}
