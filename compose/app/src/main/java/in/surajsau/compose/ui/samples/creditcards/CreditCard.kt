package `in`.surajsau.compose.ui.samples.creditcards

import `in`.surajsau.compose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Month
import java.time.YearMonth

val CreditCardColors = listOf(
    CreditCardColor(Color(0xFF162447)),
    CreditCardColor(Color(0xFF1F4068)),
    CreditCardColor(Color(0xFF1B1B2F)),
    CreditCardColor(Color(0xFFE43F5A)),
    CreditCardColor(Color(0xFF382933)),
    CreditCardColor(Color(0xFF3B5249)),
    CreditCardColor(Color(0xFF519872)),
    CreditCardColor(Color(0xFFA4B494))
)

enum class CreditCardType(val iconId: Int) {
    MasterCard(R.drawable.ic_mastercard), Visa(R.drawable.ic_visa)
}

@JvmInline
value class CreditCardColor(val value: Color)

@JvmInline
value class CreditCardUser(val name: String)

data class CreditCardNumber(private val value: String) {

    private val chunks: List<String>
        get() {
            val missingLength = 16 - value.length
            return "$value${(0..missingLength).let { "*" }}"
                .chunked(4)
        }

    val isValid = value.length == 16

    val displayValue = ""

    val part1: String = chunks[0]
    val part2: String = chunks[1]
    val part3: String = chunks[2]
    val part4: String = chunks[3]
}

data class CreditCardExpiry(val month: Month, val year: Year) {

    data class Month(private val value: String) {

        val isValid = value.length == 2
        val displayValue = value
    }

    data class Year(private val value: String) {

        val isValid = value.length == 4
        val displayValue = value
    }
}

data class CreditCardInfo(
    val type: CreditCardType,
    val name: CreditCardUser,
    val number: CreditCardNumber,
    val expiry: CreditCardExpiry,
    val color: CreditCardColor,
) {
    companion object {
        val Empty = CreditCardInfo(
            type = CreditCardType.Visa,
            name = CreditCardUser(""),
            number = CreditCardNumber(""),
            expiry = CreditCardExpiry(CreditCardExpiry.Month(""), CreditCardExpiry.Year("")),
            color = CreditCardColors[(0..CreditCardColors.size).random()]
        )
    }
}

@Composable
fun CreditCard(
    info: CreditCardInfo,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(240.dp)
            .background(color = info.color.value, shape = RoundedCornerShape(8.dp))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cc_chip),
                contentDescription = "chip",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 32.dp, top = 16.dp)
                    .height(50.dp)
            )

            key(keys = arrayOf(info.type)) {
                Image(
                    painter = painterResource(id = info.type.iconId),
                    contentDescription = info.type.name,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 16.dp)
                        .height(50.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {

            key(keys = arrayOf(info.number.part1)) {
                Text(
                    text = info.number.part1,
                    modifier = Modifier,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(width = 16.dp))

            key(keys = arrayOf(info.number.part2)) {
                Text(
                    text = info.number.part2,
                    modifier = Modifier,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(width = 16.dp))

            key(keys = arrayOf(info.number.part3)) {
                Text(
                    text = info.number.part3,
                    modifier = Modifier,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(width = 16.dp))

            key(keys = arrayOf(info.number.part4)) {
                Text(
                    text = info.number.part4,
                    modifier = Modifier,
                    fontSize = 20.sp
                )
            }
        }
    }

}