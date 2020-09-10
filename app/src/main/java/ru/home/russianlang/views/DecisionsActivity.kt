package ru.home.russianlang.views

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.color
import kotlinx.android.synthetic.main.activity_main.*
import ru.home.russianlang.R
import ru.home.russianlang.model.Rule
import ru.home.russianlang.providers.DataProvider
import java.io.Serializable

class DecisionsActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PAYLOAD = "PAYLOAD"

        fun start(context: Context, payload: Serializable) {
            val intent = Intent(context, DecisionsActivity::class.java)
            intent.putExtra(EXTRA_PAYLOAD, payload)
            context.startActivity(intent)
        }
    }

    private lateinit var provider: DataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rule = intent.extras!!.get(EXTRA_PAYLOAD) as Rule;

        provider = DataProvider(this)
        provider.setupData(rule.root)

        findViewById<TextView>(R.id.title).text = rule.title

        findViewById<TextView>(R.id.exceptionIndicator).setOnClickListener { view ->
            provider.exceptionsIsShown(true)
            updateView()
        }

        updateView()

        positiveReply.setOnClickListener { view ->
            provider.onPositiveAnswer()
            resetConditions()
            updateView()
        }

        negativeReply.setOnClickListener { view ->
            provider.onNegativeAnswer()
            resetConditions()
            updateView()
        }
    }

    override fun onBackPressed() {
        if (provider.isExceptionsShown()) {
            provider.exceptionsIsShown(false)
            updateView()
        } else if (provider.onUpOnLevelHigher()) {
            resetConditions()
            updateView()
        } else {
            super.onBackPressed()
        }
    }

    private fun updateView() {
        if (provider.isExceptionsShown()) {
            val exceptionValue = provider.prepareExceptions(provider.getExceptions())
            questionToVerify.text = exceptionValue
        } else {
            questionToVerify.text = provider.getMainText()
        }
        disableButtonsOnCase()
        showException()
    }

    private fun showException() {
        if (provider.isThereExceptions() && !provider.isExceptionsShown()) {
            exceptionIndicator.visibility = View.VISIBLE
            exceptionHint.visibility = View.VISIBLE
        } else {
            exceptionIndicator.visibility = View.GONE
            exceptionHint.visibility = View.GONE
        }
    }

    private fun disableButtonsOnCase() {
        positiveReply.isEnabled = provider.isTherePositiveBranch()
        negativeReply.isEnabled = provider.isThereNegativeBranch()
    }

    private fun resetConditions() {
        negativeReply.isEnabled = true
        positiveReply.isEnabled = true
        provider.exceptionsIsShown(false)
    }
}