package creativess.weathercheck.util

import android.util.Log
import creativess.weathercheck.networking.ApiClient
import creativess.weathercheck.networking.networkingModel.current.Current
import creativess.weathercheck.networking.networkingModel.threeHour.ThreeHour

object DataRepository {
    suspend fun getCurrent(
        lat: Double, lon: Double, apiKey: String, lang: String, units: String
    ): Current? {
        try {
            val currentResponse = ApiClient.api.getCurrent(lat, lon, apiKey, lang, units)
            if (currentResponse.isSuccessful) {
                val current = currentResponse.body()
                Log.d("TAG_current", currentResponse.body().toString())
                return current!!
            } else {
                Log.d("TAG_current", currentResponse.message())
                return null
            }
        } catch (e: Exception) {
            Log.d("TAG_current", e.toString())
            return null
        }
    }

    suspend fun getThreeHour(
        lat: Double, lon: Double, apiKey: String, lang: String, units: String
    ): ThreeHour? {
        try {
            val threeHourResponse = ApiClient.api.getThreeHour(lat, lon, apiKey, lang, units)
            if (threeHourResponse.isSuccessful) {
                val threeHour = threeHourResponse.body()
                Log.d("TAG_three_hour", threeHourResponse.body().toString())
                return threeHour
            } else {
                Log.d("TAG_three_hour", threeHourResponse.message())
                return null
            }
        } catch (e: Exception) {
            Log.d("TAG_three_hour", e.toString())
            return null
        }    }
}