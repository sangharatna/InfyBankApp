import com.app.infybankapp.application.AppClass.Companion.myObjectUnderTest
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EducationUnitTest {


    @Test
    fun inputValidator_Valid_Inpute_ReturnsTrue() {


        val result = myObjectUnderTest.isValidate("Sagar Sonawane","80","2020")

        assertThat(result, `is`(VALID_VAL))
    }

    companion object {

         val VALID_VAL = true
    }


    @Test
    fun inputValidator_EmptyStringName_ReturnsFalse() {
        assertFalse(myObjectUnderTest.isValidate("","45","2020"))
    }

    @Test
    fun inputValidator_NullInputPercent_ReturnsFalse() {
        assertFalse(myObjectUnderTest.isValidate("sagar sonawane","","2020"))
    }

    @Test
    fun inputValidator_NullInputYear_ReturnsFalse() {
        assertFalse(myObjectUnderTest.isValidate("sagar sonawane","88",""))
    }

    @Test
    fun inputValidator_NullInputAll_ReturnsFalse() {
        assertFalse(myObjectUnderTest.isValidate("","",""))
    }

}