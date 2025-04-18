package creativess.weathercheck.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import creativess.weathercheck.util.DataRepository
import creativess.weathercheck.screens.home.HomeView
import creativess.weathercheck.screens.home.HomeViewModel
import creativess.weathercheck.screens.search.SearchView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val homeViewModel = HomeViewModel()
            HomeView(homeViewModel)
        }
        composable(Screen.Search.route) {
            SearchView()
        }
    }
}