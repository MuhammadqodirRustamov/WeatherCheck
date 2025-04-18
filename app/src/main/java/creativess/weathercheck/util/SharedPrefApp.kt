package creativess.weathercheck.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefApp private constructor(context: Context) {

    companion object {
        private var instance: SharedPrefApp? = null

        fun getInstance(context: Context): SharedPrefApp {
            if (instance == null) instance = SharedPrefApp(context)
            return instance!!
        }
    }

    private val sharedPrefNameKey = "app_prefs"
    private val isFirstLaunchKey = "is_first_launch"

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(sharedPrefNameKey, Context.MODE_PRIVATE)
    }
    private var isFirstLaunched: Boolean
        get() = sharedPref.getBoolean(isFirstLaunchKey, true)
        set(value) = sharedPref.edit().putBoolean(isFirstLaunchKey, value).apply()

    fun isFirstLaunch(): Boolean {
        return isFirstLaunched
    }

    fun setFirstLaunchFalse() {
        isFirstLaunched = false
    }
    //TODO("Add city coordinates")
    fun getCityCoordinates(): List<Map<String, Double>> {
        return listOf(
            mapOf("lat" to 40.36, "lon" to 71.52),
            mapOf("lat" to 40.42, "lon" to 71.50),
            mapOf("lat" to 40.43, "lon" to 71.53)
        )
    }
}

