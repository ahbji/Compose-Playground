package `in`.surajsau.compose.ui.samples.creditcards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun CreditCardScreen() {

    CreditCardScreen(modifier = Modifier.fillMaxSize())
}

@Composable
private fun CreditCardScreen(
    modifier: Modifier = Modifier,
) {

    val creditCardInfo = remember { mutableStateOf(CreditCardInfo.Empty) }

    val scrollState = rememberScrollState()

    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {

        CreditCard(
            info = creditCardInfo.value,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {

            CreditCardNumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                number = creditCardInfo.value.number,
                onValueChanged = {
                    creditCardInfo.value = creditCardInfo.value.copy(number = it)
                },
                onValidValueEntered = { focusManager.moveFocus(FocusDirection.Down) }
            )

            CreditCardNameTextField(
                user = creditCardInfo.value.name,
                onValueChanged = {
                    creditCardInfo.value = creditCardInfo.value.copy(name = it)
                }
            )

            CreditCardExpiry(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                expiryDate = creditCardInfo.value.expiry,
                onMonthChanged = {
                    val expiry = creditCardInfo.value.expiry
                    creditCardInfo.value = creditCardInfo.value.copy(expiry = expiry.copy(month = it))
                },
                onYearChanged = {
                    val expiry = creditCardInfo.value.expiry
                    creditCardInfo.value = creditCardInfo.value.copy(expiry = expiry.copy(year = it))
                },
                onValidDateEntered = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            )
        }
    }
}

@Composable
private fun CreditCardNumberTextField(
    modifier: Modifier = Modifier,
    number: CreditCardNumber,
    onValueChanged: (CreditCardNumber) -> Unit,
    onValidValueEntered: () -> Unit,
) {

    OutlinedTextField(
        modifier = modifier,
        value = number.displayValue,
        onValueChange = {
            val newNumber = CreditCardNumber(it)
            onValueChanged(newNumber)

            if (newNumber.isValid) {
                onValidValueEntered()
            }
        },
        label = { Text(text = "Card Number") }
    )
}

@Composable
private fun CreditCardNameTextField(
    modifier: Modifier = Modifier,
    user: CreditCardUser,
    onValueChanged: (CreditCardUser) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier,
        value = user.name,
        onValueChange = { onValueChanged(CreditCardUser(it)) },
        label = { Text(text = "Card Number") }
    )
}

@Composable
private fun CreditCardExpiry(
    modifier: Modifier = Modifier,
    expiryDate: CreditCardExpiry,
    onMonthChanged: (CreditCardExpiry.Month) -> Unit,
    onYearChanged: (CreditCardExpiry.Year) -> Unit,
    onValidDateEntered: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Row(modifier = modifier) {

        OutlinedTextField(
            modifier = modifier,
            value = expiryDate.month.displayValue,
            onValueChange = {
                val newMonth = CreditCardExpiry.Month(it)
                when {
                    newMonth.isValid && expiryDate.year.isValid -> {
                        onMonthChanged(newMonth)
                        onValidDateEntered()
                    }

                    newMonth.isValid -> {
                        onMonthChanged(newMonth)
                        focusManager.moveFocus(FocusDirection.Right)
                    }

                    else -> { /* do nothing */ }
                }
            },
            label = { Text(text = "MM") }
        )

        OutlinedTextField(
            modifier = modifier,
            value = expiryDate.year.displayValue,
            onValueChange = {
                val newYear = CreditCardExpiry.Year(it)
                when {
                    newYear.isValid && expiryDate.month.isValid -> {
                        onYearChanged(newYear)
                        onValidDateEntered()
                    }

                    newYear.isValid -> {
                        onYearChanged(newYear)
                    }

                    else -> { /* do nothing */ }
                }
            },
            label = { Text(text = "YYYY") }
        )
    }

}