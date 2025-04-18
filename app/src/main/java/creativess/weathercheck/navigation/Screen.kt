package creativess.weathercheck.navigation

sealed class Screen(val route:String) {
    data object Home : Screen("home")
    data object Welcome : Screen("welcome")
    data object Search : Screen("welcome")
}