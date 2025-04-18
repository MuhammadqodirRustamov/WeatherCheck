package creativess.weathercheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import creativess.weathercheck.navigation.AppNavigation
import creativess.weathercheck.ui.theme.WeatherCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response =
//                    ApiClient.api.getThreeHour(40.369487, 71.528777, "8bf6c3821d21b2918a85de8b3bfb4e01", "en", units = "metric")
//                val responseBody = response.body()
//                withContext(Dispatchers.Main) {
//                    if (responseBody != null) {
//                        Log.d("TAG", responseBody.toString())
//                    }
//                }
//            } catch (e: Exception) {
//                Log.d("TAG", e.toString())
//            }
//
//        }

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            WeatherCheckTheme {
                AppNavigation(navController)
            }
        }
    }
}

