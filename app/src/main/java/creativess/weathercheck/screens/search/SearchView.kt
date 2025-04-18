package creativess.weathercheck.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import creativess.weathercheck.ui.theme.Gray25
import creativess.weathercheck.ui.theme.SecondaryInfoColorLight
import creativess.weathercheck.ui.theme.Typography

@Composable
fun SearchView() {
    Scaffold(topBar = { SearchScreenTopBar() }) {
        Column(Modifier.padding(top = it.calculateTopPadding())) {
            Results()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenTopBar() {
    Row(Modifier.fillMaxWidth()) {
        SearchBar(
            query = "", onQueryChange = {}, onSearch = {}, active = false, onActiveChange = {},
            enabled = true,
            placeholder = { Text("Search for a city", style = Typography.bodyMedium) },
            leadingIcon = { Icon(Icons.Rounded.Search, "", tint = Gray25) },
            trailingIcon = { Icon(Icons.Rounded.Clear, "") },
            shape = RoundedCornerShape(12.dp),
            colors = SearchBarDefaults.colors(
                dividerColor = Color.Transparent,
                inputFieldColors = SearchBarDefaults.inputFieldColors()
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) { }
    }
}

@Composable
fun Results() {
    Column(Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Spacer(Modifier.height(4.dp))
        SearchResultItem("London, UK")
        SearchResultItem("Washington, USA")
        SearchResultItem("Moscow, Russia")
        SearchResultItem("Fergana, Uzbekistan")
        SearchResultItem("Tashkent, Uzbekistan")
        SearchResultItem("Seoul, South Korea")
        SearchResultItem("Sacramento, USA")
    }
}

@Composable
fun SearchResultItem(text: String) {
    Row {
        Text(text, fontSize = 15.sp, color = SecondaryInfoColorLight)
    }
}
