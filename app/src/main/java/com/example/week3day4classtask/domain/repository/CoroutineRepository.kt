package com.example.week3day4classtask.domain.repository



interface CoroutineRepository {
    suspend fun runSequentialExecution(): String
    suspend fun runConcurrentExecution(): String
}

