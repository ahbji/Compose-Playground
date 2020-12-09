package `in`.surajsau.compose.screens

import `in`.surajsau.compose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reference: [https://dribbble.com/shots/3898209-iPhone-X-Social-App]
*/

@Composable
fun SocialApp() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val(bottomAppBar, image, infoCard) = createRefs()

        Image(
            bitmap = imageResource(id = R.drawable.velkhana),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Box(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(topLeft = 24.dp, topRight = 24.dp))
                .background(color = Color(0xFFD1D1D1))
                .constrainAs(infoCard) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier) {
                        Text(text = "Title")
                        Text(text = "Subtitle")
                    }
                    
                    Button(
                        onClick = {},
                        colors = ButtonConstants.defaultButtonColors(
                            backgroundColor = Color.Red
                        ),
                        modifier = Modifier.height(height = 24.dp)
                            .width(width = 24.dp),
                        shape = RoundedCornerShape(size = 12.dp)
                    ) {
                        Box(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                            Image(bitmap = imageResource(id = R.drawable.geo),
                                modifier = Modifier.size(size = 16.dp)
                                    .padding(all = 8.dp)
                            )
                        }
                    }
                }

                Text(text = "Profile description goes here")
            }
        }

        BottomAppBar(
            modifier = Modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(topLeft = 24.dp, topRight = 24.dp))
                .constrainAs(bottomAppBar) {
                    bottom.linkTo(parent.bottom)
                },
            backgroundColor = Color.Black,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf(
                    R.drawable.anemo,
                    R.drawable.cryo,
                    R.drawable.dendro,
                    R.drawable.cryo,
                    R.drawable.electro
                ).forEach {
                    Button(
                        onClick = {},
                        elevation = ButtonConstants.defaultElevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
                        colors = ButtonConstants.defaultButtonColors(backgroundColor = Color.Transparent)
                    ) {
                        Image(bitmap = imageResource(id = it),
                            modifier = Modifier.size(size = 24.dp),
                            colorFilter = ColorFilter.tint(color = Color.LightGray)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxWidth()
        .clip(shape = RoundedCornerShape(
            topLeft = 8.dp,
            topRight = 8.dp
        ))
        .background(color = Color.White)
    )
}

@Composable
@Preview
fun PreviewSocialApp() {
    SocialApp()
}
