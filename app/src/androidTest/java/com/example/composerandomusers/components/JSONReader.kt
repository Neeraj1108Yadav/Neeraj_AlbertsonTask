package com.example.composerandomusers.components

import android.content.Context
import java.io.InputStreamReader
import kotlin.collections.forEach
import kotlin.io.readLines
import kotlin.jvm.javaClass

object JSONReader {

    fun getJsonFileFromAssets(fileName:String):String{
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream,"UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}