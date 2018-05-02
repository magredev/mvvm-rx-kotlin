package com.magre.challenge.extension

import java.text.SimpleDateFormat

fun String.getYear(patternIn: String = "yyyy-MM-dd", patternOut: String = "yyyy") : Int {
    val dfIn = SimpleDateFormat(patternIn)
    val dfOut = SimpleDateFormat(patternOut)

    return dfOut.format(dfIn.parse(this)).toInt()
}
