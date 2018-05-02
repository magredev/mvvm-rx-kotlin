package com.magre.challenge.extension

import java.text.ParseException
import java.text.SimpleDateFormat

fun String.getYear(patternIn: String = "yyyy-MM-dd", patternOut: String = "yyyy") : Int {
    try {
        val dfIn = SimpleDateFormat(patternIn)
        val dfOut = SimpleDateFormat(patternOut)

        return dfOut.format(dfIn.parse(this)).toInt()
    } catch (e: ParseException) {
        e.printStackTrace()
        return 0
    }
}
