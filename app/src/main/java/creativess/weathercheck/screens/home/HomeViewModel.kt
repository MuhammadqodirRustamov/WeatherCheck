package creativess.weathercheck.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import creativess.weathercheck.networking.networkingModel.Coord
import creativess.weathercheck.networking.networkingModel.WeatherData
import creativess.weathercheck.networking.networkingModel.current.Current
import creativess.weathercheck.networking.networkingModel.threeHour.ThreeHour
import creativess.weathercheck.util.DataRepository

class HomeViewModel : ViewModel() {
    var weatherDataList by mutableStateOf<List<WeatherData>>(emptyList())
        private set


    fun getWeatherData() {
        val weatherDataList = listOf(
            WeatherData(current = Current(coord = Coord(1.00, 2.00)), ThreeHour()),
            WeatherData(current = Current(coord = Coord(2.00, 2.00)), ThreeHour()),
            WeatherData(current = Current(coord = Coord(3.00, 2.00)), ThreeHour()),
            WeatherData(current = Current(coord = Coord(4.00, 2.00)), ThreeHour()),
        )
    }

    suspend fun refresh(coord: Coord){
        val weatherData = makeRequest(coord)
        addWeatherDataToList(weatherData)
        saveWeatherDataToSharedPreferences(weatherData)
    }

    suspend fun makeRequest(coord: Coord): WeatherData {
        val current = DataRepository.getCurrent(coord.lat!!, coord.lon!!, getAPIKey(), getLanguage(), getUnits())
        val threeHour = DataRepository.getThreeHour(coord.lat, coord.lon, getAPIKey(), getLanguage(), getUnits())
        val weatherData = WeatherData(coord, current!!, threeHour!!)
        return weatherData
    }

    fun saveWeatherDataToSharedPreferences(weatherData: WeatherData) {
        //TODO("Not yet implemented")
    }

    fun addWeatherDataToList(weatherData: WeatherData) {
        val list = weatherDataList.toMutableList()
        list.forEachIndexed { index, it ->
            if (it.current.coord == weatherData.current.coord) {
                list[index] = weatherData
                weatherDataList = list
                return
            }
        }
    }

    fun getCityCoordinates(): List<Map<String, Double>> {
        return listOf(
            mapOf("lat" to 40.36, "lon" to 71.52),
            mapOf("lat" to 37.5482, "lon" to 127.0203),
            mapOf("lat" to 51.50, "lon" to 0.12),
            mapOf("lat" to 34.05, "lon" to -118.24)
        )

    }


    fun getAPIKey(): String {
        return "8bf6c3821d21b2918a85de8b3bfb4e01"
    }

    fun getLanguage(): String {
        return "en"
    }

    fun getUnits(): String {
        return "metric"
    }

}
