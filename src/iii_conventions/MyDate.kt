package iii_conventions

import java.math.BigDecimal
import java.util.*

data class MyDate(
        val year: Int,
        val month: Int,
        val dayOfMonth: Int
) : Comparable<MyDate>{

    override fun compareTo(other: MyDate): Int {
        if (this == other){
            return 0
        }

        val myCal = Calendar.getInstance()
        myCal.set(year, month, dayOfMonth)

        val otherCal = Calendar.getInstance()
        otherCal.set(other.year, other.month, other.dayOfMonth)

        return myCal.compareTo(otherCal)
    }

    operator fun plus(interval: TimeInterval) : MyDate{
        return addTimeIntervals(interval, 1)
    }

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval) : MyDate {
        return addTimeIntervals(repeatedTimeInterval.interval, repeatedTimeInterval.times)
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(coeff: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, coeff)
}

data class RepeatedTimeInterval(val interval: TimeInterval, val times : Int)

data class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate>{
            var current : MyDate? = null

            override fun hasNext(): Boolean {
                return current?.nextDay() ?: start <= endInclusive
            }

            override fun next(): MyDate {
                val next = current?.nextDay() ?: start
                current = next
                return next
            }
        }
    }

    operator fun contains(d: MyDate) : Boolean{
        return start <= d && d <= endInclusive
    }
}
