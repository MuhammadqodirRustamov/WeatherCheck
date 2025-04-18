package creativess.weathercheck.screens.home.uiElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import creativess.weathercheck.R
import creativess.weathercheck.ui.theme.Gray4
import creativess.weathercheck.ui.theme.SecondaryInfoColorLight
import creativess.weathercheck.util.ImportantThresholds
import creativess.weathercheck.util.Units

@Composable
fun Important(vm: CityWeatherViewModel) {
    val rain = vm.current.value!!.rain
    val snow = vm.current.value!!.snow
    val humidity = vm.current.value!!.main!!.humidity!!
    val visibility = vm.current.value!!.visibility!!
    val windSpeed = vm.current.value!!.wind!!.speed

    Column(Modifier.padding(horizontal = 12.dp)) {
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp * 4 * 0),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (ImportantThresholds.showRainSnow(rain, snow)) {
                1 -> ImportantItem(
                    R.drawable.rain_icon, rain!!.`1h`.toString(), Units.getPrecipitationUnit()
                )

                2 -> ImportantItem(
                    R.drawable.snow_icon, snow!!.`1h`.toString(), Units.getPrecipitationUnit()
                )
            }
            if (ImportantThresholds.showHumidity(humidity) > 0) ImportantItem(
                R.drawable.humidity_icon,
                "$humidity${Units.getHumidityUnit()}",
                when (ImportantThresholds.showHumidity(humidity)) {
                    1 -> "Low"
                    2 -> "High"
                    else -> "Very high"
                }
            )
            if (ImportantThresholds.showVisibility(visibility)) ImportantItem(
                R.drawable.visibility_icon, visibility.toString(), Units.getDistanceUnit()
            )
            if (ImportantThresholds.showWindSpeed(windSpeed)) ImportantItem(
                R.drawable.wind_icon, windSpeed!!.toInt().toString(), Units.getSpeedUnit(true)
            )
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
fun RowScope.ImportantItem(icon: Int, value: String, unit: String) {
    Column(
        Modifier
            .background(color = Gray4, RoundedCornerShape(12.dp))
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(16.dp))
        Icon(
            painterResource(icon),
            contentDescription = "",
            tint = SecondaryInfoColorLight,
            modifier = Modifier.size(42.dp)
        )
        Spacer(Modifier.height(6.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 16.sp, textAlign = TextAlign.Center, lineHeight = 12.sp)
            Text(unit, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 12.sp)
        }
        Spacer(Modifier.height(16.dp))
    }
}

