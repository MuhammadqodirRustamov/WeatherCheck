package creativess.weathercheck.networking.networkingModel.threeHour

data class ThreeHour(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<Forecast>? = null,
    val message: Int? = null
)