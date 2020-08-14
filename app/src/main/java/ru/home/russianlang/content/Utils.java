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

    public static Node generateRule20_1() {
        Node root = new Node(null);
        root.setTitle("Окончания E//И слов предметов (существительных)");
        root.setValue("Слово женского рода с мягким знаком на конце (в начальной форме) ?");
        root.setPositive(new Node(null, "И (тени)"));
        root.setNegative(new Node(null, "на -ия, -ие, -ий  // на -мя, -дитя"));

        Node nodeNegativeLevel1 = root.getNegative();
        nodeNegativeLevel1.setPositive(new Node(null, "И (армии)"));
        nodeNegativeLevel1.setExceptions("-ие сохраняется при ответе на вопрос кого?//что? (войти в здание)");
        nodeNegativeLevel1.setNegative(new Node(null, "Это слово типа страна // земля ?"));

        Node nodePositiveLevel2 = nodeNegativeLevel1.getPositive();
        nodePositiveLevel2.setExceptions("(на острие))");

        Node nodeNegativeLevel2 = nodeNegativeLevel1.getNegative();
        nodeNegativeLevel2.setPositive(new Node(null, "Пишем, как страна (о стране => о земле)"));
        nodeNegativeLevel2.setNegative(new Node(null, "Е (о товарище)"));
        nodeNegativeLevel2.getNegative().setExceptions("в забытьи");

        return root;
    }

    public static Node generateRule20_2() {
        Node root = new Node(null);

        root.setTitle("Безударные окончания слов признаков \n Которые отвечают на вопросы Какой? Какая? Какое? Какие?");
        root.setValue("Пишем такие же окончания, но в той форме, которой требует слово-предмет -- носитель данного признака (в каком? в лёгком блестящем костюме)");
        root.setExceptions("В мужском род при 'какой?' -ЫЙ, -ИЙ, шар -какой? -синий, -белый");
        root.setExceptions("В словах на -ИЙ, -ЬЯ, -ЬЕ (принадлежность!) Ь знак во всех формах кроме именительного и винительного падежей мужского рода (лисий, лисьего, лисьему, лисий, лисьим, о лисьем)");
        return root;
    }

    public static Node generateRule20_3() {
        Node root = new Node(null);

        root.setTitle("Гласные в безударных окончаниях слов действий (глаголы)");
        root.setValue("Из двенадцати глаголов исключений?");
        root.setPositive(new Node(null, "И//А(Я) (держит, держат)"));
        root.setNegative(new Node(null, "на -ИТЬ"));

        Node negativeLevel1 = root.getNegative();
        negativeLevel1.setPositive(new Node(null, "И//А(Я) (строит, строят)"));
        negativeLevel1.setNegative(new Node(null, "Е//У(Ю) (пишет, пишут)"));

        Node positiveLevel2 = negativeLevel1.getPositive();
        positiveLevel2.setExceptions("брить, стелить (стелет, стелют)");

        root.setExceptions(
                "1. Глаголы, у которых личное ударное окончание, с прибавлением приставки 'вы' пишется также как и без неё (бежишь => выбежишь) \n" +
                "2. Те же и так же пишутся гласные в суффиксах ущ(ющ)//ащ(ящ), ем//им слов признаков, образованных от глагола: строящийся, читаемый");
        return root;
    }

    // Правописание глаголов и отглагольных слов
    public static Node generateRule22_1() {
        Node root = new Node(null);

        root.setTitle("ИТЕ//ЕТЕ на конце глаголов");
        root.setValue("повелительная форма?");
        root.setPositive(new Node(null, "ИТЕ (почистите)"));
        root.setNegative(new Node(null, "из 11 исключений // на ИТЬ?"));

        Node negativeLevel1 = root.getNegative();
        negativeLevel1.setPositive(new Node(null, "ИТЕ (строите)"));
        negativeLevel1.setNegative(new Node(null, "ЕТЕ (пишете)"));
        return root;
    }

    public static Node generateRule22_2() {
        Node root = new Node(null);

        root.setTitle("ТСЯ//ТЬСЯ на конце глаголов");
        root.setValue("Отвечает на вопрос 'что делает?' (кружится), отвечает на вопрос 'что делать?' (кружиться)");

        return root;
    }

    public static Node generateRule22_3() {
        Node root = new Node(null);

        root.setTitle("Гласная перед суффиксами Л//ВШ//В(вши)//ВА//ТЕЛЬ//ТЕЛЬН");
        root.setValue(
                "Та же гласная, что перед ТЬ в начальной форме (строить => строил, " +
                "измазать => измазавший, высмотреть => высмотрев, одолеть => одолевать, " +
                "множить => множитель, трогать => трогательный)"
        );
        root.setExceptions("застрять, затмить, продлить, вать");

        return root;
    }

    public static Node generateRule22_4() {
        Node root = new Node(null);

        root.setTitle("НЕ с глаголами");
        root.setValue("НЕ с глаголами пишется отдельно (не знает, не видет, не слушает)");
        root.setExceptions("Есть глаголы, которые без НЕ не бывают (негодовать)");
        return root;
    }
}
