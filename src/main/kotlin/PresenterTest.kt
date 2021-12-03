package presenter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PresenterTest
{
    lateinit var presenter:          PhotoPresenterContract
    lateinit var photoViewContract:  PhotoViewContract
    lateinit var repositoryContract: RepositoryContract

    @Captor
    lateinit var t: ArgumentCaptor<Throwable>


    @BeforeAll
    fun init()
    {
        t = ArgumentCaptor.forClass(Throwable::class.java)

        presenter = Presenter()

        photoViewContract = mock(PhotoViewContract::class.java)
        presenter.registerView(photoViewContract)

        repositoryContract = mock(RepositoryContract::class.java)
        presenter.registerRepository(repositoryContract)

        `when`(repositoryContract.getMoreData("hello")).thenReturn("world")

    }


    @Test
    fun testErrorData() {
        presenter.dataReady("error")
        verify(photoViewContract).showError(t.capture())
        assertEquals("ERROR", t.value.message)
        verifyNoMoreInteractions(photoViewContract)
    }

    @Test
    fun testNormalData() {
        presenter.dataReady("some data")
        verify(photoViewContract).newData("some data")
        verifyNoMoreInteractions(photoViewContract)
    }

    @Test
    fun testRepositoryInteraction() {
        presenter.getMoreData("hello")
        verify(repositoryContract).getMoreData("hello")
        verify(photoViewContract).newData("world:hello")
        verifyNoMoreInteractions(repositoryContract)
        verifyNoMoreInteractions(photoViewContract)
    }


}