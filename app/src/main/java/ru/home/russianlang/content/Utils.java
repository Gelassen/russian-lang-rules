package ru.home.russianlang.content;

import ru.home.russianlang.evaluator.Evaluator;
import ru.home.russianlang.evaluator.Node;

public class Utils {

    public static Evaluator generateEvaluationTreeRule20() {
        Evaluator tree = new Evaluator();
        Node root = new Node(tree);
        root.setTitle("E//И слов предметов (существительных)");
        root.setValue("Слово женского рода с мягким знаком на конце (в начальной форме) ?");
        root.setPositive(new Node(tree, "И (тени)"));
        root.setNegative(new Node(tree, "на -ия, -ие, -ий  // на -мя, -дитя"));
        root.setException("-ие сохраняется при ответе на вопрос кого?//что? (войти в здание)");

        Node nodeNegativeLevel1 = root.getNegative();
        nodeNegativeLevel1.setPositive(new Node(tree, "И (армии)"));
        nodeNegativeLevel1.setException("(на острие))");
        nodeNegativeLevel1.setNegative(new Node(tree, "Это слово типа страна // земля ?"));

        Node nodeNegativeLevel2 = nodeNegativeLevel1.getNegative();
        nodeNegativeLevel2.setPositive(new Node(tree, "Пишем, как страна (о стране => о земле)"));
        nodeNegativeLevel2.setNegative(new Node(tree, "Е (о товарище)"));
        nodeNegativeLevel2.getNegative().setException("в забытьи");

        tree.setRoot(root);
        return tree;
    }
}
