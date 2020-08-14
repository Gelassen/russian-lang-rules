package ru.home.russianlang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.home.russianlang.evaluator.Node
import ru.home.russianlang.providers.DataProvider
import java.io.Serializable

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

        val node = intent.extras!!.get(EXTRA_PAYLOAD) as Node;

        provider.setupData(node)

        findViewById<TextView>(R.id.title).text = provider.getCurrentData()!!.title

        updateView()

        positiveReply.setOnClickListener { view ->
            provider.onPositiveAnswer()
            updateView()
        }

        negativeReply.setOnClickListener { view ->
            provider.onNegativeAnswer()
            updateView()
        }
    }

    override fun onBackPressed() {
        if (provider.onUpOnLevelHigher()) {
            resetConditions()
            updateView()
        } else {
            super.onBackPressed()
        }
    }

    private fun updateView() {
        questionToVerify.text = provider.getCurrentData()!!.value

        disableButtonsOnCase()
        showException()
    }

    private fun showException() {
        if (provider.getCurrentData()!!.exceptions.isNotEmpty()) {
            exception.visibility = View.VISIBLE
            exception.text = provider.getCurrentData()!!.exceptions.get(0)
            exceptionTitle.visibility = View.VISIBLE
        } else {
            exceptionTitle.visibility = View.GONE
            exception.visibility = View.GONE
        }
    }

    private fun disableButtonsOnCase() {
        if (provider.getCurrentData()!!.positive == null
            && provider.getCurrentData()!!.negative == null) {
            negativeReply.isEnabled = false
            positiveReply.isEnabled = false
        }
    }

    private fun resetConditions() {
        negativeReply.isEnabled = true
        positiveReply.isEnabled = true
        exceptionTitle.visibility = View.GONE
        exception.visibility = View.GONE
    }
}