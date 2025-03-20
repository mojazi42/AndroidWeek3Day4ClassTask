package com.example.week3day4classtask.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week3day4classtask.domain.use_case.RunCoroutinesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val runCoroutinesUseCase: RunCoroutinesUseCase) : ViewModel() {
    private val _coroutineOutput = MutableStateFlow("Press the button to start execution")
    val coroutineOutput: StateFlow<String> = _coroutineOutput

    fun executeCoroutines() {
        viewModelScope.launch {
            _coroutineOutput.value = "Running Sequential Execution..."
            val sequentialResult = runCoroutinesUseCase.executeSequential()
            _coroutineOutput.value = sequentialResult

            _coroutineOutput.value = "Running Concurrent Execution..."
            val concurrentResult = runCoroutinesUseCase.executeConcurrent()
            _coroutineOutput.value = concurrentResult
        }
    }
}
