package ru.home.russianlang.content

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import ru.home.russianlang.RulesActivity
import ru.home.russianlang.evaluator.Node
import ru.home.russianlang.model.GroupOfRules
import ru.home.russianlang.model.Rule
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class RulesGen {

    fun generateRules(): List<RulesActivity.Rule<*>> {
        val result: MutableList<RulesActivity.Rule<*>> =
            ArrayList()
        result.add(prepareRule20())
        result.add(prepareRule22())
        return result
    }

    private fun prepareRule20(): RulesActivity.Rule<*> {
        val rule: RulesActivity.Rule<*> =
            RulesActivity.Rule<Node>("Безударные окончания разных частей речи")
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule20_1()))
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule20_2()))
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule20_3()))
        return rule
    }

    private fun prepareRule22(): RulesActivity.Rule<*> {
        val rule: RulesActivity.Rule<*> =
            RulesActivity.Rule<Node>("Правописание глаголов и отглагольных слов")
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule22_1()))
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule22_2()))
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule22_3()))
        rule.addSubRule(RulesActivity.Rule(Utils.generateRule22_4()))
        return rule
    }

    public fun readRules(context: Context): List<Rule> {
        val result = ArrayList<Rule>()

        val filenames = listAssetFiles("rules", context)
        for (filePath in filenames) {
            val assetAsString = assetToString(context, filePath)

            val gson: Gson = Gson()
            val groupOfRulesType = object : TypeToken<GroupOfRules>() {}.type
            val rules: GroupOfRules = gson.fromJson(assetAsString, groupOfRulesType)

            Log.d("TAG", "Rules are parsed: " + rules.toString())
        }

        return result
    }

    private class AppJsonDes: JsonDeserializer<Node> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Node {
            TODO("Not yet implemented")
        }

    }

    /**
     * Gather file names in assets folder
     *
     * @author https://stackoverflow.com/a/50121931/13572763
     * */
    private fun listAssetFiles(path: String, context: Context): List<String> {
        val result = ArrayList<String>()
        context.assets.list(path)!!.forEach { file ->
            val innerFiles = listAssetFiles("$path/$file", context)
            if (!innerFiles.isEmpty()) {
                result.addAll(innerFiles)
            } else {
                // it can be an empty folder or file you don't like, you can check it here
                result.add("$path/$file")
            }
        }
        return result
    }

    private fun assetToString(context: Context, jsonName: String): String {
        return context.assets.open(jsonName)
            .bufferedReader()
            .use { it.readText() }
    }
}