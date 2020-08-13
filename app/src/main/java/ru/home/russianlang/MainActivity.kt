package ru.home.russianlang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        positiveReply.setOnClickListener { view ->
            provider.onPositiveAnswer()

            questionToVerify.text = provider.getCurrentData()!!.value
            if (provider.getCurrentData()!!.positive == null
                && provider.getCurrentData()!!.negative == null) {
                positiveReply.isEnabled = false
                negativeReply.isEnabled = false
            }
        }

        negativeReply.setOnClickListener { view ->
            provider.onNegativeAnswer()

            questionToVerify.text = provider.getCurrentData()!!.value
            if (provider.getCurrentData()!!.positive == null
                && provider.getCurrentData()!!.negative == null) {
                negativeReply.isEnabled = false
                positiveReply.isEnabled = false
            }
        }
    }
}