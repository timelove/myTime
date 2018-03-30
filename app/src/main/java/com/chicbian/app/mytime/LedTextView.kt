package com.chicbian.app.mytime

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author chicbian
 * @data  2018/3/30 13:34
 * @description LedTextView
 */
class LedTextView: TextView {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        val assets = context.assets
        val font = Typeface.createFromAsset(assets,
                FONT_DIGITAL_7)
        typeface = font
    }

    companion object {

        private val FONTS_FOLDER = "fonts"
        private val FONT_DIGITAL_7 = ("digital-7.ttf")
    }
}