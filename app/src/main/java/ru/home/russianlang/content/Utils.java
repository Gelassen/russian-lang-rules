package ru.home.russianlang.content;

import ru.home.russianlang.evaluator.Evaluator;
import ru.home.russianlang.evaluator.Node;

public class Utils {

    public static Evaluator generateEvaluationTreeRule20_1() {
        Evaluator tree = new Evaluator();
        Node root = new Node(tree);
        root.setTitle("Окончания E//И слов предметов (существительных)");
        root.setValue("Слово женского рода с мягким знаком на конце (в начальной форме) ?");
        root.setPositive(new Node(tree, "И (тени)"));
        root.setNegative(new Node(tree, "на -ия, -ие, -ий  // на -мя, -дитя"));

        Node nodeNegativeLevel1 = root.getNegative();
        nodeNegativeLevel1.setPositive(new Node(tree, "И (армии)"));
        nodeNegativeLevel1.setExceptions("-ие сохраняется при ответе на вопрос кого?//что? (войти в здание)");
        nodeNegativeLevel1.setNegative(new Node(tree, "Это слово типа страна // земля ?"));

        Node nodePositiveLevel2 = nodeNegativeLevel1.getPositive();
        nodePositiveLevel2.setExceptions("(на острие))");

        Node nodeNegativeLevel2 = nodeNegativeLevel1.getNegative();
        nodeNegativeLevel2.setPositive(new Node(tree, "Пишем, как страна (о стране => о земле)"));
        nodeNegativeLevel2.setNegative(new Node(tree, "Е (о товарище)"));
        nodeNegativeLevel2.getNegative().setExceptions("в забытьи");

        tree.setRoot(root);
        return tree;
    }

    public static Node generateRule20_2() {
        Node root = new Node(null);

        root.setTitle("Безударные окончания слов признаков \n Которые отвечают на вопросы Какой? Какая? Какое? Какие?");
        root.setValue("Пишем такие же окончания, но в той форме, которой требует слово-предмет -- носитель данного признака (в каком? в лёгком блестящем костюме)");

        return root;
    }
}
