package ru.home.russianlang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.home.russianlang.providers.DataProvider

class MainActivity : AppCompatActivity() {

    private val provider = DataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val view = R.id.questionToVerify
//        view.text = ""
        provider.startRule20()

        questionToVerify.text = provider.getCurrentData()!!.value

        findViewById<TextView>(R.id.title).text = provider.getCurrentData()!!.title

        positiveReply.setOnClickListener { view ->
            provider.onPositiveAnswer()

            questionToVerify.text = provider.getCurrentData()!!.value

            disableButtonsOnCase()
            showException()
        }

        negativeReply.setOnClickListener { view ->
            provider.onNegativeAnswer()

            questionToVerify.text = provider.getCurrentData()!!.value

            disableButtonsOnCase()
            showException()
        }
    }

    private fun showException() {
        if (provider.getCurrentData()!!.exception != null) {
            exception.visibility = View.VISIBLE
            exception.text = /*"Исключение: \n" + */provider.getCurrentData()!!.exception
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