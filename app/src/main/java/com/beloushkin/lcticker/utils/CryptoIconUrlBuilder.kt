package com.beloushkin.lcticker.utils

import android.graphics.Color
import java.net.URL

class CryptoIconUrl(
    val code: String,
    val style: Style,
    val size: Int,
    val color: Int?
) {
    var Url: String? = null
        get() = run {
            val colorString = if(color != null) "/${color.toJsHexColor()}" else ""
            return "$baseURL/api/${style.name}/${code.toLowerCase()}/$size$colorString"
        }
        private set

    val baseURL = URL("https://cryptoicons.org")

    private constructor(builder: Builder): this(builder.code, builder.style, builder.size, builder.color)

    class Builder {
        var code: String= "btc"
            private set
        var style: Style =
            Style.black
            private set
        var size: Int = 128
            private set
        var color: Int? = null
            private set

        fun code(value: String) = apply { this.code = value }
        fun style(value: Style) = apply { this.style = value }
        fun size(value: Int) = apply { this.size = value }
        fun color(value: Color) = apply { this.color = color }

        fun build() =
            CryptoIconUrl(this)
    }
}

enum class Style {
    black, white, color, icon
}

fun Int.toJsHexColor(): String {
    val hexColor = String.format("%06X", 0xFFFFFFFF.and(this.toLong()))
    return hexColor.substring(2) + hexColor.substring(0, 2)
}