package creativess.weathercheck.screens.home.uiElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import creativess.weathercheck.networking.networkingModel.threeHour.Forecast
import creativess.weathercheck.ui.theme.Gray2

@Composable
fun ThreeHourForecast(vm: CityWeatherViewModel) {
    val threeHour = vm.threeHour.value!!
    Column {
        Spacer(Modifier.height(12.dp))
        LazyRow(
            Modifier
                //            .background(Gray2, RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(Modifier.width(0.dp)) }
            items(16) {
                ThreeHourForecastItem(threeHour.list!![it])
            }
            item { Spacer(Modifier.width(0.dp)) }
        }
    }
}

@Composable
fun ThreeHourForecastItem(forecast: Forecast) {
    val temperature = forecast.main!!.temp!!.toInt().toString()
    val time = forecast.dt_txt!!.substring(11, 16)
    val iconUrl = "https://openweathermap.org/img/wn/${forecast.weather!![0].icon!!}@4x.png"

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .background(Gray2, RoundedCornerShape(12.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp)
        .clickable { }) {
        Text(time, fontSize = 14.sp)
        AsyncImage(
            model = iconUrl,
            contentDescription = "",
            modifier = Modifier.width(52.dp),
            contentScale = ContentScale.FillWidth
        )
        Row {
            Text(" $temperature°", fontSize = 20.sp)
        }
    }
}