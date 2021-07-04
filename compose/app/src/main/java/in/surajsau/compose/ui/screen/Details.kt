package `in`.surajsau.compose.ui.screen

import `in`.surajsau.compose.R
import `in`.surajsau.compose.model.ScreenInformation
import `in`.surajsau.compose.ui.component.Fab
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Details(information: ScreenInformation) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        information.content()

        Fab(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            icon = R.drawable.ic_info,
            onClick = {}
        )
    }
}