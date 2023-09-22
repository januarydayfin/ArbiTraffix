package com.krayapp.arbitraffix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.krayapp.arbitraffix.ui.theme.ArbiTraffixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArbiTraffixTheme {
                myApp()
            }
        }
    }
}


@Composable
fun myApp() {
    val shouldShowOnboarding = rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding.value)
        OnboardingScreen { shouldShowOnboarding.value = !shouldShowOnboarding.value }
    else
        LazyColumn {
            itemsIndexed(getList()) { index, item ->
                CardContent(name = item)
            }
        }

}

private fun getList(): ArrayList<String> {
    val list = ArrayList<String>()

    for (i in 0..200) {
        list.add("Здарова $i")
    }
    return list
}

@Composable
fun OnboardingScreen(onClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onClicked() }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(name: String) {
    CardContent(name)
}


@Composable
fun CardContent(name: String) {
    val expandedState = remember { mutableStateOf(false) }

    Surface(Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {

        Row(
            modifier = Modifier
                .background(Color.Cyan, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Hello \n$name",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            Font(
                                R.font.manrope_bold
                            )
                        )
                    ),
                    modifier = Modifier.padding(start = 20.dp),
                )
                if (expandedState.value)
                    Text(text = "СерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерегаСерегасерега")

            }
            IconButton(
                onClick = { expandedState.value = !expandedState.value }
            ) {
                Icon(
                    imageVector = if (expandedState.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expandedState.value) "Show less" else "Show more"
                )
            }
        }
    }
}