package `in`.surajsau.genshinloader.androidx

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween

val animationProgress = FloatPropKey()

interface ValueAnimator {

    companion object {
        fun definition(
                duration: Int = 1_000
        ): TransitionDefinition<Progress> {
            return transitionDefinition {
                state(Progress.START) { this[animationProgress] = 0f }
                state(Progress.END) { this[animationProgress] = 1f }

                transition(Progress.START to Progress.END) {
                    animationProgress using tween(
                            durationMillis = duration
                    )
                }
            }
        }
    }
}

enum class Progress {
    START, END
}