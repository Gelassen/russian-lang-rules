package ru.home.russianlang.model;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import ru.home.russianlang.providers.evaluator.Node;

public interface IViewNode {

    void setListener(ru.home.russianlang.providers.evaluator.Node.Callbacks listener);

    String getValue();

    void setValue(String value);

    String getTitle();

    void setTitle(String title);

    List<String> getExceptions();

    void setExceptions(String exception);

    Node getPositive();

    void setPositive(Node positive);

    Node getNegative();

    void setNegative(Node negative);

    void setParent();

    Node getParent();

    void notifyListeners(@Nullable Node node);
}
