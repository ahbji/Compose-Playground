package `in`.surajsau.compose.model

import `in`.surajsau.compose.ui.samples.genshin.GenshinScreen
import `in`.surajsau.compose.ui.samples.neomorph.NeomorphScreen
import `in`.surajsau.compose.ui.samples.parallax.ParallaxScreen
import `in`.surajsau.compose.ui.samples.single.ExpandableTextScreen
import `in`.surajsau.compose.ui.samples.single.FeedbackScreen
import `in`.surajsau.compose.ui.samples.single.GoogleFontsAnimationScreen
import `in`.surajsau.compose.ui.samples.single.OlympicsGlobeScreen
import `in`.surajsau.compose.ui.samples.single.ReorderListScreen
import `in`.surajsau.compose.ui.samples.single.RotatingGlobeScreen
import `in`.surajsau.compose.ui.samples.single.MagnifyImageScreen
import `in`.surajsau.compose.ui.samples.single.ScrollableGraphScreen

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

val FeedbackSlider = ScreenInformation(
    "Feedback Slider",
    "--",
    "--",
    { FeedbackScreen() }
)

val RotatingGlobe = ScreenInformation(
    "Rotating Globe",
    "--",
    "--",
    { RotatingGlobeScreen() }
)

val OlympicsGlobe = ScreenInformation(
    "Olympics Drone Globe",
    "--",
    "--",
    { OlympicsGlobeScreen() }
)

val ReorderList = ScreenInformation(
    "Reorder List",
    "--",
    "--",
    { ReorderListScreen() }
)

val ExpandableText = ScreenInformation(
    "Expandable Text",
    "--",
    "--",
    { ExpandableTextScreen() }
)

val Zoomable = ScreenInformation(
    "Zoomable",
    "--",
    "--",
    { ScrollableGraphScreen() }
)

val Samples = listOf(
    ReorderList,
    Genshin, NeomorphButton, ParallaxCards,
    GoogleFontAnimation, FeedbackSlider, RotatingGlobe,
    OlympicsGlobe, ExpandableText, Zoomable
)
