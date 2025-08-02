package com.example.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmaster.ui.theme.TaskMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            TaskMasterApp()
        }
    }
}


@Composable
fun TaskMasterApp() {
    var darkTheme by remember { mutableStateOf(false) }
    TaskMasterTheme(darkTheme = darkTheme) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBarOfTaskMaster({
                        darkTheme=!darkTheme
                })
            }) { innerPadding ->

            Box(modifier = Modifier.padding(innerPadding)) {
                Text("helloMasterop")
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOfTaskMaster(onToggleDarkTheme: () -> Unit = {}) {
    var alpha by remember { mutableStateOf(true) }

    val icon by remember {
        derivedStateOf {
            if (alpha) Icons.Default.AddCircle else Icons.Default.Done
        }
    }
    TopAppBar(title ={Text("Task Master App")},
        actions = {
            IconButton(onClick = { onToggleDarkTheme()
            alpha=!alpha}

            ) {
                Icon(icon,"Toggle Theme")
            }
        }
        )


}

