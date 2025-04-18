package creativess.weathercheck.networking

import creativess.weathercheck.networking.networkingModel.current.Current
import creativess.weathercheck.networking.networkingModel.threeHour.ThreeHour
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrent(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APIkey") apiKey: String,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Response<Current>

    @GET("data/2.5/forecast")
    suspend fun getThreeHour(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APIkey") apiKey: String,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Response<ThreeHour>


}