package com.example.week3day4classtask.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.week3day4classtask.data.repository.CoroutineRepositoryImpl
import com.example.week3day4classtask.domain.use_case.RunCoroutinesUseCase
import com.example.week3day4classtask.presentation.vm.MainViewModel
import com.example.week3day4classtask.ui.theme.Week3Day4ClassTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineRepository = CoroutineRepositoryImpl()
            val runCoroutinesUseCase = RunCoroutinesUseCase(coroutineRepository)
            val mainViewModel = MainViewModel(runCoroutinesUseCase)

            Week3Day4ClassTaskTheme {
                CoroutineScreen(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoroutineScreen(mainViewModel: MainViewModel) {
    val coroutineOutput by mainViewModel.coroutineOutput.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Coroutine Execution") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(onClick = { mainViewModel.executeCoroutines() }) {
                Text("Run Coroutines")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = coroutineOutput,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoroutineScreen() {
    Week3Day4ClassTaskTheme {
        CoroutineScreen(MainViewModel(RunCoroutinesUseCase(CoroutineRepositoryImpl())))
    }
}
