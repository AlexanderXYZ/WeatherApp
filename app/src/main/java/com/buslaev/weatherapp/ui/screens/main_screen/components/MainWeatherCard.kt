package com.buslaev.weatherapp.ui.screens.main_screen.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.buslaev.weatherapp.domain.models.WeatherData
import com.buslaev.weatherapp.domain.models.WeatherUnits
import com.buslaev.weatherapp.ui.util.toTitleString
import com.buslaev.weatherapp.ui.util.weatherInterpretation
import java.time.LocalDateTime

@Composable
fun MainWeatherCard(
    currentWeather: WeatherData?,
    weatherUnits: WeatherUnits?,
    dayTemperatures: List<Float>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardTitle()

        Spacer(modifier = Modifier.height(16.dp))

        CardText(text = currentWeather?.temperature?.toInt().toString() + "", fontSize = 96)

        Spacer(modifier = Modifier.height(8.dp))

        CardText(text = currentWeather?.code?.weatherInterpretation() ?: "", fontSize = 16)

        Spacer(modifier = Modifier.height(16.dp))

        Parameters(
            data = currentWeather,
            modifier = Modifier.fillMaxWidth(),
            units = weatherUnits
        )

        Spacer(modifier = Modifier.height(16.dp))

        Temperature(
            dayTemperatures = dayTemperatures,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp),
            pinColor = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun CardTitle() {
    val dateTime by remember { mutableStateOf(LocalDateTime.now()) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CardText(text = "Novosibirsk", fontSize = 20)

        CardText(text = "Today ${dateTime.toTitleString()}", fontSize = 16)
    }
}

@Composable
private fun CardText(text: String, fontSize: Int) {
    Text(
        text = text,
        color = MaterialTheme.colors.onPrimary,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Parameters(data: WeatherData?, units: WeatherUnits?, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        ParameterItem(text = "${data?.pressureMsl}${units?.pressureMslUnit}")

        ParameterItem(text = "${data?.relativehumidity}${units?.relativehumidityUnit}")

        ParameterItem(text = "${data?.windspeed}${units?.windspeedUnit}")
    }
}

@Composable
private fun ParameterItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.Build,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        CardText(text = text, fontSize = 16)
    }
}

@Composable
private fun Temperature(
    dayTemperatures: List<Float>,
    modifier: Modifier = Modifier,
    pinColor: Color
) {
    val lowerTemp by remember { mutableStateOf(dayTemperatures.minOrNull()!! - 4F) }
    val maxTemp by remember { mutableStateOf(dayTemperatures.maxOrNull()!! + 4F) }
    Column(modifier = modifier) {
        CardText(text = "Temperature", fontSize = 18)
//        var tempOffsets by remember { mutableStateOf(listOf<Offset>()) }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            val height = size.height
            val width = size.width
            val tempHeight = height / (maxTemp - lowerTemp)
            var tempWidth = width / 4
            tempWidth -= tempWidth / 5

            val olsOffsets = mutableListOf<Offset>()

            dayTemperatures.forEachIndexed { index, temp ->
                val x = (index + 1) * tempWidth
                val y = height - ((temp - lowerTemp) * tempHeight)
                val offset = Offset(x = x, y = y)

                olsOffsets.add(offset)

                drawLine(
                    color = Color.White,
                    alpha = 0.3F,
                    start = Offset(x = x, y = 0F),
                    end = Offset(x = x, y = height),
                )
                drawCircle(
                    color = pinColor,
                    radius = 16F,
                    center = offset
                )
                drawCircle(
                    color = Color.White,
                    radius = 10F,
                    center = offset
                )
            }

            val path = Path().apply {
                moveTo(0F, height / 2)
                repeat(4) {
                    val x = olsOffsets[it].x
                    val y = olsOffsets[it].y

                    quadraticBezierTo(
                        x1 = x - tempWidth / 2,
                        y1 = y,
                        x2 = x,
                        y2 = y,
                    )
                }

                quadraticBezierTo(
                    x1 = width - tempWidth / 2,
                    y1 = height / 2,
                    x2 = width,
                    y2 = height / 2,
                )
            }

            drawPath(
                path = path,
                color = Color.White,
                alpha = 0.3F,
                style = Stroke(
                    width = 1.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
//            tempOffsets = olsOffsets
        }
//        var x by remember { mutableStateOf(0F) }
//        println("x = $x")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
//                .onGloballyPositioned {
//                    val parentSize = it.parentLayoutCoordinates?.size?.toSize() ?: Size.Zero
//                    var tempWidth = parentSize.width / 4
//                    tempWidth -= tempWidth / 5
//                    println("tempWidth = $tempWidth")
//                    x = tempWidth
//                }
        ) {
            dayTemperatures.forEachIndexed { i, temp ->
                TemperatureItem(text = i.toTextTime(), temp = temp)
            }
        }
    }
}

private fun Int.toTextTime(): String = when (this) {
    0 -> "Morning"
    1 -> "Afternoon"
    2 -> "Evening"
    3 -> "Night"
    else -> "Night"
}


@Composable
private fun TemperatureItem(
    text: String,
    temp: Float
) {
    Column(
        horizontalAlignment = CenterHorizontally,
    ) {
        CardText(text = text, fontSize = 12)
        Spacer(modifier = Modifier.height(8.dp))
        CardText(text = temp.toString(), fontSize = 12)
    }
}