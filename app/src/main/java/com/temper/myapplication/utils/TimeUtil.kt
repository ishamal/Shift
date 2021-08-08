package com.temper.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

object TimeUtil {

    fun calTime(date: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.getDefault())
        var date = format.parse(date)
        return getCurrentDate("HH:mm", date)
    }

    fun getCurrentDate(format: String, formatDate : Date) : String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(formatDate)
    }

    fun formatDate() : String{
        var calender = Calendar.getInstance()
        var date = calender.get(Calendar.DAY_OF_WEEK)
        return "${getDates(date)} , ${getCurrentDate("dd MMM")}"
    }

    fun getDates(day : Int) : String? {
        var map = HashMap<Int, String>()
        map[1] = "zondag"
        map[2] = "maandag"
        map[3] = "dinsdag"
        map[4] = "woensdag"
        map[5] = "donderdag"
        map[6] = "vrijdag"
        map[7] = "zaterdag"

        return map[day]
    }

    fun getCurrentDate(format: String): String {
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date())
    }
}