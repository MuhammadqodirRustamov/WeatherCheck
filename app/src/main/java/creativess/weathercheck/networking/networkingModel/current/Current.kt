package creativess.weathercheck.networking.networkingModel.current

import creativess.weathercheck.networking.networkingModel.Clouds
import creativess.weathercheck.networking.networkingModel.Coord
import creativess.weathercheck.networking.networkingModel.Weather
import creativess.weathercheck.networking.networkingModel.Wind

data class Current(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coord: Coord? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)