package ru.home.russianlang.content

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.home.russianlang.model.Rules
import kotlin.collections.ArrayList


class RulesGen {

    public fun readRules(context: Context): List<Rules> {
        val result = ArrayList<Rules>()

        val gson: Gson = Gson()
        val groupOfRulesType = object : TypeToken<Rules>() {}.type

        val filenames = listAssetFiles("rules", context)
        for (filePath in filenames) {
            val assetAsString = assetToString(context, filePath)
            val rules: Rules = gson.fromJson(assetAsString, groupOfRulesType)
            result.add(rules)
            Log.d("TAG", "Rules are parsed: " + filePath)
        }

        return result
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