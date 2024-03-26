package com.example.bullseye.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.R
import com.example.bullseye.ui.theme.BullseyeTheme
import com.example.bullseye.ui.theme.components.GameDetail
import com.example.bullseye.ui.theme.components.GamePrompt
import com.example.bullseye.ui.theme.components.ResultDialog
import com.example.bullseye.ui.theme.components.TargetSlider
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun GameScreen(
    onNavigateToAbout: () -> Unit
) {
    fun newTargetValue() = Random.nextInt(1,100)

    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var slideValue by rememberSaveable { mutableStateOf(0.5f) }
    var targetValue by rememberSaveable { mutableStateOf(newTargetValue()) }

    val sliderToInt = (slideValue * 100).toInt()

    var totalScore by rememberSaveable { mutableStateOf(0) }
    var currentRound by rememberSaveable { mutableStateOf(1) }

    fun differenceAmount() = abs(targetValue - sliderToInt)


    fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference = differenceAmount()
        var bonus = 0

        if (difference == 0) {
            bonus = 100
        } else if (difference == 1) {
            bonus = 50
        }
        return (maxScore - difference) + bonus
    }

    fun startNewGame() {
        totalScore = 0
        currentRound = 1
        slideValue = 0.5f
        targetValue = newTargetValue()
    }

    fun alertTitle() : Int {
        val difference = abs(targetValue - sliderToInt)

        val title : Int = if (difference == 0) {
            R.string.alert_title_1
        } else if (difference < 5) {
            R.string.alert_title_2
        } else if (difference <= 10) {
            R.string.alert_title_3
        } else {
            R.string.alert_title_4
        }
        return title
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.target__2_),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.background_image),
            colorFilter = ColorFilter.tint(Color(0xCC8F8F8F))
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(9f)

            ) {
                GamePrompt(
                    targetValue = targetValue
                )
                TargetSlider(
                    value = slideValue,
                    valueChanged = { value ->
                        slideValue = value
                    }
                )
                Button(
                    onClick = {
                        alertIsVisible = true
                        totalScore += pointsForCurrentRound()
                        Log.i("ALERT VISIBLE? " , alertIsVisible.toString())
                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = stringResource(R.string.hit_me_button_text))
                }
                GameDetail(
                    totalScore = totalScore,
                    round = currentRound,
                    onStartOver = { startNewGame() },
                    onNavigateToAbout = onNavigateToAbout,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.weight(.5f))

            if (alertIsVisible) {
                ResultDialog(
                    dialogTitle  = alertTitle(),
                    hideDialog = { alertIsVisible = false},
                    sliderValue = sliderToInt,
                    points = pointsForCurrentRound(),
                    onRoundIncrement = {
                        currentRound += 1
                        targetValue = newTargetValue()
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun GameScreenPreview() {
    BullseyeTheme {
        GameScreen( onNavigateToAbout = {})
    }
}