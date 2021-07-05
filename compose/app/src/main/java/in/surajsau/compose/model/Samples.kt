package `in`.surajsau.compose.model

import `in`.surajsau.compose.ui.samples.genshin.GenshinScreen
import `in`.surajsau.compose.ui.samples.neomorph.NeomorphScreen
import `in`.surajsau.compose.ui.samples.parallax.ParallaxScreen
import `in`.surajsau.compose.ui.samples.single.GoogleFonts
import `in`.surajsau.compose.ui.samples.single.GoogleFontsAnimationScreen

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

val GoogleFontAnimation = ScreenInformation(
    "Google Font Animation",
    "Adam Grabowski",
    "https://dribbble.com/shots/2779833-Google-Fonts",
    { GoogleFontsAnimationScreen() }
)

val Samples = listOf(Genshin, NeomorphButton, ParallaxCards, GoogleFontAnimation)