package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTests {
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter(viewContract)
    }

    @Test
    fun onIncrementPresenterTest() {
        presenter.onIncrement()
        verify(viewContract, atLeastOnce()).setCount(1)
    }

    @Test
    fun onDecrementPresenterTest() {
        presenter.onDecrement()
        verify(viewContract, atLeastOnce()).setCount(-1)
    }

    @Test
    fun detachView_Presenter_Test() {
        presenter.onDetach()
        val instance = presenter.javaClass
        instance.declaredFields.forEach {
            it.isAccessible = true
            if (it.toString() == "ViewContract") {
                Assert.assertNull(it)
            }
        }
    }

    @Test
    fun attachView_PresenterTest() {
        presenter.onAttach(viewContract)
        val instance = presenter.javaClass
        instance.declaredFields.forEach {
            it.isAccessible = true
            if (it.toString() == "ViewContract") {
                Assert.assertEquals(viewContract, it)
            }
        }
    }

}