package `in`.surajsau.compose.ui.screen

import `in`.surajsau.compose.model.Samples
import `in`.surajsau.compose.model.ScreenInformation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navigateToDetails: (Int) -> Unit
) {

    val samples = remember { Samples }

    LazyColumn {
        itemsIndexed(samples) { index, sample ->
            SampleCard(info = sample, onClick = { navigateToDetails.invoke(index) })
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SampleCard(
    info: ScreenInformation,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = info.title,
                fontSize = 24.sp,
            )
            Text(
                text = info.author,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}