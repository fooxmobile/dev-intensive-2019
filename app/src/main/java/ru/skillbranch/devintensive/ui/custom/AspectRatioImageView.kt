package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import ru.skillbranch.devintensive.R

/*
 * Created by fooxer on 10.08.2020
 */

class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null,
    defaultStyleAttr: Int = 0
    )  : androidx.appcompat.widget.AppCompatImageView(context, attrSet, defaultStyleAttr) {

    companion object {
        private const val DEFAULT_ASPECT_RATIO = 1.78f //16:9

    }

    private var aspectRatio = DEFAULT_ASPECT_RATIO

    init{
        if(attrSet != null) {
            val typedArray = context.theme.obtainStyledAttributes(attrSet, R.styleable.AspectRatioImageView,0,0)
            aspectRatio = typedArray.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO)
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight = (measuredWidth/aspectRatio).toInt()
        setMeasuredDimension(measuredWidth, newHeight)
    }
}