import android.content.Context
import com.app.infybankapp.LoginActivityTest
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.view.updatedata.UpdatePIAct
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertFalse

@RunWith(MockitoJUnitRunner::class)
class PIUnitTest {

    @Test
    fun inputValidator_Valid_Inpute_ReturnsTrue() {


        val result = AppClass.myObjectPITest.isValidate("Sagar Sonawane","8569874123","Pune")

        assertThat(result, `is`(VALID_VAL))
    }

    companion object {

        val VALID_VAL = true
    }


    @Test
    fun inputValidator_EmptyStringName_ReturnsFalse() {
        assertFalse(AppClass.myObjectPITest.isValidate("","4523547896","Pune"))
    }

    @Test
    fun inputValidator_NullInputMobile_ReturnsFalse() {
        assertFalse(AppClass.myObjectPITest.isValidate("sagar sonawane","","Pune"))
    }

    @Test
    fun inputValidator_NullInputAddress_ReturnsFalse() {
        assertFalse(AppClass.myObjectPITest.isValidate("sagar sonawane","8845972356",""))
    }

    @Test
    fun inputValidator_NullInputAll_ReturnsFalse() {
        assertFalse(AppClass.myObjectPITest.isValidate("","",""))
    }
}