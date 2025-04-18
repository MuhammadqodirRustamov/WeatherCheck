package creativess.weathercheck.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASEURL = "https://api.openweathermap.org/"
    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}