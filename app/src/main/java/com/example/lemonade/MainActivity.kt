package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface (modifier = Modifier.padding(top = 40.dp)){
                LemonadeTheme {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeImageAndButtons(modifier: Modifier = Modifier) {
    val squeezes = (1..4).random()
    var i = 0
    var step by remember { mutableStateOf(1) }
    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> {R.drawable.lemon_restart}
    }
    val textResource = when(step) {
        1 -> R.string.tap_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_lemonade
        else -> {R.string.tap_glass}
    }

    Row(modifier = modifier
        .background(Color.Yellow)
        .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center){
        Text(
            text = "Lemonade",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageResource), // Replace with your image resource
            contentDescription = "Clickable Image",
            modifier = Modifier.clickable{
                if (step == 2) {
                    if (i + step <= squeezes) {
                        i++
                    } else {
                        step++
                    }
                }else{
                    step++
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResource),
            style = TextStyle(
                fontSize = 18.sp
            ),
        )
        if (step > 4){
            step = 1
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeImageAndButtons()
}