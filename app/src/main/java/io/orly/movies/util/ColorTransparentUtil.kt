package io.orly.movies.util

import kotlin.math.roundToInt

object ColorTransparentUtil {

    private fun getTransparency(transparency: Int): String {
        val hexString = Integer.toHexString(
            (255F * transparency / 100).roundToInt()
        )
        return if (hexString.length < 2) {
            "0$hexString"
        } else {
            hexString
        }
    }

    fun convertIntoColor(color: String, trans: Int): String =
        color.substringAfterLast("#").let {
            "#${getTransparency(trans)}$it"
        }
}