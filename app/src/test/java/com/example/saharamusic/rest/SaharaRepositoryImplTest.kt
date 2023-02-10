package com.example.saharamusic.rest

import com.example.saharamusic.model.SongItem
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SaharaRepositoryImplTest {
    private lateinit var testObject: SaharaRepository
    private val term = "house"
    private val mockSaharaAPI: ItunesAPI = mockk(relaxed = true)

    @Before
    fun setUp() {
        testObject = SaharaRepositoryImpl(mockSaharaAPI)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `retrieve the list of songs, given a specific genre, should return a SUCCESS UIState`() =
        runTest{
            // ASSIGNMENT
            coEvery { mockSaharaAPI.getListByGenre(term) } returns mockk {
                every { isSuccessful } returns true
                every { body()?.results } returns listOf(mockk(), mockk())
            }
            val states = mutableListOf<UIState<SongResponse>>()

            // ACTION
            testObject.getListByGenre(term).collect(){
                states.add(it)
            }

            // ASSERTION
            assertThat(states).hasSize(2)
            assertThat(states.first()).isInstanceOf(UIState.LOADING::class.java)
            assertThat(states.last()).isInstanceOf(UIState.SUCCESS::class.java)
        }

}