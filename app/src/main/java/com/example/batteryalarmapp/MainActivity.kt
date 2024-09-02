package com.example.batteryalarmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.batteryalarmapp.ui.theme.BatteryAlarmAppTheme
import com.example.batteryalarmapp.ui.viewmodel.BatteryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryAlarmAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BatteryStatus()
                }
            }
        }
    }
}


@Composable
fun BatteryStatus(viewModel: BatteryViewModel = viewModel()) {
    val isBatteryHigh by viewModel.isBatteryHigh.observeAsState(true)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val image = if (isBatteryHigh) {
            painterResource(id = R.drawable.battery_full)
        } else {
            painterResource(id = R.drawable.battery_low)
        }
        Image(painter = image, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun BatteryStatusPreview() {
        BatteryStatus()
}