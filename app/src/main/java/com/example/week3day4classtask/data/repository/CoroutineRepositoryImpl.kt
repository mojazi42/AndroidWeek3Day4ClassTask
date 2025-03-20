package com.example.week3day4classtask.data.repository

import android.util.Log
import com.example.week3day4classtask.domain.repository.CoroutineRepository
import kotlinx.coroutines.*

class CoroutineRepositoryImpl : CoroutineRepository {
    override suspend fun runSequentialExecution(): String {
        Log.d("Coroutines", "Sequential Execution Started")

        val result = buildString {
            append(taskOne()).append("\n")
            append(taskTwo()).append("\n")
            append(taskThree())
        }

        Log.d("Coroutines", "Sequential Execution Finished")
        return result
    }

    override suspend fun runConcurrentExecution(): String {
        Log.d("Coroutines", "Concurrent Execution Started")

        val result = coroutineScope {
            val one = async { taskOne() }
            val two = async { taskTwo() }
            val three = async { taskThree() }
            listOf(one.await(), two.await(), three.await()).joinToString("\n")
        }

        Log.d("Coroutines", "Concurrent Execution Finished")
        return result
    }

    private suspend fun taskOne(): String {
        delay(1000)
        return "Task One Completed"
    }

    private suspend fun taskTwo(): String {
        delay(2000)
        return "Task Two Completed"
    }

    private suspend fun taskThree(): String {
        delay(500)
        return "Task Three Completed"
    }
}
