package creativess.weathercheck.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import creativess.weathercheck.networking.networkingModel.WeatherData
import creativess.weathercheck.screens.home.uiElements.Details
import creativess.weathercheck.screens.home.uiElements.Important
import creativess.weathercheck.screens.home.uiElements.Primary
import creativess.weathercheck.screens.home.uiElements.SunriseSunset
import creativess.weathercheck.screens.home.uiElements.ThreeHourForecast
import creativess.weathercheck.ui.theme.Gray2
import creativess.weathercheck.ui.theme.Gray25


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(vm: HomeViewModel) {
    val weatherDataList = vm.weatherDataList

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ), verticalArrangement = Arrangement.SpaceBetween
        ) {
            val pagerState = rememberPagerState { weatherDataList.size }
            CitySearchBar()
            HorizontalPager(pagerState, modifier = Modifier.weight(1f)) {
                CityWeather(weatherDataList[it])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = weatherDataList.size,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 6.dp, bottom = 0.dp)
            )
        }
    }
}

@Composable
fun CitySearchBar() {
    Row(Modifier
        .padding(horizontal = 12.dp, vertical = 6.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Gray2)
        .clickable { }
        .padding(8.dp)) {
        Icon(Icons.Rounded.Search, "", tint = Gray25)
        Text("Search", fontSize = 16.sp, color = Gray25)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CityWeather(weatherData: WeatherData) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Primary(weatherData)
//        Important(weatherData)
//        Details(weatherData)
//        ThreeHourForecast(weatherData)
//        SunriseSunset(weatherData)
    }
}




