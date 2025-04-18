package creativess.weathercheck.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Units {
    fun getDistanceUnit(): String {
        return "m"
    }

    fun getSpeedUnit(isMetric: Boolean): String {
        return if (isMetric) "m/s" else "mph"
    }

    fun getPrecipitationUnit(): String {
        return "mm/hr"
    }

    fun getHumidityUnit(): String {
        return "%"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun unixToTimeWithShift(unixTimestamp: Int, utcShiftInSeconds: Int): String {
        val adjustedTimestamp = unixTimestamp + utcShiftInSeconds

        val instant = Instant.ofEpochSecond(adjustedTimestamp.toLong())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") // Customize format
        val localDateTime =
            instant.atZone(ZoneId.of("UTC")).toLocalDateTime() // Use specific ZoneId if needed

        return formatter.format(localDateTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTimeWithOffset(timezoneOffsetSeconds: Int): String {
        // Get the current time in UTC
        val currentUtcTime = Instant.now()

        // Adjust the UTC time by the timezone offset in seconds
        val adjustedUtcTime = currentUtcTime.plusSeconds(timezoneOffsetSeconds.toLong())

        // Convert adjusted time to local time
        val zonedDateTime = adjustedUtcTime.atZone(ZoneId.of("UTC"))

        // Format the time as you wish
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return formatter.format(zonedDateTime)
    }

    fun timeDifference(first: String, second: String): List<Int> {
        val firstMin = first.take(2).toInt() * 60 + first.takeLast(2).toInt()
        val secondMin = second.take(2).toInt() * 60 + second.takeLast(2).toInt()
        val lengthMin = secondMin - firstMin
        val lengthHour = lengthMin / 60

        return listOf(lengthHour, (lengthMin % 60))
    }
}