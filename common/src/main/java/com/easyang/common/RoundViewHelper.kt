package com.easyang.common

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

/**
 * @author SC16004984
 * @date 2019/7/30 0030.
 */
object RoundViewHelper {
    fun fromAttributeSet(context: Context, attrs: AttributeSet?, defStyleAttr: IntArray?): RoundDrawable {
        val typedArray = context.obtainStyledAttributes(attrs,defStyleAttr)
        val colorBg = typedArray.getColorStateList(R.styleable.RoundView_rv_backgroundColor)
        val colorBorder = typedArray.getColorStateList(R.styleable.RoundView_rv_borderColor)
        val borderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_borderWidth, 0)
        var isRadiusAdjustBounds = typedArray.getBoolean(R.styleable.RoundView_rv_isRadiusAdjustHeight, false)
        val mRadius = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_radius, 0)
        val mRadiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_radiusTopLeft, 0)
        val mRadiusTopRight = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_radiusTopRight, 0)
        val mRadiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_radiusBottomLeft, 0)
        val mRadiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.RoundView_rv_radiusBottomRight, 0)
        typedArray.recycle()

        val bg = RoundDrawable()
        bg.setBgData(colorBg)
        bg.setStrokeData(borderWidth, colorBorder)
        if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
            val radii = floatArrayOf(
                mRadiusTopLeft.toFloat(),
                mRadiusTopLeft.toFloat(),
                mRadiusTopRight.toFloat(),
                mRadiusTopRight.toFloat(),
                mRadiusBottomRight.toFloat(),
                mRadiusBottomRight.toFloat(),
                mRadiusBottomLeft.toFloat(),
                mRadiusBottomLeft.toFloat()
            )
            bg.cornerRadii = radii
            isRadiusAdjustBounds = false
        } else {
            bg.cornerRadius = mRadius.toFloat()
            if (mRadius > 0) {
                isRadiusAdjustBounds = false
            }
        }
        bg.setIsRadiusAdjustBounds(isRadiusAdjustBounds)
        return bg
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun setBackground(view: View, drawable: Drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = drawable
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }

    fun setBackgroundKeepingPadding(view: View, drawable: Drawable) {
        val padding = intArrayOf(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)
        setBackground(view, drawable)
        view.setPadding(padding[0], padding[1], padding[2], padding[3])
    }

}

class RoundDrawable : GradientDrawable() {

    /**
     * 圆角大小是否自适应为 View 的高度的一般
     */
    private var mRadiusAdjustBounds = true
    private var mFillColors: ColorStateList? = null
    private var mStrokeWidth = 0
    private var mStrokeColors: ColorStateList? = null

    /**
     * 设置按钮的背景色(只支持纯色,不支持 Bitmap 或 Drawable)
     */
    fun setBgData(@Nullable colors: ColorStateList?) {
        if (hasNativeStateListAPI()) {
            super.setColor(colors)
        } else {
            mFillColors = colors
            val currentColor: Int
            if (colors == null) {
                currentColor = Color.TRANSPARENT
            } else {
                currentColor = colors.getColorForState(state, 0)
            }
            setColor(currentColor)
        }
    }

    /**
     * 设置按钮的描边粗细和颜色
     */
    fun setStrokeData(width: Int, @Nullable colors: ColorStateList?) {
        if (hasNativeStateListAPI()) {
            super.setStroke(width, colors)
        } else {
            mStrokeWidth = width
            mStrokeColors = colors
            val currentColor: Int
            if (colors == null) {
                currentColor = Color.TRANSPARENT
            } else {
                currentColor = colors.getColorForState(state, 0)
            }
            setStroke(width, currentColor)
        }
    }

    private fun hasNativeStateListAPI(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * 设置圆角大小是否自动适应为 View 的高度的一半
     */
    fun setIsRadiusAdjustBounds(isRadiusAdjustBounds: Boolean) {
        mRadiusAdjustBounds = isRadiusAdjustBounds
    }

    override fun onStateChange(stateSet: IntArray): Boolean {
        var superRet = super.onStateChange(stateSet)
        if (mFillColors != null) {
            val color = mFillColors!!.getColorForState(stateSet, 0)
            setColor(color)
            superRet = true
        }
        if (mStrokeColors != null) {
            val color = mStrokeColors!!.getColorForState(stateSet, 0)
            setStroke(mStrokeWidth, color)
            superRet = true
        }
        return superRet
    }

    override fun isStateful(): Boolean {
        return (mFillColors != null && mFillColors!!.isStateful
                || mStrokeColors != null && mStrokeColors!!.isStateful
                || super.isStateful())
    }

    override fun onBoundsChange(r: Rect) {
        super.onBoundsChange(r)
        if (mRadiusAdjustBounds) {
            // 修改圆角为短边的一半
            cornerRadius = (Math.min(r.width(), r.height()) / 2).toFloat()
        }
    }


}
