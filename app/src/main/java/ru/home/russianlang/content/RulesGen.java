package ru.home.russianlang.content;

import java.util.ArrayList;
import java.util.List;

import ru.home.russianlang.RulesActivity;
import ru.home.russianlang.evaluator.Node;

public class RulesGen {

    public static List<RulesActivity.Rule> generateRules() {
        List<RulesActivity.Rule> result = new ArrayList<>();

        result.add(prepareRule20());
        result.add(prepareRule22());


        return result;
    }

    private static RulesActivity.Rule prepareRule20() {
        RulesActivity.Rule rule = new RulesActivity.Rule<Node>("Безударные окончания разных частей речи");
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule20_1()));
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule20_2()));
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule20_3()));


        return rule;
    }

    private static RulesActivity.Rule prepareRule22() {
        RulesActivity.Rule rule = new RulesActivity.Rule<Node>("Правописание глаголов и отглагольных слов");
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule22_1()));
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule22_2()));
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule22_3()));
        rule.addSubRule(new RulesActivity.Rule<Node>(Utils.generateRule22_4()));

        return rule;
    }
}
