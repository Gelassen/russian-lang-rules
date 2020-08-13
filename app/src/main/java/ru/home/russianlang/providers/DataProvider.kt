package ru.home.russianlang.providers

import ru.home.russianlang.evaluator.Evaluator
import ru.home.russianlang.evaluator.Node
import ru.home.russianlang.content.Utils

class DataProvider {
    private var evaluator: Evaluator

    fun getCurrentData(): Node? {
        return evaluator.selected
    }

    fun startRule20() {
        evaluator = Utils.generateEvaluationTreeRule20()
    }

    fun onPositiveAnswer() {
        val selected = evaluator.selected
        selected.positive
        selected.notifyListeners(selected.positive)
    }

    fun onNegativeAnswer() {
        val selected = evaluator.selected
        selected.negative
        selected.notifyListeners(selected.negative)
    }

    fun onUpOnLevelHigher(): Boolean {
        var succeed: Boolean = false
        if (evaluator.selected.parent != null) {
            evaluator.selected = evaluator.selected.parent
            succeed = true
        }
        return succeed
    }

    init {
        evaluator =
            Utils.generateEvaluationTreeRule20()
    }
}