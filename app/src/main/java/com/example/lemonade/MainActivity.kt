package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    ChangeButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ChangeButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(1) }
    val stringResource = when (result) {
        1 -> R.string.select
        2 -> R.string.squeeze
        3 -> R.string.drink
        else -> R.string.again
    }
    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        when (result) {
            2 -> {
                Text(text = stringResource(stringResource))
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = result.toString(),

                    modifier = Modifier
                        .clickable { squeezeCount-- }
                        .border(
                            BorderStroke(2.dp, Color(105, 205, 216)),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(16.dp)
                )
                    if (squeezeCount == 0) {
                        result = 3
                    }
                }

            4 -> {
                Text(text = stringResource(stringResource))
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = result.toString(),

                    modifier = Modifier
                        .clickable {result = 1 }
                        .border(
                            BorderStroke(2.dp, Color(105, 205, 216)),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(16.dp)
                )

                }
            else -> {
                Text(text = stringResource(stringResource))
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = result.toString(),

                    modifier = Modifier
                        .clickable {result++}
                        .border(
                            BorderStroke(2.dp, Color(105, 205, 216)),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(16.dp)
                )
                squeezeCount = (2..4).random()
            }
        }
    }
}


