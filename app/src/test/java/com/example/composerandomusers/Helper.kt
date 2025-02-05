package com.example.composerandomusers

import java.io.InputStreamReader
import kotlin.collections.forEach
import kotlin.io.readLines
import kotlin.jvm.javaClass

object Helper {

    fun getJsonFile(fileName:String):String{
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream,"UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}