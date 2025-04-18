package creativess.weathercheck.screens.home.uiElements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import creativess.weathercheck.ui.theme.Typography
import creativess.weathercheck.util.Units
import java.util.Locale

@Composable
fun Details(vm: CityWeatherViewModel) {
    val rain = vm.current.value!!.rain
    val snow = vm.current.value!!.snow
    val humidity = vm.current.value!!.main!!.humidity!!
    val visibility = vm.current.value!!.visibility!!
    val windSpeed = vm.current.value!!.wind!!.speed
    val windDirection = vm.current.value!!.wind!!.deg
    val clouds = vm.current.value!!.clouds!!.all!!
    val max_temp = vm.current.value!!.main!!.temp_max!!
    val min_temp = vm.current.value!!.main!!.temp_min!!
    val pressure_sea = vm.current.value!!.main!!.sea_level!!
    val pressure_ground = vm.current.value!!.main!!.grnd_level!!


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {

            }) {
            Spacer(Modifier.width(6.dp))
            Text("Show details", fontSize = 14.sp)
            Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "")
            Spacer(Modifier.width(0.dp))
        }
        if (true) Column {
            Spacer(Modifier.height(4.dp))
            DetailItem("rainfall", rain?.`1h`?.toString() ?: "no rainfall", null)
            DetailItem("snowfall", snow?.`1h`?.toString() ?: "no snowfall", null)
            DetailItem("wind",  "$windSpeed ${Units.getSpeedUnit(true)} $windDirection°", null)
            DetailItem("humidity", "$humidity%", null)
            DetailItem("visibility", "$visibility m", null)
            DetailItem("cloudiness", "${clouds}%", null)
            DetailItem("max temperature", "$max_temp°", null)
            DetailItem("min temperature", "$min_temp°", null)
            DetailItem("atmospheric press. (sea)", "$pressure_sea hPa", null)
            DetailItem("atmospheric press. (ground)", "$pressure_ground hPa", null)
        }

    }
}

@Composable
fun DetailItem(name: String, value: String, description: String?) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
        Text(
            "${name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}:",
            style = Typography.bodyMedium
        )
        Spacer(Modifier.width(6.dp))
        Text(value)
    }
}





