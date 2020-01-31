import com.app.infybankapp.application.AppClass
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Assert.assertThat
import org.hamcrest.core.Is.`is`

@RunWith(MockitoJUnitRunner::class)
class SummaryTestCase {
    @Test
    fun inputValidator_Valid_Inpute_ReturnsTrue() {


        val result = AppClass.myObjectSummaryTest.isValidate("Sagar Sonawane")

        assertThat(result, `is`(VALID_VAL))
    }

    companion object {

        val VALID_VAL = true
    }


    @Test
    fun inputValidator_EmptySummary_ReturnsFalse() {
        assertFalse(AppClass.myObjectSummaryTest.isValidate(""))
    }

}