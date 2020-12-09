package `in`.surajsau.compose.androidx

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.toggle() {
    this.value = !this.value
}