package `in`.surajsau.compose.model

import `in`.surajsau.compose.ui.samples.genshin.GenshinScreen
import `in`.surajsau.compose.ui.samples.neomorph.NeomorphScreen
import `in`.surajsau.compose.ui.samples.parallax.ParallaxScreen

val Genshin = ScreenInformation(
    "Genshin Loading",
    "Mihoyo",
    "https://genshin.mihoyo.com/",
    { GenshinScreen() }
)

val NeomorphButton = ScreenInformation(
    "Neomorphic Button",
    "--",
    "--",
    { NeomorphScreen() }
)

val ParallaxCards = ScreenInformation(
    "Parallax Cards",
    "--",
    "--",
    { ParallaxScreen() }
)

val Samples = listOf(Genshin, NeomorphButton, ParallaxCards)