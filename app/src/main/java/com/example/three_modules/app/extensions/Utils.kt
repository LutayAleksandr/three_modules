package com.example.three_modules.app.extensions

import android.content.Context
import java.io.IOException

class Utils {
    fun getJson(context: Context, file: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("towns.json").bufferedReader().use{ it.readText() }
        } catch (exception: IOException) {
            exception.printStackTrace()
            return  null
        }
        return  jsonString
    }
}