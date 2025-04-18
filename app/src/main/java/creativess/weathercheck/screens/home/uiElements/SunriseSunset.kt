package creativess.weathercheck.screens.home.uiElements

import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import creativess.weathercheck.ui.theme.Gray10
import creativess.weathercheck.ui.theme.Gray2
import creativess.weathercheck.ui.theme.PrimaryInfoColorLight
import creativess.weathercheck.ui.theme.SecondaryInfoColorLight
import creativess.weathercheck.ui.theme.SunriseSunsetDay
import creativess.weathercheck.ui.theme.SunriseSunsetNight
import creativess.weathercheck.ui.theme.Typography
import creativess.weathercheck.util.Units

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SunriseSunset(vm: CityWeatherViewModel) {
    val sunriseTime =
        Units.unixToTimeWithShift(vm.current.value!!.timezone!!, vm.current.value!!.sys!!.sunrise!!)
            .takeLast(5)
    val sunsetTime =
        Units.unixToTimeWithShift(vm.current.value!!.timezone!!, vm.current.value!!.sys!!.sunset!!)
            .takeLast(5)

    val currentTime = Units.getCurrentTimeWithOffset(vm.current.value!!.timezone!!)

    Column {
        Spacer(Modifier.height(12.dp))
        Column(
            Modifier
                .padding(horizontal = 12.dp)
                .background(Gray2, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {

            Graph(sunriseTime, sunsetTime)
            Spacer(Modifier.height(6.dp))
            SunriseSunsetMore(sunriseTime, sunsetTime, currentTime, vm.current.value!!.name!!)

        }
    }
}

@Composable
fun SunriseSunsetMore(sunriseTime: String, sunsetTime: String, currentTime: String, name: String) {
    val lengthOfDayText = "Length of day:"
    val remainingDayLightText = "Remaining daylight:"

    val lengthOfDay = Units.timeDifference(sunriseTime, sunsetTime)
    val lengthOfDayString = "${lengthOfDay[0]}h ${lengthOfDay[1]}m"
    val remainingDaylight = Units.timeDifference(currentTime.takeLast(8).take(5), sunsetTime)
    val remainingDaylightString = "${remainingDaylight[0]}h ${remainingDaylight[1]}m"
    val isDayTime =
        lengthOfDay[0] * 60 + lengthOfDay[1] > remainingDaylight[0] * 60 + remainingDaylight[1]

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        SunriseSunsetMoreItem(lengthOfDayText, lengthOfDayString)
        if (remainingDaylight[1] > -1 && isDayTime) SunriseSunsetMoreItem(
            remainingDayLightText, remainingDaylightString
        )
    }
}

@Composable
fun SunriseSunsetMoreItem(name: String, value: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(name, style = Typography.bodyMedium)
        Spacer(Modifier.width(4.dp))
        Text(value)
    }
}

@Composable
fun Graph(sunriseTime: String, sunsetTime: String) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val width = size.width
        val height = size.height

        val horizonY = height * 0.75f

        val sunrisePoint = 1 / 5.toFloat()
        val sunsetPoint = 4 / 5.toFloat()

        val sunriseX = width * sunrisePoint
        val sunsetX = width * sunsetPoint


        // NIGHT SECTIONS
        drawPath(
            path = Path().apply {
                moveTo(0f, horizonY)
                quadraticTo(
                    sunriseX / 2,
                    height + (height - horizonY),  // Control point for a smooth transition
                    sunriseX,
                    horizonY
                )
                close()
            }, color = SunriseSunsetNight
        )

        // DAY SECTION
        drawPath(
            path = Path().apply {
                moveTo(sunriseX, horizonY)
                quadraticTo(
                    (sunsetX + sunriseX) / 2, -height / 3,  // Peak of the arc
                    sunsetX, horizonY
                )

                close()
            }, color = SunriseSunsetDay
        )

//         SUNRISE & SUNSET LINES
        drawLine(
            color = Gray10,
            start = Offset(sunriseX, horizonY / 2 - 20),
            end = Offset(sunriseX, horizonY),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 5f), // Dash length, gap length
                phase = 0f // Start of the pattern
            )

        )
        drawLine(
            color = Gray10,
            start = Offset(sunsetX, horizonY / 2 - 20),
            end = Offset(sunsetX, horizonY),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 5f), // Dash length, gap length
                phase = 0f // Start of the pattern
            )
        )
        drawLine(
            color = Gray10,
            start = Offset(0f, horizonY),
            end = Offset(width, horizonY),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(10f, 5f), // Dash length, gap length
                phase = 0f // Start of the pattern
            )
        )

        // SUNRISE & SUNSET TEXT
        val timeColor = PrimaryInfoColorLight.toArgb()

        val paint = Paint().apply {
            color = SecondaryInfoColorLight.toArgb()
            textSize = 40f
            isAntiAlias = true
        }
        val paintTime = Paint().apply {
            color = timeColor
            textSize = 45f
            isAntiAlias = true
            isFakeBoldText = true
        }

        val horizonText = "Horizon"

        val sunriseText = "Sunrise"
        val sunsetText = "Sunset"

        val sunriseWidth = paint.measureText(sunriseText)
        val sunsetWidth = paint.measureText(sunsetText)

        val sunriseTimeLength = paintTime.measureText(sunriseTime)
        val sunsetTimeLength = paintTime.measureText(sunsetTime)

        drawIntoCanvas { canvas ->
            //Sunrise
            canvas.nativeCanvas.drawText(
                sunriseText, sunriseX - sunriseWidth / 2, 35f,  // Adjust position
                paint
            )
            //Sunrise time
            canvas.nativeCanvas.drawText(
                sunriseTime, sunriseX - sunriseTimeLength / 2, 85f,  // Adjust position
                paintTime
            )
            // Sunset
            canvas.nativeCanvas.drawText(
                sunsetText, sunsetX - sunsetWidth / 2, 35f,  // Adjust position dynamically
                paint
            )
            // Sunset time
            canvas.nativeCanvas.drawText(
                sunsetTime, sunsetX - sunsetTimeLength / 2, 85f,  // Adjust position dynamically
                paintTime
            )
            // Horizon
            canvas.nativeCanvas.drawText(
                horizonText, sunsetX + 30f, horizonY - 10f,  // Adjust position dynamically
                paint
            )
        }
    }
}

