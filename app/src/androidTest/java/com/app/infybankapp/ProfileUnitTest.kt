import android.content.Context
import com.app.infybankapp.application.AppClass
import com.app.infybankapp.view.updatedata.ProfDetailAct
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Assert.assertThat
import org.hamcrest.core.Is.`is`

@RunWith(MockitoJUnitRunner::class)
class ProfileUnitTest {

    @Test
    fun inputValidator_Valid_Inpute_ReturnsTrue() {


        val result = AppClass.myObjectProfileTest.isValidate("Sagar Sonawane","ABC","2019", "2020")

        assertThat(result, `is`(VALID_VAL))
    }

    companion object {

        val VALID_VAL = true
    }


    @Test
    fun inputValidator_EmptyDesignation_ReturnsFalse() {
        assertFalse(AppClass.myObjectProfileTest.isValidate("","ABC","2019","2020"))
    }

    @Test
    fun inputValidator_NullInputCompany_ReturnsFalse() {
        assertFalse(AppClass.myObjectProfileTest.isValidate("sagar sonawane","","2019","2020"))
    }

    @Test
    fun inputValidator_NullInputStartYear_ReturnsFalse() {
        assertFalse(AppClass.myObjectProfileTest.isValidate("sagar sonawane","ABC","","2020"))
    }

    @Test
    fun inputValidator_NullInputEndYear_ReturnsFalse() {
        assertFalse(AppClass.myObjectProfileTest.isValidate("sagar sonawane","ABC","2019",""))
    }

    @Test
    fun inputValidator_NullInputAll_ReturnsFalse() {
        assertFalse(AppClass.myObjectProfileTest.isValidate("","","", ""))
    }
}