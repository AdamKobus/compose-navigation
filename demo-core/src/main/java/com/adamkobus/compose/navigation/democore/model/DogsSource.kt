package com.adamkobus.compose.navigation.democore.model

import com.adamkobus.compose.navigation.democore.data.DogInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class DogsSource @Inject constructor() {
    private val data = MutableStateFlow(createData())

    suspend fun observeDogs(): Flow<List<DogInfo>> {
        delay(MOCK_DELAY)
        return data
    }

    suspend fun getDog(id: Int): DogInfo? {
        delay(MOCK_DELAY)
        if (Random.nextBoolean()) {
            return data.value.find { it.id == id }
        } else {
            throw IllegalStateException("Failed to load cat info for id $id")
        }
    }

    private fun createData() =
        generateSequence(0) { it + 1 }.take(MOCKED_DATA_SIZE).map { DogInfo(it, "Dog $it") }.toList()

    companion object {
        private const val MOCK_DELAY = 1000L
        private const val MOCKED_DATA_SIZE = 20
    }
}
