package creativess.weathercheck.networking.networkingModel.threeHour

import creativess.weathercheck.networking.networkingModel.Clouds
import creativess.weathercheck.networking.networkingModel.Weather
import creativess.weathercheck.networking.networkingModel.Wind

data class Forecast(
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val dt_txt: String? = null,
    val main: Main? = null,
    val pop: Double? = null,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val sys: Sys? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)