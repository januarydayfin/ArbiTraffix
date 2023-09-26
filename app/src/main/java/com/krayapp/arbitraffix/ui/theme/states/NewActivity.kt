package com.krayapp.arbitraffix.ui.theme.states

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krayapp.arbitraffix.ui.theme.ArbiTraffixTheme

class NewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArbiTraffixTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun WaterCounter(modifier: Modifier = Modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            var count by rememberSaveable { mutableStateOf(0) }
            if (count > 0) {
                Text("You've had $count glasses.")
            }
            Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text("Add one")
            }
        }
    }

    @Preview
    @Composable
    fun MainScreen(
        newViewModel: NewViewModel = viewModel()
    ) {

        Column(
            Modifier
                .fillMaxSize()
        ) {
            WaterCounter()
            WellnessTasksList(
                newViewModel.tasks,
                onTaskChanged = { task, checked -> newViewModel.changeTaskChecked(task, checked) },
                onCloseTask = { task -> newViewModel.remove(task) })
        }
    }


    @Composable
    fun WellnessTasksList(
        list: List<WellnessTask>,
        modifier: Modifier = Modifier,
        onTaskChanged: (WellnessTask, Boolean) -> Unit,
        onCloseTask: (WellnessTask) -> Unit
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(list) { task ->
                WellnessTaskItem(
                    taskName = task.label,
                    checked = task.checked,
                    onCheckedChange = { checked -> onTaskChanged(task, checked) },
                    onClose = { onCloseTask(task) }
                )
            }
        }
    }

    @Composable
    fun WellnessTaskItem(
        taskName: String,
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        onClose: () -> Unit,
        modifier: Modifier = Modifier
    ) {

        Row(
            modifier = modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = taskName
            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    }
}

