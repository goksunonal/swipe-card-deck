package com.profile.lib.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.material.card.MaterialCardView
import com.profile.lib.CardSwipeListener

class SwipeMaterialCardView : MaterialCardView {
    private var lastX = 0
    private var lastY = 0
    private var defaultLeft: Float = 0f
    private var defaultRight: Float = 0f
    private var defaultTop: Float = 0f
    private var defaultBottom: Float = 0f
    private var cardSwipeListener: CardSwipeListener? = null
    var isAnimationOn = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setCardSwipeListener(listener: CardSwipeListener) {
        this.cardSwipeListener = listener
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isAnimationOn) return true
        event?.let {
            val action = it.action
            val x = it.x.toInt()
            val y = it.y.toInt()
            if (defaultLeft == 0f) {
                defaultLeft = left.toFloat()
                defaultBottom = bottom.toFloat()
                defaultRight = right.toFloat()
                defaultTop = top.toFloat()
            }
            when (action) {
                MotionEvent.ACTION_MOVE -> {
                    val offsetX: Int = x - lastX
                    val offsetY: Int = y - lastY
                    layout(left + offsetX, top + offsetY, right + offsetX, bottom + offsetY)
                }
                MotionEvent.ACTION_DOWN -> {
                    lastX = x
                    lastY = y
                    //layout(defaultLeft, defaultTop, defaultRight, defaultBottom)
                }
                MotionEvent.ACTION_UP -> {
                    if (defaultLeft > (left + (width / 4))) {
                        cardSwipeListener?.onLeftOutside()
                    } else if (defaultRight < (right - (width / 4))) {
                        cardSwipeListener?.onRightOutside()
                    } else {
                        resetLayoutWithAnimation()
                    }
                }
                else -> {}
            }
            performClick()
        }
        return true
    }

    private fun resetLayoutWithAnimation() {
        animate()
            .x(defaultLeft)
            .y(defaultTop)
            .setDuration(500L)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    isAnimationOn = true
                }

                override fun onAnimationEnd(p0: Animator?) {
                    isAnimationOn = false
                }
                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationRepeat(p0: Animator?) {}
            })
            .start()
    }
}