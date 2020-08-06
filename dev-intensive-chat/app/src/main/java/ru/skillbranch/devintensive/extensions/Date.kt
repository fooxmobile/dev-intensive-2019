package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = SECOND*60
const val HOUR = MINUTE*60
const val DAY = HOUR*24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var diff =  date.time - this.time
    val inFuture = diff < 0
    diff = abs(diff)
       return when {
            (diff / SECOND <= 1 ) -> "только что"
            (diff / SECOND <= 45) -> if(inFuture) "через несколько секунд" else "несколько секунд назад"
            (diff / SECOND <= 75) -> if(inFuture) "через минуту" else "минуту назад"
            (diff / MINUTE <= 45) -> if(inFuture) "через ${TimeUnits.MINUTE.plural((diff/ MINUTE).toInt())}" else "${TimeUnits.MINUTE.plural((diff/ MINUTE).toInt())} назад"
            (diff / MINUTE <= 75) -> "час назад"
            (diff / HOUR <= 22) -> if(inFuture) "через ${TimeUnits.HOUR.plural((diff/ HOUR).toInt())}" else "${TimeUnits.HOUR.plural((diff/ HOUR).toInt())} назад"
            (diff / HOUR <= 26) -> "день назад"
            (diff / DAY <= 360) -> if(inFuture) "через ${TimeUnits.DAY.plural((diff/ DAY).toInt())}" else "${TimeUnits.DAY.plural((diff/ DAY).toInt())} назад"
            else -> if(inFuture) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

}

fun TimeUnits.plural(value: Int): String {
    return when {
        (value%10 == 1 && (value%100) != 11) ->
            when (this) {
                TimeUnits.SECOND -> "$value секунду"
                TimeUnits.MINUTE -> "$value минуту"
                TimeUnits.HOUR -> "$value час"
                TimeUnits.DAY -> "$value день"
            }
        (value%10 in 2..4 && (value%100) !in 12..14) ->
            when (this) {
                TimeUnits.SECOND -> "$value секунды"
                TimeUnits.MINUTE -> "$value минуты"
                TimeUnits.HOUR -> "$value часа"
                TimeUnits.DAY -> "$value дня"
            }
        else -> when(this) {
            TimeUnits.SECOND -> "$value секунд"
            TimeUnits.MINUTE -> "$value минут"
            TimeUnits.HOUR -> "$value часов"
            TimeUnits.DAY -> "$value дней"
        }
    }

}