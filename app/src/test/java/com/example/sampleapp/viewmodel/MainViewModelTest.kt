package com.example.sampleapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.util.MockTestUtil.mockArtistList
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistApiRepository
import com.example.sampleapp.util.LiveDataResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    //region constants

    //end region constants

    //region helper fields
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var sut: MainViewModel

    private val mArtistApiRepository = mockk<ArtistApiRepository>(relaxed = true)
    private val repositoriesLiveData = MutableLiveData<LiveDataResult<MutableList<Artist>>>()

    private val mDispatcher = Dispatchers.Unconfined
    // end region helper fields

    @Before
    fun setup() {
        sut = MainViewModel(mArtistApiRepository, repositoriesLiveData, mDispatcher, mDispatcher)
    }

    @Test
    fun `when view model fetches data then it should call the repository success`() = runBlocking {
        coEvery { mArtistApiRepository.getArtist() } returns mockArtistList()

        sut.fetchArtist().observeForever { }
        sut.fetchArtist()

        assertNotNull(sut.fetchArtist().value)
        assertEquals(sut.fetchArtist().value?.status, LiveDataResult.STATUS.SUCCESS)
    }

    @Test
    fun `when view model fetches data then it should call the repository error`() = runBlocking {
        coEvery { mArtistApiRepository.getArtist() } coAnswers { throw Exception(" No data fetch") }

        sut.fetchArtist().observeForever { }
        sut.fetchArtist()

        assertNotNull(sut.fetchArtist().value)
        assertEquals(sut.fetchArtist().value?.status, LiveDataResult.STATUS.ERROR)
    }

}