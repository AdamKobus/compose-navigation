package com.adamkobus.compose.navigation.democore.model

import com.adamkobus.compose.navigation.democore.data.CatInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CatsSource @Inject constructor() {
    private val data = MutableStateFlow(createData())

    suspend fun observeCats(): Flow<List<CatInfo>> {
        delay(MOCK_DELAY)
        return data
    }

    suspend fun getCat(id: Int): CatInfo {
        delay(MOCK_DELAY)
        if (Random.nextBoolean()) {
            return data.value.find { it.id == id } ?: throw IllegalArgumentException("Cat with id $id does not exist")
        } else {
            throw IllegalArgumentException("Failed to load cat info for id $id")
        }
    }

    private fun createData() =
        generateSequence(0) { it + 1 }.take(MOCKED_DATA_SIZE).map { CatInfo(it, "Cat $it") }.toList()

    companion object {
        private const val MOCK_DELAY = 500L
        private const val MOCKED_DATA_SIZE = 20
    }
}
