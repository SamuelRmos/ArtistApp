package com.example.sampleapp.repository

import com.example.sampleapp.base.BaseTest
import com.example.sampleapp.util.MockTestUtil.mockArtistList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ArtistApiRepositoryTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields
    private lateinit var sut: ArtistApiRepository
    private val mArtistDataRepository = mockk<ArtistDataRepository>(relaxed = true)
    // end region helper fields


    @Before
    fun setup() {
        super.setUp()
    }

    @Test
    fun `when fetch data then call api network`() = runBlocking {

        mockNetworkResponseWithFileContent("moviePosters", HttpURLConnection.HTTP_OK)

        coEvery { mArtistDataRepository.getListArtist() } returns mutableListOf()
        sut = ArtistApiRepository(createApi(), mArtistDataRepository)

        val dataReceived = sut.getArtist()
        val data = mockArtistList()

        assertNotNull(dataReceived)
        assertEquals(dataReceived!![2].name, data[0].name)
        assertEquals(dataReceived[2].id, data[0].id)

    }

    @Test
    fun `when fetch data then call database repository `() = runBlocking {

        every { mArtistDataRepository.getListArtist() } returns mockArtistList()
        sut = ArtistApiRepository(createApi(), mArtistDataRepository)

        val dataReceived = sut.getArtist()
        val data = mockArtistList()

        assertNotNull(dataReceived)
        assertEquals(dataReceived!![0].name, data[0].name)
        assertEquals(dataReceived[0].id, data[0].id)

    }
    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}