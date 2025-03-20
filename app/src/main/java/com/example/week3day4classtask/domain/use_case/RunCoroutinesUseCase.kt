package com.example.week3day4classtask.domain.use_case


import com.example.week3day4classtask.domain.repository.CoroutineRepository

class RunCoroutinesUseCase(private val repository: CoroutineRepository) {
    suspend fun executeSequential() = repository.runSequentialExecution()
    suspend fun executeConcurrent() = repository.runConcurrentExecution()
}
