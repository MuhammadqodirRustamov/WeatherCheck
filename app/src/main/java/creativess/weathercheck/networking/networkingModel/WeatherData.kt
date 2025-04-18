package creativess.weathercheck.networking.networkingModel

import creativess.weathercheck.networking.networkingModel.current.Current
import creativess.weathercheck.networking.networkingModel.threeHour.ThreeHour

data class WeatherData(
    val coord: Coord, val current: Current, val threeHour: ThreeHour
)