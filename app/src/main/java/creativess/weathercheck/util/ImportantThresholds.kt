package creativess.weathercheck.util

import creativess.weathercheck.networking.networkingModel.current.Rain
import creativess.weathercheck.networking.networkingModel.current.Snow

object ImportantThresholds {
    private const val SNOW_RAIN = 10.0

    private const val LOW_HUMIDITY = 20
    private const val HIGH_HUMIDITY = 60
    private const val VERY_HIGH_HUMIDITY = 80

    private const val VISIBILITY_METRIC = 4000
    private const val WIND_SPEED_METRIC = 10


    fun showRainSnow(rain: Rain?, snow: Snow?): Int {
        if (rain == null && snow == null) return 0
        else {
            if (rain != null && snow != null) {
                val rainValue = rain.`1h`!!
                val snowValue = snow.`1h`!!
                if (snowValue > SNOW_RAIN && snowValue > rainValue) return 2
                if (rainValue > SNOW_RAIN && rainValue > snowValue) return 1
                return 0
            } else {
                if (rain != null) {
                    if (rain.`1h`!! > SNOW_RAIN) return 1
                    return 0
                } else {
                    if (snow!!.`1h`!! > SNOW_RAIN) return 2
                    return 0
                }
            }

        }

    }

    fun showHumidity(humidity: Int?): Int {
        return if (humidity == null) 0
        else {
            if (humidity < LOW_HUMIDITY) 1
            else {
                if (humidity > VERY_HIGH_HUMIDITY) 3
                else {
                    if (humidity > HIGH_HUMIDITY) 2
                    else 0
                }
            }
        }
    }

    fun showVisibility(visibility: Int): Boolean {
        return visibility < VISIBILITY_METRIC
    }

    fun showWindSpeed(windSpeed: Double?): Boolean {
        if (windSpeed == null) return false
        if (windSpeed > WIND_SPEED_METRIC) return true
        return false
    }
}