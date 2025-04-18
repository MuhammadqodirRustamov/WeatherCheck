package creativess.weathercheck.screens.home.uiElements

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import creativess.weathercheck.R
import creativess.weathercheck.networking.networkingModel.WeatherData
import creativess.weathercheck.ui.theme.Gray25
import creativess.weathercheck.util.Units
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Primary(weatherData: WeatherData) {
    val cityName = vm.current.value!!.name!!
    val temp = vm.current.value!!.main!!.temp!!
    val feelsLike = vm.current.value!!.main!!.feels_like!!
    val weatherDescription = vm.current.value!!.weather!![0].description!!

    // TODO("Add custom icons")
    val iconUrl by remember {
        mutableStateOf(
            "https://openweathermap.org/img/wn/${vm.current.value!!.weather!![0].icon!!}@4x.png"
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        val localTime = Units.getCurrentTimeWithOffset(vm.current.value!!.timezone!!)

        Text("Local time: $localTime")
        AsyncImage(
            model = iconUrl,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(.3f),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.weather_icon),
            onError = { Log.d("TAG", it.result.throwable.message.toString()) }
        )
        Text(cityName, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1f)) { }
            Row {
                Text(temp.toInt().toString(), fontSize = 64.sp, fontWeight = FontWeight.Medium)
            }
            Column(Modifier.weight(1f)) {
                Text("°", fontSize = 32.sp)
                if (temp.toInt() != feelsLike.toInt()) Row(verticalAlignment = Alignment.Bottom) {
                    Icon(
                        painterResource(R.drawable.temperature_icon),
                        contentDescription = "",
                        Modifier.size(24.dp)
                    )
                    Text("${feelsLike.toInt()}°C", fontSize = 16.sp)
                }
                Spacer(Modifier.height(11.dp))
            }
        }
        Text(
            weatherDescription.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .background(Gray25, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}
