package com.app.infybankapp

import android.R
import android.app.Application
import android.content.Context
import com.app.infybankapp.login.viewmodel.LoginViewModel
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginActivityTest {

    @Mock
    lateinit var mMockContext: Context


    @Test
    fun readStringFromContext_LocalizedString() {

        mMockContext = mock(Context::class.java)

        val myObjectUnderTest = LoginViewModel()

        // ...when the string is returned from the object under test...
        val result = myObjectUnderTest.validateCredentials("abcd@abc.com", "12345678")

        // ...then the result should be the expected one.
        assertThat(result, `is`(FAKE_VAL))
    }

    companion object {

        private val FAKE_VAL = true
    }

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

}