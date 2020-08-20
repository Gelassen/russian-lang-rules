package ru.home.russianlang.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.home.russianlang.R
import ru.home.russianlang.model.Rule
import ru.home.russianlang.providers.DataProvider
import java.io.Serializable
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PAYLOAD = "PAYLOAD"

        fun start(context: Context, payload: Serializable) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_PAYLOAD, payload)
            context.startActivity(intent)
        }
    }

    private val provider = DataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rule = intent.extras!!.get(EXTRA_PAYLOAD) as Rule;

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
            val exceptionValue = provider.prepareExceptions(provider.getCurrentData()!!.exceptions)
            questionToVerify.text = exceptionValue
        } else {
            questionToVerify.text = provider.getCurrentData()!!.value
        }
        disableButtonsOnCase()
        showException()
    }

    private fun showException() {
        if (provider.getCurrentData()!!.exceptions.isNotEmpty() && !provider.isExceptionsShown()) {
            exceptionIndicator.visibility = View.VISIBLE
        } else {
            exceptionIndicator.visibility = View.GONE
        }
    }

    private fun disableButtonsOnCase() {
        positiveReply.isEnabled = provider.getCurrentData()!!.positive != null
        negativeReply.isEnabled = provider.getCurrentData()!!.negative != null
    }

    private fun resetConditions() {
        negativeReply.isEnabled = true
        positiveReply.isEnabled = true
        provider.exceptionsIsShown(false)
    }
}