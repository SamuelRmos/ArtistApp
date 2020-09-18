@file:Suppress("DEPRECATION")

package com.example.sampleapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.util.MockTestUtil.mockArtistList
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository
import com.example.sampleapp.util.LiveDataResult
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    //region constants

    //end region constants

    //region helper fields
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var sut: FavoriteViewModel

    private val mArtistDataRepository = mockk<ArtistDataRepository>(relaxed = true)
    private val favorite = mutableListOf<Artist>()
    private val repositoriesLiveData = MutableLiveData<LiveDataResult<MutableList<Artist>>>()
    // end region helper fields

    @Before
    fun setup() {
        sut = FavoriteViewModel(mArtistDataRepository, repositoriesLiveData, favorite)
    }

    @Test
    fun `when view model fetches data then it should call repository success`() {
        every { mArtistDataRepository.getListArtist() } returns mockArtistList()

        sut.artistFavorites().observeForever { }
        sut.artistFavorites()

        assertNotNull(sut.artistFavorites().value)
        assertEquals(sut.artistFavorites().value?.status, LiveDataResult.STATUS.SUCCESS)
    }

    @Test
    fun `when view model fetches data then it should call the repository error`() {
        every { mArtistDataRepository.getListArtist() } answers { throw Exception(" No data fetch") }

        sut.artistFavorites().observeForever { }
        sut.artistFavorites()

        assertNotNull(sut.artistFavorites().value)
        assertEquals(sut.artistFavorites().value?.status, LiveDataResult.STATUS.ERROR)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}