package com.easyang.common.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import com.easyang.common.R
import com.easyang.common.ktx.textwatch.textWatch

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */
class RoundTextView : TextView {
    var mRoundDrawable: RoundDrawable

    constructor(
        context: Context
    ) : this(context, null)

    constructor(
        context: Context, attrs: AttributeSet? = null
    ) : this(context, attrs, 0)

    constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr) {
        mRoundDrawable = RoundViewHelper.fromAttributeSet(context, attrs, R.styleable.RoundView)
        RoundViewHelper.setBackgroundKeepingPadding(this, mRoundDrawable)
    }

    fun getRoundDrawable(): RoundDrawable {
        return mRoundDrawable
    }

    fun setRadius(radius: Float) {
        mRoundDrawable.cornerRadius = radius
        RoundViewHelper.setBackground(this, mRoundDrawable)
    }

    fun setStroke(width: Int, @Nullable colors: ColorStateList?) {
        mRoundDrawable.setStrokeData(width, colors)
        RoundViewHelper.setBackground(this, mRoundDrawable)

    }

    fun setRadius(radiusTL: Float, radiusTR: Float, radiusBL: Float, radiusBR: Float) {
        mRoundDrawable.cornerRadii = floatArrayOf(radiusTL, radiusTR, radiusBL, radiusBR)
        RoundViewHelper.setBackground(this, mRoundDrawable)
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        mRoundDrawable.setBgData(ColorStateList.valueOf(color))
        RoundViewHelper.setBackground(this, mRoundDrawable)
    }

    fun update() {
        RoundViewHelper.setBackground(this@RoundTextView, mRoundDrawable)
        textWatch {

        }
    }
}