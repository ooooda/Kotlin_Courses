package jetbrains.kotlin.course.codenames.utils

import jetbrains.kotlin.course.codenames.keyCard.KeyCardCell

fun interface KeyCardGenerator {
    fun generateData(): List<KeyCardCell>
}