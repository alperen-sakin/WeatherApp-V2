package com.example.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.WeatherState
import com.example.weatherapp.ui.theme.GradientIndicatorColor
import com.example.weatherapp.ui.theme.Poppins
import com.example.weatherapp.ui.theme.SelectedTabIndicatorColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForeCastSection(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    state: WeatherState
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = backgroundColor,
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp
        )

    ) {
        var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
        val titles = listOf(
            stringResource(R.string.hourly_forecast),
            stringResource(R.string.weekly_forecast)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            SecondaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                divider = {
                    HorizontalDivider(
                        color = Color.Black,
                        thickness = 3.dp
                    )
                },
                indicator = {
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(selectedTabIndex)
                            .height(3.dp)
                            .background(
                                brush = Brush.horizontalGradient(colors = GradientIndicatorColor)
                            )
                    )
                }

            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontFamily = Poppins,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        },
                        selectedContentColor = SelectedTabIndicatorColor,
                        unselectedContentColor = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SelectTab(selectedTabIndex, state)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun SelectTab(
    selectedTabIndex: Int,
    state: WeatherState
) {
    when (selectedTabIndex) {
        0 -> HourlyForecast(
            state = state
        )

        1 -> {
            Text(text = "Weekly Forecast")
        }
    }
}
