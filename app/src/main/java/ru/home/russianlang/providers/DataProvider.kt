package ru.home.russianlang.providers

import ru.home.russianlang.providers.evaluator.Evaluator
import ru.home.russianlang.providers.evaluator.Node

class DataProvider {
    private lateinit var evaluator: Evaluator

    fun setupData(node: Node) {
        this.evaluator = Evaluator()
        this.evaluator.root = node
    }

    fun getCurrentData(): Node? {
        return evaluator.selected
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

}