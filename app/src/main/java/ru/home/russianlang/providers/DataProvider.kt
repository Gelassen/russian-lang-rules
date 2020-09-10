package ru.home.russianlang.providers

import android.content.res.Resources
import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.color
import ru.home.russianlang.R
import ru.home.russianlang.model.IViewNode
import ru.home.russianlang.providers.evaluator.Evaluator
import java.lang.StringBuilder

class DataProvider {
    private lateinit var evaluator: Evaluator

    private var positiveColor: Int
    private var negativeColor: Int

    private var isExceptionShown: Boolean = false

    constructor(res: Resources) {
        positiveColor = res.getColor(R.color.colorNegativeDecision)
        negativeColor = res.getColor(R.color.colorPositiveDecision)
    }


    fun setupData(node: IViewNode) {
        this.evaluator = Evaluator()
        this.evaluator.root = node
    }

    @Deprecated(message = "avoid INode usage behind provider")
    fun getCurrentData(): IViewNode? {
        return evaluator.selected
    }

    fun isTherePositiveBranch(): Boolean {
        return getCurrentData()!!.positive != null
    }

    fun isThereNegativeBranch(): Boolean {
        return getCurrentData()!!.negative != null
    }

    fun isThereExceptions(): Boolean {
        return getCurrentData()!!.exceptions.isNotEmpty()
    }

    fun getExceptions(): List<String> {
        return evaluator.selected.exceptions
    }

    fun getMainText(): CharSequence {
        val builder = SpannableStringBuilder()
        val list = prepareListOfDecisions()
        if (list.isNotEmpty()) {
            builder.bold { append(list) }
            builder.append("\n")
        }
        builder.append(evaluator.selected.value)
        return builder
    }

    fun onPositiveAnswer() {
        val selected = evaluator.selected
        selected.notifyListeners(selected, selected.positive)
    }

    fun onNegativeAnswer() {
        val selected = evaluator.selected
        selected.notifyListeners(selected, selected.negative)
    }

    fun onUpOnLevelHigher(): Boolean {
        var succeed: Boolean = false
        if (evaluator.selected.parent != null) {
            evaluator.selected = evaluator.selected.parent
            succeed = true
        }
        return succeed
    }

    fun prepareListOfDecisions(): String {
        if (evaluator.selected.parent == null) return ""

        val strBuilder = StringBuilder()
        for (node in evaluator.selected.ancestors.asReversed()) {
            strBuilder.append(node.title)
            strBuilder.append("\n")
        }
        return strBuilder.toString()
    }

    fun prepareExceptions(exceptions: Collection<String>): String {
        val strBuilder = StringBuilder()
        for (ex in exceptions) {
            strBuilder.append(ex)
            strBuilder.append("\n")
        }

        return strBuilder.removeSuffix("\n").toString()
    }

    fun exceptionsIsShown(value: Boolean) {
        isExceptionShown = value
    }

    fun isExceptionsShown(): Boolean {
        return isExceptionShown
    }

}